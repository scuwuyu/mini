package com.gongsi.mini.controllers;

import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.exception.BusinessException;
import com.gongsi.mini.entities.MiniQuestion;
import com.gongsi.mini.services.MiniQuestionService;
import com.gongsi.mini.utils.UserUtil;
import com.gongsi.mini.vo.QuestionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 吴宇 on 2018-08-21.
 */
@Slf4j
@Controller
@RequestMapping("/mini/question")
public class QuestionController {

    @Autowired
    private MiniQuestionService miniQuestionService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public List<MiniQuestion> add(@RequestBody QuestionVO vo){
        UserUtil.getUser(vo.getKey());
        vo.check();
        return miniQuestionService.selectByType(vo.getType());
    }
}
