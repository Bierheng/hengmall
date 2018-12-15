package com.hengmall.user.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hengmall.user.util.config.Global;

/**工具类
 * Created by Administrator on 2018/5/31.
 */
public class CommonUtils {

	/**
	 * 创建单号
	 * 
	 * @return
	 */
	public static String createOutTradeNo() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
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
	 * 
	 * @param length：指定字符串长度
	 * @return
	 */
	public static String getRandomStringByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
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
	 * json数组转string类型的数组
	 * @param strArray
	 * @return
	 */
	public static String[] toStringArray(String strArray) {
		Object[] oarr = JSON.parseArray(strArray).toArray();
		String[] arr = new String[oarr.length];
		for (int i = 0; i < oarr.length; i++) {
			arr[i] = String.valueOf(oarr[i]);
		}
		return arr;
	}
	
	/**
	 * 删除数组元素
	 * @param index 删除的下标
	 * @param array 数组
	 * @return
	 */
	public static String[] deleteArray(int index, String array[]) {
        //数组的删除其实就是覆盖前一位
		String[] arrNew = new String[array.length - 1];
        for (int i = 0; i < array.length - 1; i++) {
            if (i < index) {
                arrNew[i] = array[i];
            } else {
                arrNew[i] = array[i + 1];
            }
        }
        return arrNew;
    }
	
	
	/**
	 * 数组转字符串
	 * @param array
	 * @return
	 */
	public static String toString(String array[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if(i < array.length-1) {
				sb.append(array[i] + ",");
			}else {
				sb.append(array[i]);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 根据token拿当前登录用户属性
	 * @param token
	 * @return
	 */
	public static String getUserAttribute(String token,String attribute) {
		return JSON.parseObject(JedisConnectUtil.hget(Global.getConfig("login.userDb"), token)).getString(attribute);
	}
	
	
	/**
	 * 根据中文地址拿经纬度
	 * @param address 中文地址
	 * @return Map<(经度:lng),(纬度:lat)>
	 * @throws Exception 
	 */
	public static Map<String,String> getLngOrLat(String address) throws Exception {
		StringBuffer url = new StringBuffer();
    	url.append("http://api.map.baidu.com/geocoder/v2/?address=")
    	   .append(address)
    	   .append("&output=json&ak="+Global.getConfig("baidu.ak"));
    	String response =  HttpReqUtil.doGet(url.toString());
    	
    	JSONObject jsonObj = JSON.parseObject(response);
    	if(jsonObj.getIntValue("status") == 0){
    		Map<String,String> map = new HashMap<String,String>();
    		JSONObject location = JSON.parseObject(JSON.parseObject(jsonObj.getString("result")).getString("location"));
    		
        	map.put("lng", location.getString("lng"));
        	map.put("lat", location.getString("lat"));
        	return map;
    	}else if(jsonObj.getIntValue("status") == 1){
    		throw new Exception(jsonObj.getString("msg"));
    	}else{
    		response = new String(response.getBytes("ISO-8859-1"),"utf-8");
    		throw new Exception(JSON.parseObject(response).getString("message"));
    	}
	}
	
	
	/**
	 * 随机获取图片
	 * @return
	 * @throws Exception 
	 */
	public static String getImg() throws Exception {
		StringBuffer url = new StringBuffer();
    	url.append("https://api.ixiaowai.cn/gqapi/gqapi.php?")
    	   .append("&return=json");
    	String response =  HttpReqUtil.doGet(url.toString());
    	JSONObject jsonObj = JSON.parseObject(response);
    	if(jsonObj.getIntValue("code") == 200) {
    		return jsonObj.getString("imgurl");
    	}else {
    		throw new Exception(JSON.parseObject(response).getString("message"));
    	}
	}
	
	/**
	 * 随机获取一个汉字来组成名字
	    * 
	    * @return
	    */
   public static String getName() {
	   StringBuffer nameStr = new StringBuffer();
	   for (int i = 0; i < 2; i++) {
		   int highCode, lowCode;
		   Random random = new Random();
		   // 区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
		   highCode = (176 + Math.abs(random.nextInt(71)));
		   random = new Random();
		   // 位码，0xA0打头，范围第1~94列
		   lowCode = 161 + Math.abs(random.nextInt(94));
		
		   byte[] codeArr = new byte[2];
		   codeArr[0] = (new Integer(highCode)).byteValue();
		   codeArr[1] = (new Integer(lowCode)).byteValue();
		   try {
		   // 区位码组合成汉字
		   nameStr.append(new String(codeArr, "GBK"));
		   } catch (UnsupportedEncodingException e) {
		   e.printStackTrace();
		   }
		   if(i < 1) {
		   nameStr.append("***");		    	   
		   }
		}
	   return nameStr.toString();
   }
}
