package com.gongsi.mini.controllers;

import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.services.OrderService;
import com.gongsi.mini.utils.UserUtil;
import com.gongsi.mini.vo.ActivityVO;
import com.gongsi.mini.vo.OrderVO;
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
@Controller
@RequestMapping("/mini/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public List<OrderVO> list(@RequestBody OrderVO vo){
        Ensure.that(vo.getStatus()).isNotNull("订单状态不能为空");
        return orderService.selectList(vo, UserUtil.getUser(vo.getKey()));
    }
}
