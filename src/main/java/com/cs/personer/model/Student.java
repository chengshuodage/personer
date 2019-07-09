package com.cs.personer.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Student {
    private Integer id;
    private String name;
//    private LocalDateTime cTime;
    private Date cTime;
    private Date expireTime;

}