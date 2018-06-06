package com.gongsi.mini.vo;

import lombok.Data;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Data
public class UserVO extends BaseVO{
    private String userId;

    private String nickName;

    private String openId;

    private String avatarUrl;
}
