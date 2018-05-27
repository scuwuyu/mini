package com.gongsi.mini.core.result;

import lombok.Data;

/**
 * Created by 吴宇 on 2018-05-27.
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
