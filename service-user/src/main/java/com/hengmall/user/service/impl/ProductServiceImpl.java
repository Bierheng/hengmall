package com.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.StringUtil;
import com.server.dao.PlatformDao;
import com.server.dao.RelCarouselDao;
import com.server.dao.SCouponDao;
import com.server.dao.SProductDao;
import com.server.dao.SResourcesDao;
import com.server.dao.ShopsDao;
import com.server.entity.DataTablesResult;
import com.server.entity.RelCarouselEntity;
import com.server.entity.RelProductCategoryEntity;
import com.server.entity.Result;
import com.server.entity.SCategory;
import com.server.entity.SCouponBean;
import com.server.entity.SCouponEntity;
import com.server.entity.SResources;
import com.server.entity.SkuReq;
import com.server.entity.SkuReq2;
import com.server.entity.TbGoodsTag;
import com.server.entity.TbGoodsTagBean;
import com.server.entity.TbProductTag;
import com.server.entity.api.Ajax;
import com.server.entity.manage.product.ListProductReq;
import com.server.entity.manage.product.ListProductResp;
import com.server.entity.manage.product.ProductBean;
import com.server.entity.manage.product.ProductBean2;
import com.server.entity.manage.product.ProductDetailBean;
import com.server.entity.manage.product.ProductIdReq;
import com.server.entity.manage.product.ProductReq;
import com.server.entity.manage.product.SProductBean;
import com.server.entity.platform.StateRequest;
import com.server.service.CommonService;
import com.server.service.GoodsTypeService;
import com.server.service.ProductService;
import com.server.service.TigLibraryService;
import com.server.utils.CommonUtils;
import com.server.utils.ResultUtil;

@Service
public class ProductServiceImpl implements ProductService  {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private SResourcesDao sResourcesDao;
	@Autowired
	private SProductDao sProductDao;
	@Autowired	
	private CommonService commonService;
	@Autowired
	private RelCarouselDao relCarouselDao;
	@Autowired
	private SCouponDao sCouponDao;
	@Autowired
	private GoodsTypeService goodsTypeService;
	@Autowired
	private TigLibraryService tigLibraryService;
	@Autowired
	private PlatformDao platformDao;
	@Autowired
	private ShopsDao shopsDao;
	
	/**
	 * @Title: 获取商品列表
	 * @Description: 
	 */
	@Override
	public Ajax<ListProductResp> getProductList(ListProductReq listProductReq)throws Exception {
		int page = listProductReq.getPage();
		int pagesize = listProductReq.getPagesize();

		Map<String, Object> param = new HashMap<>();
		param.put("page", (page-1)*pagesize);
		param.put("pagesize", pagesize);

		List<SProductBean> productList = sProductDao.productList(param);
		List<ProductBean2> list = new LinkedList<>();
		if (productList != null && productList.size() > 0) {
			for (SProductBean sProductEntity : productList) {
				ProductBean2 productBean = new ProductBean2();
				productBean.setId(sProductEntity.getId());
				productBean.setName(sProductEntity.getName());
				productBean.setHeadimg(sProductEntity.getHeadimg());
				productBean.setPrice((double)((sProductEntity.getPrice())/100));
				productBean.setStock(sProductEntity.getStock());
				productBean.setAllowrefund(sProductEntity.getAllowrefund());
				productBean.setStatus(sProductEntity.getStatus()); // 状态：1上架，0下架
				productBean.setCtime(sProductEntity.getCtime()); // 发布时间
				productBean.setType(sProductEntity.getType());   //商品类型
				productBean.setSalesvolumeFalse(sProductEntity.getSalesvolumeFalse());  //虚拟销量
				list.add(productBean);
			}
		}
		ListProductResp productListResp = new ListProductResp();
		productListResp.setProductList(list);
		productListResp.setTotal(param.containsKey("total") ? Integer.parseInt(String.valueOf(param.get("total"))) : 0);
		return new Ajax<ListProductResp>(productListResp);
	}
	
