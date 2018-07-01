package com.gongsi.mini.core;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.exception.BusinessException;
import com.gongsi.mini.core.result.Result;
import com.gongsi.mini.core.result.ResultUtils;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Created by 吴宇 on 2018-05-30.
 */
public class MiniExceptionResolver extends SimpleMappingExceptionResolver {

    private Charset charset = StandardCharsets.UTF_8;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error(ex.getMessage(),ex);

        Result result = getResult(ex);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            String jsonStr = JSON.toJSONString(result);

            /** 返回结果在MessageConverter 或 ExceptionResolver */
            MiniContext miniContext = MiniContext.getContext();
            miniContext.setReturnBody(jsonStr);

            stream.write(jsonStr.getBytes(charset));
        } catch (IOException e) {
            logger.error("写入字符串错误",e);
        }finally {
            if (Objects.nonNull(stream)){
                try {
                    stream.close();
                } catch (IOException e) {
                    logger.error("IOException",e);
                }
            }
        }

        return new ModelAndView();
    }

    private Result getResult(Exception ex) {
        if (ex instanceof BusinessException) {
            return ResultUtils.getBusinessExceptionResult((BusinessException) ex);
        }
        return ResultUtils.getFaultResult();
    }
}
