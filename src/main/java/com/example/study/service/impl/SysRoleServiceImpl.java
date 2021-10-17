package com.example.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.SysRoleMapper;
import com.example.study.mapper.SysRoleMenuMapper;
import com.example.study.mapper.SysRoleResourceMapper;
import com.example.study.model.*;
import com.example.study.service.SysRoleService;
import com.example.study.utils.DataWithPageInfo;
import com.example.study.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public SysRole addRole(SysRole role) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper
                .eq("name", role.getName())
                .isNull("deleted_at");
        SysRole exists = sysRoleMapper.selectOne(wrapper);
        if (exists == null) {
            sysRoleMapper.insert(role);
            return role;
        } else return null;
    }

    @Override
    public int deleteRoleById(Integer roleId) {
        return sysRoleMapper.deleteRoleById(roleId);
    }

    @Override
    public int updateRole(Integer roleId, SysRole role) {
        role.setId(roleId);
        role.setUpdatedAt(new Date());
        return sysRoleMapper.updateById(role);
    }

    @Override
    public DataWithPageInfo getRoleList(Long pageNum, Integer pageSize) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper
                .orderByDesc("created_at") // 根据创建时间降序排列
                .isNull("deleted_at");
        Page<SysRole> page = new Page<>(pageNum, pageSize);
        sysRoleMapper.selectPage(page, wrapper);
        return PageInfo.convert(page);
    }

    @Override
    public int allocMenus(Integer roleId, List<Integer> menuIds) {
        //先删除原有关系
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        sysRoleMenuMapper.delete(wrapper);
        //批量插入新关系
        for (Integer menuId : menuIds) {
            SysRoleMenu roleMenu = new SysRoleMenu(roleId, menuId);
            sysRoleMenuMapper.insert(roleMenu);
        }
        return menuIds.size();
    }

    @Override
    public int allocResources(Integer roleId, List<Integer> resourceIds) {
        //先删除原有关系
        QueryWrapper<SysRoleResource> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        sysRoleResourceMapper.delete(wrapper);
        //批量插入新关系
        for (Integer menuId : resourceIds) {
            SysRoleResource roleResource = new SysRoleResource(roleId, menuId);
            sysRoleResourceMapper.insert(roleResource);
        }
        return resourceIds.size();
    }

    @Override
    public List<SysMenu> findMenuListByRoleId(Integer roleId) {
        return sysRoleMapper.findMenuListByRoleId(roleId);
    }

    @Override
    public List<SysResource> findResourceListByRoleId(Integer roleId) {
        return sysRoleMapper.findResourceListByRoleId(roleId);
    }
}
