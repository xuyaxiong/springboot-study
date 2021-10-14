package com.example.study.controller;

import com.example.study.model.SysResource;
import com.example.study.service.ResourceService;
import com.example.study.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @GetMapping(path = "/resources")
    @ResponseBody
    AjaxResponse getResourceList(
            @RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return AjaxResponse.success("查询成功", resourceService.getResourceList(pageNum, pageSize));
    }

    @PostMapping(path = "/resources")
    @ResponseBody
    AjaxResponse addResource(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "url") String url
    ) {
        SysResource resource = new SysResource(name, url);
        return AjaxResponse.success("添加成功", resourceService.addResource(resource));
    }
}
