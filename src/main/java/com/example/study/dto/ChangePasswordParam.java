package com.example.study.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ChangePasswordParam {
    @NotNull(message = "用户名不能为空")
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotNull(message = "旧密码不能为空")
    @NotEmpty(message = "旧密码不能为空")
    private String oldPassword;
    @NotNull(message = "新密码不能为空")
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;
}
