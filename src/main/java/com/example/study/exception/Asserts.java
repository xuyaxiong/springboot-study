package com.example.study.exception;

public class Asserts {
    public static void failure(String msg) {
        throw new ApiException(msg);
    }
}
