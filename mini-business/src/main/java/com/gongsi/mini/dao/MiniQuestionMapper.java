package com.gongsi.mini.dao;

import com.gongsi.mini.entities.MiniQuestion;

import java.util.List;

public interface MiniQuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MiniQuestion record);

    int insertSelective(MiniQuestion record);

    MiniQuestion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MiniQuestion record);

    int updateByPrimaryKey(MiniQuestion record);

    List<MiniQuestion> selectByType(Integer type);
}