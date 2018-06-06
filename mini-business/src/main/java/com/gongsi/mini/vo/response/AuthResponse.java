package com.gongsi.mini.vo.response;

import lombok.Data;

/**
 * Created by 吴宇 on 2018-06-05.
 */
@Data
public class AuthResponse {
    private String key;
    /** 是否需要获取用户信息授权 */
    private boolean authInfo;

    public AuthResponse(String key,boolean authInfo) {
        this.authInfo = authInfo;
        this.key = key;
    }
}
