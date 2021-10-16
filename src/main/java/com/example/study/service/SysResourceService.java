package com.example.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.SysResource;
import com.example.study.utils.DataWithPageInfo;

public interface SysResourceService extends IService<SysResource> {
    // 查询资源列表
    DataWithPageInfo getResourceList(Long pageNum, Integer pageSize);

    // 添加资源
    SysResource addResource(SysResource resource);

    // 删除资源
    int deleteResourceById(Integer resourceId);

    int updateResource(Integer resourceId, SysResource resource);
}
