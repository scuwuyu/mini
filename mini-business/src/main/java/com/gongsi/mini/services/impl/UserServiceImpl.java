package com.gongsi.mini.services.impl;

import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.BeanMapper;
import com.gongsi.mini.dao.UserMapper;
import com.gongsi.mini.entities.User;
import com.gongsi.mini.services.ActivityService;
import com.gongsi.mini.services.OrderService;
import com.gongsi.mini.services.UserService;
import com.gongsi.mini.vo.MineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 吴宇 on 2018-05-22.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private OrderService orderService;

    public User selectByUserId(String userId){
        return userMapper.selectByUserId(userId);
    }

    /** 我的页面*/
    public MineVO mine(String userId){
        User user = userMapper.selectByUserId(userId);
        Ensure.that(user).isNotNull("用户不存在");
        MineVO mineVO = BeanMapper.map(user,MineVO.class);

        mineVO.setActivityNumber(activityService.countByUserId(userId));
        mineVO.setOrder(orderService.countByUserId(userId));
        return mineVO;
    }
}
