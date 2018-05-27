package com.gongsi.mini.core.result;

/**
 * Created by 吴宇 on 2018-05-27.
 */
public class ResultUtils {
    private static final String SUCCESS_CODE = "10000";
    private static final String FAULT_CODE = "-1";
    private static final String MESSAGE = "OK";


    public static Result getFaultResult() {
        return new Result(FAULT_CODE, MESSAGE);
    }

    public static Result getSuccessResult(){
        return new Result(SUCCESS_CODE, MESSAGE);
    }

    public static Result getSuccessResult(Object obj){
        return new Result(SUCCESS_CODE, MESSAGE, obj);
    }
}
