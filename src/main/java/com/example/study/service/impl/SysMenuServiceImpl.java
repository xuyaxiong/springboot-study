package com.example.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.study.mapper.SysMenuMapper;
import com.example.study.model.SysMenu;
import com.example.study.model.SysMenuNode;
import com.example.study.service.SysMenuService;
import com.example.study.utils.DataWithPageInfo;
import com.example.study.utils.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public DataWithPageInfo getMenuList(String keyword, Long pageNum, Integer pageSize) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper
                .orderByDesc("created_at");// 根据创建时间降序排列
        if (keyword != null && !keyword.equals("")) {
            wrapper.like("name", keyword);
        }
        Page<SysMenu> page = new Page<>(pageNum, pageSize);
        sysMenuMapper.selectPage(page, wrapper);
        return PageInfo.convert(page);
    }

    @Override
    public List<SysMenuNode> getMenuTree() {
        List<SysMenu> menuList = list();
        return menuList.stream()
                .filter(menu -> menu.getParentId().equals(0))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
    }

    private SysMenuNode covertMenuNode(SysMenu menu, List<SysMenu> menuList) {
        SysMenuNode node = new SysMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<SysMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    @Override
    public int deleteMenuById(Integer menuId) {
        return sysMenuMapper.deleteMenuById(menuId);
    }

    @Override
    public boolean addMenu(SysMenu sysMenu) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper
                .eq("name", sysMenu.getName());
        SysMenu exists = sysMenuMapper.selectOne(wrapper);
        if (exists == null) {
            return sysMenuMapper.insert(sysMenu) > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateMenu(Integer menuId, SysMenu sysMenu) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper
                .ne("id", menuId)
                .eq("name", sysMenu.getName());
        SysMenu exists = sysMenuMapper.selectOne(wrapper);
        if (exists == null) {
            sysMenu.setId(menuId);
            sysMenu.setUpdatedAt(new Date());
            return sysMenuMapper.updateById(sysMenu) > 0;
        } else {
            return false;
        }
    }

    @Override
    public List<SysMenu> findMenuListByUserId(Long userId) {
        return sysMenuMapper.findMenuListByUserId(userId);
    }
}
