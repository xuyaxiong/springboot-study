package com.example.study.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.dto.RegisterParam;
import com.example.study.exception.Asserts;
import com.example.study.mapper.SysLoginLogMapper;
import com.example.study.mapper.SysResourceMapper;
import com.example.study.mapper.SysRoleMapper;
import com.example.study.mapper.SysUserMapper;
import com.example.study.model.*;
import com.example.study.service.RedisService;
import com.example.study.service.SysUserService;
import com.example.study.utils.DataWithPageInfo;
import com.example.study.utils.JwtTokenUtil;
import com.example.study.utils.PageInfo;
import com.example.study.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
    private SysLoginLogMapper sysLoginLogMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.captcha}")
    private String REDIS_KEY_PREFIX_CAPTCHA;
    @Value(("${redis.key.expire.captcha}"))
    private Long CAPTCHA_EXPIRE_SECONDS;

    @Override
    public String login(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            Asserts.failure("密码不正确");
        }
        if (!userDetails.isEnabled()) {
            Asserts.failure("账号已被禁用");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        addLoginLog(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    private void addLoginLog(String username) {
        SysUser user = findUserByUsername(username);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        SysLoginLog log = new SysLoginLog();
        log.setUserId(user.getId());
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            log.setUserAgent(request.getHeader("user-agent"));
            log.setIp(RequestUtil.getRequestIp(request));
        }
        log.setCreatedAt(new Date());
        sysLoginLogMapper.insert(log);
    }

    @Override
    public boolean register(RegisterParam registerParam) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", registerParam.getUsername());
        SysUser exists = sysUserMapper.selectOne(wrapper);
        if (exists != null) Asserts.failure("该用户名已存在");
        SysUser user = new SysUser(registerParam.getUsername(), passwordEncoder.encode(registerParam.getPassword()));
        int count = sysUserMapper.addUser(user);
        return count > 0;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public DataWithPageInfo getUserList(String keyword, Long pageNum, Integer pageSize) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper
                .orderByDesc("created_at"); // 根据创建时间降序排列
        if (keyword != null && !keyword.equals("")) {
            wrapper.like("username", keyword);
        }
        Page<SysUser> page = new Page<>(pageNum, pageSize);
        sysUserMapper.selectPage(page, wrapper);
        return PageInfo.convert(page);
    }

    @Override
    public int deleteUserById(Long userId) {
        return sysUserMapper.deleteById(userId);
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
                .eq("username", username);
        return sysUserMapper.selectOne(wrapper);
    }

    @Override
    public List<SysRole> findRoleListByUserId(Long userId) {
        return sysRoleMapper.findRoleListByUserId(userId);
    }

    @Override
    public Pair<Boolean, String> changePassword(String username, String oldPassword, String newPassword) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        SysUser user = sysUserMapper.selectOne(wrapper);
        if (user == null) {
            return new Pair<>(false, "用户不存在");
        }
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return new Pair<>(false, "旧密码错误");
        }
        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            return new Pair<>(false, "新密码与旧密码相同");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(new Date());
        sysUserMapper.updateById(user);
        return new Pair<>(true, "修改成功");

    }

    @Override
    public Pair<String, CircleCaptcha> generateCaptcha() {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(116, 36, 4, 5);
        String code = captcha.getCode();
        String uuid = IdUtil.simpleUUID();
        String key = REDIS_KEY_PREFIX_CAPTCHA + uuid;
        redisService.set(key, code);
        redisService.expire(key, CAPTCHA_EXPIRE_SECONDS);
        return new Pair<>(uuid, captcha);
    }

    @Override
    public boolean verifyCaptcha(String uuid, String captcha) {
        String key = REDIS_KEY_PREFIX_CAPTCHA + uuid;
        String realAuthCode = redisService.get(key);
        if (realAuthCode == null) Asserts.failure("验证码已失效");
        redisService.remove(key);
        return captcha.equals(realAuthCode);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        List<SysResource> resources = sysResourceMapper.findResourceListByUserId(sysUser.getId());
        return new SysUserDetails(sysUser, resources);
    }
}
