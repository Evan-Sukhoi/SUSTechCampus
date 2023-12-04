package sustech.ln.alipay.pojo;


import lombok.Data;

/**
 * @ClassName Product
 * @Description 商品信息
 * @Author LN
 * @Date 2023/12/2
 */

@Data
public class Product {
    /** 商品编号-必填 */
    private String id;
    /** 商品名称-必填 */
    private String subject;
    /** 商品金额-必填 */
    private String amount;
    /** 商品兑换码 -必填 */
    private String CDKEK;
    /** 商品商店 -必填 */
    private String shop;
    /** 商品描述-可空 */
    private String body;
}
