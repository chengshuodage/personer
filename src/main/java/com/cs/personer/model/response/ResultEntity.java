package com.cs.personer.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jack
 */
@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class ResultEntity {
    private String message;
    private Object data;
    private Boolean success;

    private ResultEntity(Object data, Boolean success) {
        this.data = data;
        this.success = success;
    }

    private ResultEntity(String message, Boolean success) {
        this.success = success;
        this.message = message;
    }

    private ResultEntity(Boolean success) {
        this.success = success;
    }


    public static ResultEntity failure() {
        return new ResultEntity(false);
    }

    public static ResultEntity success(Object object) {
        return new ResultEntity(object, true);
    }

    public static ResultEntity failure(String message) {
        return new ResultEntity(message, false);
    }

}
