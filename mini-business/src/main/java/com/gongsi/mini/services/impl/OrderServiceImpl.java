package com.gongsi.mini.services.impl;

import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.BeanMapper;
import com.gongsi.mini.core.utils.IdGenerator;
import com.gongsi.mini.dao.OrderMapper;
import com.gongsi.mini.entities.Activity;
import com.gongsi.mini.entities.Order;
import com.gongsi.mini.entities.OrderItem;
import com.gongsi.mini.services.*;
import com.gongsi.mini.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
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
    @Autowired
    private AddressService addressService;
    @Autowired
    private GoodsService goodsService;

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
    @Transactional
    public String order(OrderVO vo, UserSessionVO sessionVO){
        Ensure.that(vo.getOrderItemList()).isNotEmpty("商品列表不能为空");
        Activity activity = activityService.selectById(vo.getActivityId());
        Ensure.that(activity).isNotNull("活动不存在");
        addressService.selectById(vo.getAddressId(),sessionVO.getUserId());

        Order order = new Order();
        order.setOrderNumber(IdGenerator.nextId());
        order.setUserId(sessionVO.getUserId());
        order.setSellerId(activity.getUserId());
        order.setActivityId(vo.getActivityId());
        order.setAddressId(vo.getAddressId());
        int result = orderMapper.insertSelective(order);
        Ensure.that(result).isEq(1,"保存订单失败");

        List<GoodsVO> list = goodsService.selectByIds(vo.getOrderItemList().stream()
                .map(OrderItemVO::getGoodsId).collect(Collectors.toList())
        );
        Ensure.that(vo.getOrderItemList().size()).isEq(list.size(),"部分商品已删除，请刷新重试");
        Map<Long,GoodsVO> map = list.stream().collect(Collectors.toMap(GoodsVO::getId,Function.identity()));

        List<OrderItem> orderItems = vo.getOrderItemList().stream().map(item -> {
            OrderItem orderItem = BeanMapper.map(item, OrderItem.class);
            orderItem.setOrderNumber(order.getOrderNumber());
            orderItem.setGoodsName(map.get(item.getGoodsId()).getName());
            orderItem.setPrice(map.get(item.getGoodsId()).getPrice());
            orderItem.setTotalPrice(orderItem.getPrice().multiply(new BigDecimal(item.getQuantity())));

            return orderItem;
        }).collect(Collectors.toList());

        result = orderItemService.batchInsert(orderItems);
        Ensure.that(result==orderItems.size()).isTrue("保存商品列表失败");
        return order.getOrderNumber();
    }
}
