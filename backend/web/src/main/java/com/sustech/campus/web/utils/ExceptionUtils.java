package com.sustech.campus.web.utils;

import com.sustech.campus.web.handler.ApiException;

public class ExceptionUtils{
    public static void asserts(boolean condition, String message){
        if(!condition){
            throw ApiException.badRequest(message);
        }
    }

    public static void warns(boolean condition, String message, String operation){
        if(!condition){
            throw ApiException.unauthorized(message);
        }
    }
}