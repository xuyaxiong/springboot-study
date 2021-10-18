package com.example.study.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class SysResource {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String url;

    private Date createdAt;
    private Date updatedAt;
    @JsonIgnore
    private Date deletedAt;

    public SysResource(String name, String url) {
        this.name = name;
        this.url = url;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
