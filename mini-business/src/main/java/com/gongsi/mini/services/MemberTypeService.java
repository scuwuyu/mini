package com.gongsi.mini.services;

import com.gongsi.mini.vo.MemberTypeVO;

import java.util.List;

/**
 * Created by 吴宇 on 2018-06-03.
 */
public interface MemberTypeService {

    /**
     * 会员购买套餐列表
     * @return
     */
    List<MemberTypeVO> selectList();
}
