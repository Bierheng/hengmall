package com.server.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.server.entity.Brands;
import com.server.entity.DataTablesResult;
import com.server.entity.FlashSale;
import com.server.entity.FlashSaleTimeLiness;
import com.server.entity.Result;
import com.server.entity.TbGoodsTag;
import com.server.entity.common.CategoryListRequest;
import com.server.entity.common.CategoryListResponse;
import com.server.entity.common.ProductRequest;
import com.server.entity.common.ProductResponse;
import com.server.entity.common.combineSale.CombineSaleDelRequest;
import com.server.entity.common.combineSale.CombineSaleRequest;
import com.server.entity.common.combineSale.CombineSaleResponse;
import com.server.entity.common.combineSale.CombineSaleSaveRequest;
import com.server.entity.manage.product.isnew.ShopsRightEntity;
import com.server.entity.persistence.Page;

public interface CommonService {
	
	 /**
     * 通过id获取品牌
     * @param id
     * @return
     */
	Brands getGoodsBrandById(Integer id)throws Exception;

    /**
     * 获取品牌总记录数
     * @return
     * @throws Exception
     */
    int countBrands() throws Exception;
	
    /**
     * 获得品牌列表
     * @param parentId
     * @return
     */
    DataTablesResult getGoodsBrandList(int draw ,int length)throws Exception;

    /**
     * 添加品牌
     * @param tbItemCat
     * @return
     */
    int addGoodsBrand(Brands tbGoodsBrand)throws Exception;

    /**
     * 编辑品牌
     * @param tbItemCat
     * @return
     */
    int updateGoodsBrand(Brands tbGoodsBrand)throws Exception;

    /**
     * 删除品牌
     * @param id
     */
    int deleteGoodsBrand(Integer id)throws Exception;

    /**
     * 获取标签总记录数
     * @return
     * @throws Exception
     */
    int countTag() throws Exception;
    
    /**
     * 通过id获取标签
     * @param id
     * @return
     */
	TbGoodsTag getGoodsTagById(Integer id) throws Exception;

    /**
     * 获得标签列表
     * @param parentId
     * @return
     */
    DataTablesResult getGoodsTagList(int draw , int length) throws Exception;
    
    /**
     * 添加标签
     * @param typeId 
     * @param tbItemCat
     * @return
     */
    int addGoodsTag(TbGoodsTag tbGoodsTag) throws Exception;

    /**
     * 编辑标签
     * @param typeId 
     * @param tbItemCat
     * @return
     */
    int updateGoodsTag(TbGoodsTag tbGoodsTag) throws Exception;

    /**
     * 删除单个标签
     * @param id
     */
    int deleteGoodsTag(Integer id) throws Exception;
    
    /**
     * 处理商品标签
     * @param int[] tagIgs 改动后商品所属标签ID的数组
     * @param goodsId  商品ID
     * @return
     */
    DataTablesResult updateTagList(List<Integer> tagIds ,int goodsId) throws Exception;
    
    /**
     * 判断处理前端传来的图片的数据
     * @param pictrueIds 图片ID的数组
     * @param goodsId  商品ID
     * @return
     * @throws Exception
     */
	DataTablesResult judgePictureList(List<Integer> pictrueIds, int goodsId) throws Exception;
	
	/**
	 * 判断处理前端传来的商品详情的数据
	 * @param pictrueIds 详情ID的数组
	 * @param goodsId  商品ID
	 * @return
	 * @throws Exception
	 */
	DataTablesResult judgeDetailList(List<Integer> pictrueIds, int goodsId) throws Exception;

	/**
	 * 将商品到了指定时间自动上架的服务
	 * @throws Exception 
	 */
	int autoGoodsOn() throws Exception;

	/**
	 * 获取抢购活动列表
	 * @param draw 分页，当前页面数
	 * @param length 分页，页面大小
	 * @return
	 * @throws Exception
	 */
	DataTablesResult getFlashSaleList(int draw, int length) throws Exception;

	/**
	 * 修改抢购活动
	 * @param flashSale  抢购活动实体类
	 * @return
	 * @throws Exception
	 */
	int updateFlashSale(FlashSale flashSale) throws Exception;

	/**
	 *  新增抢购活动
	 * @param flashSale 抢购活动实体类
	 * @return
	 * @throws Exception
	 */
	int addFlashSale(FlashSale flashSale) throws Exception;

	/**
	 * 删除抢购活动
	 * @param ids  被删除的ID
	 * @return
	 * @throws Exception
	 */
	int delFlashSale(int ids) throws Exception;

	/**
	 * 获取抢购时间段的列表
	 * @param draw
	 * @param length
	 * @return
	 * @throws Exception
	 */
	DataTablesResult getFlashSaleTimeList(int draw, int length) throws Exception;

	/**
	 * 修改抢购时间段的信息
	 * @param flashSaleTime  抢购时间段的实体类对象
	 * @return
	 * @throws Exception
	 */
	int updateFlashSaleTime(FlashSaleTimeLiness flashSaleTime) throws Exception;

	/**
	 * 新增抢购时间段的信息
	 * @param flashSaleTime  抢购时间段的实体类对象
	 * @return
	 * @throws Exception
	 */
	int addFlashSaleTime(FlashSaleTimeLiness flashSaleTime) throws Exception;

	/**
	 * 删除抢购时间段的信息
	 * @param ids  被删除的ID
	 * @return
	 * @throws Exception
	 */
	int delFlashSaleTime(int ids) throws Exception;

	/**
	 * 根据ID获取抢购活动全部信息
	 * @param id  抢购活动的ID
	 * @return
	 * @throws Exception
	 */
	JSONObject getFlashSaleInfo(int id) throws Exception;
	
	 /**
     * 获取抢购活动记录总数
     * @return
     * @throws Exception
     */
	Result<Object> countFlashSale() throws Exception;
	
    /**
     * 获取抢购活动时间段记录总数
     * @return
     * @throws Exception
     */
	Result<Object> countFlashSaleTimeLiness() throws Exception;
	
	
	/**
	 * 获取商品分类列表
	 * @param page
	 * @param categoryListRequest
	 * @return
	 */
	Page<CategoryListResponse> categoryList(Page<CategoryListResponse> page,CategoryListRequest categoryListRequest);
	
	
	/**
	 * 获取平台商品列表
	 * @param page
	 * @param productReq
	 * @return
	 */
	Page<ProductResponse> productList(Page<ProductResponse> page,ProductRequest productReq);
	
	
	/**
	 * 获取商品特色服务
	 * @return
	 */
	List<ShopsRightEntity> rights();
	
	
	/**
	 * 拼单商品列表
	 * @param page
	 * @param combineSaleRequest
	 * @return
	 */
	Page<CombineSaleResponse> combineSaleList(Page<CombineSaleResponse> page,CombineSaleRequest combineSaleRequest);

	
	/**
	 * 拼单商品增改
	 * @param combineSaleSaveRequest
	 * @throws Exception 
	 */
	void combineSaleSave(CombineSaleSaveRequest combineSaleSaveRequest) throws Exception;

	
	/**
	 * 拼单商品删除
	 * @param combineSaleDelRequest
	 * @throws Exception 
	 */
	void combineSaleDel(CombineSaleDelRequest combineSaleDelRequest) throws Exception;
}
