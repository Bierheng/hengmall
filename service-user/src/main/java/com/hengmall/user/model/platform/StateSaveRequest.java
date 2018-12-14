package com.server.entity.platform;

import org.hibernate.validator.constraints.NotEmpty;

import com.server.entity.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 产品货源地国籍添加、修改 entity  请求需要的参数
 * @author Administrator
 *
 */
public class StateSaveRequest extends BasicsSaveBean{
	
	@NotEmpty(message="国旗不能为空")
	@ApiModelProperty(value="国旗",required=true)
	private String stateUrl;
	
	
	@NotEmpty(message="国家名称不能为空")
	@ApiModelProperty(value="国家名称",required=true)
	private String stateName;


	public String getStateUrl() {
		return stateUrl;
	}


	public void setStateUrl(String stateUrl) {
		this.stateUrl = stateUrl;
	}


	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	
	
}