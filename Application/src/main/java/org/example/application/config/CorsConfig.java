package org.example.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//所有接口
                .allowedOrigins("http://127.0.0.1:5500")//允许的源
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")//允许的https方法
                .allowedHeaders("*")//允许的请求头
                .allowCredentials(true)//允许携带cookie
                .maxAge(3600);//预检请求缓存时间（秒）
    }
}
