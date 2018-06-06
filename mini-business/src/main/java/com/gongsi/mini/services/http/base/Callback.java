package com.gongsi.mini.services.http.base;

import org.apache.http.HttpResponse;

/**
 * Created by 吴宇 on 2018-06-04.
 */
public interface Callback<T> {
    T execute(HttpResponse response, String charset) throws Exception;
}
