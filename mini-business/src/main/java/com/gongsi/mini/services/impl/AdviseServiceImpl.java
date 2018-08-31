package com.gongsi.mini.services.impl;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.exception.BusinessException;
import com.gongsi.mini.dao.AdviseMapper;
import com.gongsi.mini.entities.Advise;
import com.gongsi.mini.services.AdviseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 吴宇 on 2018-08-31.
 */
@Slf4j
@Service("adviseService")
public class AdviseServiceImpl implements AdviseService {

    @Autowired
    private AdviseMapper adviseMapper;

    public  void add(Advise advise){
        try {
            adviseMapper.insertSelective(advise);
        }catch (Exception e){
            log.error("添加反馈错误advise={}", JSON.toJSONString(advise),e);

            throw  new BusinessException("请去掉特殊符号后重试");
        }
    }
}
