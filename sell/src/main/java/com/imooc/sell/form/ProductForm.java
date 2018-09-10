package com.imooc.sell.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductForm {


    private String productId;

    /** 商品名称 */
    private String productName;

    /** 商品单价 */
    private BigDecimal productPrice;

    /** 商品库存 */
    private Integer productStock;

    /** 商品描述 */
    private String productDescription;

    /** 小图是链接地址 */
    private String productIcon;


    /** 类目编号 */
    private Integer categoryType;

}
