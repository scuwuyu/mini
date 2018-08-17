package com.gongsi.mini.services.stock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gongsi.mini.core.exception.BusinessException;
import com.gongsi.mini.entities.StockShangHai;
import com.gongsi.mini.services.http.base.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyu on 2018/8/16.
 */
@Slf4j
@Service
public class StockServiceImpl {

    private static final String URL_SHANGHAI = "http://ipo.sseinfo.com/info/commonQuery.do?isPagination=true&sqlId=COMMON_SSE_IPO_IPO_LIST_L&pageHelp.pageSize=5";
    private static final String URL_SHENZHEN = "http://www.cninfo.com.cn/eipo/index.jsp";


    /** 查询股票ipo信息 */
    public List<StockShangHai> query(){
        List<StockShangHai> list = queryShangHai();

        try {
            List<StockShangHai> stockShenZhenList = queryShenZhen();
            list.addAll(stockShenZhenList);
        }catch (Exception e){
            log.error("查询深圳证券交易所股票错误",e);
        }

        return list;
    }



    /** 查询上海ipo信息 */
    public List<StockShangHai> queryShangHai(){
        return HttpClientUtils.getInstance().postJson(URL_SHANGHAI, "",
                (response, charset) -> {
                    HttpEntity entity = response.getEntity();
                    StatusLine statusLine = response.getStatusLine();
                    int statusCode = statusLine.getStatusCode();
                    HttpEntity httpEntity = response.getEntity();
                    if (statusCode != 200) {
                        EntityUtils.consume(httpEntity);
                        throw new BusinessException("相应状态码异常");
                    }
                    String toString = IOUtils.toString(entity.getContent(), charset);
                    JSONObject jsonObject = JSON.parseObject(toString);

                    return JSON.parseArray(jsonObject.get("result").toString(),StockShangHai.class);
                });
    }

    /** 查询深圳ipo信息 */
    public List<StockShangHai> queryShenZhen() throws IOException {
        Document document = Jsoup.connect(URL_SHENZHEN).timeout(3000).get();
        Elements elements = document.select("[id=newstock_table]").select("tbody").select("tr");

        List<StockShangHai> list = new ArrayList<>();

        elements.forEach(item -> {
            Elements tds = item.select("td");

            StockShangHai stock = new StockShangHai();
            stock.setSecurity_code(tds.get(0).text());
            stock.setSecurity_name(tds.get(1).text());
            stock.setTotal_initial_issue(tds.get(5).text());
            stock.setOnline_issuance_date(tds.get(2).text());
            stock.setIssue_price(tds.get(3).text());

            stock.setAnnounce_success_rate_result_date(tds.get(11).text());
            stock.setListed_date(tds.get(12).text());
            list.add(stock);
        });

        return list;
    }
}
