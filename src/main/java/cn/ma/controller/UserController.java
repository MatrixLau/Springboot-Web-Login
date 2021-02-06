package cn.ma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ma.bean.Result;
import cn.ma.bean.User;
import cn.ma.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestParam String username, @RequestParam String passwd) {
        return userService.register(username, passwd);
    }

    @PostMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String passwd) {
        User user = new User();
        user.setUsername(username);
        user.setPasswd(passwd);
        return userService.login(user);
    }

}