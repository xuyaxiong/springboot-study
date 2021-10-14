package com.example.study.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API通用返回
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AjaxResponse {
    private int code;
    private String msg;
    private Object data;

    public static AjaxResponse success(String msg, Object data) {
        AjaxResponse res = new AjaxResponse();
        res.code = 200;
        res.msg = msg;
        res.data = data;
        return res;
    }

    public static AjaxResponse success(String msg) {
        return AjaxResponse.success(msg, null);
    }

    public static AjaxResponse failure(int code, String msg) {
        AjaxResponse res = new AjaxResponse();
        res.code = code;
        res.msg = msg;
        res.data = null;
        return res;
    }
}
