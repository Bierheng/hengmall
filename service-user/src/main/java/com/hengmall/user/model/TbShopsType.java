package com.server.entity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分类列表request
 * @author Administrator
 *
 */
public class TbShopsType {

	@ApiModelProperty(value = "id")
	private int id;
	
	@ApiModelProperty(value = "分类或标签名")
	private String name;
	
	@ApiModelProperty(value = "图标")
	private String icon;

	@ApiModelProperty(value = "下级对象")
	private List<TbShopsType> children = new ArrayList<TbShopsType>();
	
	@ApiModelProperty(value = "父级id",hidden=true)
	private int parent_id;
	
	@ApiModelProperty(value = "店铺ID")
	private int shops_id;
	
	@ApiModelProperty(value = "级别")
	private int level;
	
	@ApiModelProperty(value = "所属标签库ID")
	private int type_id;
	
	@ApiModelProperty(value = "所属标签库ID数组")
	private JSONArray type_ids;
	
	@ApiModelProperty(value = "所属标签库ID数组")
	private String type_idsArr;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

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

	public List<TbShopsType> getChildren() {
		return children;
	}

	public void setChildren(List<TbShopsType> children) {
		this.children = children;
	}

	public int getShops_id() {
		return shops_id;
	}

	public void setShops_id(int shops_id) {
		this.shops_id = shops_id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public JSONArray getType_ids() {
		return type_ids;
	}

	public void setType_ids(JSONArray type_ids) {
		this.type_ids = type_ids;
	}

	public String getType_idsArr() {
		return type_idsArr;
	}

	public void setType_idsArr(String type_idsArr) {
		this.type_idsArr = type_idsArr;
	}
	
}
