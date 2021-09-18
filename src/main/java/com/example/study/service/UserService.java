package com.example.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.study.model.User;
import com.example.study.utils.DataWithPageInfo;

public interface UserService extends IService<User> {
    DataWithPageInfo getUsersWithPageInfo(Long currentPage, Integer pageSize);
}
