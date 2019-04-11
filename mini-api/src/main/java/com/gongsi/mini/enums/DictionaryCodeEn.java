package com.gongsi.mini.enums;


/**
 * Created by wuyu on 2019/4/11.
 */
public enum DictionaryCodeEn {

    EMAIL_KEY("email_key","电子邮件key"),
    ;

    private String code;

    private String desc;

    DictionaryCodeEn(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
