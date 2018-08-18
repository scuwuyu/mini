package com.gongsi.mini.dao;

import com.gongsi.mini.entities.StockIPO;

public interface StockIPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockIPO record);

    int insertSelective(StockIPO record);

    StockIPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockIPO record);

    int updateByPrimaryKey(StockIPO record);

    StockIPO selectByCode(String code);
}