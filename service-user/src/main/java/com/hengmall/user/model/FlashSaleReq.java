package com.hengmall.user.model;

import io.swagger.annotations.ApiModelProperty;

public class FlashSaleReq {
	
	@ApiModelProperty(value = "抢购活动ID")
    private Integer id;




	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("FlashSale{");
		buffer.append("id='").append(id).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}