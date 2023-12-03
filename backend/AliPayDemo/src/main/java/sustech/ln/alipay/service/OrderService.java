package sustech.ln.alipay.service;

import sustech.ln.alipay.pojo.PayAsyncVo;
import sustech.ln.alipay.pojo.PayVo;

/**
 * @ClassName OrderService
 * @Description
 * @Author LN
 * @Date 2023/12/3
 */
public interface OrderService {
    PayVo getOrderPay(String orderSn);

    String handlePayResult(PayAsyncVo vo);
}
