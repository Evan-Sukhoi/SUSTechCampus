package com.sustech.campus.controller;

import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Comment;
import com.sustech.campus.database.po.Bus_line;
import com.sustech.campus.service.PublicService;
import com.sustech.campus.web.annotation.MappingController;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@MappingController("/public")
public class PublicController {

    @Resource
    private PublicService publicService;

    @ApiOperation("获取所有建筑信息")
    @RequestMapping("/building/all")
    public List<Building> getAllBuilding() {
        return publicService.getAllBuilding();
    }

    @ApiOperation("获取所有评论信息")
    @RequestMapping("/comment/all")
    public List<Comment> getAllComment() {
        return publicService.getAllComment();
    }

    @ApiOperation("获取所有公交线路信息")
    @RequestMapping("/bus_line/all")
    public List<Bus_line> getAllBusLine() {
        return publicService.getAllBusLine();
    }
}
