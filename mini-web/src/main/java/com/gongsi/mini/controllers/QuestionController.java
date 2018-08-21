package com.gongsi.mini.controllers;

import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.exception.BusinessException;
import com.gongsi.mini.utils.UserUtil;
import com.gongsi.mini.vo.QuestionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 吴宇 on 2018-08-21.
 */
@Slf4j
@Controller
@RequestMapping("/mini/question")
public class QuestionController {


    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody QuestionVO vo){
        UserUtil.getUser(vo.getKey());
        vo.check();
        switch (vo.getType()){
            case 1:
                return "1、卖家问题：\n" +
                        "\n" +
                        "代购助手的定位：\n" +
                        "代购助手是一款主要面向代购商的订单管理工具\n" +
                        "\n" +
                        "他能够帮助代购者、微商等群体高效的进行订单管理，商品统计，以及订单发货等，让代购者完全无纸化管理自己的代购生意\n" +
                        "\n" +
                        "2、卖家问题：\n" +
                        "首先卖家可以先创建一些基础商品，比如劳力士、SKII等\n" +
                        "目前第一个版本还未支持不同的活动关联不同的商品，后面会考虑支持\n" +
                        "\n" +
                        "然后卖家需要创建一个活动，这个活动可以是这样的：10月1日飞日本\n" +
                        "设置完活动名称、地点、时间、和简介后，创建成功\n" +
                        "之后卖家可以在“我的活动”列表中看到这条活动记录\n" +
                        "\n" +
                        "创建完活动后，将其转发给你的朋友、或者你的微信群，让之前有意下单的客户直接在“代购助手”小程序中下单，生成订单后，卖家就可以在“活动列表”—“活动订单”中看到买家下的这笔订单\n" +
                        "\n" +
                        "卖家可以在采购清单中看到本次活动不同种类的商品需采购的数量统计，根据统计的数据进行相应的采购\n" +
                        "\n" +
                        "卖家可以对订单进行备注，另外当完成采购后，可以点击发货，选择快递进行记录\n" +
                        "\n" +
                        "当卖家想要停止该活动，可以编辑活动将活动状态置为“关闭”状态，这样客户就无法在小程序上下单了";
            case 2:
                return "买家问题：\n" +
                        "买家可以在“我是买家”tab中的“我的订单”列表中看到自己在各个活动中下的所有订单\n" +
                        "\n" +
                        "当买家发现他下错单了，可以取消订单，重新下单";
            default:
                throw new BusinessException("问题类型错误");
        }
    }
}
