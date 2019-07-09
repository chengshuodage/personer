package com.cs.personer.dao;

import com.cs.personer.model.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Jack
 */
@Repository
public interface StudentMapper {

    Student getById(@Param("id") Integer id);

}