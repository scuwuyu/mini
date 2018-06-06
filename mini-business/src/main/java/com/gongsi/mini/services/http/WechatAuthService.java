package com.gongsi.mini.services.http;

import com.gongsi.mini.services.http.base.BaseService;
import com.gongsi.mini.vo.auth.AuthVO;

/**
 * Created by 吴宇 on 2018-06-04.
 */
public class WechatAuthService extends BaseService {
    /** jscode 换取 openId 和 session_key*/
    private static final String AUTH_URL = "https://api.weixin.qq.com/sns/jscode2session?" +
            "appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

    public static WechatAuthResult authByJSCode(AuthVO authVO){
//        String requestUrl = AUTH_URL.replace("JSCODE",authVO.getCode());
//        return exePostJson(requestUrl,null, WechatAuthResult.class);

        WechatAuthResult wechatAuthResult = new WechatAuthResult();
        wechatAuthResult.setOpenid("openid");
        return wechatAuthResult;
    }
}
