package com.gongsi.mini.services;

import com.gongsi.mini.core.Pagination;
import com.gongsi.mini.vo.GoodsVO;
import com.gongsi.mini.vo.UserSessionVO;
import com.gongsi.mini.vo.page.GoodsPageVO;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
public interface GoodsService {
    /** 新增商品 */
    void add(GoodsVO vo, UserSessionVO user);

    /** 编辑商品 */
    void edit(GoodsVO vo, UserSessionVO user);

    GoodsVO detail(GoodsVO vo, UserSessionVO user);

    /** 商品分页查询*/
    Pagination<GoodsVO> selectList(GoodsPageVO vo, String userId);

    void delete(List<Long> goodsIds, UserSessionVO user);

    List<GoodsVO> selectByIds(List<Long> goodsIds);
}
