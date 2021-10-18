package com.example.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.SysResourceMapper;
import com.example.study.model.SysResource;
import com.example.study.service.SysResourceService;
import com.example.study.utils.DataWithPageInfo;
import com.example.study.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    public SysResource addResource(SysResource resource) {
        QueryWrapper<SysResource> wrapper = new QueryWrapper<>();
        wrapper
                .eq("name", resource.getName());
        SysResource exists = sysResourceMapper.selectOne(wrapper);
        if (exists == null) {
            sysResourceMapper.insert(resource);
            return resource;
        } else return null;
    }

    @Override
    public int deleteResourceById(Integer resourceId) {
        return sysResourceMapper.deleteResourceById(resourceId);
    }

    @Override
    public int updateResource(Integer resourceId, SysResource resource) {
        resource.setId(resourceId);
        resource.setUpdatedAt(new Date());
        return sysResourceMapper.updateById(resource);
    }

    @Override
    public DataWithPageInfo getResourceList(Long pageNum, Integer pageSize) {
        QueryWrapper<SysResource> wrapper = new QueryWrapper<>();
        wrapper
                .orderByDesc("created_at"); // 根据创建时间降序排列
        Page<SysResource> page = new Page<>(pageNum, pageSize);
        sysResourceMapper.selectPage(page, wrapper);
        return PageInfo.convert(page);
    }
}
