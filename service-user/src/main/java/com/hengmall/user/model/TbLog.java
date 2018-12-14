package com.server.entity;

import java.util.Date;

public class TbLog {
    private Integer id;

    private String name;

    private Integer type;

    private String url;

    private String request_type;

    private String request_param;

    private String user;

    private String ip;

    private String ip_info;

    private Integer time;

    private Date create_date;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

	public String getRequest_param() {
		return request_param;
	}

	public void setRequest_param(String request_param) {
		this.request_param = request_param;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp_info() {
		return ip_info;
	}

	public void setIp_info(String ip_info) {
		this.ip_info = ip_info;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

   
}