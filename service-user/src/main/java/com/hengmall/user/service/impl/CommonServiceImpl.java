package com.hengmall.user.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hengmall.user.dao.BrandsDao;
import com.hengmall.user.dao.CommonDao;
import com.hengmall.user.dao.FlashSaleDao;
import com.hengmall.user.dao.FlashSaleTimeLinessDao;
import com.hengmall.user.dao.SProductDao;
import com.hengmall.user.dao.SResourcesDao;
import com.hengmall.user.dao.ShopsDao;
import com.hengmall.user.dao.TbGoodsTagDao;
import com.hengmall.user.dao.TbTopicDao;
import com.hengmall.user.exception.XmallException;
import com.hengmall.user.model.Brands;
import com.hengmall.user.model.DataTablesResult;
import com.hengmall.user.model.FlashSale;
import com.hengmall.user.model.FlashSaleBean;
import com.hengmall.user.model.FlashSaleTimeLiness;
import com.hengmall.user.model.Result;
import com.hengmall.user.model.SProductEntity;
import com.hengmall.user.model.SResourcesEntity;
import com.hengmall.user.model.TbGoodsTag;
import com.hengmall.user.model.TbGoodsTagBean;
import com.hengmall.user.model.TbProductTag;
import com.hengmall.user.model.TbTopic;
import com.hengmall.user.model.TbTopicBean;
import com.hengmall.user.model.TbTopicImg;
import com.hengmall.user.model.common.CategoryListRequest;
import com.hengmall.user.model.common.CategoryListResponse;
import com.hengmall.user.model.common.ProductRequest;
import com.hengmall.user.model.common.ProductResponse;
import com.hengmall.user.model.common.combineSale.CombineSaleDelRequest;
import com.hengmall.user.model.common.combineSale.CombineSaleRequest;
import com.hengmall.user.model.common.combineSale.CombineSaleResponse;
import com.hengmall.user.model.common.combineSale.CombineSaleSaveRequest;
import com.hengmall.user.model.common.combineSale.Select;
import com.hengmall.user.model.manage.product.ProductDetailBean;
import com.hengmall.user.model.manage.product.SProductBean;
import com.hengmall.user.model.manage.product.isnew.ShopsRightEntity;
import com.hengmall.user.model.persistence.Page;
import com.hengmall.user.service.CommonService;
import com.hengmall.user.util.ResultUtil;


@Service
public class CommonServiceImpl implements CommonService {

	 final static Logger log= LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private BrandsDao brandsDao;
	@Autowired
	private TbGoodsTagDao tbGoodsTagDao;
	@Autowired
	private SProductDao sProductDao;
    @Autowired
    private SResourcesDao sResourcesDao;
    @Autowired
    private FlashSaleDao flashSaleDao;
    @Autowired
    private FlashSaleTimeLinessDao flashSaleTimeDao;
    @Autowired
    private TbTopicDao tbTopicDao;
    @Autowired
    private ShopsDao shopsDao;
    @Autowired
    private CommonDao commonDao;
    
	 /**
     * 通过id获取品牌
     * @param id
     * @return
     */
    
	@Override
	@Transactional
	public Brands getGoodsBrandById(Integer id)throws Exception {
			Brands tbGoodsBrand = brandsDao.queryById(id);
			return tbGoodsBrand;
	}

    /**
     * 获得品牌列表
     * @param parentId
     * @return
     */
	@Override
	@Transactional
	public DataTablesResult getGoodsBrandList(int draw ,int length)throws Exception {
			DataTablesResult result = new DataTablesResult();
			long countBrands = brandsDao.countBrands();
			
	        //分页
	        //紧跟着分页器后面的第一个查询会被分页
	        PageHelper.startPage(draw,length);
			List<Brands> list = brandsDao.queryForList();
	        PageInfo<Brands> pageInfo=new PageInfo<>(list);
	        result.setRecordsFiltered((int)pageInfo.getEndRow());
	        result.setRecordsTotal(Math.toIntExact(countBrands));
	        result.setSuccess(true);
	        result.setData(list);
	        result.setDraw(draw);
	        return result;
	}

    /**
     * 添加品牌
     * @param tbItemCat
     * @return
     */
	@Override
	@Transactional
	public int addGoodsBrand(Brands tbGoodsBrand) throws Exception{
		int result = brandsDao.insert(tbGoodsBrand);
        if(result!=1){
            throw new XmallException("新增商品品牌失败");
        }
		return result;
	}