	/**
	 * @Title: 获取仓库商品列表
	 * @Description: 
	 */
	@Override
	public Ajax<ListProductResp> getWareProductList(ListProductReq listProductReq)throws Exception {
		int page = listProductReq.getPage();
		int pagesize = listProductReq.getPagesize();

		Map<String, Object> param = new HashMap<>();
		param.put("page", (page-1)*pagesize);
		param.put("pagesize", pagesize);

		List<SProductBean> productList = sProductDao.wareProductList(param);
		List<ProductBean2> list = new LinkedList<>();
		if (productList != null && productList.size() > 0) {
			for (SProductBean sProductEntity : productList) {
				ProductBean2 productBean = new ProductBean2();
				productBean.setId(sProductEntity.getId());
				productBean.setName(sProductEntity.getName());
				productBean.setHeadimg(sProductEntity.getHeadimg());
				productBean.setPrice((double)((sProductEntity.getPrice())/100));
				productBean.setStock(sProductEntity.getStock());
				productBean.setAllowrefund(sProductEntity.getAllowrefund());
				productBean.setStatus(sProductEntity.getStatus()); // 状态：1上架，0下架
				productBean.setCtime(sProductEntity.getCtime()); // 发布时间
				list.add(productBean);
			}
		}
		ListProductResp productListResp = new ListProductResp();
		productListResp.setProductList(list);
		productListResp.setTotal(param.containsKey("total") ? Integer.parseInt(String.valueOf(param.get("total"))) : 0);
		return new Ajax<ListProductResp>(productListResp);
	}

	/**
	 * @return 
	 * @Title: 获取商品信息列表
	 * @Description: 
	 */
	@Override
	@Transactional
	public  DataTablesResult getProductByList(@RequestBody ProductIdReq product )throws Exception {
		logger.info("客户端请求数据【manage/product/getProductList】,{}");
		DataTablesResult result = new DataTablesResult();
		int typeId = product.getTypeId();
		SCategory  sCategory = goodsTypeService.getGoodsTypeById(typeId);
		if(sCategory.getLevel() == 1){
			List<SProductBean> productList = sProductDao.shortProductList(typeId);
			result.setData(productList);
		}else{
			List<SProductBean> productList = sProductDao.shortProductListTwe(typeId);
			result.setData(productList); 
		}
		result.setSuccess(true);;
        return result;
	}	
	
