package com.gongsi.mini.vo;

import java.io.Serializable;

/**
 * Created by wuyu on 2018/5/28.
 */
public class UserSessionVO implements Serializable{
    private String userId;

    private String openId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
