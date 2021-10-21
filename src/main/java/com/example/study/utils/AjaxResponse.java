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
        return new AjaxResponse(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static AjaxResponse success(String msg) {
        return success(msg, null);
    }

    public static AjaxResponse success(Object data) {
        return new AjaxResponse(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static AjaxResponse failure(int code, String msg) {
        return new AjaxResponse(code, msg, null);
    }

    public static AjaxResponse failure(String msg) {
        return failure(ResultCode.FAILED.getCode(), msg);
    }

    public static AjaxResponse failure(ResultCode resultCode) {
        return failure(resultCode.getCode(), resultCode.getMsg());
    }

    /**
     * 参数校验失败
     */
    public static AjaxResponse validateFailed(String msg) {
        return failure(ResultCode.VALIDATE_FAILED.getCode(), msg);
    }

    /**
     * 未登录
     */
    public static AjaxResponse unauthorized() {
        return failure(ResultCode.UNAUTHORIZED);
    }

    /**
     * 未授权
     */
    public static AjaxResponse forbidden() {
        return failure(ResultCode.FORBIDDEN);
    }
}
