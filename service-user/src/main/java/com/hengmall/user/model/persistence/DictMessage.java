package com.server.entity.persistence;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典常量类
 * @author Administrator
 *
 */
public class DictMessage {	
	public final static Map<String,Map<String,String>> DICT = new HashMap<String,Map<String,String>>();
	static {
		//业务支付状态码
		DICT.put("buy_status", new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
				{put("1","单独购买（商品）");}
				{put("2","发起拼单（商品）");}
				{put("3","钱包充值");}
			});
		
		//支付状态
		DICT.put("order_status", new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
				//-3  逻辑删除
				{put("-2","已退款");}
				{put("-1","已取消");}
				{put("0","待支付");}
				{put("1","已支付");}
			});
		
		//支付方式
		DICT.put("paymethod", new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
				{put("1","微信支付");}
				{put("2","支付宝支付");}
			});
		
		//订单状态
		DICT.put("deliveryStatus", new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
				{put("0","待发货");}
				{put("1","待收货");}
				{put("2","已收货");}
			});
		
		//性别
		DICT.put("type_sex", new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
				{put("1","男");}
				{put("2","女");}
				{put("0","未知");}
			});
		
		//商品状态
		DICT.put("product_status", new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
				{put("1","上架");}
				{put("0","下架");}
			});
		
		//启用、禁用
		DICT.put("platform_flag", new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
				{put("0","启用");}
				{put("1","禁用");}
			});
		
		//店铺审核申请类型
		DICT.put("examine_type", new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
				{put("1","开店审核");}
				{put("2","店铺上传商品审核");}
				{put("3","店铺首页产品图组合审核");}
				{put("4","店铺首页轮播图审核");}
				{put("5","店铺订单对外发布权限审核");}
			});
		
		//店铺审核处理状态
		DICT.put("examine_state", new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
				{put("0","未处理");}
				{put("1","同意");}
				{put("2","拒绝");}
			});
		
		//order_extra订单表状态
		DICT.put("order_extra_status", new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;
				{put("-2","已退款");}
				{put("-1","已取消");}
				{put("0","待支付");}
				{put("1","待发货");}
				{put("2","待收货");}
				{put("3","已收货");}
			});
		
		
	}
	
	
	/**
	 * 获取字典label
	 * @param value 字典类型
	 * @param type	字典key
	 * @param defaultValue 没有的时候返回的值
	 * @return
	 */
	public static String getDictLabel(String value,String type,String defaultValue) {
		String label = DICT.get(value).get(type);
		if(label == null) {
			return defaultValue;
		}
		return label;
	}
	
	
	/**
	 * 获取字典key
	 * @param value 字典类型
	 * @param label	字典value
	 * @param defaultLabel 没有的时候返回的
	 * @return
	 */
	public static String getDictValue(String value, String label, String defaultLabel) {
		for(Map.Entry<String, String>m : DICT.get(value).entrySet()) {
			if(m.getValue().equals(label)) {
				return m.getKey();
			}
		}
		return defaultLabel;
	}
	
	
	/**
	 * 获取字典
	 * @param value 字典类型
	 * @return
	 */
	public static Map<String,String> getDict(String value) {
		return DICT.get(value);
	}

}
