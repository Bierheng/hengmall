package com.server.entity.manage.product;

import java.util.Date;

import com.server.entity.manage.PageReq;
import com.server.utils.CommonUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: 商品信息  用于传回后台前端查询数据
 * @Description:
 * @author Administrator
 * @date 2018年5月28日 下午2:34:32
 */
@ApiModel
public class ProductBean2 extends PageReq {

	@ApiModelProperty(value = "底部图片")
	private String endimg;

	@ApiModelProperty(value = "所属标签数组")
	private String[] tagids;
	@ApiModelProperty(value = "价格类型，1：人民币，2：美元；")
	private String priceType;
	@ApiModelProperty(value = "商品特色服务")
	private String[] rights;
	
	
	public String[] getTagids() {
		return tagids;
	}

	public void setTagids(String tagids) {
		this.tagids = CommonUtils.toStringArray(tagids);
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String[] getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = CommonUtils.toStringArray(rights);
	}

	/******************  以上是新增的 ****************************/
	@ApiModelProperty(value = "虚拟销量")
	private int salesvolumeFalse;
	@ApiModelProperty(value = "国家信息表ID")
	private String stateId;
	
	@ApiModelProperty(value = "商品类型，1：普通商品，2：抢购商品")
	private int type;
	@ApiModelProperty(value = "商品id")
	private int id;
	@ApiModelProperty(value = "商品名称")
	private String name;
	@ApiModelProperty(value = "商品展示图片")
	private String headimg;
	@ApiModelProperty(value = "搜索关键词")
	private String keys;
	@ApiModelProperty(value = "价格")
	private double price;
	@ApiModelProperty(value = "库存")
	private int stock;
	@ApiModelProperty(value = "是否允许退款，1：允许，0：不允许；默认不允许")
	private int allowrefund;
	@ApiModelProperty(value = "状态：1上架，0下架")
	private int status;
	@ApiModelProperty(value = "尺寸大小、颜色")
	private String attribute;
	@ApiModelProperty(value = "发布时间")
	private Date ctime;
	@ApiModelProperty(value = "商品描述")
	private String description;
	@ApiModelProperty(value = "原价")
	private double oldprice;
	@ApiModelProperty(value = "金币抵扣")
	private double coinoffset;
	@ApiModelProperty(value = "商品编码")
	private String code;
	@ApiModelProperty(value = "快递运费")
	private double freight;
	@ApiModelProperty(value = "上架类型：1：立即上架；2：存入仓库；3：自定义上架")
	private int goodsontype;
	@ApiModelProperty(value = "自定义上架时间，当上架类型为3时填写")
	private Date goodsontime;
	@ApiModelProperty(value = "是否预售")
	private String ispresale;
	@ApiModelProperty(value = "预售选择发货日期，当是预售商品时填写")
	private Date saledelivertime;
	@ApiModelProperty(value = "付款后几天发货当是预售时填写")
	private int saledeliverdate;
	@ApiModelProperty(value = "限购数量，当该数字大于零且不为空时起作用")
	private int limitnum;
	@ApiModelProperty(value = "一级分类编号")
	private int onetypeid;
	@ApiModelProperty(value = "二级分类编号")
	private int twotypeid;
	@ApiModelProperty(value = "资源编号")
	private int resourcesId;
	@ApiModelProperty(value = "图片编号数组")

	@Override
	public String toString() {
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
		buffer.append("description='").append(description).append("'");
		buffer.append("oldprice='").append(oldprice).append("'");
		buffer.append("coinoffset='").append(coinoffset).append("'");
		buffer.append("code='").append(code).append("'");
		buffer.append("freight='").append(freight).append("'");
		buffer.append("goodsontype='").append(goodsontype).append("'");
		buffer.append("goodsontime='").append(goodsontime).append("'");
		buffer.append("ispresale='").append(ispresale).append("'");
		buffer.append("saledelivertime='").append(saledelivertime).append("'");
		buffer.append("saledeliverdate='").append(saledeliverdate).append("'");
		buffer.append("limitnum='").append(limitnum).append("'");
		buffer.append("onetypeid='").append(onetypeid).append("'");
		buffer.append("keys='").append(keys).append("'");
		buffer.append("twotypeid='").append(twotypeid).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
	
	public String getKeys() {
		return keys;
	}


	public void setKeys(String keys) {
		this.keys = keys;
	}


	public String getIspresale() {
		return ispresale;
	}


	public void setIspresale(String ispresale) {
		this.ispresale = ispresale;
	}

	public int getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(int resourcesId) {
		this.resourcesId = resourcesId;
	}

	public String getAttribute() {
		return attribute;
	}


	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getOnetypeid() {
		return onetypeid;
	}

	public void setOnetypeid(int onetypeid) {
		this.onetypeid = onetypeid;
	}

	public int getTwotypeid() {
		return twotypeid;
	}

	public void setTwotypeid(int twotypeid) {
		this.twotypeid = twotypeid;
	}

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

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getAllowrefund() {
		return allowrefund;
	}

	public void setAllowrefund(int allowrefund) {
		this.allowrefund = allowrefund;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public double getOldprice() {
		return oldprice;
	}

	public void setOldprice(double oldprice) {
		this.oldprice = oldprice;
	}

	public double getCoinoffset() {
		return coinoffset;
	}

	public void setCoinoffset(double coinoffset) {
		this.coinoffset = coinoffset;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getFreight() {
		return freight;
	}

	public void setFreight(double freight) {
		this.freight = freight;
	}

	public int getGoodsontype() {
		return goodsontype;
	}

	public void setGoodsontype(int goodsontype) {
		this.goodsontype = goodsontype;
	}

	public Date getGoodsontime() {
		return goodsontime;
	}

	public void setGoodsontime(Date goodsontime) {
		this.goodsontime = goodsontime;
	}

	public Date getSaledelivertime() {
		return saledelivertime;
	}

	public void setSaledelivertime(Date saledelivertime) {
		this.saledelivertime = saledelivertime;
	}

	public int getSaledeliverdate() {
		return saledeliverdate;
	}

	public void setSaledeliverdate(int saledeliverdate) {
		this.saledeliverdate = saledeliverdate;
	}

	public int getLimitnum() {
		return limitnum;
	}

	public void setLimitnum(int limitnum) {
		this.limitnum = limitnum;
	}


	public String getEndimg() {
		return endimg;
	}


	public void setEndimg(String endimg) {
		this.endimg = endimg;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSalesvolumeFalse() {
		return salesvolumeFalse;
	}

	public void setSalesvolumeFalse(int salesvolumeFalse) {
		this.salesvolumeFalse = salesvolumeFalse;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	
}
