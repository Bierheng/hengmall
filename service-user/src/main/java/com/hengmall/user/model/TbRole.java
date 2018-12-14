package com.server.entity;

import io.swagger.annotations.ApiModelProperty;

public class TbRole {
    private Integer id;
	@ApiModelProperty(value = "角色名")
    private String name;
	@ApiModelProperty(value = "描述")
    private String description;
	@ApiModelProperty(value = "角色")
    private Integer[] roles;

    public Integer[] getRoles() {
        return roles;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}