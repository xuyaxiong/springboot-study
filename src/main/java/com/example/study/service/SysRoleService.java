package com.example.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.SysMenu;
import com.example.study.model.SysResource;
import com.example.study.model.SysRole;
import com.example.study.utils.DataWithPageInfo;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    // 添加角色
    SysRole addRole(SysRole role);

    // 删除角色
    int deleteRoleById(Integer roleId);

    // 更新角色
    int updateRole(Integer roleId, SysRole role);

    // 分页查询角色列表
    DataWithPageInfo getRoleList(Long pageNum, Integer pageSize);

    // 分配菜单
    int allocMenus(Integer roleId, List<Integer> menuIds);

    // 分配资源
    int allocResources(Integer roleId, List<Integer> resourceIds);

    // 根据角色ID查询菜单
    List<SysMenu> findMenuListByRoleId(Integer roleId);

    // 根据角色ID查询资源
    List<SysResource> findResourceListByRoleId(Integer roleId);
}
