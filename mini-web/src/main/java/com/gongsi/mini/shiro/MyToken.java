package com.gongsi.mini.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 登录令牌
 * Created by 吴宇 on 2018-06-05.
 */
public class MyToken extends UsernamePasswordToken {
    private String userId;

    public MyToken(String userId){
        super();
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
