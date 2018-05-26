package com.gongsi.mini.dao;

import com.gongsi.mini.entities.OrderItem;
import com.gongsi.mini.vo.OrderItemVO;

import java.util.List;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItemVO> selectByOrderNumber(String orderNumber);
}