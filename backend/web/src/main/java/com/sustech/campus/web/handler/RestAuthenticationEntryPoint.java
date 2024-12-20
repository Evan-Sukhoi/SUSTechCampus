package com.sustech.campus.web.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sustech.campus.web.vo.ApiResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Refer: <a href="https://www.devglan.com/spring-security/exception-handling-in-spring-security">Exception handling in Spring Security</a>
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        ApiResponse response = new ApiResponse(401, "Unauthorized, no JWT or the JWT may is expired");
        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, response);
        out.flush();
    }
}