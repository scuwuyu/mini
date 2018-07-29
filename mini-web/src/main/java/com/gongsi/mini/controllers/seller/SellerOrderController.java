package com.gongsi.mini.controllers.seller;

import com.gongsi.mini.core.Pagination;
import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.services.OrderService;
import com.gongsi.mini.utils.UserUtil;
import com.gongsi.mini.vo.ExpressVO;
import com.gongsi.mini.vo.GoodsVO;
import com.gongsi.mini.vo.OrderVO;
import com.gongsi.mini.vo.page.OrderPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * b端订单管理
 * Created by 吴宇 on 2018-05-26.
 */
@Slf4j
@Controller
@RequestMapping("/mini/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    /** 活动订单 列表*/
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Pagination<OrderVO> list(@RequestBody OrderPageVO vo){
        Ensure.that(vo.getActivityId()).isNotNull("活动id不能为空");
        return orderService.activityOrderList(vo, UserUtil.getUser(vo.getKey()));
    }

    /** 采购清单 列表*/
    @RequestMapping(value = "/buy/list",method = RequestMethod.POST)
    @ResponseBody
    public List<GoodsVO> buyList(@RequestBody OrderPageVO vo){
        Ensure.that(vo.getActivityId()).isNotNull("活动id不能为空");
        return orderService.activityBuyList(vo, UserUtil.getUser(vo.getKey()));
    }

    /** 订单详情 */
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ResponseBody
    public OrderVO detail(@RequestBody OrderVO vo){
        vo.checkOrderNumber();
        return orderService.detailSeller(vo.getOrderNumber(), UserUtil.getUser(vo.getKey()));
    }

    /** 卖家备注 */
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public String comment(@RequestBody OrderVO vo){
        orderService.comment(vo, UserUtil.getUser(vo.getKey()));
        return "ok";
    }

    /** 卖家发货 */
    @RequestMapping(value = "/express",method = RequestMethod.POST)
    @ResponseBody
    public String express(@RequestBody ExpressVO vo){
        vo.check();
        orderService.express(vo, UserUtil.getUser(vo.getKey()));
        return "ok";
    }


}
