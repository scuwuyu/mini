package com.gongsi.mini.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by 吴宇 on 2018-05-23.
 */
@Data
public class ActivityVO extends BaseVO{
    /** 活动id */
    private Long id;

    private String name;

    private String picture;

    private Double longitude;

    private Double latitude;

    private Date activityTime;

    private String desc;
    /** 状态：1:进行中 5：结束*/
    private Integer status;
    /** c端查看时,增加用户信息*/
    private UserVO userInfo;
}
