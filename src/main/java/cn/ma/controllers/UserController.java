package cn.ma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ma.bean.Result;
import cn.ma.bean.User;
import cn.ma.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String passwd) {
        User user = new User();
        user.setUsername(username);
        user.setPasswd(passwd);
        return userService.login(user);
    }

}