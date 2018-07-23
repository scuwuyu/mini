package com.gongsi.mini.enums;

/**
 * Created by 吴宇 on 2018-07-23.
 */
public enum OrderStatusEn {

    WAIT_EXPRESS(1,"待发货"),
    EXPRESSED(5,"已发货"),
    ;

    private Integer code;

    private String desc;

    OrderStatusEn(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
