package com.example.study.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.study.model.Role;
import com.example.study.service.RoleService;
import com.example.study.utils.AjaxResponse;
import com.example.study.utils.DataWithPageInfo;
import com.example.study.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping(path = "/roles")
    public @ResponseBody
    AjaxResponse findAllRoles(
            @RequestParam(value = "page", defaultValue = "1") Long pageNum,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
    ) {
        var page = new Page<Role>(pageNum, pageSize);
        var roles = roleService.findAll(page);
        var pageInfo = new PageInfo(pageNum, pageSize, page.getPages(), page.getTotal());
        var data = new DataWithPageInfo(roles, pageInfo);
        return AjaxResponse.success("查询成功", data);
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
