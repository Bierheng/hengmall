package com.server.entity.common.combineSale;

import com.server.entity.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 拼单商品save  request
 * @author Administrator
 *
 */
public class CombineSaleSaveRequest extends BasicsSaveBean{

	@ApiModelProperty(value = "标题")
	private String title;
	
	@ApiModelProperty(value = "拼单所需人数")
	private Integer combineNum;
	
	@ApiModelProperty(value = "还需要的拼单人数")
	private Integer stillNeed;
	
	@ApiModelProperty(value = "有效状态；默认1：未开始，2：已开始，0：已结束")
	private Integer status;

	@ApiModelProperty(value = "商品ID")
	private String productId;
	
	@ApiModelProperty(value = "店铺ID")
	private String shopsId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCombineNum() {
		return combineNum;
	}

	public void setCombineNum(Integer combineNum) {
		this.combineNum = combineNum;
	}

	public Integer getStillNeed() {
		return stillNeed;
	}

	public void setStillNeed(Integer stillNeed) {
		this.stillNeed = stillNeed;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}
	
	
}
