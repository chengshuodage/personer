package com.cs.personer.event;

import com.cs.personer.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Order控制执行顺序(作用在@Async方法失效,同步方法有效)
 */
@Slf4j
@Component
public class AnnotationRegisterListener {

    @Order(4)
    @EventListener
    public void method4(UserInfoEvent userRegisterEvent) {
        UserInfo userInfo = userRegisterEvent.getUserInfo();
        log.info("method4");
    }

    @Order(5)
    @EventListener
    public void method5(UserInfoEvent userRegisterEvent) {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserInfo userInfo = userRegisterEvent.getUserInfo();
        log.info("method5");
    }

    @EventListener
    @Async
    public void method1(UserInfoEvent userRegisterEvent) {
        try {
            Thread.sleep(6000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserInfo userInfo = userRegisterEvent.getUserInfo();
        log.info("method1");
    }

    @Order(2)
    @EventListener
    public void method2(UserInfoEvent userRegisterEvent) {
        UserInfo userInfo = userRegisterEvent.getUserInfo();
        log.info("method2");
    }

    @Order(3)
    @EventListener
    public void method3(UserInfoEvent userRegisterEvent) {
        UserInfo userInfo = userRegisterEvent.getUserInfo();
        log.info("method3");
    }


}
