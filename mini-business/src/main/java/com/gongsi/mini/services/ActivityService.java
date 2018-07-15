package com.gongsi.mini.services;

import com.gongsi.mini.entities.Activity;
import com.gongsi.mini.vo.ActivityVO;
import com.gongsi.mini.vo.UserSessionVO;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-23.
 */
public interface ActivityService {
    /** 新增活动*/
    Integer add(ActivityVO activityVO, UserSessionVO sessionVO);

    /** 编辑活动*/
    void edit(ActivityVO activityVO, UserSessionVO sessionVO);
    /** 查询活动次数 */
    int countByUserId(String userId);
    /** 状态查询活动列表 */
    List<ActivityVO> selectList(ActivityVO vo, UserSessionVO user);

    ActivityVO detail(Long id, UserSessionVO user);

    Activity selectById(Long id);
}
