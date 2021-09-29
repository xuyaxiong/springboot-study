package com.example.study.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.study.model.Role;
import com.example.study.model.User;
import com.example.study.model.UserRole;
import com.example.study.service.RoleService;
import com.example.study.service.UserRoleService;
import com.example.study.service.UserService;
import com.example.study.utils.AjaxResponse;
import com.example.study.utils.DataWithPageInfo;
import com.example.study.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/admin/users")
    public @ResponseBody
    AjaxResponse addUser(@RequestParam String username, @RequestParam String password, @RequestParam String roleName) {
        String cryptPassword = passwordEncoder.encode(password);
        User user = new User(username, cryptPassword);
        userService.addUser(user);
        Role role = roleService.findRoleByName(roleName);
        userRoleService.save(new UserRole(user.getId(), role.getId()));
        return AjaxResponse.success("添加成功");
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @GetMapping(path = "/users")
    public @ResponseBody
    AjaxResponse findAllUsers(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "test", required = false) String test,
            @RequestParam(value = "page", defaultValue = "1") Long pageNum,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
    ) {
        var page = new Page<User>(pageNum, pageSize);
        var users = userService.findByName(page, username, test);
        var pageInfo = new PageInfo(pageNum, pageSize, page.getPages(), page.getTotal());
        var data = new DataWithPageInfo(users, pageInfo);
        return AjaxResponse.success("查询成功", data);
    }

    @GetMapping(path = "/users/{id}")
    public @ResponseBody
    AjaxResponse findUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return AjaxResponse.success("查询成功", user);
    }

    @DeleteMapping(path = "/admin/users/{id}")
    public @ResponseBody
    AjaxResponse deleteUserById(@PathVariable Long id) {
        userService.removeById(id);
        return AjaxResponse.success("删除成功");
    }
}