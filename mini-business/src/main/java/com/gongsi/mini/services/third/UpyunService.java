package com.gongsi.mini.services.third;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.gongsi.mini.core.exception.BusinessException;
import com.gongsi.mini.core.utils.DateUtils;
import com.gongsi.mini.core.utils.IdGenerator;
import com.gongsi.mini.utils.MD5Util;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import main.java.com.upyun.Base64Coder;
import main.java.com.upyun.UpYunUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 * Created by wuyu on 2018/7/25.
 */
@Slf4j
@Service
public class UpyunService {

    /**
     * formapi 请求url前缀
     */
    private static final String FORMAPI_URL_PREFIX = "http://v0.api.upyun.com/";

    @Value("${upyun.bucket}")
    private String bucket;
    @Value("${upyun.operator.name}")
    private String operatorName;
    @Value("${upyun.operator.password}")
    private String operatorPwd;

    private static final Integer expiredSeconds = 8;

    /** saveKey 后缀*/
    private static final String SAVE_KEY_SUFFIX = "{filemd5}{.suffix}";

    /** 保存路径*/
    private static final String saveKey = "/{year}/{mon}/{day}/RANDOM"+SAVE_KEY_SUFFIX;



    /**
     * 又拍云签名方案
     * http://docs.upyun.com/api/authorization/
     */
    public UpyunFormApi createFormApi(){
        Builder builder = new Builder(bucket,saveKey,expiredSeconds);

        return builder.buildFormApi();
    }


    /**
     * 构造类
     */
    @Data
    public class Builder implements Serializable {

        public Builder(String bucket,String saveKey,Integer expiredSeconds){
            this.bucket = bucket;
            /** 默认过期时间8s*/
            this.expiredSeconds = Objects.isNull(expiredSeconds)?8:expiredSeconds;

            /** 过期时间戳*/
            this.expiration = DateUtils.add(DateUtils.getCurrent(), expiredSeconds,Calendar.SECOND)
            .getTime()/1000+"";

            this.saveKey = saveKey.replace("RANDOM", IdGenerator.nextId());
        }
        /**
         * 空间名
         */
        @JSONField(ordinal = 0)
        private String bucket;

        /**
         * 保存路径
         */
        @JSONField(name = "save-key",ordinal = 1)
        private String saveKey;

        /**
         * 过期时间 UNIX UTC 时间戳（秒）
         */
        @JSONField(ordinal = 2)
        private String expiration;

//        @JSONField(ordinal = 3)
//        private String date;
        /**
         * 请求的过期时间 秒
         */
        private transient Integer expiredSeconds;

        @JSONField(name = "content-md5",ordinal = 4)
        private String contentMd5;


        /**
         * 又拍云签名方案
         * http://docs.upyun.com/api/authorization/
         */
        public UpyunFormApi buildFormApi(){
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
                    //Date
                    //.append(date).append("&")
                    // Policy
                    .append(upyunFormApi.getPolicy());
            if (StringUtils.isNotBlank(contentMd5)) {
                // Content-MD5
                key.append("&").append(contentMd5);
            }
            /** 计算signature*/
            String enCryptorPwd = MD5Util.getInstance().md5(operatorPwd);
            try {
                byte[] bytes = UpYunUtils.calculateRFC2104HMACRaw(enCryptorPwd, key.toString());
                String signature = Base64Coder.encodeLines(bytes).trim();
                upyunFormApi.setSignature(signature);
                return upyunFormApi;
            } catch (Exception e) {
                log.error("又拍云加密错误", e);
                throw new BusinessException("又拍云加密错误");
            }
        }
    }
}
