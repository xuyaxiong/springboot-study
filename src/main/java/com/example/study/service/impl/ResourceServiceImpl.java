package com.example.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.SysResourceMapper;
import com.example.study.model.SysResource;
import com.example.study.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    public List<SysResource> getResourceListByUserId(Long userId) {
        return sysResourceMapper.getSysResourceListByUserId(userId);
    }
}
