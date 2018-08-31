package com.gongsi.mini.utils;

import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.vo.UserSessionVO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;

/**
 * Created by 吴宇 on 2018-05-23.
 */
public class UserUtil {

    public static void saveUser(String key,UserSessionVO userSession){
        SecurityUtils.getSubject().getSession().setAttribute(key,userSession);
    }

    public static UserSessionVO getUser(String key){
        return getRealUser(key);
//        return getTestUser(key);
    }

    private static UserSessionVO getRealUser(String key){
        Ensure.that(key).isNotEmpty("用户key不能为空");
        UserSessionVO vo = (UserSessionVO)SecurityUtils.getSubject().getSession().getAttribute(key);
        Ensure.that(vo).isNotNull("用户未登陆","20001");
        return vo;
    }

    private static UserSessionVO getTestUser(String key){
        if (StringUtils.isNotEmpty(key)){
            UserSessionVO vo = (UserSessionVO)SecurityUtils.getSubject().getSession().getAttribute(key);
            Ensure.that(vo).isNotNull("用户未登陆","20001");
            return vo;
        }

        UserSessionVO vo = new UserSessionVO();
        vo.setUserId("123456");
        vo.setOpenId("openid");
        return vo;
    }
}
