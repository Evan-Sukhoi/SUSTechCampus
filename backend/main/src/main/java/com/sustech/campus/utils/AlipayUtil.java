//package com.sustech.campus.utils;
//
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import com.alipay.api.*;
//import com.alipay.api.domain.AlipayTradePrecreateModel;
//import com.alipay.api.domain.AlipayTradeRefundModel;
//import com.alipay.api.request.AlipayTradePrecreateRequest;
//import com.alipay.api.request.AlipayTradeRefundRequest;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Date;
//
//import static com.sustech.campus.utils.EncodeUtils.encodeBase64;
//
//
//@Component
//public class AlipayUtil {
//
//	@Value("${alipay.notify-url}")
//	private String notifyUrl;
//	@Resource
//	private AlipayClient alipayClient;
//
//	/**
//	 * @return 支付二维码的Base64编码   (可以将其嵌入到网页中或者用于其他需要显示支付二维码的场景)
//	 */
//	public String getPayQrCode(AlipayTradePrecreateModel payInfo){
//		AlipayTradePrecreateRequest payRequest = new AlipayTradePrecreateRequest();
//		payRequest.setBizModel(payInfo); //设置支付信息
//		payRequest.setNotifyUrl(notifyUrl); //异步回调地址
//		String qrCodeUrl = getResponse(payRequest)
//						  .getByPath("alipay_trade_precreate_response.qr_code", String.class);
//
//		return encodeBase64(qrCodeUrl);
//	}
//
//	/**
//	 * @return 退款成功时间
//	 */
//	public Date refund(AlipayTradeRefundModel refundInfo){
//		AlipayTradeRefundRequest refundRequest = new AlipayTradeRefundRequest();
//		refundRequest.setBizModel(refundInfo);
//		JSONObject response = getResponse(refundRequest)
//							 .get("alipay_trade_refund_response", JSONObject.class);
//		if(response.getStr("msg").equals("Success")){
//			return response.get("gmt_refund_pay",Date.class);
//		}else{throw new RuntimeException("退款失败");}
//	}
//
//	/**
//	 * @return Response的body的 {@link JSONObject}
//	 */
//	@SneakyThrows(AlipayApiException.class)
//	public <T extends AlipayResponse> JSONObject getResponse(AlipayRequest<T> request){
//		return JSONUtil.parseObj(alipayClient.execute(request).getBody());
//	}
//}
