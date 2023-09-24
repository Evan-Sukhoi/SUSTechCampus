package com.sustech.campus.controller;

import org.springframework.stereotype.Controller;
import com.sustech.campus.utils.RedisUtil;

@Controller
public class BaseController {
    final RedisUtil redisUtil = new RedisUtil();

}
