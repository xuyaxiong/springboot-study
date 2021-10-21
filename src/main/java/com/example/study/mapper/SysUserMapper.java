package com.example.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.study.model.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser> {

    int addUser(SysUser user);

    void deleteUserById(Long userId);
}
