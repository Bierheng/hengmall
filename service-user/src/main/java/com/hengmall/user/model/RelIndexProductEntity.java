package com.hengmall.user.model;

/**
 * 首页商品（关联表） 实体类
 */
public class RelIndexProductEntity {

	private int id;
	private int uid; // 代理id
	private int productid; // 代理选择的商品id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}
}
