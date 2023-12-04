package com.sustech.campus.web.utils;

import com.sustech.campus.database.dao.IllegalOperationLogDao;
import com.sustech.campus.database.po.IllegalOperationLog;
import com.sustech.campus.database.po.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sustech.campus.web.handler.ApiException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;


public class ExceptionUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionUtils.class);

    public static void asserts(boolean condition, String message) {
        if (!condition) {
            LOGGER.warn(message);
            throw ApiException.badRequest(message);
        }
    }

    public static void warns(boolean condition, String message, String operation, User user, IllegalOperationLogDao illegalOperationLogDao) {
        if (!condition) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            LOGGER.warn("检测到非法操作 Operation: {}, Ip Address: {}", operation, request.getRemoteAddr());
            illegalOperationLogDao.insert(IllegalOperationLog.builder()
                    .userId(user.getUserId())
                    .operation(operation)
                    .ipAddress(request.getRemoteAddr())
                    .port(request.getRemotePort())
                    .operationTime(new Date())
                    .build());
            throw ApiException.unauthorized(message);
        }
    }
}