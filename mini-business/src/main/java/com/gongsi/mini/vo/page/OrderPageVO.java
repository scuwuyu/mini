package com.gongsi.mini.vo.page;

import lombok.Data;

/**
 * 活动已下单列表
 * Created by 吴宇 on 2018-06-03.
 */
@Data
public class OrderPageVO extends PageVO {

    private Long activityId;

    private Integer status;

}
