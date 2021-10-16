package com.example.study.controller;

import com.example.study.model.SysResource;
import com.example.study.security.DynamicSecurityMetadataSource;
import com.example.study.service.SysResourceService;
import com.example.study.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SysResourceController {
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;


    @PostMapping(path = "/admin/resources")
    @ResponseBody
    AjaxResponse addResource(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "url") String url
    ) {
        SysResource resource = new SysResource(name, url);
        resource = sysResourceService.addResource(resource);
        if (resource != null) {
            dynamicSecurityMetadataSource.clearDataSource();
            return AjaxResponse.success("添加成功", resource);
        } else {
            return AjaxResponse.failure(-1, "添加失败");
        }
    }

    @DeleteMapping(path = "/admin/resources/{id}")
    @ResponseBody
    public AjaxResponse deleteResourceById(@PathVariable(name = "id") Integer resourceId) {
        int count = sysResourceService.deleteResourceById(resourceId);
        if (count > 0) {
            dynamicSecurityMetadataSource.clearDataSource();
            return AjaxResponse.success("删除成功");
        } else {
            return AjaxResponse.failure(-1, "删除失败");
        }
    }

    @PutMapping(path = "/admin/resources/{id}")
    @ResponseBody
    public AjaxResponse updateResource(@PathVariable Integer id, @RequestBody SysResource resource) {
        int count = sysResourceService.updateResource(id, resource);
        if (count > 0) {
            return AjaxResponse.success("更新成功");
        } else {
            return AjaxResponse.failure(-1, "更新失败");
        }
    }

    @GetMapping(path = "/resources")
    @ResponseBody
    AjaxResponse getResourceList(
            @RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return AjaxResponse.success("查询成功", sysResourceService.getResourceList(pageNum, pageSize));
    }
}
