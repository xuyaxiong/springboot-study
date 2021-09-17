package com.example.study.dao;

import com.example.study.model.User;

import java.util.List;

public interface UserDao {
    User findById(int id);

    void deleteById(int id);

    List<User> findAll();

    void insert(User user);
}
