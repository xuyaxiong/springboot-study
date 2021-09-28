package com.example.study.controller;

import com.example.study.model.Privilege;
import com.example.study.service.PrivilegeService;
import com.example.study.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PrivilegeController {
    @Autowired
    private PrivilegeService privilegeService;

    @GetMapping(path = "/privileges")
    public @ResponseBody
    AjaxResponse findAllPrivileges() {
        return AjaxResponse.success("查询成功", privilegeService.list());
    }

    @PostMapping(path = "/admin/privileges")
    public @ResponseBody
    AjaxResponse addPrivilege(Privilege privilege) {
        privilegeService.save(privilege);
        return AjaxResponse.success("添加成功");
    }

    @DeleteMapping(path = "/admin/privileges/{id}")
    public @ResponseBody
    AjaxResponse deletePrivilegeById(@PathVariable Long id) {
        privilegeService.removeById(id);
        return AjaxResponse.success("删除成功");
    }
}
