package com.cs.personer.controller;

import com.cs.personer.model.response.ResultEntity;
import com.cs.personer.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResultEntity login(@RequestParam String username, @RequestParam String password) {
        return ResultEntity.success(loginService.login(username, password));
    }
}
