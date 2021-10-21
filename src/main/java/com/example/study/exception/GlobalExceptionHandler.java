package com.example.study.exception;

import com.example.study.utils.AjaxResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public AjaxResponse handleApiException(ApiException e) {
        return AjaxResponse.failure(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String msg = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                msg = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            }
        }
        return AjaxResponse.validateFailed(msg);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public AjaxResponse handleBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String msg = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                msg = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            }
        }
        return AjaxResponse.validateFailed(msg);
    }
}
