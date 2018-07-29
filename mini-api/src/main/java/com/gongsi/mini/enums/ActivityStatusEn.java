package com.gongsi.mini.enums;

/**
 * Created by 吴宇 on 2018-05-24.
 */
public enum  ActivityStatusEn {
    ACTIVE((byte)1,"进行中"),
    END((byte)5,"结束"),
    ;

    ActivityStatusEn(Byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Byte code;

    private String desc;

    public Byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
