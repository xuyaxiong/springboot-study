package com.example.study.controller;

import com.example.study.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;
}
