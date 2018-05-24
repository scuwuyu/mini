package com.gongsi.mini.enums;

/**
 * Created by 吴宇 on 2018-05-24.
 */
public enum  ActivityStatusEn {
    ACTIVE(1,"进行中"),
    END(5,"结束"),
    ;

    ActivityStatusEn(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;

    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
