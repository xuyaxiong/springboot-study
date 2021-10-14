package com.example.study.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 分页信息
 */
@Data
@AllArgsConstructor
public class PageInfo {
    private Long pageNum;
    private Long pageSize;
    private Long pages;
    private Long total;

    public static DataWithPageInfo convert(Page page) {
        return new DataWithPageInfo(
                page.getRecords(),
                new PageInfo(
                        page.getCurrent(),
                        page.getSize(),
                        page.getPages(),
                        page.getTotal()
                )
        );
    }
}
