package com.example.study.model;

import lombok.Data;

import java.util.Date;

@Data
public class SysResource {
    private Integer id;
    private String name;
    private String url;

    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public SysResource(String name, String url) {
        this.name = name;
        this.url = url;
        this.createdAt = new Date();
    }
}
