package com.sustech.campus;


import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.sustech.campus.database.dao.BuildingDao;
import com.sustech.campus.database.po.Building;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisTest {

    @Autowired
    private BuildingDao buildingDao;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Building> userList = buildingDao.selectList(null);
        Assert.isTrue(1 == userList.size(), "");
        userList.forEach(System.out::println);
    }

}

