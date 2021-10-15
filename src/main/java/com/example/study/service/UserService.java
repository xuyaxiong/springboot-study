package com.example.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.SysUser;
import com.example.study.utils.DataWithPageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<SysUser>, UserDetailsService {


    // 用户登录
    String login(String username, String password);

    // 用户注册
    SysUser register(SysUser user);

    // 刷新Token
    String refreshToken(String oldToken);

    // 查询用户列表
    DataWithPageInfo getUserList(String keyword, Long pageNum, Integer pageSize);

    // 删除用户
    void deleteUserById(Long userId);
}
