package com.gongsi.mini.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Data
public class OrderItemVO {

    private String orderNumber;

    private Long goodsId;

    private String goodsName;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;
    /** 商品图片*/
    private String picture;
}
