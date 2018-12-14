package com.server.entity;

import java.util.Date;

/**
 * 云对象存储资源 实体类
 */
public class SResourcesEntity {

	// 0:详情图片 1:图片,2:视频
	public static final int Img_Type = 1;
	public static final int Video_Type = 2;
	public static final int Zero_Type = 0;

	private int id;
	private String path; // 资源地址，对象存储地址
	private int type; // 1:图片,2:视频
	private Date ctime; // 创建时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
}
