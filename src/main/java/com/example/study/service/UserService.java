package com.example.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.User;
import com.example.study.utils.DataWithPageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends IService<User>, UserDetailsService {
    DataWithPageInfo getUsersWithPageInfo(Long currentPage, Integer pageSize);

    List<User> findAllUserAndRoles();

    User findUserById(Long id);
}
