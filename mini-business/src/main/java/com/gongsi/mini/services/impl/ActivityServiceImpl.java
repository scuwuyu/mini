package com.gongsi.mini.services.impl;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.Pagination;
import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.BeanMapper;
import com.gongsi.mini.dao.ActivityMapper;
import com.gongsi.mini.dao.OrderMapper;
import com.gongsi.mini.entities.Activity;
import com.gongsi.mini.enums.ActivityStatusEn;
import com.gongsi.mini.enums.OrderStatusEn;
import com.gongsi.mini.services.ActivityService;
import com.gongsi.mini.services.UserService;
import com.gongsi.mini.vo.ActivityVO;
import com.gongsi.mini.vo.UserSessionVO;
import com.gongsi.mini.vo.UserVO;
import com.gongsi.mini.vo.page.OrderPageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by 吴宇 on 2018-05-23.
 */
@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserService userService;
    // TODO: 2018-08-02 后面移除位置
    @Autowired
    private OrderMapper orderMapper;
    /** 新增活动*/
    public Activity add(ActivityVO activityVO, UserSessionVO sessionVO){
        Activity activity = BeanMapper.map(activityVO,Activity.class);
        activity.setUserId(sessionVO.getUserId());
        activityMapper.insert(activity);

        return activity;
    }

    /** 编辑活动*/
    public void edit(ActivityVO activityVO, UserSessionVO sessionVO){
        Activity record = activityMapper.selectByPrimaryKey(activityVO.getId());
        Ensure.that(record).isNotNull("对应的活动不存在");
        Ensure.that(record.getUserId().equals(sessionVO.getUserId())).isTrue("对应的活动不存在");
        Activity activity = BeanMapper.map(activityVO,Activity.class);
        int result = activityMapper.updateByPrimaryKeySelective(activity);
        Ensure.that(result).isGt(0,"更新失败");
    }

    /** 查询活动次数 */
    public int countByUserId(String userId){
        return activityMapper.countByUserId(userId);
    }

    /** 状态查询活动列表 */
    public Pagination<ActivityVO> selectList(ActivityVO vo, UserSessionVO user){
        Pagination<ActivityVO> pagination = new Pagination<>(vo.getCurrentPage(),vo.getPageSize());
        pagination.setTotalCount(activityMapper.countList(vo,user.getUserId()));

        if (pagination.getTotalCount()==0){
            return pagination;
        }

        pagination.setList(activityMapper.selectList(vo,user.getUserId(),pagination));

        return pagination;
    }

    /** c端 b端用户查看活动*/
    public ActivityVO detail(Long id, UserSessionVO user){
        log.info("查看活动详情user={},id={}", JSON.toJSONString(user),id);
        Activity activity = activityMapper.selectByPrimaryKey(id);
        ActivityVO vo = BeanMapper.map(activity,ActivityVO.class);
        /** 使用活动创建人的信息 */
        Map<String,UserVO> map = userService.selectByIds(Collections.singletonList(activity.getUserId()));

        vo.setUserInfo(map.get(activity.getUserId()));

        /** 查询订单数 */
        OrderPageVO orderPageVO = new OrderPageVO();
        orderPageVO.setActivityId(id);
        vo.setTotalNumber(orderMapper.countActivityOrderList(orderPageVO));
        orderPageVO.setStatus(OrderStatusEn.WAIT_EXPRESS.getCode());
        vo.setWaitExpressNumber(orderMapper.countActivityOrderList(orderPageVO));

        return vo;
    }

    public Activity selectById(Long id){
        return activityMapper.selectByPrimaryKey(id);
    }

    public Activity selectAndCheck(Long activityId){
        Activity activity = selectById(activityId);
        Ensure.that(activity).isNotNull("活动不存在");
        Ensure.that(ActivityStatusEn.END.getCode().equals(activity.getStatus()))
                .isFalse("活动已结束");
        return activity;
    }

    public Map<Long,ActivityVO> selectByIds(ArrayList<Long> activityIds){
        List<ActivityVO> list = activityMapper.selectByIds(activityIds);
        if (CollectionUtils.isEmpty(list)){
            return new HashMap<>();
        }

        return list.stream().collect(Collectors.toMap(ActivityVO::getId, Function.identity()));
    }
}
