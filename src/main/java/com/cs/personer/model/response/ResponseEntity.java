package com.cs.personer.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jack
 */
@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class ResponseEntity {
    private String message;
    private Object data;
    private Boolean success;

    private ResponseEntity(Object data, Boolean success) {
        this.data = data;
        this.success = success;
    }

    private ResponseEntity(String message, Boolean success) {
        this.success = success;
        this.message = message;
    }

    private ResponseEntity(Boolean success) {
        this.success = success;
    }


    public static ResponseEntity failure() {
        return new ResponseEntity(false);
    }

    public static ResponseEntity success(Object object) {
        return new ResponseEntity(object, true);
    }

    public static ResponseEntity failure(String message) {
        return new ResponseEntity(message, false);
    }

}
