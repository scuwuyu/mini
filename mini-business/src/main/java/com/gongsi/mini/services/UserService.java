package com.gongsi.mini.services;

import com.gongsi.mini.entities.User;
import com.gongsi.mini.vo.MineVO;

/**
 * Created by 吴宇 on 2018-05-22.
 */
public interface UserService {
    User selectByUserId(String userId);
    /** 我的页面*/
    MineVO mine(String userId);
}
