package com.gongsi.mini.vo;

import com.gongsi.mini.entities.OrderItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Data
public class OrderVO extends BaseVO {

    private String orderNumber;

    private String userId;

    private String sellerId;

    private Long activityId;

    /** 创建时间*/
    private Date createTime;

    /** 订单状态：1:待发货 5：已发货*/
    private Integer status;

    /** 商品详情列表 */
    private List<OrderItemVO> orderItemList;
    /** 卖家信息 */
    private UserVO sellerInfo;

    /** 活动信息*/
    private ActivityVO activityInfo;

    /** 下单地址*/
    private Long addressId;

}
