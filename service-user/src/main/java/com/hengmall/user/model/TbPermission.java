package com.server.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 权限 entity
 * @author Administrator
 *
 */
public class TbPermission {
    private Integer id;
	@ApiModelProperty(value = "权限名")
    private String name;
	@ApiModelProperty(value = "权限接口")
    private String permission;

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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }
}