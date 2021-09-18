package com.example.study.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 分页信息
 */
@Data
@AllArgsConstructor
public class PageInfo {
    private Long pageNum;
    private int pageSize;
    private Long pages;
    private Long total;
}
