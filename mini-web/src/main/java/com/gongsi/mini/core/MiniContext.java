package com.gongsi.mini.core;

import com.gongsi.mini.core.ensure.Ensure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by 吴宇 on 2018-05-29.
 */
@Slf4j
public class MiniContext {
    /** 请求参数 */
    private String postBody;

    /** 返回结果 */
    private String returnBody;

    /** 请求开始时间*/
    private long startTime;

    private boolean isInited = false;
    protected HttpServletRequest request;

    private static final ThreadLocal<MiniContext> THREAD_LOCAL = new ThreadLocal<MiniContext>() {
        @Override
        protected MiniContext initialValue() {
            return new MiniContext();
        }
    };

    /** 获取当前请求的上下文 */
    public static MiniContext getContext() {
        return THREAD_LOCAL.get();
    }

    public void init(HttpServletRequest request) {
        Ensure.that(request).isNotNull("请求不能为空");
        this.isInited = true;
        this.request = request;
        this.startTime = System.currentTimeMillis();
//        if (isPostRequest(request)) {
//            this.postBody = parsePostBody(request);
//        }
    }

    /** 判断是否post请求 */
    public boolean isPostRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        return request.getMethod().equals(HttpMethod.POST.name()) &&
                (contentType.contains("application/json") || contentType.contains("text/json"));
    }

    private String parsePostBody(HttpServletRequest request){
        try {
            return IOUtils.toString(request.getInputStream(), "UTF-8");
        } catch (IOException e) {
            log.error("解析stream异常",e);
            return "{}";
        }
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    /** 请求完成清理掉*/
    public static void remove() {
        THREAD_LOCAL.remove();
    }

    public boolean isInited() {
        return isInited;
    }

    /**
     * 获取请求对应的URL
     */
    public String getRequestUrl() {
        String queryString = request.getQueryString();
        return StringUtils.isEmpty(queryString)?
                request.getRequestURI():request.getRequestURI() + "?" + queryString;
    }

    public String getReturnBody() {
        return returnBody;
    }

    public void setReturnBody(String returnBody) {
        this.returnBody = returnBody;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
