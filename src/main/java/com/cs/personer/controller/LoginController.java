package com.cs.personer.controller;

import com.cs.personer.config.dblog.LogMarket;
import com.cs.personer.event.UserInfoEvent;
import com.cs.personer.model.UserInfo;
import com.cs.personer.model.response.ResultEntity;
import com.cs.personer.service.LoginService;
import com.cs.personer.utils.HttpHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    ApplicationContext applicationContext;

    @GetMapping
    public ResultEntity login(@RequestParam String username,
                              @RequestParam String password,
                              HttpServletRequest request) {
        log.info(HttpHelper.getIpAddr(request));
        log.info(LogMarket.DB, "AUTO_LOG测试");
        return ResultEntity.success(loginService.login(username, password));
    }

    @GetMapping("/testEvent")
    public ResultEntity testEvent() {
        UserInfo userInfo = new UserInfo(1, "张三", "123456789", 12, "优秀的人");
        applicationContext.publishEvent(new UserInfoEvent(this, userInfo));
        return ResultEntity.success("事件发布成功!");
    }
}
