package com.gongsi.mini.core.utils;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyu on 2017/11/24.
 */
public class BeanMapper {
    /**
     * dozer单例
     */
    private static final DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 对象间的值拷贝
     *
     * @param source
     * @param destination
     * @param <T>
     * @return
     */
    public static <T> T map(Object source, Class<T> destination) {
        return dozer.map(source, destination);
    }

    /**
     * 基本类型之间的赋值
     *
     * @param source
     * @param destinationObject
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }

    /**
     *
     * @param list
     * @param destination
     * @param <T>
     * @return
     */
    public static <T> List<T> mapList(List list, Class<T> destination) {
        List<T> back = new ArrayList<>();
        for (Object o : list) {
            T t = dozer.map(o, destination);
            back.add(t);
        }
        return back;
    }
}
