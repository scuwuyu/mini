package com.gongsi.mini.services;

import com.gongsi.mini.entities.AppToken;

/**
 * Created by 吴宇 on 2018-10-26.
 */
public interface AppTokenService {
    /**
     * 刷新所有到期token
     */
    void updateAllTokens();

    AppToken selectByAppId(String appId);
}
