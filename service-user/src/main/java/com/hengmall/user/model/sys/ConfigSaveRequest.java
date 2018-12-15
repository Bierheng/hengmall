package com.hengmall.user.model.sys;

import org.hibernate.validator.constraints.NotEmpty;

import com.hengmall.user.model.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统配置修改 request
 * @author Administrator
 *
 */
public class ConfigSaveRequest extends BasicsSaveBean{

	@NotEmpty(message="系统配置编码不能为空")
	@ApiModelProperty(value = "系统配置编码")
	private String code;
	
	@NotEmpty(message="系统配置名称不能为空")
	@ApiModelProperty(value = "系统配置名称")
	private String name;
	
	@NotEmpty(message="系统配置值不能为空")
	@ApiModelProperty(value = "系统配置值")
	private String value;
	
	@ApiModelProperty(value = "状态,0：禁用，1：启用")
	private Integer status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
