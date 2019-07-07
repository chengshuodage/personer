package com.cs.personer.service;

import com.alibaba.fastjson.JSON;
import com.cs.personer.exception.ValidateException;
import com.cs.personer.model.UserInfo;
import com.cs.personer.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Jack
 */
@Service
public class LoginService {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登录
     */
    @SuppressWarnings("unchecked")
    public String login(final String username, final String password) {
        this.loginValidate(username, password);
        UserInfo userInfo = userInfoService.getByUsernameAndPassword(username, password);
        userInfo.setPassword(null);
        Map userMap = JSON.toJavaObject((JSON) JSON.toJSON(userInfo), Map.class);
        return JwtTokenUtil.createJWT(userMap, JwtTokenUtil.BASE64SECRET);
    }

    /**
     * 登录参数校验
     */
    private void loginValidate(final String username, final String password) {
        if (StringUtils.isBlank(username)) {
            throw new ValidateException("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new ValidateException("密码不能为空");
        }
    }
}
