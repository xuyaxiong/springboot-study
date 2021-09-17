package com.example.study.controller;

import com.example.study.model.User;
import com.example.study.service.UserService;
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
        userService.add(user);
        return "Saved";
    }

    @GetMapping
    public @ResponseBody
    Iterable<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    User findUserById(@PathVariable int id) {
        return userService.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    String deleteUserById(@PathVariable int id) {
        userService.deleteById(id);
        return "Deleted";
    }
}
