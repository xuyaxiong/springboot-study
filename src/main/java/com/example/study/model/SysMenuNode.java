package com.example.study.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SysMenuNode extends SysMenu {
    private List<SysMenuNode> children;
}
