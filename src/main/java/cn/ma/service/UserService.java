package cn.ma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ma.bean.Result;
import cn.ma.bean.User;
import cn.ma.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

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