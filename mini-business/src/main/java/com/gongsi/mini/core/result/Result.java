package com.gongsi.mini.core.result;

import lombok.Data;

/**
 * Created by wuyu on 2018/5/28.
 */
@Data
public class Result {

    private String code;
    private String message;

    private Object result;

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }
}
