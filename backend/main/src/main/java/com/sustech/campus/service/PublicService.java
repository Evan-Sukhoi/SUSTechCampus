package com.sustech.campus.service;

import com.sustech.campus.database.po.*;

import java.util.List;

public interface PublicService {

    /**
     * 获取所有建筑信息
     * @return 所有建筑信息
     */
    List<Building> getAllBuilding();

    List<Comment> getAllComment();

    List<Bus_line> getAllBusLine();
}
