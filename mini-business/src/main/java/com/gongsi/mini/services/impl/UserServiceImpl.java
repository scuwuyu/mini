package com.gongsi.mini.services.impl;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.BeanMapper;
import com.gongsi.mini.core.utils.IdGenerator;
import com.gongsi.mini.dao.UserMapper;
import com.gongsi.mini.entities.User;
import com.gongsi.mini.services.ActivityService;
import com.gongsi.mini.services.OrderService;
import com.gongsi.mini.services.UserService;
import com.gongsi.mini.utils.UrlCoderUtils;
import com.gongsi.mini.vo.MineVO;
import com.gongsi.mini.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by 吴宇 on 2018-05-22.
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private OrderService orderService;

    public User selectByUserId(String userId){
        User user = userMapper.selectByUserId(userId);
        if (Objects.nonNull(user)){
            user.setNickName(UrlCoderUtils.decode(user.getNickName()));
        }

        return user;
    }

    /** 我的页面*/
    public MineVO mine(String userId){
        User user = selectByUserId(userId);
        Ensure.that(user).isNotNull("用户不存在");
        MineVO mineVO = BeanMapper.map(user,MineVO.class);

        mineVO.setActivityNumber(activityService.countByUserId(userId));
        mineVO.setOrder(orderService.countByUserId(userId));
        return mineVO;
    }

    /** 查询用户头像*/
    public Map<String,UserVO> selectByIds(List<String> userIds){
        List<UserVO> list = userMapper.selectByIds(userIds);
        if (list.isEmpty()){
            return new HashMap<>();
        }

        return list.stream().map(item -> {
            item.setNickName(UrlCoderUtils.decode(item.getNickName()));
            return item;
        }).collect(Collectors.toMap(UserVO::getUserId, Function.identity()));
    }

    /** 如果不存在则创建*/
    public synchronized User selectByOpenId(String openId){
        User user = selectByUserId(openId);

        if (Objects.isNull(user)){
            user = new User();
            user.setUserId(IdGenerator.nextId());
            user.setOpenId(openId);
            userMapper.insertSelective(user);
        }
        return user;
    }
    /** 更新用户头像信息*/
    public void updateByUserId(UserVO vo){
        log.info("更新用户信息vo={}", JSON.toJSONString(vo));
        User user = new User();
        user.setUserId(vo.getUserId());
        user.setNickName(UrlCoderUtils.encode(vo.getNickName()));
        user.setAvatarUrl(vo.getAvatarUrl());

        int result = userMapper.updateByUserIdSelective(user);
        Ensure.that(result).isGt(0,"更新用户信息失败");
    }
}
