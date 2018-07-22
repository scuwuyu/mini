package com.gongsi.mini.core;

import com.gongsi.mini.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 吴宇 on 2018-05-29.
 */
@Slf4j
public class MiniInterceptor implements HandlerInterceptor {
    /** 错误页面 */
    private List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 501);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (errorCodeList.contains(httpServletResponse.getStatus())){
            httpServletResponse.sendRedirect("/error/" + httpServletResponse.getStatus());
            return false;
        }
        MiniContext context = MiniContext.getContext();
        if (!context.isInited()){
            context.init(httpServletRequest);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        MiniContext miniContext = MiniContext.getContext();

        if (miniContext.isInited()){
            log.info(Constants.LOG_STRING,miniContext.getRequestUrl(),miniContext.getPostBody(),miniContext.getReturnBody());
        }

        MiniContext.remove();
    }
}
