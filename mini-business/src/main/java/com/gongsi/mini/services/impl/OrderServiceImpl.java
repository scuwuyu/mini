package com.gongsi.mini.services.impl;

import com.gongsi.mini.dao.OrderMapper;
import com.gongsi.mini.services.OrderService;
import com.gongsi.mini.services.UserService;
import com.gongsi.mini.vo.OrderVO;
import com.gongsi.mini.vo.UserSessionVO;
import com.gongsi.mini.vo.UserVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
}
