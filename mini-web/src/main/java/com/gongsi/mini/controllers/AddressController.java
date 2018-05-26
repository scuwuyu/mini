package com.gongsi.mini.controllers;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.services.AddressService;
import com.gongsi.mini.utils.UserUtil;
import com.gongsi.mini.vo.ActivityVO;
import com.gongsi.mini.vo.AddressVO;
import com.gongsi.mini.vo.BaseVO;
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
@RequestMapping("/mini/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody AddressVO vo){
        log.info("新增收货地址vo={}", JSON.toJSONString(vo));
        addressService.add(vo, UserUtil.getUser(vo.getKey()));
        return "ok";
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public List<AddressVO> list(@RequestBody BaseVO vo){
        log.info("收货地址列表vo={}", JSON.toJSONString(vo));
        return addressService.list(UserUtil.getUser(vo.getKey()));
    }

    @RequestMapping(value = "/set/def",method = RequestMethod.POST)
    @ResponseBody
    public String setDef(@RequestBody AddressVO vo){
        log.info("设置默认地址vo={}", JSON.toJSONString(vo));
        addressService.setDef(vo.getId(),UserUtil.getUser(vo.getKey()));
        return "ok";
    }
}
