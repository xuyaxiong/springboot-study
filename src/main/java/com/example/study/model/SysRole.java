package com.example.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SysRole {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String nameZh;

    private Date createdAt;
    private Date updatedAt;
    @JsonIgnore
    private Date deletedAt;

    public SysRole(String name, String nameZh) {
        this.name = name;
        this.nameZh = nameZh;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
