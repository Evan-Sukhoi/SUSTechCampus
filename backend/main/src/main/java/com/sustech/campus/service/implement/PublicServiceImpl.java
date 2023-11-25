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
                        .select(Building::getBuilding_id, Building::getName, Building::getOpen_time, Building::getClose_time, Building::getLocation_name, Building::getIntroduction, Building::getNearest_station, Building::getVideo_url, Building::getCover_id)
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
    public List<Bus_line> getAllBusLine() {
        return bus_lineDao.selectJoinList(
                Bus_line.class,
                new MPJLambdaWrapper<Bus_line>()
                        .select(Bus_line::getBus_line_ID, Bus_line::getLine_ID, Bus_line::getStation, Bus_line::get_index_)
        );
    }
}
