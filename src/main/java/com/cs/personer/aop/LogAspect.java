package com.cs.personer.aop;

import com.cs.personer.config.CurrentUserInfo;
import com.cs.personer.utils.HttpHelper;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@Order(1000)
@Aspect
public class LogAspect {

    @Autowired
    private CurrentUserInfo currentUserInfo;

    @Value("${log.system.id:000000}")
    private String systemId;

    /**
     * 切所有的controller
     */
    @Pointcut("bean(*Controller)")
    public void controller() {
    }


    @Before("controller()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                        .orElseThrow(() -> new RuntimeException("获取request失败"));
        HttpServletRequest request = requestAttributes.getRequest();

        ThreadContext.put("systemId", systemId);
        ThreadContext.put("ipAddress", HttpHelper.getIpAddr(request));
        ThreadContext.put("clientAgent", request.getHeader("User-Agent"));
        ThreadContext.put("creatorId", currentUserInfo.getUserInfo() == null ? "000000" :
                currentUserInfo.getUserInfo().getId().toString());

    }

    /**
     * 执行完毕之后,清空log4j线程信息
     */
    @AfterReturning(value = "controller()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        ThreadContext.clearAll();
    }

    /**
     * 执行完毕之后,清空log4j线程信息
     */
    @AfterThrowing(value = "controller()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        ThreadContext.clearAll();
    }


}
