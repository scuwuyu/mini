package com.gongsi.mini.vo;

import com.gongsi.mini.core.ensure.Ensure;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Data
public class GoodsVO extends BaseVO {
    private Long id;
    private String name;
    private String picture;

    private Integer total;

    private String desc;
    private BigDecimal price;
    /** 需要删除的商品id*/
    private List<Long> goodsIds;

    public void check(){
        Ensure.that(name).isNotEmpty("商品名称不能为空");
        Ensure.that(price).isNotNull("商品价格不能为空");
    }
}
