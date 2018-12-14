package com.server.entity.platform;

import org.hibernate.validator.constraints.NotEmpty;

import com.server.entity.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;


/**
 * 店铺资质添加、修改 entity  请求需要的参数
 * @author Administrator
 *
 */
public class RightSaveRequest extends BasicsSaveBean{
	
	@NotEmpty(message="店铺资质图标不能为空")
	@ApiModelProperty(value="店铺资质图标",required=true)
	private String url;
	
	@NotEmpty(message="资质名称不能为空")
	@ApiModelProperty(value="资质名称",required=true)
	private String name;

	@ApiModelProperty(value="资质说明内容")
	private String content;
	
	@NotEmpty(message="商铺ID不能为空")
	@ApiModelProperty(value="商铺ID",required=true)
	private String shopsId;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getShopsId() {
		return shopsId;
	}

	public void setShopsId(String shopsId) {
		this.shopsId = shopsId;
	}

	
}