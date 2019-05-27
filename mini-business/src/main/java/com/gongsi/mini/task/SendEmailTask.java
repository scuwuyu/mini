package com.gongsi.mini.task;

import com.alibaba.fastjson.JSON;
import com.gongsi.mini.core.exception.BusinessException;
import com.gongsi.mini.dtos.StockDTO;
import com.gongsi.mini.services.StockCodeService;
import com.gongsi.mini.services.http.base.HttpClientUtils;
import com.gongsi.mini.services.stock.EmailService;
import com.gongsi.mini.utils.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

        if (!isChargeTime()){
            return;
        }
        log.info("===");
        try {

            List<String> list = stockCodeService.selectActiveCode();
            if (CollectionUtils.isNotEmpty(list)){
                List<StockDTO> stockDTOs = queryStock(list);
                if (CollectionUtils.isNotEmpty(stockDTOs)){
                    StockDTO activeOne = stockDTOs.get(0);

                    stockDTOs = queryStock(stockCodeService.selectAll());

                    Optional<BigDecimal> min = stockDTOs.stream().map(StockDTO::getChange).min(BigDecimal::compareTo);
                    if (min.isPresent()){
                        BigDecimal change = activeOne.getChange().subtract(min.get());
                        if (change.compareTo(new BigDecimal(2))>=0){
                            emailService.send("恭喜你中奖了!");
                        }
                    }
                }
            }

        } catch (Exception e) {
            log.error("发送邮件失败",e);
        }
    }


    private List<StockDTO> queryStock(List<String> codes){
        String result = query("http://hq.sinajs.cn/list="+String.join(",",codes));
        Matcher matcher = PATTERN.matcher(result);

        List<StockDTO> list = new ArrayList<>();

        while (matcher.find()){
            String group = matcher.group();
            String[] strings = group.split(",");

            StockDTO stockDTO = new StockDTO(strings[0], new BigDecimal(strings[2]),new BigDecimal(strings[3]));
            list.add(stockDTO);
        }
        log.info("list={}",list.stream().map(StockDTO::getDesc).collect(Collectors.toList()));
        return list;
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

    private boolean isChargeTime(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour<9||hour>14){
            return false;
        }
        int week = calendar.get(Calendar.DAY_OF_WEEK);

        return 1<week&&week<7;
    }

}
