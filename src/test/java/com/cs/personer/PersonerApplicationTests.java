package com.cs.personer;

import com.cs.personer.config.RedisService;
import com.cs.personer.dao.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonerApplicationTests {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RedisService redisService;

    @Test
    public void contextLoads() {
        try {
            redisService.set("token", UUID.randomUUID().toString(), 5L);
            System.out.println(redisService.get("token"));
            Thread.sleep(1000 * 7L);
            System.out.println(redisService.get("token"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