	/**
	 * @return 
	 * @Title: 新增商品及商品详情
	 * @Description: 
	 */
	@Override
	@Transactional
	public  DataTablesResult addProduct(ProductBean product)throws Exception {
		logger.info("客户端请求数据【manage/product/addProduct】,{}", product.toString());
		DataTablesResult result = new DataTablesResult();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
		String sd = sdf.format(new Date(System.currentTimeMillis()));   // 时间戳转换成时间
		Date date = sdf.parse(sd);
		int goodsId;
		//商品图片
		List<Integer> pictrueIds = product.getPictrueIds();
		//商品标签
		List<Integer> tagIds = product.getTagIds();
		//商品详情
		List<Integer> productDetail = product.getProductDetail();
		SProductBean sProductBean = new SProductBean();   //最终给dao的数据
		
		/* 新增 */
		sProductBean.setTagids(JSON.toJSONString(product.getArrTagids()));  //所属标签已选择数组
		sProductBean.setPriceType(product.getPriceType());  //价格类型
		sProductBean.setRights(JSON.toJSONString(product.getArrRights()));  //商品特色服务已选择数组
		sProductBean.setType(product.getType());   //商品类型
		sProductBean.setSalesvolumeFalse(product.getSalesvolumeFalse());   //虚拟销量
		sProductBean.setStateId(product.getStateId());      //国家信息表ID
		
		
		sProductBean.setEndimg(product.getEndimg());   //底部图片
		sProductBean.setAllowrefund(product.getAllowrefund());
		if("null".equals(String.valueOf(product.getAttribute()))){
			sProductBean.setAttribute(null);
		}else{
			sProductBean.setAttribute(product.getAttribute());
		}
		sProductBean.setCode(product.getCode());
		sProductBean.setKeys(product.getKeys());
		sProductBean.setCoinoffset((int)(product.getCoinoffset()*100));
		sProductBean.setDescription(product.getDescription());
		sProductBean.setFreight((int)(product.getFreight()*100));
		if( "null".equals(String.valueOf(product.getGoodsontime()))){
			sProductBean.setGoodsontime(date);
		}else if(product.getGoodsontime().getTime() > System.currentTimeMillis()){
			sProductBean.setGoodsontime(product.getGoodsontime());
		}else{
			sProductBean.setGoodsontime(date);
		}
		sProductBean.setGoodsontype(product.getGoodsontype());
		sProductBean.setHeadimg(product.getHeadimg());
		sProductBean.setId(product.getId());
		if(product.getIspresale()){
			sProductBean.setIspresale("true");
		}else{
			sProductBean.setIspresale("false");
		}
		sProductBean.setLimitnum(product.getLimitnum());
		sProductBean.setName(product.getName());
		sProductBean.setOldprice((int)(product.getOldprice()*100));
		sProductBean.setStock(product.getStock());
		//用于查看数据再前端原来的上架状态
		sProductBean.setStatus(product.getStatus());
		sProductBean.setSaledeliverdate(product.getSaledeliverdate());
		if( "null".equals(String.valueOf(product.getSaledelivertime()))){
			sProductBean.setSaledelivertime(date);
		}else if(product.getSaledelivertime().getTime() > System.currentTimeMillis()){
			sProductBean.setSaledelivertime(product.getSaledelivertime());
		}else{
			sProductBean.setSaledelivertime(date);
		}
		sProductBean.setPrice((int)(product.getPrice()*100));

			//先处理资源修改
			if(CommonUtils.judge(String.valueOf(product.getId()))){
				goodsId = sProductBean.getId();
				
				//此处处理上架后的动作，将商品以及分类的ID放入表中便于前端的商品查询
				RelProductCategoryEntity relProductCategory = new RelProductCategoryEntity();
				//数据原来的状态为上架，可以进行下架操作以及继续保持上架
				if(sProductBean.getStatus() == SProductBean.GOODSON_STATUS){
					
					if(product.getGoodsontype() != 1){
						//TODO 此处还没进行自定义上架处理。定时器自动每一个小时执行一次将商品满足条件的转为上架
						relProductCategory.setProductid(goodsId);
						sProductDao.deleteProductCategory(relProductCategory);
						sProductBean.setStatus(SProductBean.PUSHON_STATUS);
					}
				}else{
					//数据原来的状态为下架，
					if(product.getGoodsontype() == 1){
						relProductCategory.setProductid(goodsId);
						sProductDao.insertProductCategory(relProductCategory);
						sProductBean.setStatus(SProductBean.GOODSON_STATUS);
					}
				}
				
			//再处理商品以及商品详情的修改
				if("null".equals(String.valueOf(product.getAttribute()))){
					//TODO 此处可能需要实现一下将默认值放入SKU中的操作
					sProductDao.updateByIdNoJson(sProductBean);
				}else{
					
					//这里的操作将将skuImgIds[970, null, 971]转为[970, 0, 971]，将null转为0
					JSONObject obj = product.getAttribute();
					Object jsonArray =  obj.get("skuImgIds");
//					Object detailsJson =  obj.get("details");
					Object standardJson =  obj.get("standard");
					String skuTmgStr = jsonArray.toString();
					if(!"[]".equals(skuTmgStr)){
						skuTmgStr = skuTmgStr.replace(" ", "").substring(1, skuTmgStr.replace(" ", "").length()-1);
						String[] strArr = skuTmgStr.split(",");
						List<Integer> newStrArr = new ArrayList<>();
						for(String str : strArr){
							if("null".equals(str)){
								newStrArr.add(0);
							}else{
								newStrArr.add(Integer.valueOf(str));
							}
						}
						obj.put("skuImgIds", newStrArr);
					}
					if("[]".equals(standardJson.toString())){
						//details:[{price: 100, stock: 200, coding: "1000", one: "测试1", index: 0}]
						//standard[{vals: ["测试1"], name: "测试"}]
						SkuReq2 detailsObj = new SkuReq2();
						JSONArray detailsArr = new JSONArray();
						detailsObj.setPrice((int)(sProductBean.getPrice()/100));
						detailsObj.setStock(sProductBean.getStock());
						detailsObj.setCoding("1000");
						detailsObj.setOne(sProductBean.getName());
						detailsObj.setIndex(0);
						detailsArr.add(detailsObj);
						
						SkuReq standardObj = new SkuReq();
						JSONArray standardArr = new JSONArray();
						List<String> list = new  ArrayList<>();
						list.add(sProductBean.getName());
						standardObj.setName("默认值");
						standardObj.setVals(list);
						standardArr.add(standardObj);
						obj.put("details", detailsArr);
						obj.put("standard", standardArr);
						System.err.println("添加默认值之后的拼接："+obj);
					}
					
					sProductDao.updateById(sProductBean);    //改 s_product 表
				}
				
				if(!"[]".equals(String.valueOf(pictrueIds))){
					
					//处理图片
					commonService.judgePictureList(pictrueIds, product.getId());
				}
				if(!"[]".equals(String.valueOf(productDetail))){
					//处理详情图片
					commonService.judgeDetailList(productDetail, product.getId());
				}
				if(!"[]".equals(String.valueOf(tagIds))){
					//处理标签
		    		result = commonService.updateTagList(tagIds, goodsId);
				}
				result.setSuccess(true);
				return result;
			}else{
				
				//新增商品
				if(product.getGoodsontype() == 1){
					sProductBean.setStatus(SProductBean.GOODSON_STATUS);
				}else{
					sProductBean.setStatus(SProductBean.PUSHON_STATUS);
				}
				if("null".equals(String.valueOf(product.getAttribute()))){
					//TODO 此处可能需要实现一下将默认值放入SKU中的操作
					 sProductDao.insertNoJson(sProductBean);
					 goodsId = sProductBean.getId();
				}else{
					 sProductDao.insert(sProductBean);
					 goodsId = sProductBean.getId();
				}
				//此处处理上架后的动作，将商品以及分类的ID放入表中便于前端的商品查询
				RelProductCategoryEntity relProductCategory = new RelProductCategoryEntity();
				if(sProductBean.getStatus() == SProductBean.GOODSON_STATUS){
					
					relProductCategory.setProductid(goodsId);
					sProductDao.insertProductCategory(relProductCategory);
				}
				ProductDetailBean productDetailBean = new ProductDetailBean();
				//新增资源
				for( int pictrueId : productDetail ){
					productDetailBean.setProductid(goodsId);
					productDetailBean.setResourcesid(pictrueId);
					productDetailBean.setType(ProductDetailBean.Detail_Type);
					//新增商品详情	
					sProductDao.insertProductDetail(productDetailBean);
				}
				//新增商品图片
				for( int pictrueId : pictrueIds ){
					productDetailBean = new ProductDetailBean();
					productDetailBean.setProductid(goodsId);
					productDetailBean.setResourcesid(pictrueId);
					productDetailBean.setType(ProductDetailBean.Img_Type);
					//新增商品图片	
					sProductDao.insertProductDetail(productDetailBean);
				}
				TbProductTag ProductTag = new TbProductTag();
				for(int tagId: tagIds){
					 ProductTag = new TbProductTag();
					 ProductTag.setProductId(goodsId);
					 ProductTag.setTagId(tagId);
					sProductDao.insertProductTag(ProductTag);
				}
				result.setSuccess(true);
				return result;
			}
	}
	
