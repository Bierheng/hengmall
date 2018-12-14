package com.hengmall.goods.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by juunew on 2017/6/24.
 */
public class HttpUtil {

    /**
     * 发送post请求工具类
     * @param url
     * @param params
     * @return
     */
    public String post(String url, String params) throws IOException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        try {
            HttpPost httppost = new HttpPost(url);
            httppost.addHeader("charset", "utf-8");
            httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0");

            byte[] bytes = params.getBytes(Charset.forName("utf-8"));
            ByteArrayEntity se = new ByteArrayEntity(bytes, ContentType.APPLICATION_JSON);

            httppost.setEntity(se);

            CloseableHttpResponse execute = closeableHttpClient.execute(httppost);
            HttpEntity httpEntity = execute.getEntity();
            String result = "";
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity, "UTF-8").replace("\"{", "{").replace("}\"", "}").replaceAll("\\\\", "");
            }
            return result;
        }finally {
            closeableHttpClient.close();
        }
    }
}
