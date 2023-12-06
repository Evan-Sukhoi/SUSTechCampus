package sustech.ln.alipay.service;


import org.springframework.stereotype.Service;
import sustech.ln.alipay.pojo.PayAsyncVo;
import sustech.ln.alipay.pojo.PayVo;
import sustech.ln.alipay.pojo.TransactionStatus;

import java.util.ArrayList;

@Service
public class OrderService {
    public PayVo getOrderPay(String orderSn, String subject, String body, String amount) {
        // 模拟一个订单
        PayVo payVo = new PayVo();
        // 订单号
        payVo.setOutTradeNo(orderSn);
        payVo.setSubject(subject);
        payVo.setBody(body);
        payVo.setTotalAmount(amount);   // 订单价格
        return payVo;
    }

    /**
     * 处理支付宝支付结果
     *
     * @param vo 支付信息
     * @return 返回success
     */
    public String handlePayResult(PayAsyncVo vo) {
        // 可以将vo里的相关支付信息保存到数据库....
        // 判断是否支付成功 - 获取状态，只有以下两种状态是支付成功了
        if (TransactionStatus.TRADE_SUCCESS.equals(vo.getTrade_status()) || TransactionStatus.TRADE_FINISHED.equals(vo.getTrade_status())) {
            // 支付成功状态 - 可以根据订单号修改数据库里订单状态为已支付....
            String outTradeNo = vo.getOut_trade_no();
        }
        return "success";
    }


}
