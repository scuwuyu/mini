package com.gongsi.mini.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@RestController
public class IndexController {
    /**
     * 默认首页
     */
    @RequestMapping(value = "/")
    public String add(){
        return "I konw you are missing yourself !";
    }

    /**
     * 错误页面
     */
    @RequestMapping(value = "/error/*")
    public String error(HttpServletResponse response){
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        return "I konw you are missing yourself !\n" +
                "错误页面！";
    }

    /** 测试页面 */
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "Welcome to this page !";
    }
}
