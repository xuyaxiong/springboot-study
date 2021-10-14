package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleResource {
    private Integer id;
    private Integer roleId;
    private Integer privilegeId;
}
