package com.hengmall.goods.model;

import io.swagger.annotations.ApiModelProperty;

public class ShopsType {
	
	@ApiModelProperty(value = "店铺佣金ID")
    private Integer id;
	@ApiModelProperty(value = "平台分类ID")
    private Integer type_id;
	@ApiModelProperty(value = "创建时间")
    private Integer created_time;
	@ApiModelProperty(value = "是否推荐,默认，0否，1是")
    private Integer is_recommend;
	@ApiModelProperty(value = "店铺主人ID")
    private Integer shops_id;
	@ApiModelProperty(value = "分类图标")
    private Integer icon;
	@ApiModelProperty(value = "分类名称")
    private Integer type_name;


	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ShopsType{");
		buffer.append("id='").append(id).append("'");
		buffer.append("type_id='").append(type_id).append("'");
		buffer.append("created_time='").append(created_time).append("'");
		buffer.append("is_recommend='").append(is_recommend).append("'");
		buffer.append("shops_id='").append(shops_id).append("'");
		buffer.append("icon='").append(icon).append("'");
		buffer.append("type_name='").append(type_name).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Integer created_time) {
		this.created_time = created_time;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Integer getIs_recommend() {
		return is_recommend;
	}

	public void setIs_recommend(Integer is_recommend) {
		this.is_recommend = is_recommend;
	}

	public Integer getShops_id() {
		return shops_id;
	}

	public void setShops_id(Integer shops_id) {
		this.shops_id = shops_id;
	}

	public Integer getIcon() {
		return icon;
	}

	public void setIcon(Integer icon) {
		this.icon = icon;
	}

	public Integer getType_name() {
		return type_name;
	}

	public void setType_name(Integer type_name) {
		this.type_name = type_name;
	}
}