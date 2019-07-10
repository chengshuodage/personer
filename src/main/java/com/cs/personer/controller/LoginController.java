package com.cs.personer.controller;

import com.cs.personer.event.UserInfoEvent;
import com.cs.personer.model.UserInfo;
import com.cs.personer.model.response.ResponseEntity;
import com.cs.personer.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    ApplicationContext applicationContext;

    @GetMapping
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.success(loginService.login(username, password));
    }

    @GetMapping("/testEvent")
    public ResponseEntity testEvent() {
        UserInfo userInfo = new UserInfo(1, "张三", "123456789", 12, "优秀的人");
        applicationContext.publishEvent(new UserInfoEvent(this, userInfo));
        return ResponseEntity.success("事件发布成功!");
    }
}
