package com.cs.personer.event;

import com.cs.personer.model.UserInfo;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserInfoEvent extends ApplicationEvent {

    //注册用户对象
    private UserInfo userInfo;

    /**
     * 重写构造函数
     *
     * @param source   发生事件的对象
     * @param userInfo 注册用户对象
     */
    public UserInfoEvent(Object source, UserInfo userInfo) {
        super(source);
        this.userInfo = userInfo;
    }

}
