package com.example.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<SysUser>, UserDetailsService {


    // 用户登录
    String login(String username, String password);

    // 用户注册
    SysUser register(SysUser user);
}
