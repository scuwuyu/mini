package com.gongsi.mini.dao;

import com.gongsi.mini.core.Pagination;
import com.gongsi.mini.entities.Order;
import com.gongsi.mini.vo.OrderVO;
import com.gongsi.mini.vo.page.OrderPageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int countByUserId(String userId);

    Order selectByOrderNumber(String orderNumber);

    List<OrderVO> selectList(@Param("status") Integer status,@Param("userId") String userId);

    int countActivityOrderList(@Param("vo") OrderPageVO vo);

    List<OrderVO> activityOrderList(@Param("vo")OrderPageVO vo,@Param("pagination") Pagination pagination);
}