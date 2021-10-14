package com.example.study.controller;

import com.example.study.model.SysUser;
import com.example.study.service.RoleService;
import com.example.study.service.UserRoleService;
import com.example.study.service.UserService;
import com.example.study.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 用户登录
    @PostMapping(path = "/login")
    public @ResponseBody
    AjaxResponse login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        String token = userService.login(username, password);
        if (token == null) {
            return AjaxResponse.failure(-1, "用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return AjaxResponse.success("登录成功", tokenMap);
    }

    // 用户注册
    @PostMapping(path = "/register")
    public @ResponseBody
    AjaxResponse register(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        SysUser user = new SysUser(username, password);
        user = userService.register(user);
        if (user != null)
            return AjaxResponse.success("注册成功", user);
        else return AjaxResponse.failure(-1, "注册失败");
    }
}