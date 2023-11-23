package com.sustech.campus.web.handler;

public class ApiException extends RuntimeException {
    private final int code;
    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    public static ApiException badRequest(String message) {
        return new ApiException(400, message);
    }

    public static ApiException internalServerError(){
        return new ApiException(500,"Internal server error");
    }

    public static int getErrorCode(ApiException e) {
        return e.code;
    }
}

