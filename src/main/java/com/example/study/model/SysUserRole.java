package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRole {
    private Integer id;
    private Long userId;
    private Integer roleId;
}
