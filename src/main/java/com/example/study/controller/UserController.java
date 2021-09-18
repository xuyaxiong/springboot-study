package com.example.study.controller;

import com.example.study.model.User;
import com.example.study.service.UserService;
import com.example.study.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public @ResponseBody
    String addUser(@RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userService.save(user);
        return "Saved";
    }

    @GetMapping
    public @ResponseBody
    AjaxResponse findAllUsers(
            @RequestParam(value = "page", defaultValue = "1") Long pageNum,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
    ) {
        return new AjaxResponse(200, "查询成功", userService.getUsersWithPageInfo(pageNum, pageSize));
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    User findUserById(@PathVariable int id) {
        return userService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    String deleteUserById(@PathVariable int id) {
        userService.removeById(id);
        return "Deleted";
    }
}
