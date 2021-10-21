package com.example.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 登录日志
 */
@Data
@NoArgsConstructor
public class SysLoginLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String ip;
    private String address;
    private String userAgent;
    private Date createdAt;
}
