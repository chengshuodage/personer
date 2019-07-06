package com.cs.personer.exception;

/**
 * 自定义登录异常
 */
public class UserLoginException extends RuntimeException {
    public UserLoginException(String errorMsg) {
        super(errorMsg);
    }


}