	/**
	 * @Title: 获取指定商品ID的所有信息
	 * @Description:TOD
	 */
	@Override
    public JSONObject queryProductById(ProductReq product)throws Exception{
    	logger.info("客户端请求数据【manage/product/queryProductById】,{}", product.toString());
    	int productId = product.getProductId();
//    	DataTablesResult result = new DataTablesResult();
    	JSONObject json = new JSONObject();
    		List<SResources> list = sResourcesDao.queryByproductId(productId, ProductDetailBean.Img_Type);
    		
    		ProductBean2 productBean2 = sProductDao.queryById(productId);	//原始商品数据
    		String jsonArrayStr = productBean2.getDescription().toString();  //商品描述
    		//jsonArrayStr = new String(jsonArrayStr.getBytes("ISO-8859-1"),"utf-8");
    		
    		List<SResources> productDetail = sResourcesDao.queryByproductId(productId, ProductDetailBean.Detail_Type);
    		List<TbGoodsTagBean> tagList = sProductDao.productTagList(productId);
    		List<Integer> tagIntList = new ArrayList<>();
    		List<Integer> pictureIdsList = new ArrayList<>();
    		List<Integer> productDetailList = new ArrayList<>();
    		for(SResources sResources : productDetail){
    			productDetailList.add(sResources.getId());
    		}
    		for(TbGoodsTagBean tbGoodsTag : tagList){
    			tagIntList.add(tbGoodsTag.getId());
    		}
    		for(SResources sResources : list){
    			pictureIdsList.add(sResources.getId());
    		}
    		
    		//JSONObject jso =JSON.parseObject(new String(productBean2.getAttribute().getBytes("ISO8859-1"),"UTF-8"));
    		JSONObject jso =JSON.parseObject(productBean2.getAttribute());  //尺寸大小、颜色
    		
    		JSONArray jsonArray = JSON.parseArray(jsonArrayStr);
    		System.out.println(jso.toString());
    		List<Object> objList = jso.getJSONArray("skuImgIds");
    		System.out.println(objList);
    		List<String> picList = new ArrayList<>();
    		if(objList.size() > 0){
	    		for(int i=0; i<objList.size();i++){
	    			Object id = objList.get(i);
	    			if("null".equals(String.valueOf(id))||"0".equals(String.valueOf(id))){
	    				picList.add(null);
	    			}else{
		    			SResources sResources = sResourcesDao.queryById(Integer.valueOf(id.toString()));
		    			picList.add(sResources.getUrl());
	    			}
	    		}
    		}
    		
    		ProductBean productBean = new ProductBean();   //最终返回
    		productBean.setCode(productBean2.getCode());
    		productBean.setCoinoffset(productBean2.getCoinoffset()/100);
    		productBean.setDescription(jsonArray);
    		productBean.setKeys(productBean2.getKeys());
    		productBean.setFreight(productBean2.getFreight()/100);
    		if(productBean2.getGoodsontime().getTime() < System.currentTimeMillis()){
    			productBean.setGoodsontime(null);
    		}else{
    			productBean.setGoodsontime(productBean2.getGoodsontime());
    		}
    		productBean.setGoodsontype(productBean2.getGoodsontype());
    		productBean.setHeadimg(productBean2.getHeadimg());
    		productBean.setId(productBean2.getId());
    		if("true".equals(productBean2.getIspresale().toString())){
        		productBean.setIspresale(true);
    		}else{
    			productBean.setIspresale(false);
    		}
    		productBean.setLimitnum(productBean2.getLimitnum());
    		productBean.setName(productBean2.getName());
    		productBean.setOldprice(productBean2.getOldprice()/100);
    		productBean.setOnetypeid(productBean2.getOnetypeid());
    		productBean.setTwotypeid(productBean2.getTwotypeid());
    		productBean.setStock(productBean2.getStock());
    		productBean.setStatus(productBean2.getStatus());
    		productBean.setSaledeliverdate(productBean2.getSaledeliverdate());
			productBean.setSaledelivertime(productBean2.getSaledelivertime());
    		productBean.setPrice(productBean2.getPrice()/100);
    		productBean.setAttribute(jso);
    		productBean.setTwotypeid(productBean2.getTwotypeid());
    		productBean.setOnetypeid(productBean2.getOnetypeid());
    		productBean.setTagIds(tagIntList);
    		productBean.setProductDetail(productDetailList);
    		productBean.setPictrueIds(pictureIdsList);
    		productBean.setAllowrefund(productBean2.getAllowrefund());
    		
    		productBean.setEndimg(productBean2.getEndimg());   //底部图片
    		productBean.setArrTagids(productBean2.getTagids()); //所属标签已选择数组
    		productBean.setArrRights(productBean2.getRights());  //商品特色服务已选择数组
    		productBean.setPriceType(productBean2.getPriceType());	//价格类型，1：人民币，2：美元；
    		productBean.setType(productBean2.getType());    //商品类型
    		productBean.setStateId(productBean2.getStateId());  //国籍id
    		productBean.setSalesvolumeFalse(productBean2.getSalesvolumeFalse());  //虚拟销量
    		
    		productBean.setRightsList(sProductDao.getRights());//商品特色服务列表
    		productBean.setTigList(tigLibraryService.tigLibraryList());			//标签列表
    		productBean.setStateList(platformDao.stateList(new StateRequest()));  //国籍列表

    		
    		json.put("ProductInfo", productBean);
    		json.put("pictureIds", list);
    		json.put("productDetail", productDetail);
    		json.put("productDetail", productDetail);
    		json.put("skuImgIds", picList);
    		json.put("success", true);
            return json;
    }
	
