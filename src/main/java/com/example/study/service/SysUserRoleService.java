package com.example.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.SysUserRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SysUserRoleService extends IService<SysUserRole> {

    // 给用户分配角色
    @Transactional
    boolean allocRoles(Long userId, List<Integer> roleIds);
}
