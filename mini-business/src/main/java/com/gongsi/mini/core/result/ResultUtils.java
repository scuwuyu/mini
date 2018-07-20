package com.gongsi.mini.core.result;

import com.gongsi.mini.core.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by 吴宇 on 2018-05-27.
 */
public class ResultUtils {
    private static final String SUCCESS_CODE = "10000";
    private static final String FAULT_CODE = "-1";
    private static final String MESSAGE = "OK";
    private static final String MESSAGE_FAULT = "系统错误，请稍后再试";


    public static Result getFaultResult() {
        return new Result(FAULT_CODE, MESSAGE_FAULT);
    }

    public static Result getSuccessResult(){
        return new Result(SUCCESS_CODE, MESSAGE);
    }

    public static Result getBusinessExceptionResult(BusinessException ex) {
        return new Result(StringUtils.isEmpty(ex.getErrCode())?
                FAULT_CODE:ex.getErrCode(), ex.getMessage());
    }

    public static Result getSuccessResult(Object obj){
        return new Result(SUCCESS_CODE, MESSAGE, obj);
    }
}
