package com.sustech.campus.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //允许所有API
                .allowedOriginPatterns("*") //允许所有域名
                .allowCredentials(true) //允许Cookie
                .allowedMethods("*") //允许任何方法
                .allowedHeaders("*") //允许任何header
                .maxAge(3600); //跨域允许时间1h
    }
}
