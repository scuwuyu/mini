package com.gongsi.mini.services.http.base;

import com.gongsi.mini.core.ensure.Ensure;
import com.gongsi.mini.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Created by 吴宇 on 2018-06-04.
 */
@Slf4j
public class HttpClientUtils {
    private HttpClientUtils(){}
    private static final HttpClientUtils INSTANCE = new HttpClientUtils();
    public static HttpClientUtils getInstance() {
        return INSTANCE;
    }

    public <T> T postJson(String url, String message, Callback<T> callback) {
        return postJson(url, message, "UTF-8", callback);
    }

    public <T> T postJson(String url, String message, String charset, Callback<T> callback) {
        return post(url, message, charset, "application/json", callback);
    }

    public <T> T post(String url, String message, String charset, String contentType, Callback<T> callback) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = getCloseableHttpClient(3000,3000);
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", contentType);
            httpPost.addHeader("charset", "UTF-8");

            StringEntity strEntity = new StringEntity(message, charset);
            httpPost.setEntity(strEntity);
            response = client.execute(httpPost);

            checkResponse(url, response,message);

            return callback.execute(response, charset);
        } catch (Exception ex) {
            throw new BusinessException(ex.getMessage());
        } finally {
            closeResponse(response);
            closeHttpClient(client);
        }
    }

    public <T> T get(String url, Callback<T> callback) {
        return get(url, "UTF-8", callback);
    }

    public <T> T get(String url, String charset, Callback<T> callback) {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            client = getCloseableHttpClient(3000,3000);
            HttpGet httpGet = new HttpGet(url);
            response = client.execute(httpGet);

            checkResponse(url, response,null);

            return callback.execute(response, charset);

        } catch (Exception ex) {
            throw new BusinessException(ex.getMessage());
        } finally {
            closeResponse(response);
            closeHttpClient(client);
        }
    }

    private void checkResponse(String url,CloseableHttpResponse response,String message) throws IOException{
        HttpEntity entity = response.getEntity();
        Ensure.that(entity).isNotNull(String.format("请求没有返回结果[url=%s,message=%s]", url, message));
        if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
            int statusCode = response.getStatusLine().getStatusCode();
            InputStream in = entity.getContent();
            in.close();
            String errMsg = String.format("请求返回异常[url=%s,message=%s,statusCode=%s]", url, message, statusCode);
            throw new BusinessException(errMsg);
        }
    }

    private void closeResponse(CloseableHttpResponse response) {
        try {
            if (Objects.nonNull(response)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    entity.getContent().close();
                }
                response.close();
            }
        } catch (IOException e) {
            log.error("Close response failed", e);
        }
    }

    private void closeHttpClient(CloseableHttpClient client) {
        try{
            if (Objects.nonNull(client)) {
                client.close();
            }
        } catch (IOException e) {
            log.error("Close httpclient failed", e);
        }
    }

    private CloseableHttpClient getCloseableHttpClient(Integer connectTimeout, Integer socketTimeout) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout)
                .build();
        return HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
    }
}
