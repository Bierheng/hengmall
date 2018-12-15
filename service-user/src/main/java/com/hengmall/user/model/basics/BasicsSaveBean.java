package com.hengmall.user.model.basics;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 增加or修改基类 Entity
 * @author Administrator
 *
 */
public class BasicsSaveBean {

	@ApiModelProperty(value="id为空添加，否则修改")
	protected String id;
	
	@ApiModelProperty(value="更新时间",hidden=true)
	protected Date updateDate;

	public Date getUpdateDate() {
		return new Date();
	}
	
	
	/**
	 * 是否为新纪录
	 * @return
	 */
	public boolean isNewRecord() {
		if(id==null || id.equals("")) {
			return true;
		}else {
			return false;
		}
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	
}
