package com.example.study.controller;

import com.example.study.model.SysMenu;
import com.example.study.model.SysResource;
import com.example.study.model.SysRole;
import com.example.study.service.SysRoleService;
import com.example.study.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping(path = "/admin/roles")
    @ResponseBody
    public AjaxResponse addRole(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "nameZh") String nameZh
    ) {
        SysRole role = new SysRole(name, nameZh);
        role = sysRoleService.addRole(role);
        if (role != null) {
            return AjaxResponse.success("添加成功", role);
        } else {
            return AjaxResponse.failure(-1, "添加失败");
        }
    }

    @DeleteMapping(path = "/admin/roles/{id}")
    @ResponseBody
    public AjaxResponse deleteRoleById(@PathVariable(name = "id") Integer roleId) {
        int count = sysRoleService.deleteRoleById(roleId);
        if (count > 0) {
            return AjaxResponse.success("删除成功");
        } else {
            return AjaxResponse.failure(-1, "删除失败");
        }
    }

    @PutMapping(path = "/admin/roles/{id}")
    @ResponseBody
    public AjaxResponse updateRole(@PathVariable Integer id, @RequestBody SysRole role) {
        int count = sysRoleService.updateRole(id, role);
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
        return AjaxResponse.success("查询成功", sysRoleService.getRoleList(pageNum, pageSize));
    }

    // 给角色分配菜单
    @PostMapping(path = "/admin/roles/allocMenus")
    @ResponseBody
    public AjaxResponse allocMenus(
            @RequestParam(name = "roleId") Integer roleId,
            @RequestParam(name = "menuIds") List<Integer> menuIds
    ) {
        int count = sysRoleService.allocMenus(roleId, menuIds);
        return AjaxResponse.success("分配成功");
    }

    // 给角色分配资源
    @PostMapping(path = "/admin/roles/allocResources")
    @ResponseBody
    public AjaxResponse allocResources(
            @RequestParam(name = "roleId") Integer roleId,
            @RequestParam(name = "resourceIds") List<Integer> resourceIds
    ) {
        int count = sysRoleService.allocResources(roleId, resourceIds);
        return AjaxResponse.success("分配成功");
    }

    // 根据角色ID查询菜单
    @GetMapping(path = "/roles/{roleId}/menus")
    @ResponseBody
    public AjaxResponse findMenuListByRoleId(@PathVariable(name = "roleId") Integer roleId) {
        List<SysMenu> menus = sysRoleService.findMenuListByRoleId(roleId);
        return AjaxResponse.success("查询成功", menus);
    }

    // 根据角色ID查询资源
    @GetMapping(path = "/roles/{roleId}/resources")
    @ResponseBody
    public AjaxResponse findResourceListByRoleId(@PathVariable(name = "roleId") Integer roleId) {
        List<SysResource> resources = sysRoleService.findResourceListByRoleId(roleId);
        return AjaxResponse.success("查询成功", resources);
    }
}
