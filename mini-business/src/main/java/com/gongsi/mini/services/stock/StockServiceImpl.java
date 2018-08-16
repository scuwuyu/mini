package com.gongsi.mini.services.stock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gongsi.mini.core.exception.BusinessException;
import com.gongsi.mini.entities.Stock;
import com.gongsi.mini.services.http.base.HttpClientUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;

import java.util.List;

/**
 * Created by wuyu on 2018/8/16.
 */
public class StockServiceImpl {

    private static final String URL_SHANGHAI = "http://ipo.sseinfo.com/info/commonQuery.do?isPagination=true&sqlId=COMMON_SSE_IPO_IPO_LIST_L&pageHelp.pageSize=1";


    public static void main(String[] args) {

        HttpClientUtils.getInstance().postJson(URL_SHANGHAI, JSON.toJSONString(null),
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
                    JSONArray array = (JSONArray)jsonObject.get("result");
//                    Iterator<>array.iterator();
                    List<Stock> list = JSON.parseArray(object, Stock.class);
                    System.out.println("[array]:" + array);
                    System.out.println("[response]:" + StringUtils.substring(toString,0,2000));
                    return null;
                });
    }
}
