package com.cs.personer.controller;

import com.cs.personer.model.Student;
import com.cs.personer.model.response.ResultEntity;
import com.cs.personer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/test", produces = "application/json;charset=UTF-8")
public class TestController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResultEntity say() {
        List<Student> list = studentService.getList();
        System.out.println(list);
        System.out.println("输出");
        return ResultEntity.success(list);
    }
}
