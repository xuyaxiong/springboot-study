package com.example.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.study.model.SysMenu;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    int deleteMenuById(Integer menuId);

    List<SysMenu> findMenuListByUserId(Long userId);
}
