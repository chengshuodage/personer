package com.cs.personer.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class DateDemo {
    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private Date date;
    private List<String> list;
    private String str;
}
