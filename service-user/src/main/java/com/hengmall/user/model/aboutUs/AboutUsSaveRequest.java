package com.server.entity.aboutUs;

import org.hibernate.validator.constraints.NotEmpty;

import com.server.entity.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 关于我们 entity  请求需要的参数
 * @author Administrator
 *
 */
public class AboutUsSaveRequest extends BasicsSaveBean{
	
	@NotEmpty(message="关于我们id不能为空")
	@ApiModelProperty(value = "关于我们id",required=true)
	private String id;
	
	@NotEmpty(message="介绍内容不能为空")
	@ApiModelProperty(value = "关于我们介绍内容 type:内容类型（1：文本，2：图片，3：视频）value:内容",required=true)
	private String info;

	
	public boolean isNewRecord() {
		if(id==null || id.equals("")) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}