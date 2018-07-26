package com.gongsi.mini.vo;

import com.gongsi.mini.core.ensure.Ensure;
import lombok.Data;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Data
public class AddressVO extends BaseVO {
    private Long id;
    private String name;

    private String mobile;

    private String address;
    /** 是否默认地址*/
    private Integer defaultAddress;

    public void checkWhenAdd(){
        Ensure.that(name).isNotEmpty("收货人名称不能为空");
        Ensure.that(mobile).isNotEmpty("电话号码不能为空");
        Ensure.that(address).isNotEmpty("收货地址不能为空");
    }

    public void checkWhenEdit(){
        checkWhenAdd();
        checkWhenDetail();
    }

    public void checkWhenDetail(){
        Ensure.that(id).isNotNull("地址id不能为空");
    }

}
