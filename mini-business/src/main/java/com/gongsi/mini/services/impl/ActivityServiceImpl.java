package com.gongsi.mini.services.impl;

import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.BeanMapper;
import com.gongsi.mini.dao.ActivityMapper;
import com.gongsi.mini.entities.Activity;
import com.gongsi.mini.services.ActivityService;
import com.gongsi.mini.vo.ActivityVO;
import com.gongsi.mini.vo.UserSessionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 吴宇 on 2018-05-23.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    /** 新增活动*/
    public Integer add(ActivityVO activityVO, UserSessionVO sessionVO){
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityVO,activity);
        activity.setUserId(sessionVO.getUserId());
        return activityMapper.insert(activity);
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
}
