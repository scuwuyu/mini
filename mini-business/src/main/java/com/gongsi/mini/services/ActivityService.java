package com.gongsi.mini.services;

import com.gongsi.mini.vo.ActivityVO;
import com.gongsi.mini.vo.UserSessionVO;

/**
 * Created by 吴宇 on 2018-05-23.
 */
public interface ActivityService {
    /** 新增活动*/
    Integer add(ActivityVO activityVO, UserSessionVO sessionVO);

    /** 编辑活动*/
    void edit(ActivityVO activityVO, UserSessionVO sessionVO);
}
