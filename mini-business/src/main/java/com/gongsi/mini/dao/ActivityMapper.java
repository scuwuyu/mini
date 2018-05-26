package com.gongsi.mini.dao;

import com.gongsi.mini.entities.Activity;
import com.gongsi.mini.vo.ActivityVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    int countByUserId(String userId);

    List<ActivityVO> selectList(@Param("status") Integer status,@Param("userId") String userId);
}