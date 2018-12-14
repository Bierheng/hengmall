package com.server.entity;

import io.swagger.annotations.ApiModelProperty;

public class TbShiroFilter {
    private Integer id;
	@ApiModelProperty(value = "过滤链")
    private String name;
	@ApiModelProperty(value = "权限")
    private String perms;
	@ApiModelProperty(value = "序号")
    private Integer sort_order;

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

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }

	public Integer getSort_order() {
		return sort_order;
	}

	public void setSort_order(Integer sort_order) {
		this.sort_order = sort_order;
	}

}