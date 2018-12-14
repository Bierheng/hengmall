package com.server.entity.order;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分销收益订单
 * @author Administrator
 *
 */
public class OrderDistributionResponse {

	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "购买时间")
	private Date insertDate;
	
	@ApiModelProperty(value = "实付款,单位为：分")
	private Double pay;
	
	@ApiModelProperty(value = "订单状态(10未发货、20已发货、30确认收货)")
	private Integer status;
	
	@ApiModelProperty(value = "购买人微信头像")
	private String headPortrait;
	
	@ApiModelProperty(value = "购买人名称")
	private String nickname;
	
	@ApiModelProperty(value = "购买人收益（三级分销产生的收益），单位为：分")
	private Double profit;
	
	@ApiModelProperty(value = "订单id")
	private String orderId;
	
	@ApiModelProperty(value = "店铺名")
	private String shopsName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Double getPay() {
		return pay / 100;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Double getProfit() {
		return profit / 100;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}
	
	
}
