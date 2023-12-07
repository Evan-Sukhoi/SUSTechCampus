package com.sustech.campus.alipay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ThymeleafController
 * @Description Thymeleaf-Controller
 * @Author LN
 * @Date 2023/12/3
 */
@Controller
public class ThymeleafController {
    @RequestMapping("/hello")
    public String hello(Model model){
        return "index";
    }

    @RequestMapping("/user/service")
    public String success(Model model){
        model.addAttribute("name", "LN");
        return "success";
    }
}
