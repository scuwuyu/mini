package com.gongsi.mini.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wuyu on 2018/5/28.
 */
@Data
public class UserSessionVO implements Serializable{
    private String userId;

    private String openId;
}
