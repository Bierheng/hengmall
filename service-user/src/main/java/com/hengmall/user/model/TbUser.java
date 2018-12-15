package com.hengmall.user.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class TbUser {
	
	@ApiModelProperty(value = "用户id")
    private Long id;
	@ApiModelProperty(value = "用户名")
    private String username;
	@ApiModelProperty(value = "用户密码")
    private String password;
	@ApiModelProperty(value = "用户密码")
    private String newPassword;
	@ApiModelProperty(value = "手机号")
    private String phone;
	@ApiModelProperty(value = "邮件")
    private String email;
	@ApiModelProperty(value = "性格")
    private String sex;
	@ApiModelProperty(value = "地址")
    private String address;
	@ApiModelProperty(value = "状态:禁用：0；启用：1；")
    private Integer state;
	@ApiModelProperty(value = "描述")
    private String description;
	@ApiModelProperty(value = "角色ID")
    private Integer roleId;
	@ApiModelProperty(value = "头像")
    private String file;
	@ApiModelProperty(value = "创建时间")
    private Date created;
	@ApiModelProperty(value = "修改时间")
    private Date updated;
	@ApiModelProperty(value = "角色名")    
    private String roleNames;
	
	private String userId;  //存放store库users里的id，也就是店铺主id,用于数据过滤，角色为管理员则填-1
	
	@SuppressWarnings("unused")
	private Date lastDate;  //redis最后一次活跃时间

	//用户拥有的权限
	private List<TbPermission> permissions = new ArrayList<>();
	
	
    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

	public List<TbPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<TbPermission> permissions) {
		this.permissions = permissions;
	}

	public Date getLastDate() {
		return new Date();
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
    
}