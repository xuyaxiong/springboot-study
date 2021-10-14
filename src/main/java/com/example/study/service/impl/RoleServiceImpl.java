package com.example.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.SysRoleMapper;
import com.example.study.model.SysRole;
import com.example.study.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements RoleService {

}
