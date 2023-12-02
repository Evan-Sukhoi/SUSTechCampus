package com.sustech.campus;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.dao.RoomDao;
import com.sustech.campus.database.po.Building;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.sustech.campus.database.dao.RoomDao;
import com.sustech.campus.model.vo.RoomInfo;
import com.sustech.campus.database.po.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisJoinTest {

    @Autowired
    private RoomDao roomDao;

    @Test
    public void testSelect() {
        MPJLambdaWrapper<Room> wrapper = new MPJLambdaWrapper<Room>()
                .selectAll(Room.class)
                .select(Building::getName, Building::getBuildingId)
                .leftJoin(Building.class, Building::getBuildingId, Room::getBuildingId);

        List<RoomInfo> userList = roomDao.selectJoinList(RoomInfo.class, wrapper);

        userList.forEach(System.out::println);
    }

}

