package sustech.ln.alipay.service;

import sustech.ln.alipay.pojo.Product;

public interface ShoppingCartService {
    boolean add(Product product);

    boolean delete(String number);


}
