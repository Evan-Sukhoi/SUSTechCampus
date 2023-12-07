package com.sustech.campus.utils;


import java.util.Base64;

public class Base64Util {
    public static byte[] encoder(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }

    public static byte[] decoder(byte[] bytes) {
        return Base64.getDecoder().decode(bytes);
    }
}
