package com.sustech.campus.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.dao.*;
import com.sustech.campus.database.po.*;
import com.sustech.campus.database.utils.ImgHostUploader;
import com.sustech.campus.model.vo.BuildingInfo;
import com.sustech.campus.model.vo.BuildingInfoSimple;
import com.sustech.campus.model.vo.BuildingStation;
import com.sustech.campus.model.vo.RoomInfo;
import com.sustech.campus.service.PublicService;
import jakarta.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.sustech.campus.web.utils.ExceptionUtils.asserts;


@Service
public class PublicServiceImpl implements PublicService {

    @Resource
    private BuildingDao buildingDao;
    @Resource
    private CommentDao commentDao;
    @Resource
    private Bus_lineDao bus_lineDao;
    @Resource
    private ImageDao imageDao;
    @Resource
    private UserDao userDao;
    @Autowired
    private ImgHostUploader imgHostUploader;
//    @Resource
//    private PasswordEncoder passwordEncoder;

    @Override
    public List<Bus_line> getAllBusLine() {
        return bus_lineDao.selectJoinList(
                Bus_line.class,
                new MPJLambdaWrapper<Bus_line>()
                        .select(Bus_line::getBus_line_ID, Bus_line::getLine_ID, Bus_line::getStation, Bus_line::get_index_)
        );
    }

    @Override
    public List<BuildingInfoSimple> getSimpleBuildingInfo() {
        return buildingDao.selectJoinList(
                BuildingInfoSimple.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuilding_id, Building::getName, Building::getCover_id, Building::getIntroduction)
        );
    }

    @Override
    public BuildingInfoSimple getSimpleBuildingInfoThroughId(Integer buildingId) {
        return buildingDao.selectJoinOne(
                BuildingInfoSimple.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuilding_id, Building::getName, Building::getCover_id, Building::getIntroduction)
                        .eq(Building::getBuilding_id, buildingId)
        );
    }

    @Override
    public BuildingStation getBuildingStation(Integer buildingId) {
        return buildingDao.selectJoinOne(
                BuildingStation.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getNearest_station)
                        .eq(Building::getBuilding_id, buildingId)

        );
    }



    @Override
    public BuildingInfo getBuildingDetails(Integer buildingId) {
        return buildingDao.selectJoinOne(
                BuildingInfo.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuilding_id, Building::getName, Building::getOpen_time, Building::getClose_time, Building::getLocation_name, Building::getIntroduction, Building::getNearest_station, Building::getVideo_url, Building::getCover_id)
                        .eq(Building::getBuilding_id, buildingId)
        );
    }

    @Override
    public List<Comment> getApprovedComments(Integer buildingId) {
        return commentDao.selectJoinList(
                Comment.class,
                new MPJLambdaWrapper<Comment>()
                        .select(Comment::getComment_id, Comment::getUser_id, Comment::getTime, Comment::getText, Comment::getBuilding_id, Comment::getScore, Comment::getAdmin_id)
                        .eq(Comment::getBuilding_id, buildingId)
                        .ne(Comment::getAdmin_id, 0)
        );
    }

    @Override
    public Boolean login(String username, String password) {
        asserts(username != null && password != null, "用户名或密码不能为空");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (username.contains("@")) {
            queryWrapper.eq(User::getEmail, username);
        } else {
            queryWrapper.eq(User::getName, username);
        }
        User user = userDao.selectOne(queryWrapper);
        asserts(user != null, "用户不存在");
//        asserts(passwordEncoder.matches(password, user.getPassword()), "密码错误");

        // TODO: 添加login log和authenticate
        return true;
    }

    @Override
    public Boolean register(String username, String password, String email, String phoneNumber, MultipartFile file) throws IOException {
        asserts(username != null && password != null && email != null && phoneNumber != null, "用户名、密码、邮箱、手机号不能为空");
        asserts(username.length() >= 3 && username.length() <= 20, "用户名长度应在3-20之间");
        asserts(password.length() >= 6 && password.length() <= 20, "密码长度应在6-20之间");
        asserts(email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"), "邮箱格式不正确");
        asserts(phoneNumber.matches("^\\d{11}$"), "手机号格式不正确");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, username)) == null, "该用户名已被注册");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, email)) == null, "该邮箱已被注册");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phoneNumber)) == null, "该手机号已被注册");

        String url = imgHostUploader.upload(file);
        Image image = Image.builder()
                .image_url(url)
                .build();
        imageDao.insert(image);

        User user = User.builder()
                .name(username)
//                .password(passwordEncoder.encode(password))
                .password(password)
                .email(email)
                .phone(phoneNumber)
                .image_id(image.getImage_id())
                .build();
        userDao.insert(user);
        return true;
    }

    @Override
    public List<Comment> getCommentByBuilding(Integer buildingId) {
        return commentDao.selectJoinList(
                Comment.class,
                new MPJLambdaWrapper<Comment>()
                        .select(Comment::getComment_id, Comment::getUser_id, Comment::getTime, Comment::getText, Comment::getBuilding_id, Comment::getScore, Comment::getAdmin_id)
                        .eq(Building::getBuilding_id, buildingId)
        );
    }
}
