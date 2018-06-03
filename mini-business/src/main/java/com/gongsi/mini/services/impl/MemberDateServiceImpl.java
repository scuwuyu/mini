package com.gongsi.mini.services.impl;

import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.DateUtils;
import com.gongsi.mini.dao.MemberDateMapper;
import com.gongsi.mini.entities.MemberDate;
import com.gongsi.mini.services.MemberDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by 吴宇 on 2018-06-03.
 */
@Slf4j
@Service
public class MemberDateServiceImpl implements MemberDateService {
    @Autowired
    private MemberDateMapper memberDateMapper;

    /** 首次成为会员，免费试用*/
    public synchronized void insertWhenFirst(String userId,Integer months){
        log.info("首次会员userId={},months={}",userId,months);
        MemberDate memberDate = memberDateMapper.selectByUserId(userId);
        Ensure.that(memberDate).isNull("非首次会员不能享受试用");

        memberDate = new MemberDate();
        memberDate.setUserId(userId);
        memberDate.setStartTime(new Date());
        memberDate.setEndTime(DateUtils.add(memberDate.getStartTime(),months, Calendar.MONTH));
        memberDateMapper.insertSelective(memberDate);
    }
}
