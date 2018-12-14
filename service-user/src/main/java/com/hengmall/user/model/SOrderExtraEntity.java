package com.server.entity;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModelProperty;

/**
 * 订单详情 实体类
 */
public class SOrderExtraEntity {

	// 1:待发货，2:待收货，3:已收货，99:所有订单
	public static final int PENDING_DELIVERY_STATUS = 1;
	public static final int UNCOLLECTED_GOODS_STATUS = 2;
	public static final int RECEIVED_STATUS = 3;
	public static final int ALL_ORDERS = 99;

	private int id;
	@ApiModelProperty(value = "订单号表id")	
	private int order_id; // 订单号表id
	@ApiModelProperty(value = "商品id")	
	private int product_id; // 商品id
	@ApiModelProperty(value = "商品名称，当时记录的商品名称")	
	private String product_name; // 商品名称，当时记录的商品名称
	@ApiModelProperty(value = "应付金额，当时记录的商品价格")	
	private double price; // 应付金额，当时记录的商品价格
	@ApiModelProperty(value = "购买数量")	
	private int num; // 购买数量
	@ApiModelProperty(value = "1:待发货，2:待收货，3:已收货，")	
	private int status; // 1:待发货，2:待收货，3:已收货，
	@ApiModelProperty(value = "评价状态；默认0：订单未完成，无需评价，1：未评价，2：已评价，3：追加评价")	
	private int appraise_status; // 评价状态；默认0：订单未完成，无需评价，1：未评价，2：已评价，3：追加评价
	@ApiModelProperty(value = "购买商品的属性值")
	private JSONObject attrs;
	@ApiModelProperty(value = "支付方式; 默认1：微信支付；2：支付宝支付;3:钱包支付")
	private int paymethod;        // 支付方式
	

	@ApiModelProperty(value = "店铺id")
	private int shopsId;
	@ApiModelProperty(value = "快递单号")
	private String tracking_num;
	@ApiModelProperty(value = "快递公司")
	private String express;
	@ApiModelProperty(value = "支付流水号")
	private String payNo;
	@ApiModelProperty(value = "拼单订单状态：1：等待拼单完成；2：拼单失败；3拼单成功")
	private int combine_status;
	@ApiModelProperty(value = "用户ID")
	private int user_id;
	@ApiModelProperty(value = "订单号")
	private String out_trade_no;
	
	
	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCombine_status() {
		return combine_status;
	}

	public void setCombine_status(int combine_status) {
		this.combine_status = combine_status;
	}

	public JSONObject getAttrs() {
		return attrs;
	}

	public void setAttrs(JSONObject attrs) {
		this.attrs = attrs;
	}

	public int getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(int paymethod) {
		this.paymethod = paymethod;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAppraise_status() {
		return appraise_status;
	}

	public void setAppraise_status(int appraise_status) {
		this.appraise_status = appraise_status;
	}

	public int getShopsId() {
		return shopsId;
	}

	public void setShopsId(int shopsId) {
		this.shopsId = shopsId;
	}

	public String getTracking_num() {
		return tracking_num;
	}

	public void setTracking_num(String tracking_num) {
		this.tracking_num = tracking_num;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	
}
