package com.gongsi.mini.dao;

import com.gongsi.mini.core.Pagination;
import com.gongsi.mini.entities.Goods;
import com.gongsi.mini.vo.GoodsVO;
import com.gongsi.mini.vo.page.GoodsPageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    int countList(@Param("vo") GoodsPageVO vo, @Param("userId") String userId);

    List<GoodsVO> selectList(@Param("vo")GoodsPageVO vo,@Param("userId") String userId,
                             @Param("pagination") Pagination<GoodsVO> pagination);

    int delete(@Param("goodsIds") List<Long> goodsIds, @Param("userId") String userId);

    List<GoodsVO> selectByIds(@Param("goodsIds")List<Long> goodsIds);
}