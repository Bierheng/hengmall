package com.hengmall.goods.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2018/5/31.
 */
public class CommonUtils {

    /**
     * 创建单号
     *
     * @return
     */
    public static String createOutTradeNo() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String sysDate = df.format(new Date());// new Date()为获取当前系统时间

        String deal = sysDate.replaceAll("-", "");

        String with = deal.replaceAll(":", "");

        String dealWith = with.replaceAll(" ", "");

        int frontRandom = (int) (Math.random() * 9000 + 1000);

        int afterRandom = (int) (Math.random() * 9000 + 1000);

        String orderNumber = frontRandom + dealWith + afterRandom;

        return orderNumber;
    }


    /**
     * 获取一定长度的随机字符串，范围0-9，a-z
     * @param length   指定字符串长度
     * @param type  1：小写，2：大写，3：大小写，4：数字大小写，其他：数字
     * @return
     */
    public static String getRandomStringByLength(int length, int type) {
        String base = "0123456789";
        if (type == 1) {
            base = "abcdefghijklmnopqrstuvwxyz";
        } else if (type == 2) {
            base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        } else if (type == 3) {
            base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        } else if (type == 4) {
            base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        }

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 合并两个JSONObject
     *
     * @param source
     * @param target
     * @return
     * @throws JSONException
     */
    public static JSONObject deepMerge(JSONObject source, JSONObject target) throws JSONException {

        for (String key : source.keySet()) {
            Object value = source.get(key);
            if (!target.containsKey(key)) {
                // new value for "key":
                target.put(key, value);
            } else {
                // existing value for "key" - recursively deep merge:
                if (value instanceof JSONObject) {
                    JSONObject valueJson = (JSONObject) value;
                    deepMerge(valueJson, target.getJSONObject(key));
                } else {
                    target.put(key, value);
                }
            }
        }
        return target;
    }


    /**
     * @param date
     * @return
     * @throws ParseException
     */
    public static int TransformationTimeStamp(Date date) throws ParseException {
        return (int) (((Date) date).getTime() / 1000);
    }

    public static int TransformationTimeStamp(String date, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date dateStart = format.parse(date);
        return (int) (dateStart.getTime() / 1000);
    }

    /**
	 * 判断传入的参数是否为空
	 * 当不为空时为TRUE 为“null”或者“0”时返回false
	 * @param length：指定字符串长度
	 * @return
	 */
	public static Boolean judge (String draw) {
		if("null".equals( draw) || "0".equals( draw)){
			return false;
		}else{
			return true;
		}
	}
	
    /**
    * 生成小程序二维码 -并保存到本地-返回二维码地址
    * 
    * @param request
    * @param access_token
    * @param path
    * @param width
    * @param scene
    * @return
    */
    public static String createwxaqrcode(HttpServletRequest request) {
	String path = "";	
	String width = "430";
	String scene = "XXX";
    String appid = new String();
    String appScret = new String();
    Properties prop = new Properties();

    System.out.println(appid);
    System.out.println(appScret);
    String access_token = WechatUtil.getWXToken();
    System.out.println("获取微信token为："+access_token);
    
    String URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + access_token;
    // 二维码图片位置
    String downloadUrl = null;
    try {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, String> line_color = new HashMap<String, String>();
    line_color.put("r", "0");
    line_color.put("g", "0");
    line_color.put("b", "0");
    map.put("path", path);
    map.put("width", width);
    map.put("scene", scene);
    map.put("auto_color", false);
    map.put("line_color", line_color);
    net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(map);
    downloadUrl = CommonUtils.HttpPostWithJson(URL, json.toString());
    // 返回给前端的后台服务器文件下载路径
    } catch (Exception e) {
    e.printStackTrace();
    }
    return downloadUrl;
    }

    public static String HttpPostWithJson(String url, String json) {
		String returnValue = "这是默认返回值，接口调用失败";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		try{
			//第一步：创建HttpClient对象
		 httpClient = HttpClients.createDefault();
		 	
		 	//第二步：创建httpPost对象
	        HttpPost httpPost = new HttpPost(url);
	        
	        //第三步：给httpPost设置JSON格式的参数
	        StringEntity requestEntity = new StringEntity(json,"utf-8");
	        requestEntity.setContentEncoding("UTF-8");    	        
	        httpPost.setHeader("Content-type", "application/json");
	        httpPost.setEntity(requestEntity);
	       
	       //第四步：发送HttpPost请求，获取返回值
	       returnValue = httpClient.execute(httpPost,responseHandler); //调接口获取返回值时，必须用此方法
	      
		}
		 catch(Exception e)
		{
			 e.printStackTrace();
		}
		
		finally {
	       try {
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
		 //第五步：处理返回值
	     return returnValue;
	}

}
