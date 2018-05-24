package com.gongsi.mini.core.ensure.extension;

import com.gongsi.mini.core.ensure.EnsureParam;
import com.gongsi.mini.core.exception.BusinessException;

/**
 * Created by 吴宇 on 2018-05-24.
 */
public class EnsureBooleanExtension extends EnsureParam<Boolean> {
    public EnsureBooleanExtension(Boolean obj) {
        super(obj);
    }

    public void isTrue(String msg){
        if (!obj){
            throw new BusinessException(msg);
        }
    }

    public void isFalse(String msg){
        if (obj){
            throw new BusinessException(msg);
        }
    }
}
