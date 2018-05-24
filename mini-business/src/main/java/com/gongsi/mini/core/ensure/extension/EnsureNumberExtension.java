package com.gongsi.mini.core.ensure.extension;

import com.gongsi.mini.core.ensure.EnsureParam;
import com.gongsi.mini.core.exception.BusinessException;

/**
 * Created by 吴宇 on 2018-05-24.
 */
public class EnsureNumberExtension extends EnsureParam<Number> {
    public EnsureNumberExtension(Number obj) {
        super(obj);
    }

    public EnsureNumberExtension isGt(Number target, String msg) {
        if(obj.doubleValue() <= target.doubleValue()) {
            throw new BusinessException(msg);
        }

        return this;
    }

    public EnsureNumberExtension isGe(Number target, String msg) {
        if(obj.doubleValue() < target.doubleValue()) {
            throw new BusinessException(msg);
        }

        return this;
    }

    public EnsureNumberExtension isEq(Number target, String msg) {
        if(obj.doubleValue() != target.doubleValue()) {
            throw new BusinessException(msg);
        }
        return this;
    }

    public EnsureNumberExtension isNotEq(Number target, String msg) {
        if(obj.doubleValue() == target.doubleValue()) {
            throw new BusinessException(msg);
        }
        return this;
    }

    public EnsureNumberExtension isLt(Number target, String msg) {
        if(obj.doubleValue() >= target.doubleValue()) {
            throw new BusinessException(msg);
        }
        return this;
    }

    public EnsureNumberExtension isLe(Number target, String msg) {
        if(obj.doubleValue() > target.doubleValue()) {
            throw new BusinessException(msg);
        }
        return this;
    }
}
