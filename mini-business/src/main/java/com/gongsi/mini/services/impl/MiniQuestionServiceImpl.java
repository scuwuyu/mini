package com.gongsi.mini.services.impl;

import com.gongsi.mini.dao.MiniQuestionMapper;
import com.gongsi.mini.entities.MiniQuestion;
import com.gongsi.mini.services.MiniQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 吴宇 on 2018-08-26.
 */
@Service("miniQuestionService")
public class MiniQuestionServiceImpl implements MiniQuestionService {

    @Autowired
    private MiniQuestionMapper miniQuestionMapper;

    public List<MiniQuestion> selectByType(Integer type){
        List<MiniQuestion> list = miniQuestionMapper.selectByType(type);

        return list;
    }

}
