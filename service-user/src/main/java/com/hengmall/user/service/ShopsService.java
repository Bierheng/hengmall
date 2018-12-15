package com.hengmall.user.service;

import java.util.List;

import com.hengmall.user.model.persistence.Page;
import com.hengmall.user.model.DataTablesResult;
import com.hengmall.user.model.ShopsInfo;
import com.hengmall.user.model.ShopsTypeBean;
import com.hengmall.user.model.TbShopsType;
import com.hengmall.user.model.TbShopsTypeAddReq;
import com.hengmall.user.model.TbShopsTypeDelReq;
import com.hengmall.user.model.manage.product.ProductReq;
import com.hengmall.user.model.shops.AddShopsRequest;
import com.hengmall.user.model.shops.AppraiseDelRequest;
import com.hengmall.user.model.shops.AppraiseListRequest;
import com.hengmall.user.model.shops.AppraiseListResponse;
import com.hengmall.user.model.shops.AppraiseSubRequest;
import com.hengmall.user.model.shops.CarouselShopsDelRequest;
import com.hengmall.user.model.shops.CarouselShopsListRequest;
import com.hengmall.user.model.shops.CarouselShopsListResponse;
import com.hengmall.user.model.shops.CarouselShopsSaveRequest;
import com.hengmall.user.model.shops.SearchShopsProductRequest;
import com.hengmall.user.model.shops.ShopsAddRequest;
import com.hengmall.user.model.shops.ShopsAttentionRequest;
import com.hengmall.user.model.shops.ShopsAttentionResponse;
import com.hengmall.user.model.shops.ShopsDelRequest;
import com.hengmall.user.model.shops.ShopsOrderRequest;
import com.hengmall.user.model.shops.ShopsOrderResponse;
import com.hengmall.user.model.shops.ShopsOrderSaveRequest;
import com.hengmall.user.model.shops.ShopsPlateDelRequest;
import com.hengmall.user.model.shops.ShopsPlateRequest;
import com.hengmall.user.model.shops.ShopsPlateResponse;
import com.hengmall.user.model.shops.ShopsPlateSaveRequest;
import com.hengmall.user.model.shops.ShopsPlateproductAddRequest;
import com.hengmall.user.model.shops.ShopsPlateproductDelRequest;
import com.hengmall.user.model.shops.ShopsPlateproductRequest;
import com.hengmall.user.model.shops.ShopsPlateproductResponse;
import com.hengmall.user.model.shops.ShopsProductListRequest;
import com.hengmall.user.model.shops.ShopsProductListResponse;
import com.hengmall.user.model.shops.ShopsProductRequest;
import com.hengmall.user.model.shops.ShopsProductResponse;
import com.hengmall.user.model.shops.ShopsRequest;
import com.hengmall.user.model.shops.ShopsUserRequest;
import com.hengmall.user.model.shops.ShopsUserResponse;
import com.hengmall.user.model.shops.TbShopsOrderRequest;
import com.hengmall.user.model.shops.TbShopsOrderResponse;
import com.hengmall.user.model.shops.UpdateShopsRequest;
import com.hengmall.user.model.shops.examine.ConfirmRequest;
import com.hengmall.user.model.shops.examine.ExamineListRequest;
import com.hengmall.user.model.shops.examine.ExamineRequest;
import com.hengmall.user.model.shops.examine.ExamineResponse;
import com.hengmall.user.model.shops.shopEntity.ShopsResponse;

public interface ShopsService {

	DataTablesResult getShopsOrderList(int draw, int length,int userId)throws Exception;

	int getShopsOrderCountById(int userId)throws Exception;

	DataTablesResult getShopsMemberList(int draw, int length, int userId)throws Exception;

	int getShopsMemberCountById(int userId)throws Exception;

	int getShopsValueCountById(int userId)throws Exception;

	DataTablesResult getShopsValueList(int draw, int length, int userId)throws Exception;

	DataTablesResult getShopsProductList(int draw, int length, int userId)throws Exception;

	int getShopsProductCountById(int userId)throws Exception;

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
		

	/**
	 * 获取店铺列表
	 */
	Page<ShopsResponse> shopList(Page<ShopsResponse> page,ShopsRequest shopsRequest);
	
	
	/**
	 * 获取店铺粉丝列表
	 * @param shopsRequest
	 * @return
	 */
	Page<ShopsAttentionResponse> shopAttentionList(Page<ShopsAttentionResponse> page,ShopsAttentionRequest shopsUsersRequest);
	
	
	
	/**
	 * 新增店铺
	 */
	void newShop(AddShopsRequest addShopsRequest);
	
	/**
	 * 修改店铺
	 */
	int updateShop(UpdateShopsRequest updateShopsRequest);
	
	
	/**
	 * 店铺会员列表
	 * @param shopsUserRequest
	 * @return
	 */
	Page<ShopsUserResponse> shopUserList(ShopsUserRequest shopsUserRequest);
	
	
	/**
	 * 店铺板块列表
	 * @param shopsUserRequest
	 * @return
	 */
	Page<ShopsPlateResponse> shopPlateList(Page<ShopsPlateResponse> page,ShopsPlateRequest shopsPlateRequest);
	
