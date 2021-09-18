package com.example.study.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.UserMapper;
import com.example.study.model.User;
import com.example.study.service.UserService;
import com.example.study.utils.DataWithPageInfo;
import com.example.study.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserService userService;

    @Override
    public DataWithPageInfo getUsersWithPageInfo(Long pageNum, Integer pageSize) {
        Page<User> userPage = new Page<>(pageNum, pageSize);
        Page<User> page = userService.page(userPage, null);

        PageInfo pageInfo = new PageInfo(pageNum, pageSize, page.getPages(), page.getTotal());
        return new DataWithPageInfo(page.getRecords(), pageInfo);
    }
}
