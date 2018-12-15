package com.hengmall.user.model.order;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 直播推销员订单
 * @author Administrator
 *
 */
public class OrderLiveResponse {

	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "商品名称")
	private String productName;
	
	@ApiModelProperty(value = "订单状态(10未发货、20已发货、30确认收货)")
	private Integer status;
	
	@ApiModelProperty(value = "购买时间")
	private Date insertDate;
	
	@ApiModelProperty(value = "应收货款（应收货款为平台后台所填的商品成本），放大100倍")
	private Double money;
	
	@ApiModelProperty(value = "订单id")
	private String orderId;
	
	@ApiModelProperty(value = "直播推销员名称")
	private String userName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Double getMoney() {
		return money / 100;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
