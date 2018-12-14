package com.server.entity.shops.examine;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.server.entity.persistence.DictMessage;
import com.server.entity.shops.commend.ShopsCommendResponse;

import io.swagger.annotations.ApiModelProperty;

/**
 * 店铺审核列表 response    实体
 * @author Administrator
 *
 */
@JsonIgnoreProperties(value={"data","userId"})
public class ExamineResponse{

	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "申请类型")
	private Integer type;
	
	@ApiModelProperty(value = "申请类型label")
	private String typeLabel;
	
	@ApiModelProperty(value = "处理状态，0：未处理，1：同意，2：拒绝")
	private Integer state;
	
	@ApiModelProperty(value = "处理状态label")
	private String stateLabel;
	
	@ApiModelProperty(value = "申请时间")
	private Date insertDate;
	
	@ApiModelProperty(value = "处理时间(未处理则为空)")
	private Date updateDate;
	
	@ApiModelProperty(value = "申请人id",hidden=true)
	private String userId;
	
	@ApiModelProperty(value = "申请人")
	private String userName;
	
	@ApiModelProperty(value = "提交审核的数据",hidden=true,required=false)
	private String data;

	@ApiModelProperty(value = "开店审核详情表单")
	private ExShopsResponse exShopsResponse;

	@ApiModelProperty(value = "店铺选择商品申请表单")
	private ProductShopsResponse productShopsResponse;
	
	@ApiModelProperty(value = "店铺首页产品图组合详情表单")
	private ShopsCommendResponse shopsCommendResponse;

	@ApiModelProperty(value = "店铺首页轮播图详情表单")
	private CarouselShopsResponse carouselShopsResponse;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ExShopsResponse getExShopsResponse() {
		return exShopsResponse;
	}

	public void setExShopsResponse(ExShopsResponse exShopsResponse) {
		this.exShopsResponse = exShopsResponse;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public ShopsCommendResponse getShopsCommendResponse() {
		return shopsCommendResponse;
	}

	public void setShopsCommendResponse(ShopsCommendResponse shopsCommendResponse) {
		this.shopsCommendResponse = shopsCommendResponse;
	}

	public ProductShopsResponse getProductShopsResponse() {
		return productShopsResponse;
	}

	public void setProductShopsResponse(ProductShopsResponse productShopsResponse) {
		this.productShopsResponse = productShopsResponse;
	}

	public CarouselShopsResponse getCarouselShopsResponse() {
		return carouselShopsResponse;
	}

	public void setCarouselShopsResponse(CarouselShopsResponse carouselShopsResponse) {
		this.carouselShopsResponse = carouselShopsResponse;
	}
	
	public int getState() {
		return state;
	}

	public String getTypeLabel() {
		return DictMessage.getDictLabel("examine_type", String.valueOf(type), "");
	}

	public String getStateLabel() {
		return DictMessage.getDictLabel("examine_state", String.valueOf(state), "");
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
