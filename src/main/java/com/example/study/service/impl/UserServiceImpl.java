package com.example.study.service.impl;

import com.example.study.dao.UserDao;
import com.example.study.model.User;
import com.example.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void add(User user) {
        userDao.insert(user);
    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }
}
