package com.gongsi.mini.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gongsi.mini.core.exception.BusinessException;
import com.gongsi.mini.entities.StockShangHai;
import com.gongsi.mini.services.StockCodeService;
import com.gongsi.mini.services.http.base.HttpClientUtils;
import com.gongsi.mini.services.stock.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * Created by wuyu on 2019/4/11.
 */
@Slf4j
@Service
public class SendEmailTask {

    @Autowired
    private EmailService emailService;

    @Autowired
    private StockCodeService stockCodeService;

    public void execute(){
        log.info("SendEmailTask start");
        try {
            List<String> list = stockCodeService.selectActiveCode();
            if (CollectionUtils.isNotEmpty(list)){
                queryStock(list);
            }
        } catch (Exception e) {
            log.error("发送邮件失败",e);
        }
    }


    private void queryStock(List<String> codes){
        String result = query("http://hq.sinajs.cn/list="+String.join(",",codes));
        log.info("result={}",result);
    }

    /** 查询stock信息 */
    private String query(String url){
        return HttpClientUtils.getInstance().postJson(url, "",
                (response, charset) -> {
                    HttpEntity entity = response.getEntity();
                    StatusLine statusLine = response.getStatusLine();
                    int statusCode = statusLine.getStatusCode();
                    HttpEntity httpEntity = response.getEntity();
                    if (statusCode != 200) {
                        EntityUtils.consume(httpEntity);
                        throw new BusinessException("相应状态码异常");
                    }
                    return IOUtils.toString(entity.getContent(), charset);
                });
    }





}
