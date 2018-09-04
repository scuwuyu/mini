package com.gongsi.mini.utils;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.utils.IdGenerator;
import com.gongsi.mini.vo.ActivityVO;
import com.gongsi.mini.vo.UserVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by 吴宇 on 2018-05-27.
 */
public class TestUtils {
    public static void main(String[] args) {

        testJDK8();
    }

    private static void testJDK8(){
        List<UserVO> list = new ArrayList<>();
        UserVO userVO = new UserVO();
        userVO.setUserId("923");
        list.add(userVO);
        userVO = new UserVO();
        userVO.setUserId("456");
        list.add(userVO);


        System.out.println(list.stream().map(UserVO::getUserId).collect(Collectors.toList()));
        System.out.println(list.stream().collect(Collectors.mapping(UserVO::getUserId,Collectors.toList())));

    }


    public static class Builder implements Serializable {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String test(){
            return JSON.toJSONString(this);
        }
    }

    public void testId(){
        final Object lock = new Object();
        final ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();

        for (int i=0;i<10;i++){
            new Thread(()->{
                for (int j=0;j<100000;j++){
                    String id = IdGenerator.nextId();
                    synchronized (lock){
                        System.out.println(Thread.currentThread().getName()+"======"+id);
                        if (map.containsKey(id)){
                            Ensure.that(true).isFalse("已经存在了");
                        }
                        map.putIfAbsent(id,"");
                    }
                }
            },"Thread-"+i).start();
        }
    }



}
