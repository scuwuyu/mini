package com.gongsi.mini.services;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.dao.DictionaryMapper;
import com.gongsi.mini.entities.Dictionary;
import com.gongsi.mini.entities.DictionaryExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuyu on 2019/4/11.
 */
@Slf4j
@Service
public class DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    public Dictionary selectByCode(String code){
        DictionaryExample example = new DictionaryExample();
        example.createCriteria().andCodeEqualTo(code).andActiveEqualTo(true);
        List<Dictionary> list = dictionaryMapper.selectByExample(example);

        if (list.size()!=1){
            log.error("字段查询报错list={}", JSON.toJSONString(list,true));
            return null;
        }

        return list.get(0);
    }
}
