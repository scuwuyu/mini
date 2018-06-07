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
        if (StringUtils.isNotEmpty(key)){
            return  (UserSessionVO)SecurityUtils.getSubject().getSession().getAttribute(key);
        }

        UserSessionVO vo = new UserSessionVO();
        vo.setUserId("123456");
        vo.setOpenId("openid");
        return vo;
    }
}
