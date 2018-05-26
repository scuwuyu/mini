package com.gongsi.mini.dao;

import com.gongsi.mini.entities.MemberType;

public interface MemberTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberType record);

    int insertSelective(MemberType record);

    MemberType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberType record);

    int updateByPrimaryKey(MemberType record);
}