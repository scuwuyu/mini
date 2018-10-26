package com.gongsi.mini.services.impl;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.utils.DateUtils;
import com.gongsi.mini.dao.AppTokenMapper;
import com.gongsi.mini.entities.AppToken;
import com.gongsi.mini.services.AppTokenService;
import com.gongsi.mini.services.http.WechatAuthService;
import com.gongsi.mini.services.http.base.TokenResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 吴宇 on 2018-10-26.
 */
@Slf4j
@Service
public class AppTokenServiceImpl implements AppTokenService {

    @Autowired
    private AppTokenMapper appTokenMapper;

    /**
     * 刷新所有到期token
     */
    public void updateAllTokens(){
        List<AppToken> list = appTokenMapper.selectListByExpiredTime(
                DateUtils.add(new Date(),10, Calendar.MINUTE));
        if (CollectionUtils.isEmpty(list)){
            return;
        }

        list.forEach(item -> {
            try {
                TokenResult result = WechatAuthService.getToken(item.getAppId(),item.getSecret());
                log.info("result={}",JSON.toJSONString(result));

                AppToken token = new AppToken();
                token.setId(item.getId());
                token.setAccessToken(result.getAccess_token());
                token.setExpiredTime(DateUtils.add(new Date(),result.getExpires_in(),Calendar.SECOND));
                appTokenMapper.updateByPrimaryKeySelective(token);

            }catch (Exception e){
                log.error("刷新token异常item={}", JSON.toJSONString(item));
            }
        });
    }

    public AppToken selectByAppId(String appId){
        return appTokenMapper.selectByAppId(appId);
    }
}
