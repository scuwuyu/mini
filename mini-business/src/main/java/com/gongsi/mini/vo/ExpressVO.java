package com.gongsi.mini.vo;

import com.gongsi.mini.core.ensure.Ensure;
import lombok.Data;

/**
 * 卖家发货
 * Created by 吴宇 on 2018-07-23.
 */
@Data
public class ExpressVO extends BaseVO {
    private String orderNumber;
    private String expressNumber;
    private String expressName;

    public void check(){
        Ensure.that(orderNumber).isNotEmpty("订单号不能为空");
        Ensure.that(expressNumber).isNotEmpty("快递单号不能为空");
        Ensure.that(expressName).isNotEmpty("快递名称不能为空");
    }
}
