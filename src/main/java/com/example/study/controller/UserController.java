package com.example.study.controller;

import com.example.study.model.User;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public @ResponseBody
    String addUser(@RequestParam String name
            , @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "Deleted";
    }
}
