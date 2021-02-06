package cn.ma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import cn.ma.bean.Result;
import cn.ma.bean.User;
import cn.ma.mapper.UserMapper;

@Service

public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public Result register(String username, String passwd) {
        Result result = new Result();
        result.setDetail(null);
        result.setSuccess(false);
        try {
            User knownUser = userMapper.findUser(username);
            if (knownUser != null) {
                result.setMsg("注册失败！");
                result.setDetail("原因：已存在该用户！");
            } else {
                User user = new User();
                user.setUsername(username);
                user.setPasswd(passwd);
                userMapper.register(user);
                user.setId(userMapper.findID(username));
                result.setMsg("注册成功！");
                result.setDetail(
                        "id:" + user.getId() + ",username:" + user.getUsername() + ",passwd:" + user.getPasswd());
                result.setSuccess(true);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 解决抓异常不回滚现象
            result.setMsg("注册失败！");
            result.setDetail("原因：" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Long userid = userMapper.login(user);
            if (userid != null) {
                user.setId(userid);
                result.setSuccess(true);
                result.setMsg("登录成功！");
                result.setDetail("id:" + userid + ",username:" + user.getUsername() + ",passwd:" + user.getPasswd());
            }
        } catch (Exception e) {
            result.setMsg("登录失败！");
            result.setDetail("原因：" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}