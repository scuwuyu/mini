package com.gongsi.mini.core.ensure.extension;

import com.gongsi.mini.core.ensure.EnsureParam;
import com.gongsi.mini.core.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

/**
 * Created by 吴宇 on 2018-05-24.
 */
public class EnsureCollectionExtension extends EnsureParam<Collection> {
    public EnsureCollectionExtension(Collection obj) {
        super(obj);
    }

    public void isEmpty(String msg){
        if(CollectionUtils.isNotEmpty(obj)){
            throw new BusinessException(msg);
        }
    }

    public void isNotEmpty(String msg){
        if(CollectionUtils.isEmpty(obj)){
            throw new BusinessException(msg);
        }
    }
}
