package com.hengmall.user.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class TbGoodsTagBean {
	@ApiModelProperty(value = "id",required = true)
    private Integer id;
	@ApiModelProperty(value = "标签名称")
    private String tag_name;
	@ApiModelProperty(value = "标签编码")
    private String tag_code;
	@ApiModelProperty(value = "分类ID")
    private Integer type_id;
	@ApiModelProperty(value = "创建时间")
    private Date create_time;
	@ApiModelProperty(value = "修改时间")
    private Date mod_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	public String getTag_code() {
		return tag_code;
	}

	public void setTag_code(String tag_code) {
		this.tag_code = tag_code;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getMod_time() {
		return mod_time;
	}

	public void setMod_time(Date mod_time) {
		this.mod_time = mod_time;
	}
}