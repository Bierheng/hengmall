package com.server.entity.tigLibrary;

import org.hibernate.validator.constraints.NotEmpty;

import com.server.entity.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 标签库添加、修改   实体
 * @author Administrator
 *
 */
public class TigLibraryAddReq extends BasicsSaveBean{

	@ApiModelProperty(value = "父id，为空则是根节点")
	private String pid;
	
	@NotEmpty(message="标签名不能为空")
	@ApiModelProperty(value = "标签名")
	private String name;
	
	@NotEmpty(message="标签图标不能为空")
	@ApiModelProperty(value = "标签图标")
	private String icon;
	
	@ApiModelProperty(value = "添加成功后返回的id")
	private Integer idT;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getIdT() {
		return idT;
	}

	public void setIdT(Integer idT) {
		this.idT = idT;
	}
	
}
