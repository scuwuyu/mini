package com.gongsi.mini.services;

import com.gongsi.mini.entities.User;

/**
 * Created by 吴宇 on 2018-05-22.
 */
public interface UserService {
    User selectById(Long id);
}
