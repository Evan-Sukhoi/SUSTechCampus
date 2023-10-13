package com.sustech.campus.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.database.dao.BuildingDao;
import com.sustech.campus.database.dao.RoomDao;
import com.sustech.campus.model.vo.BuildingInfo;
import com.sustech.campus.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private BuildingDao buildingDao;

    @Resource
    private RoomDao roomDao;

    @Override
    public List<BuildingInfo> getAllBuilding() {
        return buildingDao.selectJoinList(
                BuildingInfo.class,
                new MPJLambdaWrapper<BuildingInfo>()
                        .select(Building::getBuilding_ID, Building::getName, Building::getOpenTime, Building::getCloseTime, Building::getLocation_name, Building::getIntroduction, Building::getNearest_station, Building::getVideo_url, Building::getCover_ID)
        );
    }

    @Override
    public Boolean uploadBuilding(Building building) {
        buildingDao.insert(building);
        return true;
    }

    @Override
    public Boolean uploadRoom(Room room) {
        roomDao.insert(room);
        return true;
    }

    @Override
    public Boolean deleteBuilding(Integer buildingId) {
        QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("building_ID", buildingId);
        Building building = buildingDao.selectOne(queryWrapper);
        if (building == null) {
            return false;
        }else {
            buildingDao.deleteById(buildingId);
            return true;
        }
    }

    @Override
    public Boolean deleteRoom(Integer roomId) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_ID", roomId);
        Room room = roomDao.selectOne(queryWrapper);
        if (room == null) {
            return false;
        }else {
            roomDao.deleteById(roomId);
            return true;
        }
    }

    @Override
    public String uploadRoomTypeCover(MultipartFile picture, Integer roomId) {
        return null;
    }

    @Override
    public String uploadRoomTypeMedia(MultipartFile media, Integer roomId) {
        return null;
    }
}
