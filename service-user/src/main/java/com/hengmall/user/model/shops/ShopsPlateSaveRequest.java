package com.hengmall.user.model.shops;

import org.hibernate.validator.constraints.NotEmpty;

import com.hengmall.user.model.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺板块增改、增加 entity  请求需要的参数
 * @author Administrator
 *
 */
public class ShopsPlateSaveRequest extends BasicsSaveBean{

	
	@NotEmpty(message="板块内容类型不能为空")
	@ApiModelProperty(value = "板块内容类型   1：文字，2：图片，3：视频")
	private String type;
	
	@NotEmpty(message="板块内容不能为空")
	@ApiModelProperty(value = "板块内容")
	private String content;
	
	@ApiModelProperty(value = "排序号,新增时不用填")
	private Integer orderNo;
	
	@ApiModelProperty(value = "店铺id，修改时不用填")
	private String shopsId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}

}