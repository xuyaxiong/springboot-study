package com.example.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.SysRoleMapper;
import com.example.study.model.SysRole;
import com.example.study.service.RoleService;
import com.example.study.utils.DataWithPageInfo;
import com.example.study.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

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
}