	@Override
	@Transactional
	public DataTablesResult delProduct(@RequestBody ProductBean product) throws Exception {
		logger.info("客户端请求数据（manage/product/delProduct）：{}", product.toString());
		DataTablesResult result = new DataTablesResult();
			int id = product.getId();
			sProductDao.delById(id);
			sProductDao.delAdsById(id);
			sProductDao.delCarouselById(id);
			sProductDao.delCategoryById(id);
			sProductDao.delCollectionById(id);
			sProductDao.delDetailById(id);
			sProductDao.delFlashById(id);
			result.setSuccess(true);
			return result;
	}
	
	/**
	 * @Title: 查询商品所属的标签
	 * @Description: 
	 */
	@Override
	public DataTablesResult queryProductTag(int productId)throws Exception {
			DataTablesResult result = new DataTablesResult();
			List<TbGoodsTagBean> list = sProductDao.productTagList(productId);
			result.setData(list);
            result.setSuccess(true);
            return result;
	}
	
	/**
	 * @Title: 获取指定分类下的商品所属的标签
	 * @Description: TODO
	 */
	@Override
    public DataTablesResult queryTypeTag(int typeId)throws Exception{	
    	DataTablesResult result = new DataTablesResult();
    	List<TbGoodsTag> list=sProductDao.selectByTypeId(typeId);
        result.setData(list);
        result.setSuccess(true);
        return result;
    }
	
