package com.gongsi.mini.controllers;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.services.OrderService;
import com.gongsi.mini.utils.UserUtil;
import com.gongsi.mini.vo.ActivityVO;
import com.gongsi.mini.vo.OrderVO;
import com.gongsi.mini.vo.UserSessionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Slf4j
@Controller
@RequestMapping("/mini/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /** 下单 */
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    @ResponseBody
    public String order(@RequestBody OrderVO vo){
        UserSessionVO sessionVO = UserUtil.getUser(vo.getKey());
        log.info("下单信息vo={},sessionVO={}", JSON.toJSONString(vo),JSON.toJSONString(sessionVO));
        return orderService.order(vo, sessionVO);
    }

    /** 我下的订单列表 */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public List<OrderVO> list(@RequestBody OrderVO vo){
        Ensure.that(vo.getStatus()).isNotNull("订单状态不能为空");
        return orderService.selectList(vo, UserUtil.getUser(vo.getKey()));
    }

    /** 订单详情 */
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ResponseBody
    public OrderVO detail(@RequestBody OrderVO vo){
        Ensure.that(vo.getOrderNumber()).isNotNull("订单状态不能为空");
        return orderService.detail(vo.getOrderNumber(), UserUtil.getUser(vo.getKey()));
    }

    /** 活动订单 列表*/

}
