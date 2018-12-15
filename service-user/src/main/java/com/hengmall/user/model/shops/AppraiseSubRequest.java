package com.hengmall.user.model.shops;

import org.hibernate.validator.constraints.NotEmpty;

import com.hengmall.user.model.basics.BasicsSaveBean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品评论提交
 * @author Administrator
 *
 */
public class AppraiseSubRequest extends BasicsSaveBean{


	@NotEmpty(message="评价内容不能为空")
	@ApiModelProperty(value="评价内容")
	private String context;
	
	/**************************************************************************************/
	
	@ApiModelProperty(value="描述相符，星级评价",hidden=true)
	private Integer matching;
	
	@ApiModelProperty(value="发货速度星级评价",hidden=true)
	private Integer deliveryspeed;
	
	@ApiModelProperty(value="商品星级评分",hidden=true)
	private Integer score;
	
	@ApiModelProperty(value="服务质量星级评分",hidden=true)
	private Integer servicequality;
	
	@ApiModelProperty(value="配送员服务态度星级评分",hidden=true)
	private Integer serviceattitude;
	
	@ApiModelProperty(value="商品sku",hidden=true)
	private String attr;
	
	@ApiModelProperty(value="用户头像(添加时生成)",hidden=true)
	private String url;
	
	@ApiModelProperty(value="用户名(添加时生成)",hidden=true)
	private String userName;

	@ApiModelProperty(value="店铺商品表id")
	private String tbId;

	public String getTbId() {
		return tbId;
	}

	public void setTbId(String tbId) {
		this.tbId = tbId;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getMatching() {
		return matching;
	}

	public void setMatching(Integer matching) {
		this.matching = matching;
	}

	public Integer getDeliveryspeed() {
		return deliveryspeed;
	}

	public void setDeliveryspeed(Integer deliveryspeed) {
		this.deliveryspeed = deliveryspeed;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getServicequality() {
		return servicequality;
	}

	public void setServicequality(Integer servicequality) {
		this.servicequality = servicequality;
	}

	public Integer getServiceattitude() {
		return serviceattitude;
	}

	public void setServiceattitude(Integer serviceattitude) {
		this.serviceattitude = serviceattitude;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
