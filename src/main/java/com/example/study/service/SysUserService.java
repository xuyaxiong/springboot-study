package com.example.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.SysRole;
import com.example.study.model.SysUser;
import com.example.study.utils.DataWithPageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface SysUserService extends IService<SysUser>, UserDetailsService {

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

    // 更新用户
    int updateUser(Long id, SysUser user);

    // 根据用户名查询用户
    SysUser findUserByUsername(String username);

    // 根据userId查询角色列表
    List<SysRole> findRoleListByUserId(Long userId);
}
