package com.server.entity.tigLibrary;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分类列表 response
 * @author Administrator
 *
 */
public class TigLibraryRes {

	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "分类或标签名")
	private String name;
	
	@ApiModelProperty(value = "图标")
	private String icon;

	@ApiModelProperty(value = "下级对象")
	private List<TigLibraryRes> children = new ArrayList<TigLibraryRes>();
	
	@ApiModelProperty(value = "父级id",hidden=true)
	private String parentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TigLibraryRes> getChildren() {
		return children;
	}

	public void setChildren(List<TigLibraryRes> children) {
		this.children = children;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
