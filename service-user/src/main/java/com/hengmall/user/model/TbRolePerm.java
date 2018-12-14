package com.server.entity;

import io.swagger.annotations.ApiModelProperty;

public class TbRolePerm {
    private Integer id;
	@ApiModelProperty(value = "角色id")
    private Integer role_id;
	@ApiModelProperty(value = "权限ID")
    private Integer permission_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Integer getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(Integer permission_id) {
		this.permission_id = permission_id;
	}

    
}