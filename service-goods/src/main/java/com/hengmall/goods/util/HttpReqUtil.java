package com.hengmall.goods.util;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;

/**
 * Created by juunew on 2017/9/2.
 */
public class HttpReqUtil {

    private static Logger  log = LoggerFactory.getLogger(HttpReqUtil.class);

    /**
     * IP地址
     * @param request
     * @return
     */
    public static String getRemortIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ip)) {
            ip = request.getRemoteAddr();
        } else {
            if (StringUtils.contains(ip, ",")) {
                String[] ips = StringUtils.split(ip, ",");
                if (ips.length > 1) {
                    ip = ips[0];
                }
            }
        }
        return ip;
    }


    /**
     * 发送https请求
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return 返回微信服务器响应的信息
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {
// 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            //sslContext.init(null, tm, new Java.security.SecureRandom());
            sslContext.init(null, tm, new SecureRandom());

// 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);

// 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
// 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
// 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
// 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
// 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            log.error("连接超时：{}", ce);
        } catch (Exception e) {
            log.error("https请求异常：{}", e);
        }
        return null;
    }

    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 发送 get请求
     */
    public static String doGet(String url)throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpGet httpget = new HttpGet(url);
        System.out.println("executing request " + httpget.getURI());
            try {
                // 执行get请求.
                response = httpclient.execute(httpget);
                StatusLine status = response.getStatusLine();
                int state = status.getStatusCode();
                if (state == HttpStatus.SC_OK) {
                	HttpEntity responseEntity = response.getEntity();
                	String jsonString = EntityUtils.toString(responseEntity);
                	return jsonString;
                }
                else{
    				 log.error("请求返回:"+state+"("+url+")");
    			}
            	
            } finally {
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
    				httpclient.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
            }
        return null;
    }
}
