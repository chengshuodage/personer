package com.cs.personer.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决请求体中的流只能读取一次的问题.
 *
 * @author Jack
 */
@Component
@Order(1)
@WebFilter(urlPatterns = "/*")
public class ReplaceStreamFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        filterChain.doFilter(requestWrapper, response);
    }
}
