package com.hengmall.user.model;

/**
 * 商品表和类别表的关联表 实体类
 */
public class RelProductCategoryEntity {

	private int productid; // 商品id
	private int categoryid; // 商品类别id

	public int getProductid() {
		return productid;
	}
	
	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
}
