package com.example.study.controller;

import com.example.study.model.SysMenu;
import com.example.study.model.SysMenuNode;
import com.example.study.service.SysMenuService;
import com.example.study.utils.AjaxResponse;
import com.example.study.utils.DataWithPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    // 添加菜单
    @PostMapping(path = "/admin/menus")
    @ResponseBody
    public AjaxResponse addMenu(@RequestBody SysMenu sysMenu) {
        sysMenu.setCreatedAt(new Date());
        boolean isSaved = sysMenuService.addMenu(sysMenu);
        if (isSaved) {
            return AjaxResponse.success("添加成功");
        } else {
            return AjaxResponse.failure(-1, "存储失败");
        }
    }

    // 删除菜单
    @DeleteMapping(path = "/admin/menus/{id}")
    @ResponseBody
    public AjaxResponse deleteMenuById(@PathVariable(name = "id") Integer id) {
        int count = sysMenuService.deleteMenuById(id);
        if (count > 0) {
            return AjaxResponse.success("删除成功");
        } else {
            return AjaxResponse.failure(-1, "删除失败");
        }
    }

    // 更新菜单
    @PutMapping(path = "/admin/menus/{id}")
    @ResponseBody
    public AjaxResponse updateMenu(@PathVariable(name = "id") Integer id, @RequestBody SysMenu sysMenu) {
        boolean isUpdated = sysMenuService.updateMenu(id, sysMenu);
        if (isUpdated) return AjaxResponse.success("更新成功");
        else return AjaxResponse.failure(-1, "更新失败");
    }

    // 分页查询菜单列表
    @GetMapping(path = "/menus")
    @ResponseBody
    public AjaxResponse getMenuList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Long pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        DataWithPageInfo data = sysMenuService.getMenuList(keyword, pageNum, pageSize);
        return AjaxResponse.success("查询成功", data);
    }

    // 查询树状结构菜单
    @GetMapping(path = "/menus/tree")
    @ResponseBody
    public AjaxResponse getMenuTree() {
        List<SysMenuNode> menus = sysMenuService.getMenuTree();
        return AjaxResponse.success("查询成功", menus);
    }
}
