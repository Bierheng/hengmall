package com.hengmall.user.model.platform;

import org.hibernate.validator.constraints.NotEmpty;

import com.hengmall.user.model.basics.BasicsSaveBean;
import io.swagger.annotations.ApiModelProperty;


/**
 * 平台标签添加、修改 entity  请求需要的参数
 * @author Administrator
 *
 */
public class TagSaveRequest extends BasicsSaveBean{
	
	@NotEmpty(message="平台标签图标不能为空")
	@ApiModelProperty(value="平台标签图标",required=true)
	private String url;
	
	@NotEmpty(message="平台标签名称不能为空")
	@ApiModelProperty(value="平台标签名称",required=true)
	private String name;

	@ApiModelProperty(value="状态（0：启用，1：禁用,添加时不填）",required=true)
	private Integer flag;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFlag() {
		return flag; 
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	
}