    /**
     * 获取商品记录总数
     * @return
     * @throws Exception
     */
	@Override
    public Result<Object> countProduct() throws Exception{
    		int result = sProductDao.countProduct();
            return new ResultUtil<Object>().setData(result);
    }
    
    /**
     * 获取仓库商品记录总数
     * @return
     * @throws Exception
     */
	@Override
    public Result<Object> countWareProduct() throws Exception{
    		int result = sProductDao.countWareProduct();
            return new ResultUtil<Object>().setData(result);
    }
    
    
    /** 
     * 获取所有轮播图列表
     * @return
     * @throws Exception
     */
	@Override
    public DataTablesResult carouselList(int draw , int length)throws Exception {
    	DataTablesResult result = new DataTablesResult();
    		if(CommonUtils.judge(String.valueOf(draw))&&CommonUtils.judge(String.valueOf(length))){
    			Map<String, Object> param = new HashMap<>();
    			param.put("page", (draw-1)*length);
    			param.put("pagesize", length);
    			List<RelCarouselEntity> list=relCarouselDao.carouselList(param);
    	        /*List<RelCarouselBean> list2 = new ArrayList<>();
    	        for(RelCarouselEntity carousel : list){
    	        	int productId = carousel.getProduct_id();
    	        	int typeId = carousel.getS_category_id();
    	        	ProductBean2 productBean = sProductDao.queryById(productId);
    	        	SCategory sCategory = sCategoryDao.queryById(typeId);
    	        	RelCarouselBean carouselBean = new RelCarouselBean();
    	        	carouselBean.setS_category_id(carousel.getS_category_id());
    	        	carouselBean.setCategoryName(sCategory.getName());
    	        	carouselBean.setCreated_time(carousel.getCreated_time());
    	        	carouselBean.setId(carousel.getId());
    	        	carouselBean.setProduct_id(carousel.getProduct_id());
    	        	carouselBean.setProductName(productBean.getName());
    	        	carouselBean.setPath(carousel.getPath());
    	        	list2.add(carouselBean);
    	        }*/
    			result.setData(list);
    			result.setSuccess(true);
    			return result;
    		}else{
    			result.setSuccess(false);
    			result.setError("传入参数为空！");
    			return result;
    		}
    }

