package com.gongsi.mini.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 吴宇 on 2018-05-27.
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static Date getCurrent(){
        Calendar c = Calendar.getInstance() ;
        return c.getTime() ;
    }

    public static String getNow() {
        return format(new Date());
    }

    public static String format(Date date) {
        return format(date, YYYY_MM_DD_HHMMSS);
    }

    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    public static String format(Long timestamp, String pattern) {

        return format(new Date(timestamp),pattern);
    }

    public static Date parse(String strDate) {
        return parse(strDate,YYYY_MM_DD_HHMMSS);
    }

    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            logger.error("日期格式转换错误",e);
            return null;
        }
    }

    /**
     * 增加单位
     * @param date
     * @param num   增量  可以为负
     * @param unit  单位，Calendar.MINUTE、Calendar.HOUR、Calendar.DATE、Calendar.MONTH
     * @return
     */
    public static Date add(Date date, int num, int unit) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(unit, num);
        return cal.getTime();
    }

    public static String toGMTString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.UK);
        df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }
}
