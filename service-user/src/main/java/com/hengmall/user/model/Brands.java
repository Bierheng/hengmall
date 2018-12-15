package com.hengmall.user.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class Brands {
    private Integer id;
	@ApiModelProperty(value = "品牌名称")
    private String name;
	@ApiModelProperty(value = "品牌图标")
    private String icon;
	@ApiModelProperty(value = "描述")
    private String desc;
	@ApiModelProperty(value = "创建时间")
    private Date created_time;
	@ApiModelProperty(value = "标识；默认1：有效，0：无效")
    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}