    /**
     * 编辑品牌
     * @param tbItemCat
     * @return
     */
	@Override
	@Transactional
	public int updateGoodsBrand(Brands tbGoodsBrand)throws Exception {
		int result = brandsDao.updateById(tbGoodsBrand);
		return result;
	}

    /**
     * 删除品牌
     * @param id
     */
	@Override
	public int deleteGoodsBrand(Integer id) throws Exception{
		int result =  brandsDao.delById(id);
		return result;
	}

    /**
     * 通过id获取标签
     * @param id
     * @return
     */
	@Override
	@Transactional
	public TbGoodsTag getGoodsTagById(Integer id) throws Exception {
		TbGoodsTag tbGoodsTag = tbGoodsTagDao.queryById(id);
		return tbGoodsTag;
	}

    /**
     * 获得标签列表
     * @param parentId
     * @return
     */
	@Override
	@Transactional
	public DataTablesResult getGoodsTagList(int draw ,int length)throws Exception {
		DataTablesResult result = new DataTablesResult();
		long counts = tbGoodsTagDao.countTbGoodsTag();
        PageHelper.startPage(draw,length);
        List<TbGoodsTag> list = tbGoodsTagDao.queryForList();
        PageInfo<TbGoodsTag> pageInfo=new PageInfo<>(list);
        result.setRecordsFiltered((int)pageInfo.getEndRow());
        result.setRecordsTotal(Math.toIntExact(counts));
        result.setSuccess(true);
        result.setData(list);
        result.setDraw(draw);
        return result;
	}

    /**
     * 添加标签
     * @param tbGoodsTag 标签实体类
     * @return
     */
	@Override
	@Transactional
	public int addGoodsTag(TbGoodsTag tbGoodsTag)throws Exception {
		int result = tbGoodsTagDao.insert(tbGoodsTag);
        if(result!=1){
            throw new XmallException("新增标签失败");
        }
		return result;
	}

    /**
     * 编辑标签
     * @param tbItemCat
     * @return
     */
	@Override
	@Transactional
	public int updateGoodsTag(TbGoodsTag tbGoodsTag)throws Exception {
		int result = tbGoodsTagDao.updateById(tbGoodsTag);
		return result;
	}
	
    /**
     * 删除单个标签
     * @param id
     */
	@Override
	@Transactional
	public int deleteGoodsTag(Integer id)throws Exception {
		int result =  tbGoodsTagDao.delById(id);
        if(result!=1){
            throw new XmallException("删除标签失败");
        }
		return result;
	}

	

