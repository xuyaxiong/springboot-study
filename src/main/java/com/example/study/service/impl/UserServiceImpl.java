package com.example.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.SysResourceMapper;
import com.example.study.mapper.SysUserMapper;
import com.example.study.model.AdminUserDetails;
import com.example.study.model.SysResource;
import com.example.study.model.SysUser;
import com.example.study.service.UserService;
import com.example.study.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;
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
        SysUser exists = sysUserMapper.findUserByUsername(user.getUsername());
        if (exists == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            sysUserMapper.addUser(user);
            return user;
        } else return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.findUserByUsername(username);
        List<SysResource> privileges = sysResourceMapper.getSysResourceListByUserId(sysUser.getId());
        AdminUserDetails userDetails = new AdminUserDetails(sysUser, privileges);
        return userDetails;
    }
}
