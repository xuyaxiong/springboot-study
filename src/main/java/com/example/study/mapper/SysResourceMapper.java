package com.example.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.study.model.SysResource;

import java.util.List;

public interface SysResourceMapper extends BaseMapper<SysResource> {
    List<SysResource> getSysResourceListByUserId(Long userId);
}
