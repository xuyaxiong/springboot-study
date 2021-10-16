package com.example.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SysMenu {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name; // 菜单名称
    private Integer parentId; // 父级菜单ID
    private Integer level; // 菜单级数
    private Integer sort; // 菜单排序
    private String icon; // 菜单图标
    private Boolean hidden; // 是否隐藏

    private Date createdAt;
    private Date updatedAt;
    @JsonIgnore
    private Date deletedAt;

    public SysMenu(String name, Integer parentId, Integer level, Integer sort, String icon, Boolean hidden) {
        this.name = name;
        this.parentId = parentId;
        this.level = level;
        this.sort = sort;
        this.icon = icon;
        this.hidden = hidden;
        this.createdAt = new Date();
    }
}
