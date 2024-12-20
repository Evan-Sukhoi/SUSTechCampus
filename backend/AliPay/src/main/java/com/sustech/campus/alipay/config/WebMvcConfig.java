package com.sustech.campus.alipay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfig
 * @Description 主要是给Thymeleaf配置
 * @Author LN
 * @Date 2023/12/3
 */

// 配置URL对应视图
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/index.html").setViewName("index");
//        registry.addViewController("http://localhost:8080/user/service").setViewName("success");
//        registry.addViewController("http://localhost:8080").setViewName("success");
    }
}
