package com.gongsi.mini.dao;

import com.gongsi.mini.entities.AppToken;

import java.util.Date;
import java.util.List;

public interface AppTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppToken record);

    int insertSelective(AppToken record);

    AppToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppToken record);

    int updateByPrimaryKey(AppToken record);

    List<AppToken> selectListByExpiredTime(Date expiredTime);

    AppToken selectByAppId(String appId);
}