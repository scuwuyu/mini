package com.gongsi.mini.services;

import com.gongsi.mini.dao.StockCodeMapper;
import com.gongsi.mini.entities.StockCode;
import com.gongsi.mini.entities.StockCodeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wuyu on 2019/4/11.
 */
@Service
public class StockCodeService {

    @Autowired
    private StockCodeMapper stockCodeMapper;


    public List<String> selectActiveCode(){
        StockCodeExample example = new StockCodeExample();
        example.createCriteria().andActiveEqualTo(true);

        List<StockCode> list = stockCodeMapper.selectByExample(example);
        return list.stream().map(item -> this.covert(item.getCode())).collect(Collectors.toList());
    }

    public List<String> selectAll(){
        StockCodeExample example = new StockCodeExample();
        example.createCriteria().andActiveEqualTo(false);

        List<StockCode> list = stockCodeMapper.selectByExample(example);
        return list.stream().map(item -> this.covert(item.getCode())).collect(Collectors.toList());
    }

    private String covert(String code){
        return (code.startsWith("0")?"sz":"sh")+code;
    }

}
