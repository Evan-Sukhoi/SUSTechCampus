package com.sustech.campus.web.config;

import com.sustech.campus.web.filter.JwtAuthenticationFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig implements WebMvcConfigurer {

    @Resource
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Resource
    AuthenticationEntryPoint authenticationEntryPoint;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    /**
//     * WebSocket 配置  弃用
//     * @return WebSocket配置类
//     */
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter(){
//        return new ServerEndpointExporter();
//    }

    //https://github.com/spring-projects/spring-security/issues/11792
    @Bean
    public static AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * SpringSecurity配置
     * https://stackoverflow.com/questions/74683225/updating-to-spring-security-6-0-replacing-removed-and-deprecated-functionality
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors().and() //开启SpringSecurity跨域
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //关闭Session(不通过Session获取SecurityContext)
                .authorizeHttpRequests(
                        auth -> auth //定义在前面的会覆盖后面的
//                                .requestMatchers(new AntPathRequestMatcher("/user/upload-headshot")).authenticated() //上传头像时需要token
//                                .requestMatchers(new AntPathRequestMatcher("/user/change-headshot")).authenticated()
//                                .requestMatchers(new AntPathRequestMatcher("/user/**")).anonymous()
                                .requestMatchers(new AntPathRequestMatcher("/public/**")).permitAll() //放行获取信息相关URL
                                .requestMatchers(new AntPathRequestMatcher("/admin")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/admin/login")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/admin/user/all")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/user/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/websocket/**")).permitAll()
//                                .requestMatchers("/doc.html", "/webjars/**", "/img.icons/**", "/swagger-resources", "/v2/api-docs", "/favicon.ico").permitAll() //放行Knife4j相关URL
                                .anyRequest().authenticated()
                )
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and() //认证失败
//				                        .accessDeniedHandler(accessDeniedHandler).and() //鉴权失败
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) //在UsernamePasswordAuthenticationFilter**之前**添加JWT过滤器(为什么?)
//					.userDetailsService(userDetailsService) //注入userDetailsService
                .build();
    }

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