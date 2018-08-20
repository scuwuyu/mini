package com.gongsi.mini.dao;

import com.gongsi.mini.core.Pagination;
import com.gongsi.mini.entities.Activity;
import com.gongsi.mini.vo.ActivityVO;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    int countByUserId(String userId);

    int countList(@Param("vo") ActivityVO vo,@Param("userId") String userId);
    List<ActivityVO> selectList(@Param("vo") ActivityVO vo,@Param("userId") String userId,
                                @Param("pagination") Pagination<ActivityVO> pagination);

    List<ActivityVO> selectByIds(@Param("activityIds") ArrayList<Long> activityIds);
}