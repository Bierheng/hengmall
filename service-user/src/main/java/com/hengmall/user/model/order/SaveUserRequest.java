package com.hengmall.user.model.order;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 保存派单员 request
 * @author Administrator
 *
 */
public class SaveUserRequest {

	@NotEmpty(message="派单员id不能为空")
	@ApiModelProperty(value = "派单员（用户）id")
	private String userId;
	
	@NotEmpty(message="订单id不能为空")
	@ApiModelProperty(value = "订单id")
	private String orderId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
