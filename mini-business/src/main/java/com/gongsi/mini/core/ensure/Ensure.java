package com.gongsi.mini.core.ensure;

import com.gongsi.mini.core.ensure.extension.*;

import java.util.Collection;

/**
 * Created by 吴宇 on 2018-05-24.
 */
public class Ensure {
    private Ensure() {
    }

    public static EnsureObjectExtension that(Object obj) {
        return new EnsureObjectExtension(obj);
    }

    public static EnsureBooleanExtension that(boolean obj) {
        return new EnsureBooleanExtension(obj);
    }

    public static <T extends Collection> EnsureCollectionExtension that(T obj) {
        return new EnsureCollectionExtension(obj);
    }

    public static <T extends Number> EnsureNumberExtension that(T obj) {
        return new EnsureNumberExtension(obj);
    }

    public static EnsureStringExtension that(String obj) {
        return new EnsureStringExtension(obj);
    }

}
