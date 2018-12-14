package com.server.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 订单 实体类
 */
public class SOrderEntity {

	// -3:逻辑删除,-2:已退款,-1:已取消,0:待支付,1:已支付
	public static final int DEL_STATUS = -3;
	public static final int RETURN_STATUS = -2;
	public static final int CANCEL_STATUS = -1;
	public static final int PENDING_STATUS = 0;
	public static final int ALREADY_STATUS = 1;

	@ApiModelProperty(value = "订单id")
	private int id; 
	@ApiModelProperty(value = "商城订单号")
	private String trade_no; 
	@ApiModelProperty(value = "用户id")	
	private int user_id; 
	@ApiModelProperty(value = "用户名称")	
	private String user_name;
	@ApiModelProperty(value = "使用优惠券ID")	
	private int s_coupon_id; 
	@ApiModelProperty(value = "订单隐藏状态；0：正常，1：隐藏订单")	
	private int hide_status; 
	@ApiModelProperty(value = "实付金额")	
	private int price; 
	@ApiModelProperty(value = "支付方式: 默认1：微信支付；2：支付宝支付")	
	private int paymethod; 
	@ApiModelProperty(value = "-3逻辑删除,-2已退款,-1已取消,0-待支付,1-已支付")	
	private int status; 
	@ApiModelProperty(value = "订单创建时间")	
	private Date created_time; 
	@ApiModelProperty(value = "支付时间")	
	private Date pay_time; 
	@ApiModelProperty(value = "退款时间")	
	private Date refund_time; 
	@ApiModelProperty(value = "支付平台订单号")	
	private String out_trade_no; 
	@ApiModelProperty(value = "订单地址及用户名关联ID")	
	private int address_id; 
	@ApiModelProperty(value = "业务支付状态码；默认1：单独购买（商品），2：发起拼单（商品），3：钱包充值")
	private int buy_status;
	@ApiModelProperty(value = "拼单ID,不为空时为拼单订单，为空时为普通订单")
	private int combine_sale_id;
	@ApiModelProperty(value = "拼单ID,不为空时为拼单订单，为空时为普通订单")
	private int initiator_id;

	
	public int getInitiator_id() {
		return initiator_id;
	}

	public void setInitiator_id(int initiator_id) {
		this.initiator_id = initiator_id;
	}

	public int getCombine_sale_id() {
		return combine_sale_id;
	}

	public void setCombine_sale_id(int combine_sale_id) {
		this.combine_sale_id = combine_sale_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getS_coupon_id() {
		return s_coupon_id;
	}

	public void setS_coupon_id(int s_coupon_id) {
		this.s_coupon_id = s_coupon_id;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(int paymethod) {
		this.paymethod = paymethod;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public Date getRefund_time() {
		return refund_time;
	}

	public void setRefund_time(Date refund_time) {
		this.refund_time = refund_time;
	}

	public int getHide_status() {
		return hide_status;
	}

	public void setHide_status(int hide_status) {
		this.hide_status = hide_status;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getBuy_status() {
		return buy_status;
	}

	public void setBuy_status(int buy_status) {
		this.buy_status = buy_status;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}
