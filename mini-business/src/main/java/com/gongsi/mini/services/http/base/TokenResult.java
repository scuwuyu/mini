package com.gongsi.mini.services.http.base;

import lombok.Data;

/**
 * Created by 吴宇 on 2018-10-26.
 */
@Data
public class TokenResult extends BaseResult {
    private String access_token;
    /** 凭证有效时间，单位：秒*/
    private Integer expires_in;
}
