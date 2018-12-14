package com.server.entity;

public class SCategory {
	
	// 类别级别1或2
	public static final int First_Level = 1;
	public static final int Second_Level = 2;
    private Integer id;

    private String name;

    private Integer level;

    private Integer pid;

    private String icon1;

    private String icon2;
    
    private String pname;
    
    private Integer shops_id;
    
    private String shops_name;
    

    public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIcon1() {
        return icon1;
    }

    public void setIcon1(String icon1) {
        this.icon1 = icon1 == null ? null : icon1.trim();
    }

    public String getIcon2() {
        return icon2;
    }

    public void setIcon2(String icon2) {
        this.icon2 = icon2 == null ? null : icon2.trim();
    }
    
	public String getShops_name() {
		return shops_name;
	}

	public void setShops_name(String shops_name) {
		this.shops_name = shops_name;
	}

	public Integer getShops_id() {
		return shops_id;
	}

	public void setShops_id(Integer shops_id) {
		this.shops_id = shops_id;
	}
	
    
    
}