package com.server.entity.shops;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.server.entity.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 修改店铺 entity  请求需要的参数
 * @author Administrator
 *
 */
public class UpdateShopsRequest extends BasicsSaveBean{
	
	@NotEmpty(message="店铺id不能为空")
	@ApiModelProperty(value="店铺id",required=true)
	private String id;
	
	@ApiModelProperty(value="店铺地址",required=false)
	private String address;	
	
	@ApiModelProperty(value="店铺名",required=false)
	private String shopsName;
	
	@ApiModelProperty(value="国籍",required=false)
	private String national;
	
	@Size(min=0, max=255,message="店铺描述长度在{min}-{max}之间")
	@ApiModelProperty(value="店铺描述",required=false)
	private String describe;
	
	@ApiModelProperty(value="纬度",hidden=true)
	private String lat;
	
	@ApiModelProperty(value="经度",hidden=true)
	private String lng;
	
	@ApiModelProperty(value="头像地址",required=false)
	private String avatarUrl;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}