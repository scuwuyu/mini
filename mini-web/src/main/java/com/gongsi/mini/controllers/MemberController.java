package com.gongsi.mini.controllers;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.Pagination;
import com.gongsi.mini.services.MemberTypeService;
import com.gongsi.mini.utils.UserUtil;
import com.gongsi.mini.vo.GoodsVO;
import com.gongsi.mini.vo.MemberTypeVO;
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
 * 会员套餐
 * Created by 吴宇 on 2018-06-03.
 */
@Slf4j
@Controller
@RequestMapping("/mini/member")
public class MemberController {

    @Autowired
    private MemberTypeService memberTypeService;

    /** 会员套餐列表 */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public List<MemberTypeVO> list(@RequestBody MemberTypeVO vo){
        log.info("会员购买vo={}", UserUtil.getUser(vo.getKey()));
        return memberTypeService.selectList();
    }
}
