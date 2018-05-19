package com.gongsi.mini.services;

import com.gongsi.mini.entities.QueryUserResult;
import com.gongsi.mini.entities.User;
import com.gongsi.mini.dtos.QueryUserDto;

/**
 * local服务，入参和返回值可以使用po对象，也可以只用dto对象，但是禁止出现vo对象
 *
 */
public interface TestService {

    /**
     * 普通查询
     *
     * @param dto
     * @return
     */
    QueryUserResult findResult(QueryUserDto dto);

    /**
     * 保存user对象
     *
     * @param user
     * @return
     */
    Long saveUser(User user);

}
