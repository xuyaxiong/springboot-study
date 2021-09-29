package com.example.study.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.User;
import com.example.study.utils.DataWithPageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends IService<User>, UserDetailsService {
    DataWithPageInfo getUsersWithPageInfo(Long currentPage, Integer pageSize);

    List<User> findAllUserAndRoles(Page<User> page);

    User findUserById(Long id);

    void addUser(User user);

    // page必须放在第一位
    List<User> findByName(Page<User> page, String username, String email);
}
