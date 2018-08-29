package com.gongsi.mini.services.impl;

import com.gongsi.mini.core.Pagination;
import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.BeanMapper;
import com.gongsi.mini.dao.GoodsMapper;
import com.gongsi.mini.entities.Goods;
import com.gongsi.mini.services.GoodsService;
import com.gongsi.mini.utils.UrlCoderUtils;
import com.gongsi.mini.vo.GoodsVO;
import com.gongsi.mini.vo.UserSessionVO;
import com.gongsi.mini.vo.page.GoodsPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    /** 新增商品 */
    public void add(GoodsVO vo, UserSessionVO user){
        vo.checkWhenAdd();
        Goods goods = BeanMapper.map(vo, Goods.class);
        goods.setUserId(user.getUserId());

        goods.setDesc(UrlCoderUtils.encode(goods.getDesc()));
        Integer result = goodsMapper.insertSelective(goods);
        Ensure.that(result).isEq(1,"商品保存失败");
    }

    /** 编辑商品 */
    public void edit(GoodsVO vo, UserSessionVO user){
        vo.checkWhenEdit();
        selectById(vo.getId(),user.getUserId());
        Goods goods = BeanMapper.map(vo, Goods.class);

        goods.setDesc(UrlCoderUtils.encode(goods.getDesc()));
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    public GoodsVO detail(GoodsVO vo, UserSessionVO user){
        return selectById(vo.getId(),user.getUserId());
    }



    /** 商品分页查询*/
    public Pagination<GoodsVO> selectList(GoodsPageVO vo, String userId){
        Pagination<GoodsVO> pagination = new Pagination<>(vo.getCurrentPage(),vo.getPageSize());
        int count = goodsMapper.countList(vo,userId);
        pagination.setTotalCount(count);
        if (count == 0){
            return pagination;
        }

        List<GoodsVO> list = goodsMapper.selectList(vo,userId,pagination);
        pagination.setList(list);

        return pagination;
    }
    /** 删除商品 */
    @Transactional
    public void delete(List<Long> goodsIds, UserSessionVO user){
        Ensure.that(goodsIds).isNotEmpty("商品id不能为空");
        int result = goodsMapper.delete(goodsIds,user.getUserId());
        Ensure.that(result).isEq(goodsIds.size(),"删除失败，请刷新重试");
    }

    public List<GoodsVO> selectByIds(List<Long> goodsIds){
        return goodsMapper.selectByIds(goodsIds);
    }


    public GoodsVO selectById(Long id,String userId){
        Goods goods = goodsMapper.selectByPrimaryKey(id);
//        Ensure.that(Objects.nonNull(goods)&&goods.getUserId().equals(userId))
//                .isTrue("对应商品不存在");
        goods.setDesc(UrlCoderUtils.decode(goods.getDesc()));
        return BeanMapper.map(goods, GoodsVO.class);
    }
}
