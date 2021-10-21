package com.example.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.SysRoleMapper;
import com.example.study.mapper.SysUserRoleMapper;
import com.example.study.model.SysRole;
import com.example.study.model.SysUserRole;
import com.example.study.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public boolean allocRoles(Long userId, List<Integer> roleIds) {
        // 先删除原来的关系
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        remove(wrapper);
        if (roleIds.size() == 0) return true;
        // 建立新关系
        List<SysRole> roles = sysRoleMapper.selectBatchIds(roleIds);
        List<SysUserRole> userRoles = new ArrayList<>();
        for (SysRole role : roles) {
            SysUserRole userRole = new SysUserRole(userId, role.getId());
            userRoles.add(userRole);
        }
        return saveBatch(userRoles);
    }

}
