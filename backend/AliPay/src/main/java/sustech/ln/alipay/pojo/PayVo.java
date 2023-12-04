package sustech.ln.alipay.pojo;

import lombok.Data;

/**
 * @ClassName PayVo
 * @Description 支付信息
 * @Author LN
 * @Date 2023/12/2
 */
@Data
public class PayVo {
    /** 商户订单号-必填 */
    private String outTradeNo;
    /** 订单名称-必填 */
    private String subject;
    /** 付款金额-必填 */
    private String totalAmount;
    /** 商品描述-可空 */
    private String body;
}
