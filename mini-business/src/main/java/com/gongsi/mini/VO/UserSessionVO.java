package com.gongsi.mini.vo;

import java.io.Serializable;

/**
 * 用户回话信息保存
 * Created by 吴宇 on 2018-05-23.
 */
public class UserSessionVO implements Serializable{

    private String UserId;

    private String openId;


    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
