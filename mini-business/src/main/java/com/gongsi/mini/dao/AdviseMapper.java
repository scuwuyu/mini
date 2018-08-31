package com.gongsi.mini.dao;

import com.gongsi.mini.entities.Advise;

public interface AdviseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Advise record);

    int insertSelective(Advise record);

    Advise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Advise record);

    int updateByPrimaryKey(Advise record);
}