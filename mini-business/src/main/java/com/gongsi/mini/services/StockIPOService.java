package com.gongsi.mini.services;

import com.gongsi.mini.entities.StockIPO;

import java.util.List;

/**
 * Created by 吴宇 on 2018-08-18.
 */
public interface StockIPOService {
    /** 同步ipo股票*/
    void syncList(List<StockIPO> list);
}
