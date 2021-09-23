package com.example.study.controller;

import com.example.study.service.UserService;
import com.example.study.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login")
    public @ResponseBody
    AjaxResponse login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return AjaxResponse.success("登录成功");
    }
}
