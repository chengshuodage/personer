package com.cs.personer;

import com.cs.personer.config.MailService;
import com.cs.personer.config.RedisService;
import com.cs.personer.dao.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonerApplicationTests {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MailService mailService;

    @Test
    public void contextLoads() {
        mailService.sendSimpleMail("1783461699@qq.com", "测试邮件1", "测试邮件内容");
        mailService.sendSimpleMail("1783461699@qq.com", "测试邮件2", "测试邮件内容");
        mailService.sendSimpleMail("1783461699@qq.com", "测试邮件3", "测试邮件内容");
    }
}
