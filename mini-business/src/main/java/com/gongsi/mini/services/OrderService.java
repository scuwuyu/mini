package com.gongsi.mini.services;

import com.gongsi.mini.vo.OrderVO;
import com.gongsi.mini.vo.UserSessionVO;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
public interface OrderService {
    /** 统计订单数量*/
    int countByUserId(String userId);
    /** 查询订单列表 */
    List<OrderVO> selectList(OrderVO vo, UserSessionVO user);
}
