package com.gongsi.mini.dao;

import com.gongsi.mini.entities.QueryUserResult;
import com.gongsi.mini.dtos.QueryUserDto;
import com.gongsi.mini.entities.User;
import org.apache.ibatis.annotations.Param;

/**
 */
public interface UserDAO {

    QueryUserResult findResult(@Param("result") QueryUserDto result);

    Long saveUser(User user);

}
