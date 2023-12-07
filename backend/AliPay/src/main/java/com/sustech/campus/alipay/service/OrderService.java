package com.sustech.campus.alipay.service;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.alipay.pojo.PayAsyncVo;
import com.sustech.campus.database.dao.OrderDao;
import com.sustech.campus.database.dao.ProductDao;
import com.sustech.campus.database.po.OrderInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sustech.campus.alipay.pojo.PayVo;
import com.sustech.campus.alipay.pojo.TransactionStatus;

@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;
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
            System.out.println("订单" + outTradeNo + "支付成功");
            OrderInfo order = orderDao.selectOne(
                    new MPJLambdaWrapper<OrderInfo>()
                            .eq(OrderInfo::getOrderSn, outTradeNo)
            );
            order.setStatus(1);
        }
        return "success";
    }


}
