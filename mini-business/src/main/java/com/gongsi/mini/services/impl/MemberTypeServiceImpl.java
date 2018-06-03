package com.gongsi.mini.services.impl;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.BeanMapper;
import com.gongsi.mini.dao.MemberTypeMapper;
import com.gongsi.mini.entities.MemberTransaction;
import com.gongsi.mini.entities.MemberType;
import com.gongsi.mini.services.MemberDateService;
import com.gongsi.mini.services.MemberTransactionService;
import com.gongsi.mini.services.MemberTypeService;
import com.gongsi.mini.vo.MemberTypeVO;
import com.gongsi.mini.vo.UserSessionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 吴宇 on 2018-06-03.
 */
@Slf4j
@Service
public class MemberTypeServiceImpl implements MemberTypeService {

    @Autowired
    private MemberTypeMapper memberTypeMapper;

    @Autowired
    private MemberTransactionService memberTransactionService;

    @Autowired
    private MemberDateService memberDateService;
    /**
     * 会员购买套餐列表
     * @return
     */
    public List<MemberTypeVO> selectList(){
        List<MemberType> list =  memberTypeMapper.selectList();
        return BeanMapper.mapList(list,MemberTypeVO.class);
    }

    /** 购买套餐 */
    public MemberTypeVO buy(MemberTypeVO vo, UserSessionVO user){
        MemberType memberType = memberTypeMapper.selectByPrimaryKey(vo.getId());
        Ensure.that(memberType).isNotNull("会员套餐不存在");

        if (memberType.getCondition()){
            log.info("第一次购买，不需要支付user={},vo={}", JSON.toJSONString(user),JSON.toJSONString(vo));
            /** 校验并且插入有效期 */
            memberDateService.insertWhenFirst(user.getUserId(),memberType.getMonths());
            return new MemberTypeVO(false);
        }

        MemberTransaction memberTransaction = memberTransactionService.insertOrGet(user.getUserId(),memberType.getId(),memberType.getPrice());
        // TODO: 2018-06-03 调用微信支付
        return null;
    }
}
