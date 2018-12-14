package com.hengmall.goods.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.hengmall.goods.model.DataTablesResult;
import com.hengmall.goods.model.ProductReq;
import com.hengmall.goods.model.ShopCarouselEntity;
import com.hengmall.goods.model.ShopsInfo;
import com.hengmall.goods.model.ShopsTypeBean;

public interface ShopsService {

    /**
     * 根据店主ID获取店铺爆款商品列表，只显示十条
     * @param userId
     * @return
     * @throws Exception
     */
	DataTablesResult getProductList(int draw, int length, int userId);

	   /**
     * 根据店主ID获取店铺商品二级分类
     * @param userId
     * @return
     * @throws Exception
     */
	List<ShopsTypeBean> getProductTypeList(int userId);

	
	List<ProductReq> queryByOrderStatus(int userId, int orderStatus, List<Integer> typeIdList);

	ShopsInfo queryShops(int userId,int storeId) throws Exception;

	List<ProductReq> queryShopsTypeList(int storeId, List<Integer> typeList);

	int attention(int storeId, int userId)throws Exception;

	Boolean isAttention(int storeId, int userId);

	JSONObject getTopProductList(int shopsId);

	List<ShopCarouselEntity> getShopsCarousel(int storeId);
		

}
