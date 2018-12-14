package com.server.entity.sensitive;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 敏感词库返回entity
 * @author Administrator
 *
 */
public class SensitiveWordResponse{
	
	@ApiModelProperty(value = "id(修改需要)")
	private String id;
	
	@ApiModelProperty(value = "敏感词")
	private String txt;
	
	@ApiModelProperty(value = "添加时间")
	private Date insertDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	
}