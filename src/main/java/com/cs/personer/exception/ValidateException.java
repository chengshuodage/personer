package com.cs.personer.exception;

/**
 * 校验异常
 *
 * @author Jack
 */
public class ValidateException extends RuntimeException {
    public ValidateException(String message) {
        super(message);
    }
}
