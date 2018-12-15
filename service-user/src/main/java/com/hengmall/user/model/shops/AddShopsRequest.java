package com.hengmall.user.model.shops;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.hengmall.user.model.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 新增店铺 entity  请求需要的参数
 * @author Administrator
 *
 */
public class AddShopsRequest extends BasicsSaveBean{
	
	@NotEmpty(message="店铺地址不能为空")
	@ApiModelProperty(value="店铺地址",required=true)
	private String address;	
	
	@NotNull(message="店铺主ID不能为空")
	@ApiModelProperty(value="店铺主ID",required=true)
	private Integer userId;	
	
	@NotEmpty(message="店铺名不能为空")
	@ApiModelProperty(value="店铺名",required=true)
	private String shopsName;
	
	@NotEmpty(message="国籍不能为空")
	@ApiModelProperty(value="国籍",required=true)
	private String national;
	
	@Size(min=0, max=255,message="店铺描述长度在{min}-{max}之间")
	@ApiModelProperty(value="店铺描述",required=false)
	private String describe;
	
	@ApiModelProperty(value="纬度",hidden=true)
	private String lat;
	
	@ApiModelProperty(value="经度",hidden=true)
	private String lng;
	
	@ApiModelProperty(value="头像地址",hidden=true)
	private String avatarUrl;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getShopsName() {
		return shopsName;
	}

	public void setShopsName(String shopsName) {
		this.shopsName = shopsName;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	
}