package sustech.ln.alipay.controller;

import com.alipay.api.AlipayApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sustech.ln.alipay.config.AliPayTemplate;
import sustech.ln.alipay.model.OrderParam;
import sustech.ln.alipay.pojo.PayVo;
import sustech.ln.alipay.service.OrderService;
import sustech.ln.alipay.util.SnowflakeIdWorker;

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

    /**
     * 调用该方法会去到支付宝支付
     * @param order 订单参数
     * @return 返回的是支付宝的页面 - produces = "text/html"
     * @throws AlipayApiException 支付异常
     */
    @GetMapping(value = "/payOrder", produces = "text/html")
    @ResponseBody
    public String payOrder(@RequestBody @Validated OrderParam order) throws AlipayApiException {
        // 这里订单号应该是前端传过来的，即orderSn
        // 但是为了Demo方便测试，前端传来的是一个固定的数字(按理说前端应该传的是具体订单号)
        // 支付宝这个订单号又不能重复，所以在这个方法里实际使用了一个雪花算法生成的订单号，即noStr
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

}
