package com.sustech.campus.controller;

import com.sustech.campus.service.AdminService;
import com.sustech.campus.web.annotation.MappingController;
import jakarta.annotation.Resource;

@MappingController("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;


}
