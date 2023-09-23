package com.ooad.sustechcampus.dao;

import com.ooad.sustechcampus.model.User;
import com.ooad.sustechcampus.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {
    //第一步：获得sqlSession对象
    SqlSession sqlSession;
    @Test
    public void test(){
        try {

            sqlSession = MybatisUtils.getSqlSession();
            //方式一：getMapper
            UserDAO mapper = sqlSession.getMapper(UserDAO.class);
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            //关闭sqlSession
            sqlSession.close();
        }

    }
}

