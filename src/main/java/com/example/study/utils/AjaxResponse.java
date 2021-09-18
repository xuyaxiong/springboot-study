package com.example.study.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API通用返回
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResponse {
    private int code;
    private String msg;
    private Object data;
}
