package com.gongsi.mini.services.http;

import com.gongsi.mini.entities.AppToken;
import com.gongsi.mini.services.http.base.BaseService;
import com.gongsi.mini.services.http.base.TokenResult;
import com.gongsi.mini.vo.auth.AuthVO;

/**
 * Created by 吴宇 on 2018-06-04.
 */
public class WechatAuthService extends BaseService {
    /** jscode 换取 openId 和 session_key*/
    private static final String AUTH_URL = "https://api.weixin.qq.com/sns/jscode2session?" +
            "appid=APPID&secret=APPSECRET&js_code=JSCODE&grant_type=authorization_code";
    /** 刷新tokenurl*/
    private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
            "&appid=APPID&secret=APPSECRET";


    public static WechatAuthResult authByJSCode(AuthVO authVO, AppToken appToken){
        String requestUrl = AUTH_URL.replace("JSCODE",authVO.getCode())
                .replace("APPID",appToken.getAppId()).replace("APPSECRET",appToken.getSecret());
        return exePostJson(requestUrl,null, WechatAuthResult.class);
    }

    public static TokenResult getToken(String appId,String secret){
        String requestUrl = TOKEN_URL.replace("APPID",appId).replace("APPSECRET",secret);
        return exeGetJson(requestUrl,TokenResult.class);
    }
}
