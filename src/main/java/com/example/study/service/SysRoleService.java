package com.example.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.SysRole;
import com.example.study.utils.DataWithPageInfo;

public interface SysRoleService extends IService<SysRole> {

    // 添加角色
    SysRole addRole(SysRole role);

    // 删除角色
    int deleteRoleById(Integer roleId);

    // 更新角色
    int updateRole(Integer roleId, SysRole role);

    // 查询角色列表
    DataWithPageInfo getRoleList(Long pageNum, Integer pageSize);
}
