//package sustech.ln.alipay.service.impl;
//
//import org.springframework.stereotype.Service;
//import sustech.ln.alipay.pojo.Product;
//import sustech.ln.alipay.service.ShoppingCartService;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ShoppingCertImpl implements ShoppingCartService {
//
//    // 购物车
//    @Resource
//    List<Product> shoppingCert = new ArrayList<>();
//    @Override
//    public boolean add(Product product) {
//        shoppingCert.add(product);
//        return false;
//    }
//
//    @Override
//    public boolean delete(String subject) {
//        shoppingCert.removeIf(p -> p.getSubject().equals(subject));
//        return false;
//    }
//}