	/**
	 * 获取品牌记录总数量
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int countBrands() throws Exception {
		long result =  brandsDao.countBrands();
		return (int)result;
	}

	/**
	 *  获取标签记录总数量
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int countTag() throws Exception {
		long result =  tbGoodsTagDao.countTbGoodsTag();
		return (int)result;
	}



	@Override
	@Transactional
	public DataTablesResult updateTagList(List<Integer> tagIds, int goodsId) throws Exception {
		DataTablesResult result = new DataTablesResult();
		//获取数据库当前商品已有的标签
		List<TbGoodsTagBean> list = sProductDao.productTagList(goodsId);
		Map<String, TbGoodsTagBean> tagIdMap = new HashMap<>();
		Map<String, String> addTagIdMap = new HashMap<>();
		Map<String, String> DeleteTagIdMap = new HashMap<>();
		for(TbGoodsTagBean tbGoodsTag : list){
			tagIdMap.put(tbGoodsTag.getId().toString(), tbGoodsTag);
		}
		//通过for循环对比出商品新增的标签
		TbProductTag ProductTag = new TbProductTag();
		for(int tagId : tagIds){
			if(!tagIdMap.containsKey(String.valueOf(tagId))){
				addTagIdMap.put(String.valueOf(tagId),String.valueOf(tagId));
				 ProductTag = new TbProductTag();
				 ProductTag.setProductId(goodsId);
				 ProductTag.setTagId(tagId);
				sProductDao.insertProductTag(ProductTag);
			}else{
				addTagIdMap.put(String.valueOf(tagId),String.valueOf(tagId));
			}
		}
		//通过for循环对比出商品删除的标签
		for(String newTagId : tagIdMap.keySet()){
			if(!addTagIdMap.containsKey(newTagId)){
				DeleteTagIdMap.put(newTagId,newTagId);
				sProductDao.deleteProductTag(Integer.valueOf(newTagId));
			}
		}
        result.setData(null);
        result.setSuccess(true);
        return result;
	}
	
	@Override
	@Transactional
	public DataTablesResult judgePictureList(List<Integer> pictrueIds, int goodsId) throws Exception {
		DataTablesResult result = new DataTablesResult();
		//获取数据库当前商品已有的图片
		List<SResourcesEntity> list = sResourcesDao.queryByproductIdList(goodsId, ProductDetailBean.Img_Type);
		Map<String, SResourcesEntity> pictureIdMap = new HashMap<>();
		Map<String, String> addPictureIdMap = new HashMap<>();
		Map<String, String> DeletePictureIdMap = new HashMap<>();
		for(SResourcesEntity picture : list){
			pictureIdMap.put(String.valueOf(picture.getId()) , picture);
		}
		//通过for循环对比出商品新增的图片
		for(int pictureId : pictrueIds){
			if(!pictureIdMap.containsKey(String.valueOf(pictureId))){
				addPictureIdMap.put(String.valueOf(pictureId),String.valueOf(pictureId));
				ProductDetailBean productDetailBean = new ProductDetailBean();
				productDetailBean.setProductid(goodsId); 
				productDetailBean.setResourcesid(pictureId);
				productDetailBean.setType(ProductDetailBean.Img_Type);
				sProductDao.insertProductDetail(productDetailBean);
			}else{
				addPictureIdMap.put(String.valueOf(pictureId),String.valueOf(pictureId));
			}
		}
		//通过for循环对比出商品删除的图片
		for(String newTagId : pictureIdMap.keySet()){
			if(!addPictureIdMap.containsKey(newTagId)){
				//注意这里的对比map变为前端传回的数组数据
				DeletePictureIdMap.put(newTagId,newTagId);
				sProductDao.deleteProductDetail(Integer.valueOf(newTagId));
				sResourcesDao.deleteResources(Integer.valueOf(newTagId));
			}
		}
        result.setData(null);
        result.setSuccess(true);
        return result;
	}
	

	@Transactional
	public DataTablesResult judgeTopicList(List<Integer> pictrueIds, int topicId) throws Exception {
		DataTablesResult result = new DataTablesResult();
		//获取数据库当前商品已有的图片
		List<SResourcesEntity> list = sResourcesDao.queryByTopicIdAll(topicId, 1);
		Map<String, SResourcesEntity> pictureIdMap = new HashMap<>();
		Map<String, String> addPictureIdMap = new HashMap<>();
		Map<String, String> DeletePictureIdMap = new HashMap<>();
		for(SResourcesEntity picture : list){
			pictureIdMap.put(String.valueOf(picture.getId()) , picture);
		}
		//通过for循环对比出商品新增的图片
		for(int pictureId : pictrueIds){
			if(!pictureIdMap.containsKey(String.valueOf(pictureId))){
				addPictureIdMap.put(String.valueOf(pictureId),String.valueOf(pictureId));
				TbTopicImg  tbTopicImg = new TbTopicImg();
				tbTopicImg.setTopic_id(topicId);
				tbTopicImg.setResources_id(pictureId);
				tbTopicImg.setType(1);
				tbTopicDao.insertTopicImg(tbTopicImg);
			}else{
				addPictureIdMap.put(String.valueOf(pictureId),String.valueOf(pictureId));
			}
		}
		//通过for循环对比出商品删除的图片
		for(String newTagId : pictureIdMap.keySet()){
			//注意这里的对比map变为前端传回的数组数据
			if(!addPictureIdMap.containsKey(newTagId)){
				DeletePictureIdMap.put(newTagId,newTagId);
				tbTopicDao.delTopicImgById(topicId, Integer.valueOf(newTagId));
				sResourcesDao.deleteResources(Integer.valueOf(newTagId));
			}
		}
        result.setData(null);
        result.setSuccess(true);
        return result;
	}
	
	@Override
	@Transactional
	public DataTablesResult judgeDetailList(List<Integer> productDetail, int goodsId) throws Exception {
		DataTablesResult result = new DataTablesResult();
		//获取数据库当前商品已有的详情图片
		List<SResourcesEntity> list = sResourcesDao.queryByproductIdList(goodsId, ProductDetailBean.Detail_Type);
		Map<String, SResourcesEntity> pictureIdMap = new HashMap<>();
		Map<String, String> addPictureIdMap = new HashMap<>();
		Map<String, String> DeletePictureIdMap = new HashMap<>();
		for(SResourcesEntity picture : list){
			pictureIdMap.put(String.valueOf(picture.getId()) , picture);
		}
		//通过for循环对比出商品新增的详情图片
		for(int pictureId : productDetail){
			if(!pictureIdMap.containsKey(String.valueOf(pictureId))){
				addPictureIdMap.put(String.valueOf(pictureId),String.valueOf(pictureId));
				ProductDetailBean productDetailBean = new ProductDetailBean();
				productDetailBean.setProductid(goodsId); 
				productDetailBean.setResourcesid(pictureId);
				productDetailBean.setType(ProductDetailBean.Detail_Type);
				sProductDao.insertProductDetail(productDetailBean);
			}else{
				addPictureIdMap.put(String.valueOf(pictureId),String.valueOf(pictureId));
			}
		}
		//通过for循环对比出商品详情删除的详情
		for(String newTagId : pictureIdMap.keySet()){
			//注意这里的对比map变为前端传回的数组数据
			if(!addPictureIdMap.containsKey(newTagId)){
				DeletePictureIdMap.put(newTagId,newTagId);
				sProductDao.deleteProductDetail(Integer.valueOf(newTagId));
				sResourcesDao.deleteResources(Integer.valueOf(newTagId));
			}
		}
        result.setData(null);
        result.setSuccess(true);
        return result;
	}
	
    @Override
    @Transactional
    public int autoGoodsOn()throws Exception{
    	List<SProductBean> productList = sProductDao.autoProductList();
    	int i=0;
    	for(SProductBean sProductBean : productList){
    		
    		if(sProductBean.getGoodsontime().getTime() <= System.currentTimeMillis()){
    			sProductBean.setStatus(SProductBean.GOODSON_STATUS);
    			sProductBean.setGoodsontype(SProductBean.GOODSON_STATUS);
    			sProductDao.updateByIdNoJsonAll(sProductBean);
    			log.info("商品自动上架成功，商品id为："+sProductBean.getId());
    		}
    		i++;
    	}
    	log.info("商品自动上架服务执行完毕，修改的商品数为："+i);
        return 1;
    }

	@Override
	public DataTablesResult getFlashSaleList(int draw, int length)throws Exception{
		DataTablesResult result = new DataTablesResult();
		int counts = flashSaleDao.countFlashSale();
		PageHelper.startPage(draw,length);
		List<FlashSale> list = flashSaleDao.flashSaleList();
        PageInfo<FlashSale> pageInfo=new PageInfo<>(list);
        for(FlashSale flashSale : list){
        	flashSale.setPrice(flashSale.getPrice()/100);
        }
        result.setRecordsFiltered((int)pageInfo.getEndRow());
        result.setRecordsTotal(Math.toIntExact(counts));
        result.setSuccess(true);
        result.setData(list);
        result.setDraw(draw);
        return result;
	}

	@Override
	@Transactional
	public int updateFlashSale(FlashSale flashSale)throws Exception{
		flashSale.setPrice(flashSale.getPrice()*100);
		int productId = flashSale.getProduct_id();
		int shops_id = flashSale.getShops_id();
		SProductEntity product =  sProductDao.queryNewById2(productId,shops_id);
		int shopsId = flashSale.getShops_id();
		String shopsName =  shopsDao.getShopsName(String.valueOf(shopsId));
		flashSale.setProduct_name(product.getName());
		flashSale.setShops_name(shopsName);
		int result = flashSaleDao.updateById(flashSale);
        if(result!=1){
            throw new XmallException("修改抢购活动失败");
        }
		return result;
	}

	@Override
	@Transactional
	public int addFlashSale(FlashSale flashSale)throws Exception{
		int productId = flashSale.getProduct_id();
		int shops_id = flashSale.getShops_id();
		SProductEntity product =  sProductDao.queryNewById2(productId,shops_id);
		int shopsId = flashSale.getShops_id();
		String shopsName =  shopsDao.getShopsName(String.valueOf(shopsId));
		String attr = product.getAttribute();
		JSONObject jso =JSON.parseObject(new String(attr.getBytes("ISO8859-1"),"UTF-8"));
		flashSale.setAttribute(jso);
		flashSale.setProduct_name(product.getName());
		flashSale.setShops_name(shopsName);
		//默认下状态为1,由定时器来开启抢购活动
		flashSale.setStatus(1);
		flashSale.setPrice(flashSale.getPrice()*100);
		flashSale.setStill_need(flashSale.getCombine_num());
		int result = flashSaleDao.insert(flashSale);
        if(result!=1){
            throw new XmallException("新增抢购活动失败");
        }
		return result;
	}

	@Override
	@Transactional
	public int delFlashSale(int ids)throws Exception{
		int result =  flashSaleDao.delById(ids);
        if(result!=1){
            throw new XmallException("删除抢购活动失败");
        }
		return result;
	}

	@Override
	public DataTablesResult getFlashSaleTimeList(int draw, int length)throws Exception{
		DataTablesResult result = new DataTablesResult();
		int counts = flashSaleDao.countFlashSale();
		PageHelper.startPage(draw,length);
		List<FlashSaleTimeLiness> list = flashSaleTimeDao.flashSaleTimeLinessList();
        PageInfo<FlashSaleTimeLiness> pageInfo=new PageInfo<>(list);
        result.setRecordsFiltered((int)pageInfo.getEndRow());
        result.setRecordsTotal(Math.toIntExact(counts));
        result.setSuccess(true);
        result.setData(list);
        result.setDraw(draw);
        return result;
	}

	@Override
	@Transactional
	public int updateFlashSaleTime(FlashSaleTimeLiness flashSaleTime)throws Exception{
		int result = flashSaleTimeDao.updateById(flashSaleTime);
        if(result!=1){
            throw new XmallException("修改抢购时间段失败");
        }
		return result;
	}

	@Override
	@Transactional
	public int addFlashSaleTime(FlashSaleTimeLiness flashSaleTime)throws Exception{
		int result = flashSaleTimeDao.insert(flashSaleTime);
        if(result!=1){
            throw new XmallException("修改抢购时间段失败");
        }
		return result;
	}

	@Override
	@Transactional
	public int delFlashSaleTime(int ids) throws Exception{
		int result =  flashSaleTimeDao.delById(ids);
        if(result!=1){
            throw new XmallException("删除抢购时间段失败");
        }
		return result;
	}

	/**
	 * 根据ID获取抢购活动全部信息
	 * @param id  抢购活动信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject getFlashSaleInfo(int id) throws Exception{
		JSONObject json = new JSONObject();
		FlashSaleBean flashSaleBean = flashSaleDao.queryByIdString(id);
		FlashSale flashSale = new FlashSale();
		JSONObject jso =JSON.parseObject(new String(flashSaleBean.getAttribute().getBytes("ISO8859-1"),"UTF-8"));
		flashSale.setCreated_time(flashSaleBean.getCreated_time());
		flashSale.setIcon(flashSaleBean.getIcon());
		flashSale.setId(flashSaleBean.getId());
//		flashSale.setPrice(Integer.parseInt((flashSaleBean.getPrice())));
		flashSale.setProduct_id(flashSaleBean.getProduct_id());
		flashSale.setStatus(flashSaleBean.getStatus());
		flashSale.setStock(flashSaleBean.getStock());
		flashSale.setTimeliness_id(flashSaleBean.getTimeliness_id());
		flashSale.setTitle(flashSaleBean.getTitle());
		flashSale.setUpdated_time(flashSaleBean.getUpdated_time());
		flashSale.setAttribute(jso);
		flashSale.setCombine_num(flashSaleBean.getCombine_num());
		
		json.put("FlashSaleInfo", flashSale);
		json.put("success", true);
		return json;
	}
	
    
    
    /**
     * 获取抢购时间段总数
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
    public Result<Object> countFlashSaleTimeLiness() throws Exception{
    		int result = flashSaleTimeDao.countFlashSaleTimeLiness();
            return new ResultUtil<Object>().setData(result);
    }
    
    /**
     * 获取抢购活动记录总数
     * @return
     * @throws Exception
     */
	@Override
	@Transactional
    public Result<Object> countFlashSale() throws Exception{
    		int result = flashSaleDao.countFlashSale();
            return new ResultUtil<Object>().setData(result);
    }
	
