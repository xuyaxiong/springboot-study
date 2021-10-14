package com.example.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.SysRoleResourceMapper;
import com.example.study.model.SysRoleResource;
import com.example.study.service.RoleResourceService;
import org.springframework.stereotype.Service;

@Service
public class RoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements RoleResourceService {
}
