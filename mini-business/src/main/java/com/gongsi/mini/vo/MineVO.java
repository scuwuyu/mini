package com.gongsi.mini.vo;

import lombok.Data;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Data
public class MineVO {
    /** 昵称*/
    private String nickname;
    /** 头像 */
    private String picture;
    /** 是不是卖家 */
    private Boolean isSeller;

    /** 我发起的活动数量*/
    private int activityNumber;

    /** 下单数量*/
    private int order;


}