    /**
     * 添加或修改轮播图
     * @param relCarousel  轮播图的实体类
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
    public Result<Object> addCarousel(RelCarouselEntity relCarousel)throws Exception{
    	logger.info("客户端请求数据（manage/product/addCarousel）：{}", relCarousel.toString());
			int shops_id = relCarousel.getShops_id();
			String shops_name = shopsDao.getShopsName(String.valueOf(shops_id));
			relCarousel.setShops_name(shops_name);
        	if(CommonUtils.judge(String.valueOf(relCarousel.getId()))){
    				//需要添加对比记录的结果 根据对比记录的结果来确定哪些数据需要删除修改等
        			relCarouselDao.updateById(relCarousel);
        	}else{
        			relCarouselDao.insert(relCarousel);
        	}
            return new ResultUtil<Object>().setData(null);
    }

    /**
     * 删除轮播图
     * @param ids   id数组
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
    public Result<Object> delBrand(int id)throws Exception{
            int result = relCarouselDao.delById(id);
            if(result == 0){
                return new ResultUtil<Object>().setErrorMsg("删除失败！");
            }
            return new ResultUtil<Object>().setData(null);
    }
    
    /**
     * 获取轮播图记录总数
     * @return
     * @throws Exception
     */
	@Override
    public Result<Object> countCarousel() throws Exception{
    		int result = relCarouselDao.countCarousel();
            return new ResultUtil<Object>().setData(result);
    }
    
    /** 
     * 获取所有优惠券列表
     * @return
     * @throws Exception
     */
	@Override
    public DataTablesResult couponList(int draw , int length)throws Exception {
    	DataTablesResult result = new DataTablesResult();
		if(CommonUtils.judge(String.valueOf(draw))&&CommonUtils.judge(String.valueOf(length))){
			Map<String, Object> param = new HashMap<>();
			param.put("page", (draw-1)*length);
			param.put("pagesize", length);
			List<SCouponEntity> list=sCouponDao.couponList(param);
			result.setData(list);
			result.setSuccess(true);
			return result;
		}else{
			result.setSuccess(false);
			result.setError("传入参数为空！");
			return result;
		}
    }

