package com.gongsi.mini.services.impl;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.Pagination;
import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.BeanMapper;
import com.gongsi.mini.core.utils.IdGenerator;
import com.gongsi.mini.dao.OrderMapper;
import com.gongsi.mini.entities.Activity;
import com.gongsi.mini.entities.Address;
import com.gongsi.mini.entities.Order;
import com.gongsi.mini.entities.OrderItem;
import com.gongsi.mini.enums.ActivityStatusEn;
import com.gongsi.mini.enums.OrderStatusEn;
import com.gongsi.mini.services.*;
import com.gongsi.mini.vo.*;
import com.gongsi.mini.vo.page.OrderPageVO;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        vo.checkWhenOrder();
        Activity activity = activityService.selectAndCheck(vo.getActivityId());
        Address address = addressService.selectById(vo.getAddressId(),sessionVO.getUserId());

        Order order = new Order();
        order.setOrderNumber(IdGenerator.nextId());
        order.setUserId(sessionVO.getUserId());
        order.setSellerId(activity.getUserId());
        order.setActivityId(vo.getActivityId());
        order.setReceiverName(address.getName());
        order.setReceiverAddress(address.getAddress());
        order.setReceiverMobile(address.getMobile());
        int result = orderMapper.insertSelective(order);
        Ensure.that(result).isEq(1,"保存订单失败");

        List<OrderItem> orderItems = covert(vo.getOrderItemList(),order.getOrderNumber());

        result = orderItemService.batchInsert(orderItems);
        Ensure.that(result==orderItems.size()).isTrue("保存商品列表失败");
        return order.getOrderNumber();
    }

    /** C端用户 编辑订单 */
    @Transactional
    public void edit(OrderVO vo, UserSessionVO sessionVO){
        vo.checkWhenEdit();
        activityService.selectAndCheck(vo.getActivityId());
        Address address = addressService.selectById(vo.getAddressId(),sessionVO.getUserId());

        Order order = orderMapper.selectByOrderNumber(vo.getOrderNumber());
        Ensure.that(order).isNotNull("订单不存在");
        Ensure.that(OrderStatusEn.EXPRESSED.getCode().equals(order.getStatus())).isFalse("订单已发货，不能修改");
        Ensure.that(sessionVO.getUserId().equals(order.getUserId())).isTrue("订单不存在");

        log.info("修改之前的订单order={}", JSON.toJSONString(order));
        Long id = order.getId();
        order = new Order();
        order.setId(id);
        order.setReceiverName(address.getName());
        order.setReceiverAddress(address.getAddress());
        order.setReceiverMobile(address.getMobile());
        int result = orderMapper.updateByPrimaryKeySelective(order);
        Ensure.that(result).isEq(1,"修改订单失败");

        /** 更新订单明细 */
        List<OrderItem> orderItems = covert(vo.getOrderItemList(),vo.getOrderNumber());

        orderItemService.deleteByOrderNumber(vo.getOrderNumber());
        result = orderItemService.batchInsert(orderItems);
        Ensure.that(result==orderItems.size()).isTrue("保存商品列表失败");
    }

    /** 删除订单 */
    public void delete(OrderVO vo, UserSessionVO sessionVO){
        vo.checkWhenDelete();

        Order order = orderMapper.selectByOrderNumber(vo.getOrderNumber());
        Ensure.that(order).isNotNull("订单不存在");
        Ensure.that(OrderStatusEn.EXPRESSED.getCode().equals(order.getStatus())).isFalse("订单已发货，不能删除");
        Ensure.that(sessionVO.getUserId().equals(order.getUserId())).isTrue("订单不存在");
        activityService.selectAndCheck(order.getActivityId());

        log.info("删除订单信息order={}",JSON.toJSONString(order));
        orderMapper.deleteByPrimaryKey(order.getId());

    }

    private List<OrderItem> covert(List<OrderItemVO> orderItemList,String orderNumber){
        List<GoodsVO> list = goodsService.selectByIds(orderItemList.stream()
                .map(OrderItemVO::getGoodsId).collect(Collectors.toList())
        );
        Ensure.that(orderItemList.size()).isEq(list.size(),"部分商品已删除，请刷新重试");
        Map<Long,GoodsVO> map = list.stream().collect(Collectors.toMap(GoodsVO::getId,Function.identity()));

        return orderItemList.stream().map(item -> {
            OrderItem orderItem = BeanMapper.map(item, OrderItem.class);
            orderItem.setOrderNumber(orderNumber);
            orderItem.setGoodsName(map.get(item.getGoodsId()).getName());
            orderItem.setPrice(map.get(item.getGoodsId()).getPrice());
            orderItem.setTotalPrice(orderItem.getPrice().multiply(new BigDecimal(item.getQuantity())));

            return orderItem;
        }).collect(Collectors.toList());
    }



    /** ------------------以下为b端操作------------------------*/

    /** 活动订单列表*/
    public Pagination<OrderVO> activityOrderList(OrderPageVO vo, UserSessionVO user){
        Pagination<OrderVO> pagination = new Pagination<>(vo.getCurrentPage(),vo.getPageSize());
        int count = orderMapper.countActivityOrderList(vo);
        pagination.setTotalCount(count);
        if (count==0){
            return pagination;
        }
        List<OrderVO> list = orderMapper.activityOrderList(vo,pagination);
        pagination.setList(list);
        /** 设置用户信息 */
        Map<String,UserVO> map = userService.selectByIds(new ArrayList<>(list.stream().map(OrderVO::getUserId).collect(Collectors.toSet())));

        list.forEach(item -> item.setBuyerInfo(map.get(item.getUserId())));

        return pagination;
    }

    /** 采购清单 列表*/
    public List<GoodsVO> activityBuyList(OrderPageVO vo, UserSessionVO user){
        return orderMapper.activityBuyList(vo.getActivityId());
    }

    /** 卖家发货 */
    public void express(ExpressVO vo, UserSessionVO user){
        Order order = orderMapper.selectByOrderNumber(vo.getOrderNumber());
        Ensure.that(Objects.nonNull(order)&&order.getSellerId().equals(user.getUserId())).isTrue("订单不存在");

        Ensure.that(OrderStatusEn.WAIT_EXPRESS.getCode().equals(order.getStatus())).isTrue("订单已经发货");

        Long id = order.getId();
        order = new Order();
        order.setId(id);
        order.setStatus(OrderStatusEn.EXPRESSED.getCode());
        order.setExpressNumber(vo.getExpressNumber());
        order.setExpressName(vo.getExpressName());
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
