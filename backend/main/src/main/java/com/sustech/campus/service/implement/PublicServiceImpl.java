package com.sustech.campus.service.implement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.dao.*;
import com.sustech.campus.database.po.*;
import com.sustech.campus.database.utils.ImgHostUploader;
import com.sustech.campus.database.utils.RedisUtil;
import com.sustech.campus.model.vo.*;
import com.sustech.campus.service.PublicService;
import com.sustech.campus.utils.AuthCodeUtil;
import com.sustech.campus.utils.Base64Util;
import com.sustech.campus.utils.EmailUtil;
import com.sustech.campus.utils.RsaUtil;
import com.sustech.campus.web.utils.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.KeyPair;
import java.security.PrivateKey;
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
    private BuildingsImageDao buildingsImageDao;
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
    @Resource
    private ProductDao productDao;
    @Resource
    private OrderDao orderDao;
    org.springframework.core.io.Resource buslineResource;
    @Autowired
    private ImgHostUploader imgHostUploader;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ImgHostUploader.class);
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Object getAllBusLine() throws IOException {
        BufferedReader reader = null;
        JSONArray ret = null;
        try {
            File file = new File("backend/main/src/main/resources/static/busline/busline.json");
            FileInputStream resource = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(resource));
            StringBuilder builder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            ret = JSON.parseArray(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return ret;
        // 下面是使用Resource读取json，但在本地的json发生改变后，Resource不会更新，所以改用FileInputStream
        // http://t.csdnimg.cn/h4870
//        buslineResource = resourceLoader.getResource("classpath:static/busline/busline.json");
//        InputStream inputStream = buslineResource.getInputStream();
//        byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
//        return new ObjectMapper().readValue(bdata, Object.class);
    }

    @Override
    public List<BuildingInfoSimple> getSimpleBuildingInfo() {
        List<Building> buildings = buildingDao.selectList(
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuildingId)
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
                        .select(Building::getBuildingId, Building::getName, Building::getIntroduction, Building::getBuildingType, Building::getIsReservable)
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
        List<String> image_url = buildingsImageDao.selectList(
                        new MPJLambdaWrapper<BuildingsImage>()
                                .select(BuildingsImage::getImageId)
                                .eq(BuildingsImage::getBuildingId, buildingId)
                )
                .stream()
                .map(BuildingsImage::getImageId)
                .map(imageId -> imageDao
                        .selectById(imageId)
                        .getImageUrl()
                ).toList();
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
                .isReservable(building.getIsReservable())
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
    public UserInfo login(String username, String password) throws Exception {
        asserts(username != null && password != null, "用户名或密码不能为空");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (username.contains("@")) {
            queryWrapper.eq(User::getEmail, username);
        } else {
            queryWrapper.eq(User::getName, username);
        }
        User user = userDao.selectOne(queryWrapper);
        asserts(user != null, "用户不存在");
        try {
            String privateKey = redis.getObject("RSA_PRIVATE_KEY");
            String decrypt = RsaUtil.decrypt(password, RsaUtil.getPrivateKey(privateKey));
            asserts(passwordEncoder.matches(decrypt, user.getPassword()), "密码错误");
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            throw e;
        }
        // 添加login log和authenticate
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        loginLogDao.insert(new LoginLog()
                .setUserId(user.getUserId())
                .setIpAddress(request.getRemoteAddr())
                .setPort(request.getRemotePort())
                .setPort(request.getRemotePort())
                .setLoginTime(new Date()));

        String token = authenticate(user);

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
    public String authenticate(User user) {
        String password = user.getPassword();
        String id = String.valueOf(user.getUserId());
        Authentication authentication = new UsernamePasswordAuthenticationToken(id, password, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        redis.setObject("User login:" + id, user, 60 * 60 * 2);
        return JwtUtil.createJwt("user" + id);
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
    public String getKey() throws Exception {
        Object privateKey = redis.getObject("RSA_PRIVATE_KEY");
        Object publicKey = redis.getObject("RSA_PUBLIC_KEY");
        if (privateKey == null || publicKey == null) {
            KeyPair keyPair = RsaUtil.getKeyPair();
            privateKey = new String(Base64Util.encoder(keyPair.getPrivate().getEncoded()));
            publicKey = new String(Base64Util.encoder(keyPair.getPublic().getEncoded()));
            // 存入私钥
            redis.setObject("RSA_PRIVATE_KEY", privateKey);
            // 存入公钥
            redis.setObject("RSA_PUBLIC_KEY", publicKey);
        }
        LOGGER.info("privateKey = {}，publicKey = {}", privateKey, publicKey);
        return publicKey.toString();
    }

    @Override
    public List<ProductInfo> getAllProduct() {
        List<Product> products = productDao.selectList(null);
        return products.stream().map(product -> {
            return ProductInfo.builder()
                    .productId(product.getProductId())
                    .subject(product.getSubject())
                    .body(product.getBody())
                    .shop(product.getShop())
                    .amount(product.getAmount())
                    .inventory(product.getInventory())
                    .imageUrl(imageDao.selectById(product.getImageId()).getImageUrl())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public String getCDKey(Long orderId) {
        OrderInfo order = orderDao.selectOne(
                new LambdaQueryWrapper<OrderInfo>()
                        .select(OrderInfo::getCdkey, OrderInfo::getStatus)
                        .eq(OrderInfo::getOrderSn, orderId));
        asserts(order != null, "订单不存在");
        asserts(order.getStatus() == 0, "该订单的CDKEY已被获取");
        order.setStatus(1);
        orderDao.updateById(order);
        LOGGER.info("订单{}已支付", order);
        return order.getCdkey();
    }


    @Override
    public Boolean register(String username, String password, String email, String phoneNumber, String authCode, MultipartFile file) throws Exception {
        asserts(username != null && password != null && email != null && phoneNumber != null, "用户名、密码、邮箱、手机号不能为空");
        asserts(username.length() >= 3 && username.length() <= 20, "用户名长度应在3-20之间");
        String decrypt = null;
        try {
            String privateKey = redis.getObject("RSA_PRIVATE_KEY");
            decrypt = RsaUtil.decrypt(password, RsaUtil.getPrivateKey(privateKey));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            throw e;
        }
        asserts(decrypt.length() >= 6 && decrypt.length() <= 20, "密码长度应在6-20之间");
        asserts(email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"), "邮箱格式不正确");
        asserts(phoneNumber.matches("^\\d{11}$"), "手机号格式不正确");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, username)) == null, "该用户名已被注册");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, email)) == null, "该邮箱已被注册");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phoneNumber)) == null, "该手机号已被注册");
        asserts(authCode != null, "验证码不能为空");
        asserts(authCode.toString().matches("^\\d{6}$"), "验证码格式不正确");

        String trueCode = redis.getObject("verification:" + email);

        asserts(trueCode != null, "验证码已过期或未获取，请重新获取");
        asserts(authCode.equals(trueCode), "验证码错误");

        String url = imgHostUploader.upload(file);
        Image image = Image.builder()
                .imageUrl(url)
                .build();
        imageDao.insert(image);

        User user = User.builder()
                .name(username)
                .password(passwordEncoder.encode(decrypt))
//                .password(password)
                .email(email)
                .phone(phoneNumber)
                .imageId(image.getImageId())
                .isBlocked(false)
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
