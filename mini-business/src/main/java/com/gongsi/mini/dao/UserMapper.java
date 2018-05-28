package com.gongsi.mini.dao;

import com.gongsi.mini.entities.User;
import com.gongsi.mini.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserId(String userId);

    List<UserVO> selectByIds(@Param("userIds") List<String> userIds);

    String test(Long id);
}