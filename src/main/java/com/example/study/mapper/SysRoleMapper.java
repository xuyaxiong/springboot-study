package com.example.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.study.model.SysRole;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    int deleteRoleById(Integer roleId);

    List<SysRole> findRoleListByUserId(Long userId);
}
