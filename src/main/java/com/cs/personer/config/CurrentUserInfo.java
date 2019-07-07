package com.cs.personer.config;

import com.cs.personer.model.UserInfo;
import org.springframework.stereotype.Component;

/**
 * @author Jack
 */
@Component
public class CurrentUserInfo {
    /**
     * 线程变量,保存用户信息
     */
    private ThreadLocal<UserInfo> currentUserInfo = new ThreadLocal<>();

    public UserInfo getUserInfo() {
        return currentUserInfo.get();
    }

    public void setUserInfo(UserInfo userInfo) {
        currentUserInfo.set(userInfo);
    }

    public void remove(){
        currentUserInfo.remove();
    }

}
