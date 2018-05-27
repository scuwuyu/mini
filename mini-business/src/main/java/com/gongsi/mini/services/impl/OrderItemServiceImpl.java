package com.gongsi.mini.services.impl;

import com.gongsi.mini.dao.OrderItemMapper;
import com.gongsi.mini.entities.OrderItem;
import com.gongsi.mini.services.OrderItemService;
import com.gongsi.mini.vo.OrderItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    /** 查询订单商品列表 */
    public List<OrderItemVO> selectByOrderNumber(String orderNumber){
        return orderItemMapper.selectByOrderNumber(orderNumber);
    }

    public int batchInsert(List<OrderItem> orderItems){
        return orderItemMapper.batchInsert(orderItems);
    }
}
