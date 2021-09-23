package com.example.study.controller;

import com.example.study.model.Role;
import com.example.study.service.RoleService;
import com.example.study.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping(path = "/roles")
    public @ResponseBody
    AjaxResponse findAllRoles() {
        return AjaxResponse.success("查询成功", roleService.list());
    }

    @PostMapping(path = "/admin/roles")
    public @ResponseBody
    AjaxResponse addRole(Role role) {
        roleService.save(role);
        return AjaxResponse.success("添加成功");
    }

    @DeleteMapping(path = "/admin/roles/{id}")
    public @ResponseBody
    AjaxResponse deleteRoleById(@PathVariable Long id) {
        roleService.removeById(id);
        return AjaxResponse.success("删除成功");
    }


}
