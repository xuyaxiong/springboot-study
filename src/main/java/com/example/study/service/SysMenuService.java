package com.example.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.SysMenu;
import com.example.study.model.SysMenuNode;
import com.example.study.utils.DataWithPageInfo;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    // 添加菜单
    boolean addMenu(SysMenu sysMenu);

    // 删除菜单
    int deleteMenuById(Integer menuId);

    // 更新菜单
    boolean updateMenu(Integer menuId, SysMenu sysMenu);

    // 根据用户ID查询菜单
    List<SysMenu> findMenuListByUserId(Long userId);

    // 分页查询菜单列表
    DataWithPageInfo getMenuList(String keyword, Long pageNum, Integer pageSize);

    // 查询树形结构菜单
    List<SysMenuNode> getMenuTree();
}
