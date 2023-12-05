package sustech.ln.alipay.service;


import sustech.ln.alipay.pojo.PayAsyncVo;
import sustech.ln.alipay.pojo.PayVo;

import java.util.ArrayList;

/**
 * @ClassName OrderService
 * @Description   订单Service实现接口
 * @Author LN
 * @Date 2023/12/3
 */
public interface OrderService {
    PayVo getOrderPay(String orderSn);

    String handlePayResult(PayAsyncVo vo);


}
