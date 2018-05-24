package com.gongsi.mini.core.exception;

/**
 * Created by 吴宇 on 2018-05-24.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public BusinessException() {
    }

    public BusinessException(String errMsg, String errCode) {
        super(errMsg);
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    private String errMsg;

    private String errCode;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
