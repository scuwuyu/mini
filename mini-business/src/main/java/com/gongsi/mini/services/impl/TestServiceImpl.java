package com.gongsi.mini.services.impl;

import com.gongsi.mini.entities.QueryUserResult;
import com.gongsi.mini.dao.UserDAO;
import com.gongsi.mini.dtos.QueryUserDto;
import com.gongsi.mini.entities.User;
import com.gongsi.mini.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public QueryUserResult findResult(QueryUserDto dto) {
        return userDAO.findResult(dto);
    }

    @Override
    public Long saveUser(User user) {
        userDAO.saveUser(user);
        return user.getId();
    }
}
