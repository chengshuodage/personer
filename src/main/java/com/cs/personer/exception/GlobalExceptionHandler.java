package com.cs.personer.exception;

import com.cs.personer.model.response.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author Jack
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ValidateException.class)
    public ResponseEntity handler(ValidateException e) {
        log.error(e.getMessage());
        return ResponseEntity.failure(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handler(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.failure(e.getMessage());
    }
}