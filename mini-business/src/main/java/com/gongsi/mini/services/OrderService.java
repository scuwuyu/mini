package com.gongsi.mini.services;

import com.gongsi.mini.core.Pagination;
import com.gongsi.mini.vo.ExpressVO;
import com.gongsi.mini.vo.GoodsVO;
import com.gongsi.mini.vo.OrderVO;
import com.gongsi.mini.vo.UserSessionVO;
import com.gongsi.mini.vo.page.OrderPageVO;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
public interface OrderService {
    /** 统计订单数量*/
    int countByUserId(String userId);
    /** 查询订单列表 */
    List<OrderVO> selectList(OrderVO vo, UserSessionVO user);

    /** 订单详情 */
    OrderVO detail(String orderNumber, UserSessionVO user);

    /** 下单，返回订单号 */
    String order(OrderVO vo, UserSessionVO sessionVO);

    /** 编辑订单 */
    void edit(OrderVO vo, UserSessionVO sessionVO);

    /** 删除订单 */
    void delete(OrderVO vo, UserSessionVO sessionVO);

    /** ------------------以下为b端操作------------------------*/

    /** 活动订单列表*/
    Pagination<OrderVO> activityOrderList(OrderPageVO vo, UserSessionVO user);

    /** 采购清单 列表*/
    List<GoodsVO> activityBuyList(OrderPageVO vo, UserSessionVO user);

    /** 卖家发货 */
    void express(ExpressVO vo, UserSessionVO user);
}
