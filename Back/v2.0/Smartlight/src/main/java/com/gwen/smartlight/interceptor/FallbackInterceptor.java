package com.gwen.smartlight.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @FileName : FallbackInterceptor
 * @Author : JeoPorn.Z
 * @Date : 2025/9/23
 * @Description
 */
@Component
public class FallbackInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.startsWith("/lights/")) {
            // 可以做一些存在性判断，这里简单处理：所有不存在的API直接跳转
             response.sendRedirect("forward:/index.html");
            return false; // 不继续执行
        }
        return true;
    }
}
