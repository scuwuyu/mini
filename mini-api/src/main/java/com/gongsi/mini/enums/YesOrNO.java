package com.gongsi.mini.enums;


/**
 * Created by 吴宇 on 2018-05-26.
 */
public enum YesOrNO {
    YES(1,"是"),
    NO(0,"否"),
    ;

    private Integer code;

    private String desc;

    YesOrNO(Integer code, String desc) {
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
