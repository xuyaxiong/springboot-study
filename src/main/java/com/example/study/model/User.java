package com.example.study.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Integer id;
    private String username; // 账号
    private String password; // 密码
    private String name;
    private String email;
}
