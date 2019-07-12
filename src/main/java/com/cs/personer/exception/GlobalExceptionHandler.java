package com.cs.personer.exception;

import com.cs.personer.model.response.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(value = UserLoginException.class)
    public ResponseEntity handler(UserLoginException e) {
        log.error(e.getMessage());
        return this.response(HttpStatus.OK, e.getMessage());
    }

    @ExceptionHandler(value = ValidateException.class)
    public ResponseEntity handler(ValidateException e) {
        log.error(e.getMessage());
        return this.response(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handler(Exception e) {
        log.error(e.getMessage());
        return this.response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    protected ResponseEntity response(final HttpStatus httpStatus, final String exMsg) {
        return ResponseEntity.status(httpStatus)
                .header("Content-Type", "application/json;charset=utf-8")
                .body(ResultEntity.failure(exMsg));
    }
}
