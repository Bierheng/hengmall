package com.hengmall.user.model;

/**
 * 商品详情 （主要展示图片和视频） 实体类
 */
public class RelProductDetailEntity {

	public static final int Details_Type = 1;
	public static final int Introduce_Type = 2;

	private int id;
	private int productid; // 商品id
	private int resourcesid; // 资源id
	private int type; // 1商品介绍资源，2商品详情资源

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

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

	public int getResourcesid() {
		return resourcesid;
	}

	public void setResourcesid(int resourcesid) {
		this.resourcesid = resourcesid;
	}
}
