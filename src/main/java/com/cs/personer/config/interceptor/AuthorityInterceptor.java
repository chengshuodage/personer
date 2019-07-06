package com.cs.personer.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author Jack
 */
@Slf4j
@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override//在一个请求进入Controller层方法执行前执行这个方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在这里可以对参数做一些预处理和做一些验证
        log.info("方法执行之前的拦截!");
        return true;
    }

    //返回model前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //Controller执行完毕后，返回之前，可以对request和reponse进行处理
        //如果是前后端没有分离，在进入View层中前执行
    }

    //返回model后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        //在一个请求处理完毕，即将销毁的时候，执行，可以做一些资源释放之类的工作
    }
}
