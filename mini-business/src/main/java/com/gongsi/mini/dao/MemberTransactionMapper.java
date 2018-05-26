package com.gongsi.mini.dao;

import com.gongsi.mini.entities.MemberTransaction;

public interface MemberTransactionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberTransaction record);

    int insertSelective(MemberTransaction record);

    MemberTransaction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberTransaction record);

    int updateByPrimaryKey(MemberTransaction record);
}