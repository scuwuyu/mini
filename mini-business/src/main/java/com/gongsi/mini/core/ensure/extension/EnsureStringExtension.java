package com.gongsi.mini.core.ensure.extension;

import com.gongsi.mini.core.ensure.EnsureParam;
import com.gongsi.mini.core.exception.BusinessException;
import org.apache.commons.lang.StringUtils;

/**
 * Created by 吴宇 on 2018-05-24.
 */
public class EnsureStringExtension extends EnsureParam<String> {
    public EnsureStringExtension(String obj) {
        super(obj);
    }

    public void isNotEmpty(String msg){
        if (StringUtils.isEmpty(obj)){
            throw new BusinessException(msg);
        }
    }

    public void isEmpty(String msg){
        if (StringUtils.isNotEmpty(obj)){
            throw new BusinessException(msg);
        }
    }
    public void isNotBlank(String msg){
        if (StringUtils.isBlank(obj)){
            throw new BusinessException(msg);
        }
    }

    public void isBlank(String msg){
        if (StringUtils.isNotBlank(obj)){
            throw new BusinessException(msg);
        }
    }
}
