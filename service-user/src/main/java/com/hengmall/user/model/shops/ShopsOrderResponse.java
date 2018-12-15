package com.hengmall.user.model.shops;

import java.util.Date;

import com.hengmall.user.model.persistence.DictMessage;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺佣金返回 entity
 * @author Administrator
 *
 */
public class ShopsOrderResponse{
	
	@ApiModelProperty(value = "店铺名称")
	private String shopsName;
	
	@ApiModelProperty(value = "店铺佣金")
	private String value;
	
	@ApiModelProperty(value = "用户昵称")
	private String nickname;
	
	@ApiModelProperty(value = "支付金额")
	private Double price;
	
	@ApiModelProperty(value = "支付方式")
	private String paymethod;		//1：微信支付；2：支付宝支付

	@ApiModelProperty(value = "支付状态")
	private String status;	//-3逻辑删除,-2已退款,-1已取消,0-待支付,1-已支付
	
	@ApiModelProperty(value = "物流状态(删除)")
	private String deliveryStatus;  //0：待发货，1：待收货，2：已收货
	
	@ApiModelProperty(value = "订单创建时间")
	private Date orderCTime;
	
	@ApiModelProperty(value = "店铺佣金创建时间")
	private Date valCTime;

	
	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Double getPrice() {
		return price / 100d;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPaymethod() {
		return DictMessage.getDictLabel("paymethod", paymethod, "");
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getStatus() {
		return DictMessage.getDictLabel("order_status", status, "");
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeliveryStatus() {
		return DictMessage.getDictLabel("deliveryStatus", deliveryStatus, "");
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Date getOrderCTime() {
		return orderCTime;
	}

	public void setOrderCTime(Date orderCTime) {
		this.orderCTime = orderCTime;
	}

	public Date getValCTime() {
		return valCTime;
	}

	public void setValCTime(Date valCTime) {
		this.valCTime = valCTime;
	}

}