package com.gongsi.mini.utils;

import com.gongsi.mini.vo.UserSessionVO;

/**
 * Created by 吴宇 on 2018-05-23.
 */
public class UserUtil {

    public static void saveUser(String key,UserSessionVO userSession){

    }

    public static UserSessionVO getUser(String key){
        UserSessionVO vo = new UserSessionVO();
        vo.setUserId("123456");
        vo.setOpenId("1111");
        return vo;
    }
}
