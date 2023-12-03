package com.sustech.campus.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.dao.*;
import com.sustech.campus.database.po.*;
import com.sustech.campus.database.utils.ImgHostUploader;
import com.sustech.campus.database.utils.RedisUtil;
import com.sustech.campus.model.vo.*;
import com.sustech.campus.service.PublicService;
import com.sustech.campus.utils.AuthCodeUtil;
import com.sustech.campus.utils.EmailUtil;
import com.sustech.campus.web.utils.JwtUtil;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.sustech.campus.web.utils.ExceptionUtils.asserts;


@Service
public class PublicServiceImpl implements PublicService {

    @Resource
    private BuildingDao buildingDao;
    @Resource
    private CommentDao commentDao;
    @Resource
    private BuslineDao buslineDao;
    @Resource
    private ImageDao imageDao;
    @Resource
    private UserDao userDao;
    @Resource
    private RedisUtil redis;
    @Resource
    private LoginLogDao loginLogDao;
    @Resource
    private ResourceLoader resourceLoader;
    @Resource
    private CommentIdImageDao commentIdImageDao;
    @Autowired
    private ImgHostUploader imgHostUploader;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ImgHostUploader.class);
//    @Resource
//    private PasswordEncoder passwordEncoder;

    @Override
    public Object getAllBusLine() throws IOException {
        org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:static/busline/busline.json");
        InputStream inputStream = resource.getInputStream();
        byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
//        return new String(bdata, StandardCharsets.UTF_8);
        return new ObjectMapper().readValue(bdata, Object.class);
    }

    @Override
    public List<BuildingInfoSimple> getSimpleBuildingInfo() {
        List<Building> buildings = buildingDao.selectList(
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuildingId, Building::getName, Building::getIntroduction, Building::getCoverId, Building::getBuildingType)
        );
        return buildings.stream().map(building -> getBuildingInfoSimpleThroughId(building.getBuildingId())).toList();
    }

    @Override
    public BuildingStation getBuildingStationThroughId(Integer buildingId) {
        return buildingDao.selectJoinOne(
                BuildingStation.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getNearestStation)
                        .eq(Building::getBuildingId, buildingId)
        );
    }

    @Override
    public BuildingInfoSimple getBuildingInfoSimpleThroughId(Integer buildingId) {
        return buildingDao.selectJoinOne(
                BuildingInfoSimple.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuildingId, Building::getName, Building::getIntroduction, Building::getBuildingType)
                        .leftJoin(Image.class, Image::getImageId, Building::getCoverId)
                        .selectAs(Image::getImageUrl, BuildingInfoSimple::getCoverUrl)
                        .eq(Building::getBuildingId, buildingId)
        );
    }

    @Override
    public BuildingInfo getBuildingDetails(Integer buildingId) {
        Building building = buildingDao.selectJoinOne(
                Building.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuildingId, Building::getName, Building::getOpenTime, Building::getCloseTime, Building::getLocationName, Building::getIntroduction, Building::getNearestStation, Building::getVideoUrl, Building::getCoverId)
                        .eq(Building::getBuildingId, buildingId)
        );
        List<BuildingsImage> buildingsImages = buildingDao.selectJoinList(
                BuildingsImage.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuildingId)
                        .eq(Building::getBuildingId, buildingId)
        );
        List<String> image_url = buildingsImages.stream().map(buildingsImage -> {
            Image image = imageDao.selectById(buildingsImage.getImageId());
            if (image == null) {
                LOGGER.warn("imageDao.selectById(buildingsImage.getImageId()) == null");
                return null;
            }
            String url = image.getImageUrl();
            if (url == null) {
                LOGGER.warn("imageDao.selectById(buildingsImage.getImageId()).getImageUrl() == null");
                return null;
            } else {
                return url;
            }
        }).toList();
        String cover_url = null;
        Image image = imageDao.selectById(building.getCoverId());
        if (image == null) {
            LOGGER.warn("imageDao.selectById(building.getCoverId()) == null");
        } else {
            cover_url = image.getImageUrl();
        }
        return BuildingInfo.builder()
                .buildingId(building.getBuildingId())
                .name(building.getName())
                .openTime(building.getOpenTime())
                .closeTime(building.getCloseTime())
                .locationName(building.getLocationName())
                .introduction(building.getIntroduction())
                .nearestStation(building.getNearestStation())
                .videoUrl(building.getVideoUrl())
                .buildingType(building.getBuildingType())
                .coverUrl(cover_url)
                .imageUrl(image_url)
                .build();
    }

    @Override
    public List<CommentInfo> getApprovedComments(Integer buildingId) {
        List<Comment> comments = commentDao.selectList(
                new MPJLambdaWrapper<Comment>()
                        .select(Comment::getCommentId, Comment::getUserId, Comment::getTime, Comment::getText, Comment::getBuildingId, Comment::getScore, Comment::getAdminId)
                        .eq(Comment::getBuildingId, buildingId)
                        .ne(Comment::getAdminId, 0)
        );
        return comments.stream().map(comment -> {
            User user = userDao.selectById(comment.getUserId());
            String userImageUrl = null;
            if (imageDao.selectById(user.getImageId()) == null) {
                LOGGER.warn("imageDao.selectById(user.getImageId()) == null");
            } else {
                userImageUrl = imageDao.selectById(user.getImageId()).getImageUrl();
            }
            List<CommentIdImage> commentIdImages = commentIdImageDao.selectList(
                    new MPJLambdaWrapper<CommentIdImage>()
                            .select(CommentIdImage::getImageId)
                            .eq(CommentIdImage::getCommentId, comment.getCommentId())
            );
            List<String> image_url = commentIdImages.stream().map(commentIdImage -> {
                return imageDao.selectById(commentIdImage.getImageId()).getImageUrl();
            }).toList();
            return CommentInfo.builder()
                    .commentId(comment.getCommentId())
                    .userId(comment.getUserId())
                    .time(comment.getTime())
                    .text(comment.getText())
                    .buildingId(comment.getBuildingId())
                    .score(comment.getScore())
                    .username(user.getName())
                    .userImageUrl(userImageUrl)
                    .imageUrl(image_url)
                    .adminId(comment.getAdminId())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public UserInfo login(String username, String password) {
        asserts(username != null && password != null, "用户名或密码不能为空");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (username.contains("@")) {
            queryWrapper.eq(User::getEmail, username);
        } else {
            queryWrapper.eq(User::getName, username);
        }
        User user = userDao.selectOne(queryWrapper);
        asserts(user != null, "用户不存在");
        asserts(user.getPassword().equals(password), "密码错误");
        // TODO: 密码加密
//        asserts(passwordEncoder.matches(password, user.getPassword()), "密码错误");

        // 添加login log和authenticate
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        loginLogDao.insert(new LoginLog()
                .setUserId(user.getUserId())
                .setIpAddress(request.getRemoteAddr())
                .setPort(request.getRemotePort())
                .setPort(request.getRemotePort())
                .setLoginTime(new Date()));

        String token = authenticate(user.getName(), user.getPassword());

        return UserInfo.builder()
                .token(token)
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .imageUrl(imageDao.selectById(user.getImageId()).getImageUrl())
                .build();
    }

    @Override
    public String authenticate(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        redis.setObject("login:" + username, username, 60 * 60 * 2);
        return JwtUtil.createJwt(username);
    }

    @Resource
    private EmailUtil emailUtil;
    @Override
    public Boolean sendAuthCode(String email) {
        asserts(email != null, "邮箱不能为空");
        asserts(email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"), "邮箱格式不正确");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, email)) == null, "该邮箱已被注册");

        String code = AuthCodeUtil.generateAuthCode();
        String content = "您的验证码为：" + code + "，有效期为5分钟。请勿泄露给他人。";
        String subject = "SUSTech Campus-注册验证码-" + code;
        emailUtil.sendMail(email, subject, content);

        redis.setObject("verification:" + email, code, 60 * 5);

        return true;
    }

    @Override
    public Boolean register(String username, String password, String email, String phoneNumber, Integer authCode, MultipartFile file) throws IOException {
        asserts(username != null && password != null && email != null && phoneNumber != null, "用户名、密码、邮箱、手机号不能为空");
        asserts(username.length() >= 3 && username.length() <= 20, "用户名长度应在3-20之间");
        asserts(password.length() >= 6 && password.length() <= 20, "密码长度应在6-20之间");
        asserts(email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"), "邮箱格式不正确");
        asserts(phoneNumber.matches("^\\d{11}$"), "手机号格式不正确");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, username)) == null, "该用户名已被注册");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, email)) == null, "该邮箱已被注册");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phoneNumber)) == null, "该手机号已被注册");
        asserts(authCode != null, "验证码不能为空");

        String trueCode = redis.getObject("verification:" + email);

        asserts(trueCode != null, "验证码已过期或未获取，请重新获取");
        asserts(authCode.equals(Integer.parseInt(trueCode)), "验证码错误");

        String url = imgHostUploader.upload(file);
        Image image = Image.builder()
                .imageUrl(url)
                .build();
        imageDao.insert(image);

        User user = User.builder()
                .name(username)
//                .password(passwordEncoder.encode(password))
                .password(password)
                .email(email)
                .phone(phoneNumber)
                .imageId(image.getImageId())
                .build();
        userDao.insert(user);
        return true;
    }

    @Override
    public List<Comment> getCommentByBuilding(Integer buildingId) {
        return commentDao.selectJoinList(
                Comment.class,
                new MPJLambdaWrapper<Comment>()
                        .select(Comment::getCommentId, Comment::getUserId, Comment::getTime, Comment::getText, Comment::getBuildingId, Comment::getScore, Comment::getAdminId)
                        .eq(Building::getBuildingId, buildingId)
        );
    }
}
