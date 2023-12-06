package com.sustech.campus.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.sustech.campus.alipay.config.AliPayTemplate;
import com.sustech.campus.alipay.model.OrderParam;
import com.sustech.campus.alipay.pojo.PayVo;
import com.sustech.campus.alipay.service.OrderService;
import com.sustech.campus.alipay.util.SnowflakeIdWorker;
import com.sustech.campus.database.dao.OrderDao;
import com.sustech.campus.database.dao.ProductDao;
import com.sustech.campus.database.po.Order;
import com.sustech.campus.database.po.Product;
import com.sustech.campus.database.utils.ImgHostUploader;
import io.swagger.annotations.ApiParam;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.SecureRandom;
import java.util.Date;

import static com.sustech.campus.web.utils.ExceptionUtils.asserts;

/**
 * @ClassName PayWebController
 * @Description 支付调用的Controller
 * @Author LN
 * @Date 2023/12/3
 */
@Controller
@Slf4j
public class PayWebController {
    @Autowired
    private AliPayTemplate aliPayTemplate;
    @Autowired
    private OrderService orderService;

    @Resource
    private ProductDao productDao;
    @Resource
    private OrderDao orderDao;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ImgHostUploader.class);

    /**
     * 调用该方法会去到支付宝支付
     * @param order 订单参数
     * @return 返回的是支付宝的页面 - produces = "text/html"
     * @throws AlipayApiException 支付异常
     */
    @GetMapping(value = "/payOrder", produces = "text/html")
    @ResponseBody
    public String payOrder(@ApiParam("返回时跳转的url") @RequestParam String url,
                           @ApiParam("商品ID") @RequestParam Integer productId) throws AlipayApiException {
        // 这里订单号应该是前端传过来的，即orderSn
        // 但是为了Demo方便测试，前端传来的是一个固定的数字(按理说前端应该传的是具体订单号)
        // 支付宝这个订单号又不能重复，所以在这个方法里实际使用了一个雪花算法生成的订单号，即noStr
        Product product = productDao.selectById(productId);
        asserts(product != null, "商品不存在");
        String cdkey = generateCDKey(8);
        String sn = generateCDKey(15);
        Order order = Order.builder()
                .orderSn(sn)
                .productId(productId)
                .amount(product.getAmount())
                .cdkey(cdkey)
                .time(new Date())
                .status(0)
                .build();
        LOGGER.info("订单已生成，即将跳转支付宝");

        PayVo payVo = orderService.getOrderPay(
                sn,
                product.getSubject(),
                product.getBody(),
                String.valueOf(product.getAmount())
        );
        return aliPayTemplate.pay(payVo);
        String noStr = SnowflakeIdWorker.generateId() + "";
        log.info("订单ID：{}", noStr);
        PayVo payVo = orderService.getOrderPay(
                order.getOrderSn(),
                order.getSubject(),
                order.getBody(),
                String.valueOf(order.getAmount())
        );
        return aliPayTemplate.pay(payVo);
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static SecureRandom random = new SecureRandom();
    private static String generateCDKey(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

}
