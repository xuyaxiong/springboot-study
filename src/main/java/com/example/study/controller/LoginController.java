package com.example.study.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.study.model.User;
import com.example.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public @ResponseBody
    String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username);
        queryWrapper.lambda().eq(User::getPassword, password);
        List<User> users = userService.list(queryWrapper);
        if (users.size() == 0) {
            return "无此用户";
        } else {
            User user = users.get(0);
            return "登录成功: " + user;
        }

    }
}