	/**
	 * 该方法用于对话题的实体类数据进行转换，
	 * @param list
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public List<TbTopic> convertTopic(List<TbTopicBean>  list) throws UnsupportedEncodingException {
		TbTopic tbTopic = new TbTopic();
		List<TbTopic> list1 = new ArrayList<>();
		for(TbTopicBean tbTopicBean : list){
			tbTopic = new TbTopic();
			tbTopic.setAppraise_num(tbTopicBean.getAppraise_num());
			String jsonArrayStr = tbTopicBean.getArticle().toString();
			jsonArrayStr = new String(jsonArrayStr.getBytes("ISO-8859-1"),"utf-8");
			JSONArray jsonArray = JSON.parseArray(jsonArrayStr);
			tbTopic.setArticle(jsonArray);
			tbTopic.setCreate_time(tbTopicBean.getCreate_time());
			tbTopic.setId(tbTopicBean.getId());
			tbTopic.setIs_recommend(tbTopicBean.getIs_recommend());
			tbTopic.setPraise_num(tbTopicBean.getPraise_num());
			tbTopic.setPublisher_id(tbTopic.getPublisher_id());
			tbTopic.setPublisher_type(tbTopicBean.getPublisher_type());
			tbTopic.setSee_num(tbTopicBean.getSee_num());
			if(!"[]".equals(tbTopicBean.getThumbnail().toString())){
			String jsonArrayStr2 = tbTopicBean.getThumbnail();
			jsonArrayStr2 = jsonArrayStr2.replace(" ", "").substring(1, jsonArrayStr2.replace(" ", "").length()-1);
			String[] strArr = jsonArrayStr2.split(",");
			List<Integer> list2 = new ArrayList<>();
			for(String str :  strArr){
				list2.add(Integer.valueOf(str));
			}
			tbTopic.setThumbnail(list2);
			}
			tbTopic.setTopic_type(tbTopicBean.getTopic_type());
			tbTopic.setIs_appraise(tbTopicBean.getIs_appraise());
			tbTopic.setTitle(tbTopicBean.getTitle());
			tbTopic.setVideo(tbTopicBean.getVideo());
			list1.add(tbTopic);
		}
		return list1;
	}

	
	
	/**
	 * 获取商品分类列表
	 */
	@Override
	public Page<CategoryListResponse> categoryList(Page<CategoryListResponse> page,
			CategoryListRequest categoryListRequest) {
		PageHelper.startPage(categoryListRequest.getPage(),categoryListRequest.getListPage());
		List<CategoryListResponse> list = commonDao.categoryList(categoryListRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}

	
	/**
	 * 获取平台商品列表
	 */
	@Override
	public Page<ProductResponse> productList(Page<ProductResponse> page, ProductRequest productReq) {
		PageHelper.startPage(productReq.getPage(),productReq.getListPage());
		List<ProductResponse> list = commonDao.productList(productReq);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}

	
	/**
	 * 获取商品特色服务
	 */
	@Override
	public List<ShopsRightEntity> rights() {
		return sProductDao.getRights();
	}

	
	/**
	 * 拼单商品列表
	 */
	@Override
	public Page<CombineSaleResponse> combineSaleList(Page<CombineSaleResponse> page,
			CombineSaleRequest combineSaleRequest) {
		PageHelper.startPage(combineSaleRequest.getPage(),combineSaleRequest.getListPage());
		List<CombineSaleResponse> list = commonDao.combineSaleList(combineSaleRequest);
		List<Select> getShops = commonDao.getShops(combineSaleRequest); //店铺
		for (CombineSaleResponse com : list) {
			com.setShopsList(getShops);
			com.setProductList(commonDao.getShopsProduct(combineSaleRequest,com.getShopsId()));
		}
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}

	
	/**
	 * 拼单商品增改
	 * @throws Exception 
	 */
	@Override
	public void combineSaleSave(CombineSaleSaveRequest combineSaleSaveRequest) throws Exception {
		if(combineSaleSaveRequest.isNewRecord()){
			commonDao.combineSaleAdd(combineSaleSaveRequest);
		}else {
			if(commonDao.combineSaleUpdate(combineSaleSaveRequest) == 0) {
				throw new Exception("id不存在");
			}
		}
	}

	
	/**
	 * 拼单商品删除
	 * @throws Exception 
	 */
	@Override
	public void combineSaleDel(CombineSaleDelRequest combineSaleDelRequest) throws Exception {
		if(commonDao.combineSaleDel(combineSaleDelRequest) == 0) {
			throw new Exception("id不存在");
		}
	}
}
