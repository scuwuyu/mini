package com.gongsi.mini.services.impl;

import com.gongsi.mini.dao.StockIPOMapper;
import com.gongsi.mini.entities.StockIPO;
import com.gongsi.mini.services.StockIPOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by 吴宇 on 2018-08-18.
 */
@Service
public class StockIPOServiceImpl implements StockIPOService {

    @Autowired
    private StockIPOMapper stockIPOMapper;

    /** 同步ipo股票*/
    public void syncList(List<StockIPO> list){
        list.forEach(item -> {
            StockIPO record = stockIPOMapper.selectByCode(item.getCode());

            if (Objects.nonNull(record)){
                /** 判断是否相同*/
                if(!item.equals(record)){
                    item.setId(record.getId());
                    stockIPOMapper.updateByPrimaryKeySelective(item);
                }
            }else{
                stockIPOMapper.insertSelective(item);
            }

        });
    }
}
