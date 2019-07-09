package com.cs.personer.service;

import com.cs.personer.dao.StudentMapper;
import com.cs.personer.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jack
 */
@Service
public class StudentService {

    @Autowired
    public StudentMapper studentMapper;

    public Student getById(Integer id) {
//        PageHelper.startPage(1, 1);
        return studentMapper.getById(id);
    }
}
