package com.hengmall.goods.util;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PublicUtil {

	/**
	 * 判断对象是否Empty(null或元素为0)
	 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 *
	 * @param pObj 待检查对象
	 *
	 * @return boolean 返回的布尔值 ,为空返回true
	 */
	public static boolean isEmpty(Object pObj) {
		if (pObj == null) {
			return true;
		}
		if (pObj == "") {
			return true;
		}
		if (pObj instanceof String) {
			return ((String) pObj).length() == 0;
		} else if (pObj instanceof Collection) {
			return ((Collection) pObj).isEmpty();
		} else if (pObj instanceof Map) {
			return ((Map) pObj).size() == 0;
		}
		return false;
	}


    /**
     * 判断对象为空
     * 
     * @param obj
     *            对象名
     * @return 是否为空,为空时返回真，
     */
    @SuppressWarnings("rawtypes")
    public static boolean objIsEmpty(Object obj)
    {
        if (obj == null)
        {
            return true;
        }
        if ((obj instanceof List))
        {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String))
        {
            return ((String) obj).trim().equals("");
        }
        return false;
    }

	
	/**
	 * 判断对象是否为NotEmpty(!null或元素大于0)
	 * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
	 *
	 * @param pObj 待检查对象
	 *
	 * @return boolean 返回的布尔值
	 */
	public static boolean isNotEmpty(Object pObj) {
		if (pObj == null) {
			return false;
		}
		if (pObj == "") {
			return false;
		}
		if (pObj instanceof String) {
			return ((String) pObj).length() != 0;
		} else if (pObj instanceof Collection) {
			return !((Collection) pObj).isEmpty();
		} else if (pObj instanceof Map) {
			return ((Map) pObj).size() != 0;
		}
		return true;
	}
	
	    //自动生成名字（中文）
	public static String getRandomJianHan(int len) {
	String ret = "";
	for (int i = 0; i < len; i++) {
	    String str = null;
	    int hightPos, lowPos; // 定义高低位
	    Random random = new Random();
	    hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
	    lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
	   byte[] b = new byte[2];
	   b[0] = (new Integer(hightPos).byteValue());
	   b[1] = (new Integer(lowPos).byteValue());
	   try {
	       str = new String(b, "GBK"); // 转成中文
	   } catch (UnsupportedEncodingException ex) {
	       ex.printStackTrace();
	   }
	   ret += str;
	}
	return ret;
}
	/**
	* 随机获取一个汉字来组成名字
    * 
    * @return
    */
   public static String getName() {
       String nameStr = "";
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
           nameStr = new String(codeArr, "GB2312");
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
       return nameStr;
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
	
}
