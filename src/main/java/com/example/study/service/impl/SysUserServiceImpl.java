package com.example.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.SysResourceMapper;
import com.example.study.mapper.SysRoleMapper;
import com.example.study.mapper.SysUserMapper;
import com.example.study.model.SysResource;
import com.example.study.model.SysRole;
import com.example.study.model.SysUser;
import com.example.study.model.SysUserDetails;
import com.example.study.service.SysUserService;
import com.example.study.utils.DataWithPageInfo;
import com.example.study.utils.JwtTokenUtil;
import com.example.study.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new Exception("密码不正确");
            }
            if (!userDetails.isEnabled()) {
                throw new Exception("账号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public SysUser register(SysUser user) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper
                .eq("username", user.getUsername())
                .isNull("deleted_at");
        SysUser exists = sysUserMapper.selectOne(wrapper);
        if (exists == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            sysUserMapper.addUser(user);
            return user;
        } else return null;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public DataWithPageInfo getUserList(String keyword, Long pageNum, Integer pageSize) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper
                .orderByDesc("created_at") // 根据创建时间降序排列
                .isNull("deleted_at");
        if (keyword != null && !keyword.equals("")) {
            wrapper.like("username", keyword);
        }
        Page<SysUser> page = new Page<>(pageNum, pageSize);
        sysUserMapper.selectPage(page, wrapper);
        return PageInfo.convert(page);
    }

    @Override
    public void deleteUserById(Long userId) {
        sysUserMapper.deleteUserById(userId);
    }

    @Override
    public int updateUser(Long id, SysUser user) {
        user.setId(id);
        user.setUpdatedAt(new Date());
        return sysUserMapper.updateById(user);
    }

    @Override
    public SysUser findUserByUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper
                .eq("username", username)
                .isNull("deleted_at");
        return sysUserMapper.selectOne(wrapper);
    }

    @Override
    public List<SysRole> findRoleListByUserId(Long userId) {
        return sysRoleMapper.findRoleListByUserId(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper
                .eq("username", username)
                .isNull("deleted_at");
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        List<SysResource> privileges = sysResourceMapper.findResourceListByUserId(sysUser.getId());
        return new SysUserDetails(sysUser, privileges);
    }
}
