package com.gwen.smartlight.config;

import com.gwen.smartlight.interceptor.FallbackInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 前端请求跨域问题
*
* */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Autowired
    private FallbackInterceptor fallbackInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 确保有 POST
                .allowCredentials(true);
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(fallbackInterceptor)
                .addPathPatterns("/lights/**");
    }
}
