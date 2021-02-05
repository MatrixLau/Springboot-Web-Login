package cn.ma.services;

import org.springframework.stereotype.Service;

import cn.ma.bean.Result;
import cn.ma.bean.User;

@Service
public class UserService {

    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            if (user.getUsername().equals("root") & user.getPasswd().equals("root")) {
                result.setMsg("登录成功！");
                result.setDetail("用户名：" + user.getUsername() + "密码：" + user.getPasswd());
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}