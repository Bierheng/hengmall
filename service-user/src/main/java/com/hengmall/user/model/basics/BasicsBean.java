package com.server.entity.basics;

import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;

import io.swagger.annotations.ApiModelProperty;

/**
 * 基类 Entity
 * @author Administrator
 *
 */
public class BasicsBean {

	@ApiModelProperty(value = "页码(为空则默认为第一页)")
	protected int page = 1;  //页码，默认第一页
	
	@ApiModelProperty(value = "每页显示条数(为空则每页显示n条)")
	protected int listPage = 10; //每页条数,默认每页10条
	
	@ApiModelProperty(value = "是否允许分页 true:分页,false：不分页")
	protected boolean isPage = true;
	
	@ApiModelProperty(hidden=true)
	protected int state = 0;		//状态，  0：正常

	@ApiModelProperty(hidden=true)
	protected String token;		//token 用于识别当前登录用户

	
	@ApiModelProperty(hidden=true)
	protected Map<String, String> sqlMap;		//自定义sql
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page != 0) {
			this.page = page;			
		}
	}

	public int getListPage() {
		return listPage;
	}

	public void setListPage(int listPage) {
		if(listPage != 0) {
			this.listPage = listPage;			
		}
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@JsonIgnore
	@XmlTransient
	public Map<String, String> getSqlMap() {
		if (sqlMap == null){
			sqlMap = Maps.newHashMap();
		}
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}

	public boolean getIsPage() {
		return isPage;
	}

	public void setIsPage(boolean isPage) {
		this.isPage = isPage;
	}
	
}
