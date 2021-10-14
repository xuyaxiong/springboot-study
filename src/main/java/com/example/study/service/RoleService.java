package com.example.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.SysRole;
import com.example.study.utils.DataWithPageInfo;

public interface RoleService extends IService<SysRole> {
    // 查询角色列表
    DataWithPageInfo getRoleList(Long pageNum, Integer pageSize);

    // 添加角色
    SysRole addRole(SysRole role);

    // 删除角色
    void deleteRoleById(Integer roleId);
}
