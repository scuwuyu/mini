package com.gongsi.mini.utils;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.IdGenerator;
import com.gongsi.mini.vo.ActivityVO;
import com.gongsi.mini.vo.UserVO;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by 吴宇 on 2018-05-27.
 */
public class TestUtils {
    public static void main(String[] args) {
        testSort();

    }

    public static void testSort(){
//        Integer[] arrays = new Integer[]{
//                0,0,0, 0,0,0, 0,0,0, 0,0,0, 0,0,0, 3,
//                0,0,0, 0,0,0, 0,0,0, 0,0,0, 3,3,3, 30
//        };

        List<Integer> list = create128();
        System.out.println(list);
        Collections.sort(list,COMPARATOR);
        System.out.println(list);
    }

    public static final Comparator<Integer> COMPARATOR = (o1,o2) -> {
        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
        }
        return o1>o2?1:-1;
    };


    private static List<Integer> create128(){
        List<Integer> list = new ArrayList<>();
        list.addAll(create(80));
        list.addAll(create(52));
        list.addAll(create(26));
        list.addAll(create(25));

        return list;
    }

    private static List<Integer> create64(){
        List<Integer> list = new ArrayList<>();
        list.addAll(create(120));
        list.addAll(create(80));
        list.addAll(create(3));
        list.addAll(create(1));

        return list;
    }

    private static List<Integer> create(Integer i){
        List<Integer> list = new ArrayList<>();
        while (i>0){
            list.add(i);
            i--;
        }

        return list;
    }




}
