package com.hengmall.user.model.manage.product;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hengmall.user.model.manage.PageReq;
import com.hengmall.user.model.manage.product.isnew.ShopsRightEntity;
import com.hengmall.user.model.platform.StateResponse;
import com.hengmall.user.model.tigLibrary.TigLibraryRes;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: 商品信息 用于接受后台前端的数据
 * @Description:
 * @author Administrator
 * @date 2018年5月28日 下午2:34:32
 */
@ApiModel
public class ProductBean extends PageReq {
	
	public static final int GOODSON_TYPE = 1;
	public static final int PUSHON_TYPE = 0;
	
	
	@ApiModelProperty(value = "底部图片")
	private String endimg;
	
	/* 列表数据 */
	@ApiModelProperty(value = "所属标签已选择数组")
	private String[] arrTagids;
	@ApiModelProperty(value = "价格类型，1：人民币，2：美元；")
	private String priceType;
	@ApiModelProperty(value = "商品特色服务已选择数组")
	private String[] arrRights;
	
	@ApiModelProperty(value = "商品特色服务列表")
	private List<ShopsRightEntity> rightsList;
	@ApiModelProperty(value = "标签列表")
	private List<TigLibraryRes> tigList;
	

	public String[] getArrTagids() {
		return arrTagids;
	}

	public void setArrTagids(String[] arrTagids) {
		this.arrTagids = arrTagids;
	}
	
	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public List<TigLibraryRes> getTigList() {
		return tigList;
	}

	public void setTigList(List<TigLibraryRes> tigList) {
		this.tigList = tigList;
	}

	public String[] getArrRights() {
		return arrRights;
	}

	public void setArrRights(String[] arrRights) {
		this.arrRights = arrRights;
	}

	public List<ShopsRightEntity> getRightsList() {
		return rightsList;
	}

	public void setRightsList(List<ShopsRightEntity> rightsList) {
		this.rightsList = rightsList;
	}



	/******************  以上是新增的 ****************************/
	
	@ApiModelProperty(value = "虚拟销量")
	private int salesvolumeFalse;
	@ApiModelProperty(value = "国家信息表ID")
	private String stateId;
	@ApiModelProperty(value = "国籍列表")
	private List<StateResponse> stateList;
	
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
	private JSONObject attribute;
	@ApiModelProperty(value = "发布时间")
	private Date ctime;
	@ApiModelProperty(value = "商品描述")
	private JSONArray description;
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
	private Boolean ispresale;
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
	private List<Integer> pictrueIds;
	@ApiModelProperty(value = "标签编号数组")
	private List<Integer> productDetail;
	@ApiModelProperty(value = "商品详情数组")
	private List<Integer> tagIds;

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
		buffer.append("twotypeid='").append(twotypeid).append("'");
		buffer.append("keys='").append(keys).append("'");
		buffer.append("resourcesId='").append(resourcesId).append("'");
		buffer.append("pictrueIds='").append(pictrueIds).append("'");
		buffer.append("tagIds='").append(tagIds).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}



	public Boolean getIspresale() {
		return ispresale;
	}



	public void setIspresale(Boolean ispresale) {
		this.ispresale = ispresale;
	}



	public List<Integer> getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(List<Integer> productDetail) {
		this.productDetail = productDetail;
	}

	public int getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(int resourcesId) {
		this.resourcesId = resourcesId;
	}

	public List<Integer> getPictrueIds() {
		return pictrueIds;
	}

	public void setPictrueIds(List<Integer> pictrueIds) {
		this.pictrueIds = pictrueIds;
	}

	public List<Integer> getTagIds() {
		return tagIds;
	}

	public void setTagIds(List<Integer> tagIds) {
		this.tagIds = tagIds;
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

	public JSONObject getAttribute() {
		return attribute;
	}

	public void setAttribute(JSONObject attribute) {
		this.attribute = attribute;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public JSONArray getDescription() {
		return description;
	}

	public void setDescription(JSONArray description) {
		this.description = description;
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

	public List<StateResponse> getStateList() {
		return stateList;
	}

	public void setStateList(List<StateResponse> stateList) {
		this.stateList = stateList;
	}
	
}
