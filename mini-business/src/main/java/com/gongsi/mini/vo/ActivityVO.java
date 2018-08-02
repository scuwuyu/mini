package com.gongsi.mini.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.gongsi.mini.core.ensure.Ensure;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

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

    @JSONField(format = "yyyy-MM-dd")
    private Date activityTime;

    private String desc;

    private String address;

    /** 状态：1:进行中 5：结束*/
    private Integer status;
    /** c端查看时,增加用户信息*/
    private UserVO userInfo;

    /** 查询时间范围 */
    private Date startTime;
    private Date endTime;

    private Integer totalNumber;
    private Integer waitExpressNumber;

    public void check(){
        Ensure.that(name).isNotEmpty("活动名称不能为空");
        Ensure.that(address).isNotEmpty("活动地址不能为空");
        Ensure.that(activityTime).isNotNull("活动日期不能为空");
        Ensure.that(activityTime.after(new Date())).isTrue("活动日期不能小于当前时间");
        Ensure.that(Objects.isNull(desc)||desc.length()<1<<8).isTrue("活动简介最多200字");
    }
}
