package com.gongsi.mini.services.impl;

import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.BeanMapper;
import com.gongsi.mini.dao.OrderMapper;
import com.gongsi.mini.entities.Activity;
import com.gongsi.mini.entities.Order;
import com.gongsi.mini.services.ActivityService;
import com.gongsi.mini.services.OrderItemService;
import com.gongsi.mini.services.OrderService;
import com.gongsi.mini.services.UserService;
import com.gongsi.mini.vo.ActivityVO;
import com.gongsi.mini.vo.OrderVO;
import com.gongsi.mini.vo.UserSessionVO;
import com.gongsi.mini.vo.UserVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private OrderItemService orderItemService;

    /** 统计订单数量*/
    public int countByUserId(String userId){
        return orderMapper.countByUserId(userId);
    }

    /** 查询订单列表 */
    public List<OrderVO> selectList(OrderVO vo, UserSessionVO user){
        List<OrderVO> list =  orderMapper.selectList(vo.getStatus(),user.getUserId());
        if (CollectionUtils.isEmpty(list)){
            return list;
        }
        /** 查询卖家信息*/
        Set<String> sellerIds = list.stream().map(OrderVO::getSellerId).collect(Collectors.toSet());
        Map<String,UserVO> map = userService.selectByIds(new ArrayList<>(sellerIds));

        list.forEach(item -> item.setSellerInfo(map.get(item.getSellerId())));

        return list;
    }

    /** 订单详情 */
    public OrderVO detail(String orderNumber, UserSessionVO user){
        Order order = orderMapper.selectByOrderNumber(orderNumber);
        Ensure.that(order).isNotNull("订单不存在");
        Ensure.that(order.getUserId().equals(user.getUserId())).isNotNull("订单不存在");

        OrderVO orderVO = BeanMapper.map(order,OrderVO.class);
        /** 卖家信息*/
        Map<String,UserVO> map = userService.selectByIds(Collections.singletonList(order.getSellerId()));
        orderVO.setSellerInfo(map.get(order.getSellerId()));

        /** 活动信息*/
        Activity activity = activityService.selectById(order.getActivityId());
        orderVO.setActivityInfo(BeanMapper.map(activity, ActivityVO.class));

        /** 商品信息*/
        orderVO.setOrderItemList(orderItemService.selectByOrderNumber(orderNumber));

        return orderVO;
    }

    /** 下单，返回订单号 */
    public String order(OrderVO vo, UserSessionVO sessionVO){
        return null;
    }
}
