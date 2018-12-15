package com.hengmall.user.model.record;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分销收益记录列表  response
 * @author Administrator
 *
 */
public class ProfitResponse {

	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "收益类型 ，1:一级分销,2:二级分销")
	private Integer profitType;
	
	@ApiModelProperty(value = "收益金额。单位：分")
	private Double profitMoney;
	
	@ApiModelProperty(value = "用户名")
	private String userName;
	
	@ApiModelProperty(value = "创建时间")
	private Date cretateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getProfitType() {
		return profitType;
	}

	public void setProfitType(Integer profitType) {
		this.profitType = profitType;
	}


	public Double getProfitMoney() {
		return profitMoney/100;
	}

	public void setProfitMoney(Double profitMoney) {
		this.profitMoney = profitMoney;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCretateTime() {
		return cretateTime;
	}

	public void setCretateTime(Date cretateTime) {
		this.cretateTime = cretateTime;
	}
	
	
}
