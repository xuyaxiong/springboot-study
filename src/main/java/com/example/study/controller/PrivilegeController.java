package com.example.study.controller;

import com.example.study.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivilegeController {
    @Autowired
    private ResourceService resourceService;
}
