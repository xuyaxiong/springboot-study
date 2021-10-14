package com.example.study.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SysUser {
    private Long id;
    private String username; // 账号
    @JsonIgnore
    private String password; // 密码
    private String email;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
}
