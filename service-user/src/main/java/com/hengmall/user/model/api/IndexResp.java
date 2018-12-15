package com.hengmall.user.model.api;

import java.util.List;

import com.hengmall.user.model.SCategory;
import com.hengmall.user.model.constitute.Carousel;
import com.hengmall.user.model.constitute.RelIndexProduct;
import com.hengmall.user.model.constitute.RelRecommend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 首页展示
 */
@ApiModel
public class IndexResp {

	@ApiModelProperty(value = "图片轮播")
	List<Carousel> relCarousels;
	@ApiModelProperty(value = "推荐商品列表")
	List<RelRecommend> relRecommends;
	@ApiModelProperty(value = "首页商品")
	List<RelIndexProduct> relIndexProducts;
	@ApiModelProperty(value = "商品分类")
	List<SCategory> sCategories;

	@Override
	public String toString() {
		return "IndexResp{" + "relCarousels=" + relCarousels + ", relRecommends=" + relRecommends
				+ ", relIndexProducts=" + relIndexProducts + ", sCategories=" + sCategories + '}';
	}

	public List<Carousel> getRelCarousels() {
		return relCarousels;
	}

	public void setRelCarousels(List<Carousel> relCarousels) {
		this.relCarousels = relCarousels;
	}

	public List<RelRecommend> getRelRecommends() {
		return relRecommends;
	}

	public void setRelRecommends(List<RelRecommend> relRecommends) {
		this.relRecommends = relRecommends;
	}

	public List<RelIndexProduct> getRelIndexProducts() {
		return relIndexProducts;
	}

	public void setRelIndexProducts(List<RelIndexProduct> relIndexProducts) {
		this.relIndexProducts = relIndexProducts;
	}

	public List<SCategory> getsCategories() {
		return sCategories;
	}

	public void setsCategories(List<SCategory> sCategories) {
		this.sCategories = sCategories;
	}
}
