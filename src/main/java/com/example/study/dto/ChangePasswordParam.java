package com.example.study.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class ChangePasswordParam {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    @Size(min = 8, message = "新密码长度不能少于8个字符")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-zA-Z]+$", message = "新密码必须同时含有数字和字母")
    private String newPassword;
}
