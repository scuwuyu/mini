package com.gongsi.mini.utils;

import com.gongsi.mini.core.exception.BusinessException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 吴宇 on 2018-07-29.
 */
public class MD5Util {
    private static MD5Util instance = new MD5Util();
    private MD5Util() {
    }
    public static MD5Util getInstance() {
        return instance;
    }

    public String md5(String str) {
        MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException var4) {
            throw new BusinessException("MD5加密失败");
        }

        byte[] byteArray = messageDigest.digest();
        return bufferToHex(byteArray);
    }

    private String bufferToHex(byte[] byteArray) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < byteArray.length; ++i) {
            String hexString = Integer.toHexString(255 & byteArray[i]);
            if(hexString.length() == 1) {
                builder.append('0').append(hexString);
            } else {
                builder.append(hexString);
            }
        }

        return builder.toString();
    }
}
