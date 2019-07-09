package com.cs.personer;

import com.cs.personer.config.MailService;
import com.cs.personer.config.RedisService;
import com.cs.personer.model.Student;
import com.cs.personer.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonerApplicationTests {

    @Autowired
    private StudentService studentService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MailService mailService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testLocalDate() {
        Student student = studentService.getById(1);
        System.out.println(student);

    }
}
