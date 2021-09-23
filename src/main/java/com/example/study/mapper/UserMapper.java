package com.example.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.study.model.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> findAll();

    void insertUser(User user);

    List<User> selectAllUserAndRoles();

    User selectUserById(Long id);

    User selectUserByUsername(String username);
}
