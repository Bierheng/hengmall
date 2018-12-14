package com.server.entity;

/**
 * 推荐商品 实体类
 */
public class RelRecommendEntity {

	private int id;
	private int productid; // 推荐商品Id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}
}
