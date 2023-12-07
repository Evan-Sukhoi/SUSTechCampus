package com.sustech.campus;

import com.alibaba.fastjson.parser.ParserConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@MapperScan("com.sustech.campus.service")
@MapperScan("com.sustech.campus.database.dao")
@SpringBootApplication()
public class MainApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	/**
	 * 将启动类交给Servlet容器进行启动
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(com.sustech.campus.MainApplication.class);
	}

	static {
		// https://github.com/alibaba/fastjson/wiki/enable_autotype
//		ParserConfig.getGlobalInstance().addAccept("org.springframework.security.core.authority.SimpleGrantedAuthority");
		ParserConfig.getGlobalInstance().addAccept("com.sustech.campus.database.po.User");
		ParserConfig.getGlobalInstance().addAccept("com.sustech.campus.database.po.Admin");
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
	}

}
