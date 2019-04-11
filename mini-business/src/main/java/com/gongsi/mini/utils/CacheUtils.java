package com.gongsi.mini.utils;

import com.gongsi.mini.core.ensure.Ensure;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wuyu on 2019/4/11.
 */
public class CacheUtils {

    private static final ConcurrentHashMap<String,ValueObj> MAP = new ConcurrentHashMap<>();

    /**
     *
     * @param key
     * @param value
     * @param expired  单位秒
     */
    public static void put(String key,String value,int expired){
        MAP.put(key,new ValueObj(value,expired));
    }

    public static String get(String key){

    }

    private static class ValueObj{
        private String value;
        private int expired;

        public ValueObj(String value, int expired) {
            Ensure.that(expired).isGt(0,"过期时间必须大于0");
            this.value = value;
            this.expired = expired;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getExpired() {
            return expired;
        }

        public void setExpired(int expired) {
            this.expired = expired;
        }
    }
}
