package com.cs.personer.model;

import java.time.LocalDateTime;

public class Student {
    /**
     * 学生流水号
     */
    private Integer id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime cTime;

    public Student(Integer id, String name, LocalDateTime cTime) {
        this.id = id;
        this.name = name;
        this.cTime = cTime;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getcTime() {
        return cTime;
    }

    public void setcTime(LocalDateTime cTime) {
        this.cTime = cTime;
    }
}