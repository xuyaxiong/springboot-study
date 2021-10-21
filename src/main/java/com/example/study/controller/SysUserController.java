package com.example.study.controller;

import cn.hutool.core.lang.Pair;
import com.example.study.dto.ChangePasswordParam;
import com.example.study.model.SysMenu;
import com.example.study.model.SysRole;
import com.example.study.model.SysUser;
import com.example.study.service.SysMenuService;
import com.example.study.service.SysRoleService;
import com.example.study.service.SysUserService;
import com.example.study.utils.AjaxResponse;
import com.example.study.utils.DataWithPageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "用户管理")
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("用户登录")
    @PostMapping(path = "/login")
    @ResponseBody
    public AjaxResponse login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        String token = sysUserService.login(username, password);
        if (token == null) {
            return AjaxResponse.failure(-1, "登录失败");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return AjaxResponse.success("登录成功", tokenMap);
    }

    @ApiOperation("用户注册")
    @PostMapping(path = "/register")
    @ResponseBody
    public AjaxResponse register(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        SysUser user = new SysUser(username, password);
        user = sysUserService.register(user);
        if (user != null)
            return AjaxResponse.success("注册成功", user);
        else return AjaxResponse.failure(-1, "注册失败");
    }

    @ApiOperation("刷新Token")
    @GetMapping(path = "/refreshToken")
    @ResponseBody
    public AjaxResponse refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = sysUserService.refreshToken(token);
        if (refreshToken == null) {
            return AjaxResponse.failure(-1, "Token已过期");
        } else {
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", refreshToken);
            tokenMap.put("tokenHead", tokenHead);
            return AjaxResponse.success("刷新成功", tokenMap);
        }
    }

    @ApiOperation("删除用户")
    @DeleteMapping(path = "/admin/users/{id}")
    @ResponseBody
    public AjaxResponse deleteUserById(
            @PathVariable(name = "id") Long userId
    ) {
        int count = sysUserService.deleteUserById(userId);
        if (count > 0)
            return AjaxResponse.success("删除成功");
        else
            return AjaxResponse.failure(-1, "删除失败");
    }

    @ApiOperation("更新用户")
    @PutMapping(path = "/admin/users/{id}")
    @ResponseBody
    public AjaxResponse updateUser(@PathVariable Long id, @RequestBody SysUser user) {
        int count = sysUserService.updateUser(id, user);
        if (count > 0) {
            return AjaxResponse.success("更新成功");
        } else {
            return AjaxResponse.failure(-1, "更新失败");
        }
    }

    @ApiOperation("查询用户列表")
    @GetMapping(path = "/users")
    @ResponseBody
    public AjaxResponse getUserList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Long pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        DataWithPageInfo result = sysUserService.getUserList(keyword, pageNum, pageSize);
        return AjaxResponse.success("查询成功", result);
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping(path = "/users/info")
    @ResponseBody
    public AjaxResponse getUserInfo(Principal principal) {
        if (principal == null) {
            return AjaxResponse.failure(-1, "查询失败");
        }
        SysUser user = sysUserService.findUserByUsername(principal.getName());
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("email", user.getEmail());
        List<SysRole> roles = sysUserService.findRoleListByUserId(user.getId());
        data.put("roles", roles);
        List<SysMenu> menus = sysMenuService.findMenuListByUserId(user.getId());
        data.put("menus", menus);
        return AjaxResponse.success("查询成功", data);
    }

    @ApiOperation("修改密码")
    @PostMapping(path = "/users/changePassword")
    @ResponseBody
    public AjaxResponse changePassword(@Validated @RequestBody ChangePasswordParam param) {
        Pair<Boolean, String> res = sysUserService.changePassword(
                param.getUsername(),
                param.getOldPassword(),
                param.getNewPassword()
        );
        if (res.getKey()) {
            return AjaxResponse.success(res.getValue());
        } else {
            return AjaxResponse.failure(-1, res.getValue());
        }
    }
}