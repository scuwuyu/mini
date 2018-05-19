package com.gongsi.mini.dtos;

import java.io.Serializable;

/**
 * dubbo接口返回实体类，需要继承可序列化接口。只返回需要的数据
 *
 */
public class QueryUserResultDto implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
