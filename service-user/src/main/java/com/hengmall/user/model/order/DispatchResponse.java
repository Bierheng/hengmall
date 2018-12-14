package com.server.entity.order;

import java.util.Date;

import com.server.entity.persistence.DictMessage;

import io.swagger.annotations.ApiModelProperty;

/**
 * 派单管理 entity
 * @author Administrator
 *
 */
public class DispatchResponse{
	
	@ApiModelProperty(value = "派单员列表")
	private UsersResponse usersResponse;
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "店铺名称")
	private String shopsName;
	
	@ApiModelProperty(value = "商品名称")
	private String productName;
	
	@ApiModelProperty(value = "价格")
	private Integer price;

	@ApiModelProperty(value = "数量")
	private Integer num;
	
	@ApiModelProperty(value = "订单状态")
	private String status;
	
	@ApiModelProperty(value = "订单创建时间")
	private Date createdTime;
	
	@ApiModelProperty(value = "快递单号")
	private String trackingNum;
	
	@ApiModelProperty(value = "快递公司")
	private String express;
	
	@ApiModelProperty(value = "支付流水号")
	private String payNo;
	
	@ApiModelProperty(value = "是否对外发布，1:否，2:是;")
	private Integer publishStatus;
	
	@ApiModelProperty(value = "派单状态   1：未派单，2已派单")
	private Integer dispatchStatus;

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price!=null?price/100:0;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getStatus() {
		return DictMessage.getDictLabel("order_extra_status", status, "");
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getTrackingNum() {
		return trackingNum;
	}

	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
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

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDispatchStatus() {
		return dispatchStatus;
	}

	public void setDispatchStatus(Integer dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}

	public UsersResponse getUsersResponse() {
		return usersResponse;
	}

	public void setUsersResponse(UsersResponse usersResponse) {
		this.usersResponse = usersResponse;
	}

	
	
}