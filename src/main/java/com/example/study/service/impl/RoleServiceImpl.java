package com.example.study.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.RoleMapper;
import com.example.study.model.Role;
import com.example.study.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRoleAndPrivileges() {
        return roleMapper.selectAllRoleAndPrivileges();
    }

    @Override
    public Role findRoleByName(String name) {
        return roleMapper.findByName(name);
    }

    @Override
    public List<Role> findAll(Page<Role> page) {
        return roleMapper.findAll(page);
    }
}
