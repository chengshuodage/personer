package com.cs.personer.dao;

import com.cs.personer.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jack
 */
@Repository
public interface StudentMapper {
    List<Student> getList();

}