package com.gongsi.mini.controllers;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.utils.BeanMapper;
import com.gongsi.mini.core.utils.IdGenerator;
import com.gongsi.mini.entities.User;
import com.gongsi.mini.services.UserService;
import com.gongsi.mini.services.http.WechatAuthResult;
import com.gongsi.mini.services.http.WechatAuthService;
import com.gongsi.mini.shiro.MyToken;
import com.gongsi.mini.utils.UserUtil;
import com.gongsi.mini.vo.UserSessionVO;
import com.gongsi.mini.vo.auth.AuthVO;
import com.gongsi.mini.vo.response.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 吴宇 on 2018-06-04.
 */
@Slf4j
@Controller
@RequestMapping("/mini/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    /**
     * openId 注册用户信息
     * @param vo
     * @return
     */
    @RequestMapping(value = "/mine",method = RequestMethod.POST)
    @ResponseBody
    public AuthResponse add(@RequestBody AuthVO vo){
        WechatAuthResult wechatAuthResult = WechatAuthService.authByJSCode(vo);
        User user = userService.selectByOpenId(wechatAuthResult.getOpenid());

        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new MyToken(user.getUserId()));
        } catch (Exception e) {
            log.error("用户登录错误:",e.getMessage(),e);
        }
        AuthResponse response = new AuthResponse(IdGenerator.nextId(),StringUtils.isEmpty(user.getAvatarUrl()));
        log.info("user={}", JSON.toJSONString(user));
        UserUtil.saveUser(response.getKey(), BeanMapper.map(user,UserSessionVO.class));
        return response;
    }
}
