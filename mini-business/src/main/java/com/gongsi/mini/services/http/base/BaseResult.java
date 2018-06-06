package com.gongsi.mini.services.http.base;

import lombok.Data;

/**
 * 保存微信错误信息
 * Created by wuyu on 2017/4/13.
 */
@Data
public class BaseResult  {

    protected Integer errcode;

    protected String errmsg;
}
