package com.sustech.campus.alipay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// 测试
@MapperScan("com.sustech.campus.database.dao")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class AliPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliPayApplication.class, args);
    }

}
