package com.hengmall.user.model;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author Administrator
 *
 */
public class ApplyRefundReq {
	
		@ApiModelProperty(value = "退款订单ID,通过请求时只需传ID,拒绝请求时ID跟拒绝原因都需要传")
		private Integer id;
		@ApiModelProperty(value = "拒绝原因")
	    private String refuse_reason;
		@ApiModelProperty(value = "需要退款的店铺ID")
	    private int shops_id;
		@ApiModelProperty(value = "需要退款的订单ID")
	    private int order_id;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getRefuse_reason() {
			return refuse_reason;
		}
		public void setRefuse_reason(String refuse_reason) {
			this.refuse_reason = refuse_reason;
		}
		public int getShops_id() {
			return shops_id;
		}
		public void setShops_id(int shops_id) {
			this.shops_id = shops_id;
		}
		public int getOrder_id() {
			return order_id;
		}
		public void setOrder_id(int order_id) {
			this.order_id = order_id;
		}
}