	/**
	 * 店铺板块增改
	 * @return
	 */
	int shopPlateSave(ShopsPlateSaveRequest shopsPlateSaveRequest);
	
	
	/**
	 * 店铺板块删除
	 * @param shopsPlateDelRequest
	 * @return
	 */
	int shopsPlateSaveDel(ShopsPlateDelRequest shopsPlateDelRequest);
	
	
	/**
	 * 店铺板块商品列表
	 * @param shopsPlateRequest
	 * @return
	 */
	Page<ShopsPlateproductResponse> shopPlateproductList(ShopsPlateproductRequest shopsPlateproductRequest);
	
	
	/**
	 * 移除店铺板块商品
	 * @return
	 */
	int shopPlateproductDel(ShopsPlateproductDelRequest shopsPlateproductDelRequest);
	
	/**
	 *  添加店铺板块商品
	 * @return
	 */
	int addPrpducts(ShopsPlateproductAddRequest shopsPlateproductAddRequest);
	
	
	/**
	 * 获取商品列表
	 * @param shopsProductListRequest
	 * @return
	 */
	Page<ShopsProductListResponse> shopsProductList(ShopsProductListRequest shopsProductListRequest);
	
	
	/**
	 * 店铺商品列表
	 * @param shopsProductRequest
	 * @return
	 */
	Page<ShopsProductResponse> shopsProduct(ShopsProductRequest shopsProductRequest);
	
	
	/**
	 * 店铺商品列表
	 * @return
	 */
	int shopDel(ShopsDelRequest shopsDelRequest);
	
	
	/**
	 * 添加店铺商品
	 * @return
	 */
	int shopAdd(ShopsAddRequest shopsAddRequest);
	
	
	/**
	 * 店铺订单列表
	 * @param shopsOrderRequest
	 * @return
	 */
	Page<ShopsOrderResponse> shopsOrder(Page<ShopsOrderResponse> page,ShopsOrderRequest shopsOrderRequest);
	
	
	/**
	 * 店铺商品评论列表
	 * @param page
	 * @return
	 */
	Page<AppraiseListResponse> appraiseList(Page<AppraiseListResponse> page,AppraiseListRequest appraiseListRequest);
	
	
	/**
	 * 店铺商品评论增改
	 * @param appraiseSubRequest
	 * @throws Exception 
	 */
	void appraiseSave(AppraiseSubRequest appraiseSubRequest) throws Exception;
	
	/**
	 * 店铺商品评论删除
	 * @param appraiseDelRequest
	 * @return
	 */
	int appraiseDel(AppraiseDelRequest appraiseDelRequest);
	
	
	/**
	 * 店铺轮播图列表
	 * @param page
	 * @param carouselShopsListRequest
	 * @return
	 */
	Page<CarouselShopsListResponse> carouselShopsList(Page<CarouselShopsListResponse> page,CarouselShopsListRequest carouselShopsListRequest);
	
	
	/**
	 * 店铺轮播图增改
	 * @param carouselShopsSaveRequest
	 * @return
	 */
	int carouselShopsSave(CarouselShopsSaveRequest carouselShopsSaveRequest);
	
	
	/**
	 * 店铺轮播图删除
	 * @param carouselShopsSaveRequest
	 * @return
	 */
	int carouselShopsDel(CarouselShopsDelRequest carouselShopsDelRequest);
	
	
	/**
	 * 店铺订单列表
	 * @param page
	 * @param shopsOrderRequest
	 * @return
	 */
	Page<TbShopsOrderResponse> shopsOrderList(Page<TbShopsOrderResponse> page,TbShopsOrderRequest tbShopsOrderRequest);
	
	/**
	 * 店铺订单操作
	 * @param shopsOrderSaveRequest
	 * @return
	 * @throws Exception 
	 */
	int shopsOrderSave(ShopsOrderSaveRequest shopsOrderSaveRequest) throws Exception;
	
	
	/**
	 * 店铺提交申请
	 * @param examineReq
	 * @return
	 * @throws Exception 
	 */
	int examineSub(ExamineRequest examineRequest) throws Exception;
	
	
	/**
	 * 店铺审核列表
	 * @param page
	 * @param examineReq
	 * @return
	 */
	Page<ExamineResponse> examineList(Page<ExamineResponse> page,ExamineListRequest examineListRequest);
	
	
	/**
	 * 店铺审核操作
	 * @param confirmRequest
	 * @return
	 * @throws Exception 
	 */
	public void isAgree(ConfirmRequest confirmRequest) throws Exception;

	List<TbShopsType> getShopsTypeList(int shops_id) throws Exception;

	int tbShopsTypeAdd(TbShopsTypeAddReq tbShopsTypeAddReq) throws Exception;

	void tbShopsTypeDel(TbShopsTypeDelReq tbShopsTypeDelReq, int shops_id) throws Exception;

	Page<ShopsProductListResponse> searchShopsProduct(SearchShopsProductRequest searchShopsProductRequest);
}
