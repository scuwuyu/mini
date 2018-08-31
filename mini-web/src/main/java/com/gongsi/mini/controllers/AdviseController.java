package com.gongsi.mini.controllers;

import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.BeanMapper;
import com.gongsi.mini.entities.Activity;
import com.gongsi.mini.entities.Advise;
import com.gongsi.mini.services.AdviseService;
import com.gongsi.mini.utils.UserUtil;
import com.gongsi.mini.vo.ActivityVO;
import com.gongsi.mini.vo.AdviseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 吴宇 on 2018-08-31.
 */
@Controller
@RequestMapping("/mini/advise")
public class AdviseController {

    @Autowired
    private AdviseService adviseService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody AdviseVO vo){
        vo.check();

        Advise advise = BeanMapper.map(vo, Advise.class);
        advise.setUserId(UserUtil.getUser(vo.getKey()).getUserId());
        adviseService.add(advise);
        return "ok";
    }
}
