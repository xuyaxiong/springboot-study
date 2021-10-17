package com.example.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class SysRoleMenu {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private final Integer roleId;
    private final Integer menuId;

    public SysRoleMenu(Integer roleId, Integer menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
