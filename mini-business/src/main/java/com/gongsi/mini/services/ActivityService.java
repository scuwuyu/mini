package com.gongsi.mini.services;

import com.gongsi.mini.core.Pagination;
import com.gongsi.mini.entities.Activity;
import com.gongsi.mini.vo.ActivityVO;
import com.gongsi.mini.vo.UserSessionVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    Pagination<ActivityVO> selectList(ActivityVO vo, UserSessionVO user);

    ActivityVO detail(Long id, UserSessionVO user);

    Activity selectById(Long id);

    Activity selectAndCheck(Long activityId);

    Map<Long,ActivityVO> selectByIds(ArrayList<Long> activityIds);
}
