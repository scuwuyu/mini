package com.gongsi.mini.services.impl;

import com.gongsi.mini.core.utils.BeanMapper;
import com.gongsi.mini.dao.MemberTypeMapper;
import com.gongsi.mini.entities.MemberType;
import com.gongsi.mini.services.MemberTypeService;
import com.gongsi.mini.vo.MemberTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 吴宇 on 2018-06-03.
 */
@Service
public class MemberTypeServiceImpl implements MemberTypeService {

    @Autowired
    private MemberTypeMapper memberTypeMapper;
    /**
     * 会员购买套餐列表
     * @return
     */
    public List<MemberTypeVO> selectList(){
        List<MemberType> list =  memberTypeMapper.selectList();
        return BeanMapper.mapList(list,MemberTypeVO.class);
    }
}
