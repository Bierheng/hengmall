package com.hengmall.user.model.sensitive;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;


/**
 * 新增敏感词 entity  请求需要的参数
 * @author Administrator
 *
 */
public class SensitiveWordNewRequest{
	

	@ApiModelProperty(value = "敏感词")
	@NotEmpty(message="敏感词不能为空")
	private String txt;
	
	@ApiModelProperty(value = "新增时间",hidden=true)
	private Date insertDate;

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Date getInsertDate() {
		return new Date();
	}
}