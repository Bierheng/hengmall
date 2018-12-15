package com.hengmall.user.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.alibaba.fastjson.JSONArray;
import com.hengmall.user.model.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺标签库添加、修改   实体
 * @author Administrator
 *
 */
public class TbShopsTypeAddReq extends BasicsSaveBean{

	@ApiModelProperty(value = "父id，为空则是根节点")
	private String pid;
	
	@NotEmpty(message="标签名不能为空")
	@ApiModelProperty(value = "标签名")
	private String name;
	
	@ApiModelProperty(value = "标签图标")
	private String icon;
	
	@NotNull(message="店铺ID不能为空")
	@ApiModelProperty(value = "店铺ID")
	private Integer shops_id;
	
	@NotNull(message="标签库ID不能为空")
	@ApiModelProperty(value = "标签库ID")
	private Integer type_id;
	
	@ApiModelProperty(value = "所属标签库ID数组")
	private JSONArray type_ids;
	
	@ApiModelProperty(value = "所属标签库ID数组")
	private String type_idsArr;
	
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

	public Integer getShops_id() {
		return shops_id;
	}

	public void setShops_id(Integer shops_id) {
		this.shops_id = shops_id;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
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
