package com.server.dto;


import java.io.Serializable;
import java.util.List;

/**
 * @author wuhengbin
 */
public class RoleDto implements Serializable{

    private int id;

    private String name;

    private List<Integer> roles;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
