package com.gongsi.mini.controllers.upyun;

import com.gongsi.mini.services.third.UpyunFormApi;
import com.gongsi.mini.services.third.UpyunService;
import com.gongsi.mini.utils.UserUtil;
import com.gongsi.mini.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuyu on 2018/7/25.
 */
@Controller
@RequestMapping("/mini/upyun")
public class UpyunController {

    @Autowired
    private UpyunService upyunService;

    @RequestMapping(value = "/signature",method = RequestMethod.POST)
    @ResponseBody
    public UpyunFormApi add(@RequestBody BaseVO vo){
        UserUtil.getUser(vo.getKey());
        return upyunService.createFormApi();
    }
}
