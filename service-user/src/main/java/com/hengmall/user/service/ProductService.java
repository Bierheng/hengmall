package com.hengmall.user.service;

import com.alibaba.fastjson.JSONObject;
import com.hengmall.user.model.DataTablesResult;
import com.hengmall.user.model.RelCarouselEntity;
import com.hengmall.user.model.Result;
import com.hengmall.user.model.SCouponEntity;
import com.hengmall.user.model.api.Ajax;
import com.hengmall.user.model.manage.product.ListProductReq;
import com.hengmall.user.model.manage.product.ListProductResp;
import com.hengmall.user.model.manage.product.ProductBean;
import com.hengmall.user.model.manage.product.ProductIdReq;
import com.hengmall.user.model.manage.product.ProductReq;

public interface ProductService {
	/**
	 * 获取商品信息列表
	 * @param listProductReq
	 * @return
	 * @throws Exception
	 */
	Ajax<ListProductResp> getProductList(ListProductReq listProductReq)throws Exception;
	/**
	 * 获取商品仓库列表
	 * @param listProductReq
	 * @return
	 * @throws Exception
	 */
	Ajax<ListProductResp> getWareProductList(ListProductReq listProductReq)throws Exception;
	/**
	 * 查询根据一级ID获取简短商品列表
	 * @param product
	 * @return
	 * @throws Exception
	 */
	DataTablesResult getProductByList(ProductIdReq product) throws Exception;
	/**
	 * 新增或者修改商品信息
	 * @param product
	 * @return
	 * @throws Exception
	 */
	DataTablesResult addProduct(ProductBean product) throws Exception;
	/**
	 * 根据ID查询商品信息
	 * @param product
	 * @return
	 * @throws Exception
	 */
	JSONObject queryProductById(ProductReq product)throws Exception;
	/**
	 * 根据ID删除商品
	 * @param product
	 * @return
	 * @throws Exception
	 */
	DataTablesResult delProduct(ProductBean product) throws Exception;
	
	/**
	 * 获取优惠券记录总数
	 * @return
	 * @throws Exception
	 */
	Result<Object> countCoupon() throws Exception;
	
	/**
	 * 删除优惠券
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	Result<Object> delCoupon(int ids) throws Exception;
	
	/**
	 * 新增或者修改优惠券
	 * @param sCouponEntity
	 * @return
	 * @throws Exception
	 */
	Result<Object> addCoupon(SCouponEntity sCouponEntity) throws Exception;

	/**
	 * 获取优惠券的列表
	 * @param draw
	 * @param length
	 * @return
	 * @throws Exception
	 */
	DataTablesResult couponList(int draw, int length) throws Exception;
	
	/**
	 * 获取轮播图总数
	 * @return
	 * @throws Exception
	 */
	Result<Object> countCarousel() throws Exception;

	/**
	 * 删除品牌
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Result<Object> delBrand(int id) throws Exception;
	
	/**
	 * 新增或者修改轮播图
	 * @param relCarousel
	 * @return
	 * @throws Exception
	 */
	Result<Object> addCarousel(RelCarouselEntity relCarousel) throws Exception;

	/**
	 * 获取轮播图列表
	 * @param draw
	 * @param length
	 * @return
	 * @throws Exception
	 */
	DataTablesResult carouselList(int draw, int length) throws Exception;

	/**
	 * 获取仓库商品总数
	 * @return
	 * @throws Exception
	 */
	Result<Object> countWareProduct() throws Exception;

	/**
	 * 获取商品总数
	 * @return
	 * @throws Exception
	 */
	Result<Object> countProduct() throws Exception;

	/**
	 * 根据分类ID获取该分类下的标签
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	DataTablesResult queryTypeTag(int typeId)throws Exception;
	/**
	 * 根据商品ID获取该商品所属的标签
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	DataTablesResult queryProductTag(int productId) throws Exception;
	/**
	 * 下架商品
	 * @param productId
	 * @return
	 */
	DataTablesResult pullOffProduct(int productId);
	
	/**
	 * 搜索商品
	 * @param productName
	 * @return
	 */
	DataTablesResult searchProduct(String productName);

}
