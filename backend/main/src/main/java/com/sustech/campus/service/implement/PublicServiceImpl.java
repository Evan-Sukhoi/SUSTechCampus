package com.sustech.campus.service.implement;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.dao.*;
import com.sustech.campus.database.po.*;
import com.sustech.campus.service.PublicService;
import jakarta.annotation.Resource;

import java.util.List;

public class PublicServiceImpl implements PublicService {

    @Resource
    private BuildingDao buildingDao;
    @Resource
    private CommentDao commentDao;
    @Resource
    private bus_lineDao bus_lineDao;


    @Override
    public List<Building> getAllBuilding() {
        return buildingDao.selectJoinList(
                Building.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuilding_ID, Building::getName, Building::getOpenTime, Building::getCloseTime, Building::getLocation_name, Building::getIntroduction, Building::getNearest_station, Building::getVideo_url, Building::getCover_ID)
        );
    }

    @Override
    public List<Comment> getAllComment() {
        return commentDao.selectJoinList(
                Comment.class,
                new MPJLambdaWrapper<Comment>()
                        .select(Comment::getComment_id, Comment::getUser_id, Comment::getTime, Comment::getText, Comment::getBuilding_id, Comment::getScore, Comment::getAdmin_id)
        );
    }

    @Override
    public List<bus_line> getAllBusLine() {
        return bus_lineDao.selectJoinList(
                bus_line.class,
                new MPJLambdaWrapper<bus_line>()
                        .select(bus_line::getBus_line_ID, bus_line::getLine_ID, bus_line::getStation, bus_line::get_index_)
        );
    }
}
