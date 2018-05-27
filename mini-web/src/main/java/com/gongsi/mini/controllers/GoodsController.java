package com.gongsi.mini.controllers;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.Pagination;
import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.services.GoodsService;
import com.gongsi.mini.utils.UserUtil;
import com.gongsi.mini.vo.AddressVO;
import com.gongsi.mini.vo.GoodsVO;
import com.gongsi.mini.vo.page.GoodsPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Slf4j
@Controller
@RequestMapping("/mini/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody GoodsVO vo){
        log.info("新增商品vo={}", JSON.toJSONString(vo));
        goodsService.add(vo, UserUtil.getUser(vo.getKey()));
        return "ok";
    }

    /** 我的商品列表 */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Pagination<GoodsVO> list(@RequestBody GoodsPageVO vo){
        log.info("商品分页查询vo={}", JSON.toJSONString(vo));
        return goodsService.selectList(vo, UserUtil.getUser(vo.getKey()));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestBody GoodsVO vo){
        log.info("删除商品vo={}", JSON.toJSONString(vo));
        goodsService.delete(vo.getGoodsIds(), UserUtil.getUser(vo.getKey()));
        return "ok";
    }
}
