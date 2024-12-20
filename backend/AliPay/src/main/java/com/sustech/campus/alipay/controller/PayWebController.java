package com.sustech.campus.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.sustech.campus.alipay.config.AliPayTemplate;
import com.sustech.campus.alipay.pojo.PayVo;
import com.sustech.campus.alipay.service.OrderService;
import com.sustech.campus.alipay.util.SnowflakeIdWorker;
import com.sustech.campus.database.dao.OrderDao;
import com.sustech.campus.database.dao.ProductDao;
import com.sustech.campus.database.po.OrderInfo;
import com.sustech.campus.database.po.Product;
import io.swagger.annotations.ApiParam;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.sustech.campus.web.utils.ExceptionUtils.asserts;

/**
 * @ClassName PayWebController
 * @Description 支付调用的Controller
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

    /**
     * 调用该方法会去到支付宝支付
     *
     * @return 返回的是支付宝的页面 - produces = "text/html"
     * @throws AlipayApiException 支付异常
     */
    @GetMapping(value = "/payOrder", produces = "text/html")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Object payOrder(@ApiParam("商品ID") @RequestParam Integer productId) {
        try {
            Product product = productDao.selectById(productId);
            asserts(product != null, "商品不存在");
            String cdkey = generateCDKey(12);
            Long sn = SnowflakeIdWorker.generateId();
            log.info("订单ID：{}", sn);

            OrderInfo order = OrderInfo.builder()
                    .orderSn(sn)
                    .productId(productId)
                    .amount(product.getAmount())
                    .cdkey(cdkey)
                    .time(new Date())
                    .status(0)
                    .build();
            orderDao.insert(order);

            PayVo payVo = orderService.getOrderPay(
                    String.valueOf(sn),
                    product.getSubject(),
                    product.getBody(),
                    String.valueOf(product.getAmount())
            );
            return aliPayTemplate.pay(payVo);
        } catch (AlipayApiException e) {
            return ResponseEntity.accepted().body(e.getMessage());
        }
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static SecureRandom random = new SecureRandom();

    private static String generateCDKey(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            if (i % 4 == 0 && i != 0) {
                sb.append("-");
            }
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

}
