package com.gongsi.mini.core.ensure;

import com.gongsi.mini.core.exception.BusinessException;

import java.util.Objects;

/**
 * Created by 吴宇 on 2018-05-24.
 */
public class EnsureParam<T> {
    protected T obj;

    public EnsureParam(T obj) {
        this.obj = obj;
    }

    public void isNotNull(String msg){
        if (Objects.isNull(obj)){
            throw new BusinessException(msg);
        }
    }

    public void isNull(String msg){
        if (Objects.nonNull(obj)){
            throw new BusinessException(msg);
        }
    }
}
