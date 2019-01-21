package com.hengmall.user.util;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.hengmall.user.dao.TbUserDao;
import com.hengmall.user.exception.XmallException;

public class TokenUtil {
	
    @Autowired
    private TbUserDao tbUserDao;
	
	/**
	 * 获取token若已经存在就获取缓存中有的，若不存在就产生新的传回
	 * @param userName
	 * @return
	 */
    public String getToken(String userName){
        JedisConnectUtil jedisConnectUtil = new JedisConnectUtil();
    	Boolean result =  jedisConnectUtil.exists(userName);
    	if(result){
    		String token = JedisConnectUtil.getnx(userName);
    		return token;
    	} else{
    		String token = MD5Util.md5Password(System.currentTimeMillis()+"wall"+userName);
    		JedisConnectUtil.setnx(userName, token);
    		JedisConnectUtil.setnx(token, userName);
    		jedisConnectUtil.expire(userName, 3600*1000*24);
    		jedisConnectUtil.expire(token, 3600*1000*24);
    		return token;
    	}
	}
    
	/**
	 * 校验token是否在缓存中存在 若存在则为真
	 * @param token
	 * @return
	 */
    public Boolean judgeToken(String token){
        JedisConnectUtil jedisConnectUtil = new JedisConnectUtil();
    	Boolean result =  jedisConnectUtil.exists(token);
    	return result;
    }
    
    public Boolean judgeRole(String token,String url){
        JedisConnectUtil jedisConnectUtil = new JedisConnectUtil();
    	Boolean result =  jedisConnectUtil.exists(token);
    	if(result){
    		String userName = JedisConnectUtil.getnx(token);
    		Set<String> permissions = tbUserDao.getPermissions(userName);
    		result = permissions.contains(url);
    	} else{
    		result = false;
    		throw new XmallException("你没有权限访问该接口或登录已失效");
    	}
    	return result;
    }
}
