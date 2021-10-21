package com.example.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRole {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Long userId;
    private Integer roleId;

    public SysUserRole(Long userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
