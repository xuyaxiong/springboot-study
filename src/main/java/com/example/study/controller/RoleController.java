package com.example.study.controller;

import com.example.study.model.SysRole;
import com.example.study.service.RoleService;
import com.example.study.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping(path = "/roles")
    @ResponseBody
    AjaxResponse getRoleList(
            @RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return AjaxResponse.success("查询成功", roleService.getRoleList(pageNum, pageSize));
    }

    @PostMapping(path = "/roles")
    @ResponseBody
    AjaxResponse addRole(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "nameZh") String nameZh
    ) {
        SysRole role = new SysRole(name, nameZh);
        return AjaxResponse.success("添加成功", roleService.addRole(role));
    }

    @DeleteMapping(path = "/roles/{id}")
    @ResponseBody
    AjaxResponse deleteRoleById(@PathVariable(name = "id") Integer roleId) {
        roleService.deleteRoleById(roleId);
        return AjaxResponse.success("删除成功");
    }
}
