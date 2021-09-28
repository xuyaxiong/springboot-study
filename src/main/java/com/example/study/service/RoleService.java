package com.example.study.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<Role> findAllRoleAndPrivileges();

    Role findRoleByName(String name);

    List<Role> findAll(Page<Role> page);
}
