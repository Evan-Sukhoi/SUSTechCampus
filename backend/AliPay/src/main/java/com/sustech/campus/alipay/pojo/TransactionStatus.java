package com.sustech.campus.alipay.pojo;

/**
 * @ClassName TransactionStatus
 * @Description 交易状态(决定是否触发异步通知) - 支付宝官方文档：https://opendocs.alipay.com/open/270/105902
 *              对于 PC 网站支付的交易，在用户支付完成之后，支付宝会根据 API 中商家传入的 notify_url，通过 POST 请求的形式将支付结果作为参数通知到商家系统。
 * @Author LN
 * @Date 2023/12/3
 */
public interface TransactionStatus {
    /**
     * 触发通知的条件
     * TRADE_FINISHED - false（不触发通知）
     * TRADE_SUCCESS - true（触发通知）
     * WAIT_BUYER_PAY - false（不触发通知）
     * TRADE_CLOSED - false（不触发通知）
     */

    /** 交易创建，等待买家付款 */
    String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    /** 未付款交易超时关闭，或支付完成后全额退款 */
    String TRADE_CLOSED = "TRADE_CLOSED";
    /** 交易支付成功 */
    String TRADE_SUCCESS = "TRADE_SUCCESS";
    /** 交易结束，不可退款 */
    String TRADE_FINISHED = "TRADE_FINISHED";


}
