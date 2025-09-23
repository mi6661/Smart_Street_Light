/*
package com.gwen.smartlight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FallbackController {

    @GetMapping(value = "/{path:.*}")
    public String forward(HttpServletRequest request) {
        // 获取完整的请求 URI
        String requestURI = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        // 如果请求 URI 包含点号，并且不是 index.html，则不处理
        // 这通常用于过滤静态资源文件，如 .js, .css, .png 等
        if (requestURI != null && requestURI.contains(".")) {
            // 返回 null，让 Spring 继续查找其他 Handler
            return null;
        }

        // 所有其他路径都转发到 index.html
        return "forward:/index.html";
    }
}*/
