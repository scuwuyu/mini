package com.gongsi.mini.services;

import com.gongsi.mini.entities.OrderItem;
import com.gongsi.mini.vo.OrderItemVO;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
public interface OrderItemService {
    /** 查询订单商品列表 */
    List<OrderItemVO> selectByOrderNumber(String orderNumber);

    int batchInsert(List<OrderItem> orderItems);

    int deleteByOrderNumber(String orderNumber);
}
