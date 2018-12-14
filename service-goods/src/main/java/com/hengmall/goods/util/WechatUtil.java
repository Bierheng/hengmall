package com.hengmall.goods.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.hengmall.goods.model.constitute.WechatRefundApiResult;

import net.sf.json.JSONObject;

public class WechatUtil {
    private static Log logger = LogFactory.getLog(WechatUtil.class);
    protected static String appid = "";
    protected static String mch_id = "";
    protected static String apiKey = "";
    protected static String appScret = "";

   
    public static WechatRefundApiResult wxRefund(String out_trade_no, Double orderMoney, Double refundMoney) {
        try {
            Properties prop = new Properties();
            prop.load(WechatUtil.class.getClassLoader().getResourceAsStream("application.properties"));
            appid = prop.getProperty("small.appid");
            mch_id = prop.getProperty("small.mch_id");
            apiKey = prop.getProperty("small.apiKey");

        } catch (IOException e) {
            e.printStackTrace();
        }

        BigDecimal bdOrderMoney = new BigDecimal(orderMoney, MathContext.DECIMAL32);
        BigDecimal bdRefundMoney = new BigDecimal(refundMoney, MathContext.DECIMAL32);
        Map<Object, Object> params = buildRequsetMapParam(out_trade_no, bdOrderMoney, bdRefundMoney);
        String mapToXml = MapUtils.convertMap2Xml(params);
        String reponseXml = sendSSLPostToWx(mapToXml, WechatConfig.getSslcsf());
        WechatRefundApiResult result = (WechatRefundApiResult) XmlUtil.xmlStrToBean(reponseXml, WechatRefundApiResult.class);
        return result;
    }

    private static Map<Object, Object> buildRequsetMapParam(String out_trade_no, BigDecimal bdOrderMoney, BigDecimal bdRefundMoney) {
        Map<Object, Object> params = new HashMap<Object, Object>();
        params.put("appid", appid);
        params.put("mch_id", mch_id);
        params.put("nonce_str", UUID.randomUUID().toString().replaceAll("-", ""));
        params.put("out_trade_no", out_trade_no);
        params.put("out_refund_no", getBundleId());
        params.put("total_fee", bdOrderMoney.multiply(new BigDecimal(100)).intValue());
        params.put("refund_fee", bdRefundMoney.multiply(new BigDecimal(100)).intValue());
        params.put("op_user_id", mch_id);
        params.put("sign", arraySign(params, apiKey));
        return params;
    }

    public static String sendSSLPostToWx(String mapToXml, SSLConnectionSocketFactory sslcsf) {
        HttpPost httPost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
        httPost.addHeader("Connection", "keep-alive");
        httPost.addHeader("Accept", "*/*");
        httPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httPost.addHeader("Host", "api.mch.weixin.qq.com");
        httPost.addHeader("X-Requested-With", "XMLHttpRequest");
        httPost.addHeader("Cache-Control", "max-age=0");
        httPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        httPost.setEntity(new StringEntity(mapToXml, "UTF-8"));
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslcsf).build();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httPost);
            HttpEntity entity = response.getEntity();
            String xmlStr = EntityUtils.toString(entity, "UTF-8");
            return xmlStr;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
    /**
     * 支付交易ID
     */
    public static String getBundleId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String tradeno = dateFormat.format(new Date());
        String str = "000000" + (int) (Math.random() * 1000000);
        tradeno = tradeno + str.substring(str.length() - 6);
        return tradeno;
    }

    public static String arraySign(Map<Object, Object> params, String paySignKey) {
        boolean encode = false;
        Set<Object> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                try {
                    temp.append(URLEncoder.encode(valueString, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                temp.append(valueString);
            }
        }
        temp.append("&key=");
        temp.append(paySignKey);
        String packageSign = MD5Util.getMessageDigest(temp.toString());
        return packageSign;
    }

    /**
    * 获取accessToken
    * @param appID微信公众号凭证
    * @param appScret微信公众号凭证秘钥
    * @return
    */
    public static String getAccessToken() {
    	Properties prop = new Properties();
		try {
			prop.load(WechatUtil.class.getClassLoader().getResourceAsStream("application.properties"));
			appid = prop.getProperty("small.appid");
		    appScret = prop.getProperty("small.appSecret");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    String token = new String();
    // 访问微信服务器
    String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret="
    + appScret;
    try {
    URL getUrl=new URL(url);
    HttpURLConnection http=(HttpURLConnection)getUrl.openConnection();
    http.setRequestMethod("GET"); 
    http.setRequestProperty("Content-Type",
    "application/x-www-form-urlencoded");
    http.setDoOutput(true);
    http.setDoInput(true);
    http.connect();
    InputStream is = http.getInputStream(); 
    int size = is.available(); 
    byte[] b = new byte[size];
    is.read(b);

    String message = new String(b, "UTF-8");

    JSONObject json = JSONObject.fromObject(message);
    token = json.getString("access_token");
    } catch (MalformedURLException e) {
    e.printStackTrace();
    } catch (IOException e) {
    e.printStackTrace();
    }
    return token;
    }
    
    /**
    * 获取accessToken
    * @param appID微信公众号凭证
    * @param appScret微信公众号凭证秘钥
    * @return
    */
    public static String getWXToken() {
    	String AccessToken = new String();
    	AccessToken = JedisUtil.getnx("AccessToken");
    	if(PublicUtil.isEmpty(AccessToken)){
    		AccessToken = WechatUtil.getAccessToken();
    		if(PublicUtil.isNotEmpty(AccessToken)){
    		   JedisUtil.redisCache("AccessToken", AccessToken, 7200);
    		}else{
    			AccessToken = WechatUtil.getAccessToken();
    			logger.info("重新获取AccessToken，之前获取已经失败一次");
    		}
    	}
    	return AccessToken;
    }
    
}
