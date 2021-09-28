package com.example.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.study.model.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectAllRoleAndPrivileges();

    Role findByName(String name);

    List<Role> findAll(Page page);
}
