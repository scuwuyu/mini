package com.gongsi.mini.services.third;

import lombok.Data;

/**
 * Created by wuyu on 2018/7/25.
 */
@Data
public class UpyunFormApi {
    /**
     * formApi的请求url
     */
    private String url;

    private String bucket;

    private String policy;

    private String signature;

    private String operatorName;

}
