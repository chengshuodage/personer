package com.cs.personer.controller;

import com.alibaba.fastjson.JSON;
import com.cs.personer.anno.FreeLogin;
import com.cs.personer.config.CurrentUserInfo;
import com.cs.personer.model.UserInfo;
import com.cs.personer.model.response.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test", produces = "application/json;charset=UTF-8")
public class TestController {

    @Autowired
    private CurrentUserInfo currentUserInfo;

    @GetMapping
    @FreeLogin(false)
    public ResponseEntity testLogin() {
        UserInfo userInfo = currentUserInfo.getUserInfo();
        return ResponseEntity.success(JSON.toJSON(userInfo));
    }
}
