package com.ooad.sustechcampus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SusTechCampusApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SusTechCampusApplication.class, args);
    }

    /**
     * 将启动类交给Servlet容器进行启动
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(SusTechCampusApplication.class);
    }
}
