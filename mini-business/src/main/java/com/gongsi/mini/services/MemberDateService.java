package com.gongsi.mini.services;

/**
 * Created by 吴宇 on 2018-06-03.
 */
public interface MemberDateService {
    /** 首次成为会员，免费试用*/
    void insertWhenFirst(String userId,Integer months);
}
