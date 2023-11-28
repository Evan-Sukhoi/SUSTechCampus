package com.sustech.campus.web.filter;

import com.sustech.campus.database.dao.UserDao;
import com.sustech.campus.database.po.User;



import com.sustech.campus.database.utils.RedisUtil;
import com.sustech.campus.web.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull; //用于编译时和开发工具中的代码分析,旨在提供更好的代码检查,帮助IDE进行更精确的代码分析和提示,以避免空指针异常等问题
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private RedisUtil redis;
    @Resource
    private UserDao userDao;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        //每次请求都是一个独立的SecurityContext
        String token = request.getHeader("token");
        if (StringUtils.hasText(token)) {
            Claims claims = JwtUtil.parseJwt(token);
            String id = claims.getSubject();
            User user = redis.getObject("login:" + id);
            if (user == null) { //Redis的User过期了，查询数据库
                user = userDao.selectById(id);
            }
            if (user != null) { //如果Redis和数据库中都没有User，则SecurityContext中没有Authentication对象
                redis.setObject("login:" + id, user, 60 * 60 * 2); //刷新ttl为2h
                Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword(), null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response); //没有user也直接放行，之后会被Interceptor拦截，因为SecurityContext中没有Authentication对象
    }
}
