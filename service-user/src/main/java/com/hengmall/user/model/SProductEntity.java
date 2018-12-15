package com.hengmall.user.model;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

/**
 * 商品信息 实体类
 */
public class SProductEntity {

	private int id; // 商品id
	private String name; // 商品名称
	private String headimg; // 商品展示图片，用于展示在商品列表上的图片
	private int price; // 价格
	private String attribute; // 尺寸大小、颜色
	/*
	 * private String sizes; //大小，尺码(多个以“,”分隔) private String colors;
	 * //颜色，多种颜色以"，"分割
	 */
	private int stock; // 库存
	private int allowrefund; // 是否允许退款，1：允许，0：不允许；默认不允许
	private int status; // 状态：1上架，0下架
	private Date ctime; // 发布时间

	private JSONObject attr;
	private int receiving_status; // 收货状态
	private int payment_status; // 支付状态

	public int getReceiving_status() {
		return receiving_status;
	}

	public void setReceiving_status(int receiving_status) {
		this.receiving_status = receiving_status;
	}

	public int getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}

	public JSONObject getAttr() {
		return attr;
	}

	public void setAttr(JSONObject attr) {
		this.attr = attr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getAllowrefund() {
		return allowrefund;
	}

	public void setAllowrefund(int allowrefund) {
		this.allowrefund = allowrefund;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
}
