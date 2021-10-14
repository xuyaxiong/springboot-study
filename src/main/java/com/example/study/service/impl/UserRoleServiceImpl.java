package com.example.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.SysUserRoleMapper;
import com.example.study.model.SysUserRole;
import com.example.study.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements UserRoleService {
}
