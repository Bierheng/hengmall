package com.hengmall.goods.service;


import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hengmall.goods.util.MD5Util;


@Service
public class WechatPayService {

    private Logger logger = LoggerFactory.getLogger(getClass());


    //支付所需参数
    @Value("${small.appid}")
    private String appid;
    @Value("${small.mch_id}")
    private int mch_id;
    @Value("${small.appSecret}")
    private String appSecret;


    @SuppressWarnings("unchecked")
    public static String createSign(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=1lfj87uyhd6fgh45gh89jk09oi678r4h");
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        return sign;
    }
    /**
     * @param parameters 请求参数
     * @return
     * @author Mark
     * @Description：将请求参数转换为xml格式的string
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }
    /**
     * 根据code 获取openid
     *
     * @param code
     * @return
     */
    public String getOpenId(String code) throws Exception {
        //code换openid 接口
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

        String requestUrl = WX_URL.replace("APPID", appid).
                replace("SECRET", appSecret).replace("JSCODE", code).
                replace("authorization_code", "authorization_code");

        logger.info("请求前拼接url：{}", requestUrl);

        // 发起GET请求获取凭证
        JSONObject jsonObject = doGetStr(requestUrl);

        logger.info("请求后获取的参数：{}", jsonObject);

        String openid = jsonObject.getString("openid");
        //jsonObject.getString("session_key");

        return openid;
    }
    /**
     * get请求
     */
    public JSONObject doGetStr(String url) {

        logger.info("doGetStr：" + url);
        /*HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();*/

        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;

        try {
            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");

                logger.info("返回结果：" + result);
                jsonObject = JSONObject.parseObject(result);

            }
        } catch (Exception e) {
            System.out.println("出错信息：" + e);
        }
        return jsonObject;
    }

}
