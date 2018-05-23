package com.gongsi.mini.controllers;

import com.gongsi.mini.services.ActivityService;
import com.gongsi.mini.utils.UserUtil;
import com.gongsi.mini.vo.ActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 吴宇 on 2018-05-23.
 */
@Controller
@RequestMapping("/mini/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody ActivityVO vo){
        Integer result = activityService.add(vo, UserUtil.getUser(vo.getKey()));
        return "ok";
    }
}
