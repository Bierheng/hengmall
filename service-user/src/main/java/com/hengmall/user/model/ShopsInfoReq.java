package com.hengmall.user.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class ShopsInfoReq {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "店铺ID")
    int id;
    @ApiModelProperty(value = "排序状态：默认1：综合，2：销量，3：价格升序,4：价格降序")
    int orderStatus;
    @ApiModelProperty(value = "商品ID")
    int  productId;
    @ApiModelProperty(value = "商品分类ID")
    List<Integer>  typeId;
    @ApiModelProperty(value = "userId")
    int userId;
    
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public List<Integer> getTypeId() {
		return typeId;
	}
	public void setTypeId(List<Integer> typeId) {
		this.typeId = typeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
