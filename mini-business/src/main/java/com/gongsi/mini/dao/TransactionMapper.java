package com.gongsi.mini.dao;

import com.gongsi.mini.entities.Transaction;

public interface TransactionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);
}