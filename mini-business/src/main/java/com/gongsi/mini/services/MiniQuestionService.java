package com.gongsi.mini.services;

import com.gongsi.mini.entities.MiniQuestion;

import java.util.List;

/**
 * Created by 吴宇 on 2018-08-26.
 */
public interface MiniQuestionService {
    List<MiniQuestion> selectByType(Integer type);
}
