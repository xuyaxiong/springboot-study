package com.example.study.service;

import com.example.study.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void deleteById(int id);

    User findById(int id);

    List<User> findAll();

}
