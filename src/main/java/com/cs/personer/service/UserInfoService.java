package com.cs.personer.service;

import com.cs.personer.dao.UserInfoMapper;
import com.cs.personer.exception.ValidateException;
import com.cs.personer.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo getByUsernameAndPassword(String username, String password) {
        return Optional.ofNullable(userInfoMapper.getByUsernameAndPassword(username, password))
                .orElseThrow(() -> new ValidateException("用户名或密码不正确"));
    }
}
