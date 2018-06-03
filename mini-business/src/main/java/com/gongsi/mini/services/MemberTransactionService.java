package com.gongsi.mini.services;

import com.gongsi.mini.entities.MemberTransaction;

import java.math.BigDecimal;

/**
 * Created by 吴宇 on 2018-06-03.
 */
public interface MemberTransactionService {
    /**
     * 获取充值流水
     * @return
     */
    MemberTransaction insertOrGet(String userId, Long memberTypeId, BigDecimal amount);
}
