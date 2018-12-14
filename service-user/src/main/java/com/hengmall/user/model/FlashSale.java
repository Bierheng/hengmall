package com.server.entity;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModelProperty;

public class FlashSale {
    private Integer id;
	@ApiModelProperty(value = "标题")
    private String title;
	@ApiModelProperty(value = "价格")
    private Double price;
	@ApiModelProperty(value = "商品SKU")
	private JSONObject attribute;
	@ApiModelProperty(value = "抢购icon")
    private String icon;
	@ApiModelProperty(value = "有效状态；默认1：未开始，2：已开始，0：已结束")
    private Integer status;
	@ApiModelProperty(value = "秒杀库存")
    private Integer stock;
	@ApiModelProperty(value = "抢购时效性关联id")
    private Integer timeliness_id;
	@ApiModelProperty(value = "创建时间")
    private Date created_time;
	@ApiModelProperty(value = "修改时间")
    private Date  updated_time;
	@ApiModelProperty(value = "拼团所需人数（刪除）")
    private Integer  combine_num;
	@ApiModelProperty(value = "还需要的拼团人数(刪除)")
    private Integer  still_need;
	@ApiModelProperty(value = "产品ID")
    private Integer product_id;
	@ApiModelProperty(value = "商品名称")
    private String product_name;
	@ApiModelProperty(value = "店铺ID")
    private Integer shops_id;
	@ApiModelProperty(value = "店铺名称")
    private String shops_name;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("FlashSale{");
		buffer.append("id='").append(id).append("'");
		buffer.append("product_id='").append(product_id).append("'");
		buffer.append("title='").append(title).append("'");
		buffer.append("price='").append(price).append("'");
		buffer.append("stock='").append(stock).append("'");
		buffer.append("updated_time='").append(updated_time).append("'");
		buffer.append("status='").append(status).append("'");
		buffer.append("attribute='").append(attribute).append("'");
		buffer.append("created_time='").append(created_time).append("'");
		buffer.append("timeliness_id='").append(timeliness_id).append("'");
		buffer.append("icon='").append(icon).append("'");
		buffer.append("combine_num='").append(combine_num).append("'");
		buffer.append("still_need='").append(still_need).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Integer getShops_id() {
		return shops_id;
	}

	public void setShops_id(Integer shops_id) {
		this.shops_id = shops_id;
	}

	public String getShops_name() {
		return shops_name;
	}

	public void setShops_name(String shops_name) {
		this.shops_name = shops_name;
	}

	public Integer getCombine_num() {
		return combine_num;
	}

	public void setCombine_num(Integer combine_num) {
		this.combine_num = combine_num;
	}

	public Integer getStill_need() {
		return still_need;
	}

	public void setStill_need(Integer still_need) {
		this.still_need = still_need;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public JSONObject getAttribute() {
		return attribute;
	}

	public void setAttribute(JSONObject attribute) {
		this.attribute = attribute;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getTimeliness_id() {
		return timeliness_id;
	}

	public void setTimeliness_id(Integer timeliness_id) {
		this.timeliness_id = timeliness_id;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public Date getUpdated_time() {
		return updated_time;
	}

	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}
}