package com.gongsi.mini.vo;

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
}
