package com.hengmall.goods.model.manage.product;

import com.alibaba.fastjson.JSONObject;
import com.hengmall.goods.model.manage.PageReq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: 商品信息
 * @Description: 
 * @author Administrator
 * @date 2018年5月28日 下午2:34:32 
 */
@ApiModel
public class ProductBean extends PageReq
{

	@ApiModelProperty(value = "商品id")
	private int id;
	@ApiModelProperty(value = "商品名称")
	private String name;
	@ApiModelProperty(value = "商品展示图片")
	private String headimg;
	@ApiModelProperty(value = "价格")
	private double price;
	@ApiModelProperty(value = "库存")
	private int stock;
	@ApiModelProperty(value = "是否允许退款，1：允许，0：不允许；默认不允许")
	private int allowrefund;
	@ApiModelProperty(value = "状态：1上架，0下架")
	private int status;
	@ApiModelProperty(value = "尺寸大小、颜色")
	private JSONObject attribute;
	@ApiModelProperty(value = "发布时间")
	private String ctime;

	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("ProductBean{");
		buffer.append("id='").append(id).append("'");
		buffer.append("name='").append(name).append("'");
		buffer.append("headimg='").append(headimg).append("'");
		buffer.append("price='").append(price).append("'");
		buffer.append("stock='").append(stock).append("'");
		buffer.append("allowrefund='").append(allowrefund).append("'");
		buffer.append("status='").append(status).append("'");
		buffer.append("attribute='").append(attribute).append("'");
		buffer.append("ctime='").append(ctime).append("'");
		buffer.append("}");
		return buffer.toString();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getHeadimg()
	{
		return headimg;
	}

	public void setHeadimg(String headimg)
	{
		this.headimg = headimg;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getStock()
	{
		return stock;
	}

	public void setStock(int stock)
	{
		this.stock = stock;
	}

	public int getAllowrefund()
	{
		return allowrefund;
	}

	public void setAllowrefund(int allowrefund)
	{
		this.allowrefund = allowrefund;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public JSONObject getAttribute()
	{
		return attribute;
	}

	public void setAttribute(JSONObject attribute)
	{
		this.attribute = attribute;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
}
