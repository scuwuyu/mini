package com.gongsi.mini.services.impl;

import com.gongsi.mini.dao.UserMapper;
import com.gongsi.mini.entities.User;
import com.gongsi.mini.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 吴宇 on 2018-05-22.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User selectById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }
}
