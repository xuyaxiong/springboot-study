package com.example.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class SysRoleMenu {
    private final Integer roleId;
    private final Integer menuId;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    public SysRoleMenu(Integer roleId, Integer menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
