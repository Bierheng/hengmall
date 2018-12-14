package com.server.entity.platform;

import org.hibernate.validator.constraints.NotEmpty;

import com.server.entity.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 定位地图购物信息弹框添加、修改 entity  请求需要的参数
 * @author Administrator
 *
 */
public class OrderSaveRequest extends BasicsSaveBean{
	
	@NotEmpty(message="购买者用户名不能为空")
	@ApiModelProperty(value="购买者用户名",required=true)
	private String userName;
	
	@NotEmpty(message="购买位置不能为空")
	@ApiModelProperty(value="购买位置",required=true)
	private String location;

	@ApiModelProperty(value="商品名称")
	private String productName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}