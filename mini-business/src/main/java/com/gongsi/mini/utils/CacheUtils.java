package com.gongsi.mini.utils;

import com.gongsi.mini.core.ensure.Ensure;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
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
    public static synchronized void put(String key,Object value,int expired){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,expired);

        MAP.put(key,new ValueObj(value,calendar.getTime()));
    }

    public static synchronized Object get(String key){
        ValueObj valueObj = MAP.get(key);
        if (Objects.isNull(valueObj)){
            return null;
        }
        if (valueObj.getExpiredDate().before(new Date())){
            clearAllExpired();
            return null;
        }
        return valueObj.getValue();
    }

    private static void clearAllExpired(){
        Date date = new Date();
        MAP.forEach((key,value)->{
            if (value.getExpiredDate().before(date)){
                MAP.remove(key);
            }
        });
    }

    private static class ValueObj{
        private Object value;
        private Date expiredDate;

        public ValueObj(Object value, Date expiredDate) {
            Ensure.that(expiredDate).isNotNull("过期时间不能为空");
            this.value = value;
            this.expiredDate = expiredDate;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Date getExpiredDate() {
            return expiredDate;
        }

        public void setExpiredDate(Date expiredDate) {
            this.expiredDate = expiredDate;
        }
    }
}
