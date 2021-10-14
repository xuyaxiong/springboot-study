package com.example.study.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于配置白名单资源路径
 */
public class IgnoreUrlsConfig {
    private List<String> urls = new ArrayList<>();

    public IgnoreUrlsConfig() {
        urls.add("/login");
        urls.add("/logout");
        urls.add("/register");
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
