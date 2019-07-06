package com.cs.personer.service;

import com.cs.personer.dao.StudentMapper;
import com.cs.personer.model.Student;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getList() {
        PageHelper.startPage(1, 1);
        List<Student> list = studentMapper.getList();
        return list;
    }
}
