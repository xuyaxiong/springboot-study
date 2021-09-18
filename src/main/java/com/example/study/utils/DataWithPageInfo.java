package com.example.study.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页列表数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataWithPageInfo {
    private List<?> items;
    private PageInfo pageInfo;
}
