package com.hengmall.user.model;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wuhengbin on 2018/6/20.
 */
public class DataTablesResultShops implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "店铺id")
    private String id;
	
	@ApiModelProperty(value = "店铺id")
    private String shopsName;
	
	@ApiModelProperty(value = "店铺id")
    private int status;
	
	@ApiModelProperty(value = "其中一个子订单id")
    private int orderExtraId;
	
	@ApiModelProperty(value = "订单商品列表")
	private List<SOrderExtraEntity> platformList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOrderExtraId() {
		return orderExtraId;
	}

	public void setOrderExtraId(int orderExtraId) {
		this.orderExtraId = orderExtraId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public List<SOrderExtraEntity> getPlatformList() {
		return platformList;
	}

	public void setPlatformList(List<SOrderExtraEntity> platformList) {
		this.platformList = platformList;
	}

	
	
}
