package com.example.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SysUser {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String username; // 账号
    @JsonIgnore
    private String password; // 密码
    private String email;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private Date createdAt;
    private Date updatedAt;
    @JsonIgnore
    private Date deletedAt;

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
