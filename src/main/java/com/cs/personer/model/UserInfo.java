package com.cs.personer.model;

import lombok.Data;

@Data
public class UserInfo {
    /**
     * 用户流水号
     */
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 
     */
    private String remark;

    public UserInfo(Integer id, String username, String password, Integer age, String remark) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.remark = remark;
    }

    public UserInfo() {
        super();
    }

}