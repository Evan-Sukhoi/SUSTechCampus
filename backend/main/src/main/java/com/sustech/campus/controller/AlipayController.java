//package com.sustech.campus.controller;
//
//import com.sustech.campus.utils.AlipayUtil;
//import com.alipay.api.domain.AlipayTradePrecreateModel;
//import com.alipay.api.domain.AlipayTradeRefundModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//@RestController
//@RequestMapping("/alipay")
//public class AlipayController {
//
//    @Autowired
//    private AlipayUtil alipayUtil;
//
//    @PostMapping("/pay")
//    public ResponseEntity<String> getPayQrCode() {
//        try {
//            // Create an instance of AlipayTradePrecreateModel and set random values for demonstration
//            AlipayTradePrecreateModel payInfo = new AlipayTradePrecreateModel();
//
//            // Set a random outTradeNo (you may want to generate a unique ID in a real-world scenario)
//            payInfo.setOutTradeNo("ORDER" + System.currentTimeMillis());
//
//            // Set a random totalAmount (you may want to calculate this based on your business logic)
//            payInfo.setTotalAmount(String.format("%.2f", Math.random() * 100));
//
//            // Set a random subject for demonstration
//            payInfo.setSubject("Random Payment");
//
//            // Set other properties as needed...
//
//            // Call the AlipayUtil method to get the payment QR code
//            String qrCodeBase64 = alipayUtil.getPayQrCode(payInfo);
//
//            // Return the Base64-encoded QR code in the response
//            return ResponseEntity.ok(qrCodeBase64);
//        } catch (Exception e) {
//            // Handle exceptions and return an error response
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating QR code");
//        }
//    }
//
//
//    @PostMapping("/refund")
//    public Date refund(@RequestBody AlipayTradeRefundModel refundInfo) {
//        // Call the AlipayUtil method to process the refund
//        return alipayUtil.refund(refundInfo);
//    }
//}
//
//
//
