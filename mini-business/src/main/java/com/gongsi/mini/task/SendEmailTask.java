package com.gongsi.mini.task;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.exception.BusinessException;
import com.gongsi.mini.dtos.StockDTO;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuyu on 2019/4/11.
 */
@Slf4j
@Service
public class SendEmailTask {

    private static Pattern PATTERN = Pattern.compile("\"(.*?)\"");

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
        Matcher matcher = PATTERN.matcher(result);

        List<StockDTO> list = new ArrayList<>();

        while (matcher.find()){
            String group = matcher.group();
            String[] strings = group.split(",");

            StockDTO stockDTO = new StockDTO(strings[0], new BigDecimal(strings[2]),new BigDecimal(strings[3]));
            list.add(stockDTO);
        }

        log.info("list={}",JSON.toJSONString(list,true));
    }

    /** 查询stock信息 */
    private String query(String url){
        return HttpClientUtils.getInstance().postJson(url,"", "GBK",
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
