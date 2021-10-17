package com.example.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleResource {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer roleId;
    private Integer resourceId;

    public SysRoleResource(Integer roleId, Integer resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
    }
}
