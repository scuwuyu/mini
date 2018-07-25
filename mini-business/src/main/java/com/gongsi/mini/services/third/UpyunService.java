package com.gongsi.mini.services.third;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import main.java.com.upyun.Base64Coder;
import main.java.com.upyun.UpYunUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 * Created by wuyu on 2018/7/25.
 */
@Slf4j
@Service
public class UpyunService {

    /**
     * formapi 请求url前缀
     */
    private String FORMAPI_URL_PREFIX = "http://v0.api.upyun.com/";

    private String bucket = "image-up-zhh";

    private String operatorName = "";

    private String operatorPwd = "";



    /**
     * 又拍云签名方案
     * http://docs.upyun.com/api/authorization/
     */
    public UpyunFormApi createFormApi(){
        UpyunFormApi upyunFormApi = new UpyunFormApi();
        upyunFormApi.setBucket(bucket);
        upyunFormApi.setUrl(FORMAPI_URL_PREFIX + bucket);

        /** 计算policy */
        String param = JSON.toJSONString(this);
        String policy = Base64Coder.encodeString(param);
        upyunFormApi.setPolicy(policy);

        /** 操作者名称*/
        upyunFormApi.setOperatorName(operatorName);

        /** 签名加密所需的key*/
        StringBuilder key = new StringBuilder(128);
        key
                // Method
                .append("POST").append("&")
                // Url
                .append("/").append(bucket).append("&")
                // Policy
                .append(upyunFormApi.getPolicy());
//        if (StringUtils.isNotBlank(contentMd5)) {
//            // Content-MD5
//            key.append("&").append(contentMd5);
//        }
        /** 计算signature*/
        byte[] enCryptorPwd = DigestUtils.md5(operatorPwd);
        try {
            byte[] bytes = UpYunUtils.calculateRFC2104HMACRaw(new String(enCryptorPwd), key.toString());
            String signature = Base64Coder.encodeLines(bytes).trim();
            upyunFormApi.setSignature(signature);
            return upyunFormApi;
        } catch (Exception e) {
            log.error("又拍云加密错误", e);
            throw new BusinessException("又拍云加密错误");
        }
    }

}
