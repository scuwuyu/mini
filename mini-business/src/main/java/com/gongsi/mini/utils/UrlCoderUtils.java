package com.gongsi.mini.utils;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by 吴宇 on 2018-08-29.
 */
public class UrlCoderUtils {

    public static String encode(String string){
        if (StringUtils.isEmpty(string)){
            return "";
        }
        try {
            return URLEncoder.encode(string, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decode(String string){
        if (StringUtils.isEmpty(string)){
            return "";
        }
        try {
            return URLDecoder.decode(string, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
