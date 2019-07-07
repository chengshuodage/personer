package com.cs.personer.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.cs.personer.anno.FreeLogin;
import com.cs.personer.config.CurrentUserInfo;
import com.cs.personer.exception.ValidateException;
import com.cs.personer.model.UserInfo;
import com.cs.personer.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * 拦截器
 *
 * @author Jack
 */
@Slf4j
@Component
public class AuthorityInterceptor implements HandlerInterceptor {

    @Autowired
    private CurrentUserInfo currentUserInfo;

    @Override
    @SuppressWarnings("unchecked")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Method method = ((HandlerMethod) handler).getMethod();
        FreeLogin freeLogin = method.getAnnotation(FreeLogin.class);
        if (freeLogin != null && freeLogin.value()) {
            return true;
        }
        String token = request.getHeader("token");
        Map userMap;
        try {
            userMap = JwtTokenUtil.parseJWT(token, JwtTokenUtil.BASE64SECRET);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ValidateException("token解析错误!");
        }
        if ((LocalDateTime.parse((String)userMap.get(JwtTokenUtil.EXPIRE_TIME))).isBefore(LocalDateTime.now())) {
            throw new ValidateException("token过期,请重新登录!");
        }
        UserInfo userInfo = JSON.parseObject(JSON.toJSONString(userMap), UserInfo.class);
        currentUserInfo.setUserInfo(userInfo);
        // 重新生成新的token
        response.setHeader("token", JwtTokenUtil.createJWT(userMap, JwtTokenUtil.BASE64SECRET));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        //Controller执行完毕后，返回之前，可以对request和reponse进行处理
        //如果是前后端没有分离，在进入View层中前执行
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
        Optional.ofNullable(currentUserInfo.getUserInfo()).ifPresent(t -> currentUserInfo.remove());
    }
}
