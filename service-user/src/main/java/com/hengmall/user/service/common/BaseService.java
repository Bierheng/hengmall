package com.hengmall.user.service.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hengmall.user.util.JedisConnectUtil;
import com.hengmall.user.util.config.Global;

/**
 * service基类
 * @author Administrator
 *
 */
public abstract class BaseService {

	/**
	 * 数据范围过滤
	 * @param token 
	 * @param tbUser tbUser表别名
	 * @return
	 */
	public static String dataScopeFilter(String token,String tbUser) {
		//如果是调试模式则不过滤，因为没有登录就没有token
		if(Global.getConfig("login.isFiter").equals("false")) {
			return "";
		}
		StringBuilder sqlString = new StringBuilder();
		JSONObject obj = JSON.parseObject(JedisConnectUtil.hget(Global.getConfig("login.userDb"), token));
		String userId = obj.getString("userId");
		//是管理员则跳过过滤
		if(obj.getLong("roleId") != 1) {
			sqlString.append("AND "+tbUser+".user_id="+userId);
			return sqlString.toString();
		}
		return "";
	}
}
