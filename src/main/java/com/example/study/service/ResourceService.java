package com.example.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.SysResource;

import java.util.List;

public interface ResourceService extends IService<SysResource> {
    List<SysResource> getResourceListByUserId(Long userId);
}