    /**
     * 添加或修改优惠券
     * @param sCouponEntity  优惠券的实体类
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
    public Result<Object> addCoupon(SCouponEntity sCouponEntity)throws Exception{
    	logger.info("客户端请求数据【manage/product/addCoupon】,{}", sCouponEntity.toString());
    	SCouponBean sCouponBean = new SCouponBean();
        	if(CommonUtils.judge(String.valueOf(sCouponEntity.getId()))){
        		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        		String a = sdf.format(sCouponEntity.getStart_time());
        		String d = sdf.format(sCouponEntity.getEnd_time());
        		Date sd = sdf.parse(a);
        		Date sf = sdf.parse(d);
        		sCouponBean.setStart_time(sd);
        		sCouponBean.setEnd_time(sf);
        		
        		sCouponBean.setId(sCouponEntity.getId());
        		sCouponBean.setImg(sCouponEntity.getImg());
        		sCouponBean.setPrerequisite(sCouponEntity.getPrerequisite());
        		sCouponBean.setPrice(sCouponEntity.getPrice());
        		sCouponBean.setTitle(sCouponEntity.getTitle());
        		sCouponDao.updateById(sCouponBean);
        	}else{
        		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        		String a = sdf.format(sCouponEntity.getStart_time());
        		String d = sdf.format(sCouponEntity.getEnd_time());
        		Date sd = sdf.parse(a);
        		Date sf = sdf.parse(d);
        		sCouponBean.setStart_time(sd);
        		sCouponBean.setEnd_time(sf);
        		sCouponBean.setImg(sCouponEntity.getImg());
        		sCouponBean.setPrerequisite(sCouponEntity.getPrerequisite());
        		sCouponBean.setPrice(sCouponEntity.getPrice());
        		sCouponBean.setTitle(sCouponEntity.getTitle());
        		sCouponDao.insert(sCouponBean);
        	}
            return new ResultUtil<Object>().setData(null);
    }

    /**
     * 删除优惠券
     * @param ids   id数组
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
    public Result<Object> delCoupon(int ids)throws Exception{
                int result=sCouponDao.delById(ids);
                if(result==0){
                    return new ResultUtil<Object>().setErrorMsg("删除失败！");
                }
            return new ResultUtil<Object>().setData(null);
    }
    
    /**
     * 获取优惠券记录总数
     * @return
     * @throws Exception
     */
	@Override
    public Result<Object> countCoupon() throws Exception{
		int result = sCouponDao.countCoupon();
        return new ResultUtil<Object>().setData(result);
    }

	@Override
	@Transactional
	public DataTablesResult pullOffProduct(int productId) {
		DataTablesResult result = new DataTablesResult();
		SProductBean sProductBean = sProductDao.queryById2(productId);
		if(sProductBean.getStatus() == SProductBean.GOODSON_STATUS){//  0下架
			sProductBean.setStatus(SProductBean.PUSHON_STATUS);//   1上架
			sProductBean.setGoodsontype(SProductBean.WAREHOUSE_TYPE);//  存入仓库
			sProductDao.updateByIdNoJsonAll(sProductBean);
			sProductDao.delCategoryById(productId);
		}else if(sProductBean.getStatus() == SProductBean.PUSHON_STATUS) { //如果点了上架
			sProductBean.setStatus(SProductBean.GOODSON_STATUS);
			sProductBean.setGoodsontype(SProductBean.GOODSON_TYPE);//  立即上架
			sProductDao.updateByIdNoJsonAll(sProductBean);
		}
		result.setSuccess(true);
		return result;
	}

	@Override
	public DataTablesResult searchProduct(String productName) {
		DataTablesResult result = new DataTablesResult();
		List<SProductBean> list = new ArrayList<>();
		Map<String, Object> param = new HashMap<>();
		param.put("page", 1);
		param.put("pagesize", 10);
		if(!StringUtil.isEmpty(productName)){
			 list = sProductDao.findByName(productName);
		}else{
			list = sProductDao.productList(param);
		}
		result.setSuccess(true);
		result.setData(list);
		return result;
	}

}
