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

    @PostMapping(path = "/admin/roles")
    @ResponseBody
    public AjaxResponse addRole(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "nameZh") String nameZh
    ) {
        SysRole role = new SysRole(name, nameZh);
        role = roleService.addRole(role);
        if (role != null) {
            return AjaxResponse.success("添加成功", role);
        } else {
            return AjaxResponse.failure(-1, "添加失败");
        }
    }

    @DeleteMapping(path = "/admin/roles/{id}")
    @ResponseBody
    public AjaxResponse deleteRoleById(@PathVariable(name = "id") Integer roleId) {
        int count = roleService.deleteRoleById(roleId);
        if (count > 0) {
            return AjaxResponse.success("删除成功");
        } else {
            return AjaxResponse.failure(-1, "删除失败");
        }
    }

    @PutMapping(path = "/admin/roles/{id}")
    @ResponseBody
    public AjaxResponse updateRole(@PathVariable Integer id, @RequestBody SysRole role) {
        int count = roleService.updateRole(id, role);
        if (count > 0) {
            return AjaxResponse.success("更新成功");
        } else {
            return AjaxResponse.failure(-1, "更新失败");
        }
    }

    @GetMapping(path = "/roles")
    @ResponseBody
    public AjaxResponse getRoleList(
            @RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return AjaxResponse.success("查询成功", roleService.getRoleList(pageNum, pageSize));
    }
}
