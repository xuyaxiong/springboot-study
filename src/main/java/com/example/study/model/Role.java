package com.example.study.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Role {
    private Integer id;
    private String name;
    private String nameZh;
    private List<Privilege> privileges;
}
