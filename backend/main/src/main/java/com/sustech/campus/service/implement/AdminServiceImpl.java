package com.sustech.campus.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.dao.UserDao;
import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.database.dao.BuildingDao;
import com.sustech.campus.database.dao.RoomDao;
import com.sustech.campus.database.po.User;
import com.sustech.campus.model.vo.BuildingInfo;
import com.sustech.campus.model.vo.RoomInfo;
import com.sustech.campus.service.AdminService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private BuildingDao buildingDao;

    @Resource
    private RoomDao roomDao;

    @Resource
    private UserDao usersDao;

    @Override
    public User getUserInfo(Integer userId) {
        return usersDao.selectById(userId);
    }

    @Override
    public Boolean updateUserInfo(Integer userId, String name, String phone, String email, String password) {
        User user = usersDao.selectById(userId);
        if (user == null) {
            return false;
        } else {
            user.setName(name);
            user.setPhone(phone);
            user.setEmail(email);
            user.setPassword(password);
            usersDao.updateById(user);
            return true;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return usersDao.selectList(null);
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        User user = usersDao.selectById(userId);
        if (user == null) {
            return false;
        } else {
            usersDao.deleteById(userId);
            return true;
        }
    }

    @Override
    public List<BuildingInfo> getAllBuilding() {
        System.out.println((new MPJLambdaWrapper<BuildingInfo> ()
                .select(Building::getBuilding_id, Building::getName, Building::getOpen_time, Building::getClose_time, Building::getLocation_name, Building::getIntroduction, Building::getNearest_station, Building::getVideo_url, Building::getCover_id)
        ).getSqlSegment());

        System.out.println(buildingDao.selectList(null));


        return buildingDao.selectJoinList(
                BuildingInfo.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuilding_id, Building::getName, Building::getOpen_time, Building::getClose_time, Building::getLocation_name, Building::getIntroduction, Building::getNearest_station, Building::getVideo_url, Building::getCover_id)
                        .eq(Building::getBuilding_id, 1)
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
    public List<RoomInfo> getAllRoom() {
        return roomDao.selectJoinList(
                RoomInfo.class,
                new MPJLambdaWrapper<Room>()
                        .selectAll(Room.class)
                        .select(Building::getName, Building::getBuilding_id)
                        .leftJoin(Building.class, Building::getBuilding_id, Room::getBuilding_id)
        );
    }

    @Override
    public Boolean deleteBuilding(Integer buildingId) {
        QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("building_ID", buildingId);
        Building building = buildingDao.selectOne(queryWrapper);
        if (building == null) {
            return false;
        } else {
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
        } else {
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
