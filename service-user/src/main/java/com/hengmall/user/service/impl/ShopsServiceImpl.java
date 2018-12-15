package com.hengmall.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hengmall.user.dao.ShopsDao;
import com.hengmall.user.dao.TigLibraryDao;
import com.hengmall.user.model.DataTablesResult;
import com.hengmall.user.model.SOrderEntity;
import com.hengmall.user.model.ShopsInfo;
import com.hengmall.user.model.ShopsLocation;
import com.hengmall.user.model.ShopsTypeBean;
import com.hengmall.user.model.ShopsValue;
import com.hengmall.user.model.TbShopsType;
import com.hengmall.user.model.TbShopsTypeAddReq;
import com.hengmall.user.model.TbShopsTypeDelReq;
import com.hengmall.user.model.Users;
import com.hengmall.user.model.manage.product.ProductReq;
import com.hengmall.user.model.manage.product.SProductBean;
import com.hengmall.user.model.persistence.Page;
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
import com.hengmall.user.model.shops.commend.ShopsCommendResponse;
import com.hengmall.user.model.shops.examine.CarouselShopsResponse;
import com.hengmall.user.model.shops.examine.ConfirmRequest;
import com.hengmall.user.model.shops.examine.ExShopsResponse;
import com.hengmall.user.model.shops.examine.ExamineListRequest;
import com.hengmall.user.model.shops.examine.ExamineRequest;
import com.hengmall.user.model.shops.examine.ExamineResponse;
import com.hengmall.user.model.shops.examine.ProductShopsResponse;
import com.hengmall.user.model.shops.examine.operation.CarouselShopsEntity;
import com.hengmall.user.model.shops.examine.operation.ProductShopsEntity;
import com.hengmall.user.model.shops.examine.operation.ShopsCommendEntity;
import com.hengmall.user.model.shops.examine.operation.ShopsEntity;
import com.hengmall.user.model.shops.shopEntity.ShopsResponse;
import com.hengmall.user.model.tigLibrary.TigLibraryRes;
import com.hengmall.user.service.ShopsService;
import com.hengmall.user.service.common.BaseService;
import com.hengmall.user.util.CommonUtils;
import com.hengmall.user.util.JedisConnectUtil;
import com.hengmall.user.util.config.Global;

@Service
public class ShopsServiceImpl extends BaseService implements ShopsService {

	 final static Logger log= LoggerFactory.getLogger(ShopsServiceImpl.class);
	
    @Autowired
    private ShopsDao shopsDao;
    @Autowired
    private TigLibraryDao tigLibraryDao;
    @Autowired
    private PlatformTransactionManager transactionManager;

	@Override
	public DataTablesResult getShopsOrderList(int draw, int length,int userId)throws Exception{
		DataTablesResult result = new DataTablesResult();
		PageHelper.startPage(draw,length);
		List<SOrderEntity>  list = shopsDao.queryByUserId(userId);
		result.setData(list);
		result.setSuccess(true);
		return result;
	}

	@Override
	public int getShopsOrderCountById(int userId)throws Exception{
		int result = shopsDao.countShopsOrder(userId);
		return result;
	}

	@Override
	public DataTablesResult getShopsMemberList(int draw, int length, int userId)throws Exception{
		DataTablesResult result = new DataTablesResult();
		PageHelper.startPage(draw,length);
		List<Users>  list = shopsDao.queryMemberByUserId(userId);
		result.setData(list);
		result.setSuccess(true);
		return result;
	}

	@Override
	public int getShopsMemberCountById(int userId)throws Exception{
		int result = shopsDao.countMemberOrder(userId);
		return result;
	}

	@Override
	public int getShopsValueCountById(int userId)throws Exception{
		int result = shopsDao.countValueByUserId(userId);
		return result;
	}

	@Override
	public DataTablesResult getShopsValueList(int draw, int length, int userId)throws Exception{
		DataTablesResult result = new DataTablesResult();
		PageHelper.startPage(draw,length);
		List<ShopsValue> list = shopsDao.queryValueByUserId(userId);
		result.setData(list);
		result.setSuccess(true);
		return result;
	}


	@Override
	public DataTablesResult getShopsProductList(int draw, int length, int userId)throws Exception{
		DataTablesResult result = new DataTablesResult();
		PageHelper.startPage(draw,length);
		List<SProductBean> list = shopsDao.queryProductByUserId(userId);
		result.setData(list);
		result.setSuccess(true);
		return result;
	}
	
	/**
	 * 标签库列表1
	 */
	@Override
	public List<TbShopsType> getShopsTypeList(int shops_id)throws Exception {
		//原始数据  tigLibraryList
		List<TbShopsType> tbShopsTypeList = null;
		
		if(JedisConnectUtil.existsKey("ShopsType"+shops_id)) {
			Map<String,String> map = JedisConnectUtil.hgetAll("ShopsType"+shops_id);
			tbShopsTypeList = new ArrayList<TbShopsType>();
			for(Map.Entry<String, String> entry : map.entrySet()) {
				TbShopsType t = new TbShopsType();
				JSONObject obj = JSON.parseObject(entry.getValue());
				t.setId(obj.getIntValue("id"));
				t.setName(obj.getString("name"));
				t.setParent_id(obj.getIntValue("parent_id"));
				t.setIcon(obj.getString("icon"));
				t.setShops_id(obj.getIntValue("shops_id"));
				t.setType_id(obj.getIntValue("type_id"));
				t.setType_ids(obj.getJSONArray("type_ids"));
				tbShopsTypeList.add(t);
			}
		}else {
			tbShopsTypeList = shopsDao.queryTypeByUserId(shops_id);
			
			log.info("进入新增方法");
			for (TbShopsType tbShopsType : tbShopsTypeList) {
				JSONArray arr = JSONArray.parseArray(tbShopsType.getType_idsArr());
				tbShopsType.setType_ids(arr);
				String json = JSON.toJSONString(tbShopsType);
				JedisConnectUtil.hsetnx("ShopsType"+shops_id, String.valueOf(tbShopsType.getId()) , json);
			}
		}
		
		//最终结果
		List<TbShopsType> res = new ArrayList<TbShopsType>();
		
		//所有根节点
		for (TbShopsType listDao : tbShopsTypeList) {
			if(listDao.getParent_id() == 0) {
				res.add(listDao);
			}
		}
		
		//为根节点设置下级节点
		for(TbShopsType listRes : res) {
			listRes.setChildren(getChild(String.valueOf(listRes.getType_id()),tbShopsTypeList));
		}
		
		return res;
	}

	
	/**
	 * 递归查找子节点
	 * @param id  当前节点id
	 * @param classifyList  要查找的列表
	 * @return List<TigLibraryRes>
	 */
	private List<TbShopsType> getChild(String id,List<TbShopsType> tbShopsTypeList)throws Exception{
		//子节点
		List<TbShopsType> childList = new ArrayList<TbShopsType>();
		for (TbShopsType ify : tbShopsTypeList) {
			if(ify.getParent_id() == Integer.valueOf(id) ) {
				childList.add(ify);
			}
		}
		//循环子节点
		for(TbShopsType ify : childList) {
			//递归
			ify.setChildren(getChild(String.valueOf(ify.getType_id()),tbShopsTypeList));
		}
		//退出
		if(childList.size() == 0) {
			return null;
		}
		return childList;
	}
	
	
	/**
	 * 递归查找子节点
	 * @param id  当前节点的type_id
	 * @param classifyList  要查找的列表
	 * @return 子节点id
	 */
	@SuppressWarnings("unused")
	private Set<String> getChildId(String id,List<TbShopsType> tbShopsTypeList)throws Exception{
		Set<String> childId = new HashSet<String>();
		for (TbShopsType ify : tbShopsTypeList) {
			if(ify.getParent_id() == Integer.valueOf(id) ) {
				childId.add(String.valueOf(ify.getId()));
				Set<String> getChildId = getChildId(String.valueOf(ify.getType_id()),tbShopsTypeList);
				childId.addAll(getChildId);
			}
		}
		return childId;
	}


	/**
	 * 店铺标签库添加
	 */
	@Override
	public int tbShopsTypeAdd(TbShopsTypeAddReq tbShopsTypeAddReq)throws Exception {
		if(tbShopsTypeAddReq.getPid() == null || tbShopsTypeAddReq.getPid().equals("")) {
			tbShopsTypeAddReq.setPid("0");
		}
		int shops_id = tbShopsTypeAddReq.getShops_id();
		int tigId = tbShopsTypeAddReq.getType_id();
		TigLibraryRes tigLibraryRes = tigLibraryDao.query(tigId);
		tbShopsTypeAddReq.setIcon(tigLibraryRes.getIcon());
		tbShopsTypeAddReq.setName(tigLibraryRes.getName());
		tbShopsTypeAddReq.setPid(tigLibraryRes.getParentId());
		String type_idsArr = JSONArray.toJSONString(tbShopsTypeAddReq.getType_ids());
		tbShopsTypeAddReq.setType_idsArr(type_idsArr);
		//清空标签库缓存
		JedisConnectUtil.hdel("ShopsType"+shops_id);
		if(tbShopsTypeAddReq.isNewRecord()) {
			shopsDao.tbShopsTypeAdd(tbShopsTypeAddReq);
			return tbShopsTypeAddReq.getIdT();
		}else {
			shopsDao.tbShopsTypeUpdate(tbShopsTypeAddReq);
			return 0;
		}
	}


	/**
	 * 标签库删除
	 * @throws Exception 
	 */
	@Override
	public void tbShopsTypeDel(TbShopsTypeDelReq tbShopsTypeDelReq, int shops_id) throws Exception {
		TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        
        try {
        	if(shopsDao.query(tbShopsTypeDelReq.getId()) == null) {
            	throw new Exception("该标签不存在");
            }
        	TbShopsType tbShopsType = shopsDao.queryTypeById(tbShopsTypeDelReq.getId());
        	Set<String> childIds = new HashSet<>();  //子id
        	//先加入删掉自己的ID,再加入删掉子节点的ID
    		childIds.add(String.valueOf(tbShopsTypeDelReq.getId()));
    		childIds.addAll(getChildId(String.valueOf(tbShopsType.getType_id()) ,shopsDao.queryTypeByUserId(shops_id)));
    		
    		//所包含被删除标签的商品
/*    		List<Product> products = tigLibraryDao.products(childIds.toString().replace(" ", "").replace("[", "").replace("]", ""));
    		for (Product product : products) {
    			String[] tag = CommonUtils.toStringArray(product.getTagids());  //修改过后的,可以直接把商品对应的tagids字段改成这个
    			for (int i = 0; i < tag.length; i++) {
    				String[] er = tag[i].split(",");
    				for(int j = 0;j < er.length; j++) {
    					for (String childId : childIds) {
    						if(er[j].equals(childId)) {
    							er = CommonUtils.deleteArray(j,er);
    						}
    					}
    				}
    				tag[i] = CommonUtils.toString(er);
    				if(tag[i].equals("")) {
    					tag = CommonUtils.deleteArray(i, tag);
    				}
    			}
    			//修改商品对应tagids字段
    			tigLibraryDao.updateProduct(product.getId(),JSON.toJSONString(tag));
    		}*/
    		//清空标签库缓存
    		JedisConnectUtil.hdel("ShopsType"+shops_id ,String.valueOf(tbShopsTypeDelReq.getId()));
    		shopsDao.tbShopsTypeDel(childIds);
    		transactionManager.commit(status);
        }catch (Exception e) {
        	transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public int getShopsProductCountById(int userId)throws Exception{
		int result = shopsDao.countProductByUserId(userId);
		return result;
	}

	@Override
	public DataTablesResult getProductList(int draw, int length, int userId) {
		DataTablesResult result = new DataTablesResult();
		PageHelper.startPage(draw,length);
		List<SProductBean> list = shopsDao.queryProductByUserIdForsale(userId);
		result.setData(list);
		result.setSuccess(true);
		return result;
	}

	@Override
	public List<ShopsTypeBean>  getProductTypeList(int userId) {
		List<ShopsTypeBean> list = shopsDao.queryProductTypeByUserId(userId);
		return list;
	}

	@Override
	public List<ProductReq> queryByOrderStatus(int userId, int orderStatus,List<Integer> typeIdList) {
        //排序标识；默认1：综合，2：销量，3：价格升序,4：价格降序,带有分类ID数组
        String order_desc = "name";
        List<ProductReq> list = new ArrayList<>();
        if(!"null".equals(String.valueOf(typeIdList))){
        	List<ProductReq> list2 = new ArrayList<>();
        	for(int typeId : typeIdList){
                if (orderStatus == 1) {
                    order_desc = "id";
                     list = shopsDao.queryProductByOrderDesc3(userId, order_desc,typeId);
                     list2.addAll(list);
                } else if (orderStatus == 2) {
                    order_desc = "salesvolume";
                     list =  shopsDao.queryProductByOrderDesc3(userId, order_desc,typeId);
                     list2.addAll(list);
                } else if (orderStatus == 3) {
                    order_desc = "price";
                     list =  shopsDao.queryProductByOrderDesc3(userId, order_desc,typeId);
                     list2.addAll(list);
                }else if (orderStatus == 4) {
                    order_desc = "price";
                     list =  shopsDao.queryProductByOrderDesc4(userId, order_desc,typeId);
                     list2.addAll(list);
                }
        	}
        	return list2;
        }else{
            if (orderStatus == 1) {
                order_desc = "id";
                 list = shopsDao.queryProductByOrderDesc(userId, order_desc);
            } else if (orderStatus == 2) {
                order_desc = "salesvolume";
                 list =  shopsDao.queryProductByOrderDesc(userId, order_desc);
            } else if (orderStatus == 3) {
                order_desc = "price";
                 list =  shopsDao.queryProductByOrderDesc(userId, order_desc);
            }else if (orderStatus == 4) {
                order_desc = "price";
                 list =  shopsDao.queryProductByOrderDesc2(userId, order_desc);
            }
            return list;
        }
	}

	//TODO 具体的粉丝数需要更多的一个查询
	@Override
	public ShopsInfo queryShops(int userId,int storeId) throws Exception {
		ShopsLocation shopsLocation = shopsDao.queryShopsByUserId(storeId);
		ShopsInfo shops = new ShopsInfo();
		if( CommonUtils.judge(String.valueOf(shopsLocation.getId()))){
			int fans = shopsDao.queryShopsFans(shopsLocation.getId());
			int a = shopsDao.queryIsShopsFans(shopsLocation.getId(),userId);
			if(a >= 1){
				shops.setIsAttention(1);
			}else{
				shops.setIsAttention(2);
			}
			shops.setDescribe(shopsLocation.getDescribe());
			shops.setFansNum(fans);
			shops.setNational(shopsLocation.getNational());
			shops.setShopsId(shopsLocation.getId());
			shops.setShopsName(shopsLocation.getShops_name());
			shops.setUrl(shopsLocation.getAvatar_url());
		}else{
			throw new Exception("请求数据不正确，请重新请求！");
		}
		return shops;
	}

	@Override
	public List<ProductReq> queryShopsTypeList(int storeId, List<Integer> typeList) {
		List<ProductReq> list = new ArrayList<>();
		List<ProductReq> list2 = new ArrayList<>();
		for(int typeId : typeList){
			list = new ArrayList<>();
			list = shopsDao.queryShopsTypeList(storeId,typeId);
			list2.addAll(list);
		}
		return list2;
	}

	
	/**
	 * 获取店铺列表
	 * @param shopsRequest   店铺 entity  请求需要的参数
	 */
	@Override
	public Page<ShopsResponse> shopList(Page<ShopsResponse> page,ShopsRequest shopsRequest) {
		//数据过滤
		shopsRequest.getSqlMap().put("sql", dataScopeFilter(shopsRequest.getToken(),"u"));

		if(shopsRequest.getIsPage()) {
			PageHelper.startPage(shopsRequest.getPage(),shopsRequest.getListPage());
			List<ShopsResponse> list = shopsDao.shopList(shopsRequest);
			page.setData(list);
			page.setCount(new PageInfo<>(list).getTotal());
			return page;
		}else {
			List<ShopsResponse> list = shopsDao.shopList(shopsRequest);
			page.setData(list);
			page.setCount(0);
			return page;
		}
		
	}

	
	/**
	 * 获取店铺粉丝列表
	 * @param shopsRequest  店铺 entity  请求需要的参数
	 */
	@Override
	public Page<ShopsAttentionResponse> shopAttentionList(Page<ShopsAttentionResponse> page,ShopsAttentionRequest shopsUsersRequest) {
		PageHelper.startPage(shopsUsersRequest.getPage(),shopsUsersRequest.getListPage());
		List<ShopsAttentionResponse> list = shopsDao.shopAttentionList(shopsUsersRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}
	
	
	/**
	 * 新增店铺
	 */
	@Override
	public void newShop(AddShopsRequest addShopsRequest) {
		shopsDao.newShop(addShopsRequest);
	}

	/**
	 * 修改店铺
	 */
	@Override
	public int updateShop(UpdateShopsRequest updateShopsRequest) {
		return shopsDao.updateShop(updateShopsRequest);
	}

	/**
	 * 店铺会员列表
	 */
	@Override
	public Page<ShopsUserResponse> shopUserList(ShopsUserRequest shopsUserRequest) {
		//设置分页数据
		PageHelper.startPage(shopsUserRequest.getPage(),shopsUserRequest.getListPage());
		List<ShopsUserResponse> list = shopsDao.shopUserList(shopsUserRequest);
		//获得总条数
		PageInfo<ShopsUserResponse> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		
		Page<ShopsUserResponse> rl = new Page<ShopsUserResponse>();
		rl.setData(list);
		rl.setCount(total);
		return rl;
	}

	
	
	/**
	 * 店铺板块列表
	 */
	@Override
	public Page<ShopsPlateResponse> shopPlateList(Page<ShopsPlateResponse> page,ShopsPlateRequest shopsPlateRequest) {
		//数据过滤
		shopsPlateRequest.getSqlMap().put("sql", dataScopeFilter(shopsPlateRequest.getToken(),"u"));
				
		PageHelper.startPage(shopsPlateRequest.getPage(),shopsPlateRequest.getListPage());
		List<ShopsPlateResponse> list = shopsDao.shopPlateList(shopsPlateRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}

	
	/**
	 * 店铺板块商品列表
	 */
	@Override
	public Page<ShopsPlateproductResponse> shopPlateproductList(
			ShopsPlateproductRequest shopsPlateproductRequest) {
		//设置分页数据
		PageHelper.startPage(shopsPlateproductRequest.getPage(),shopsPlateproductRequest.getListPage());
		List<ShopsPlateproductResponse> list = shopsDao.shopPlateproductList(shopsPlateproductRequest);
		
		Page<ShopsPlateproductResponse> rl = new Page<ShopsPlateproductResponse>();
		rl.setData(list);
		rl.setCount(new PageInfo<>(list).getTotal());
		return rl;
	}

	
	/**
	 * 移除店铺板块商品
	 */
	@Override
	public int shopPlateproductDel(ShopsPlateproductDelRequest shopsPlateproductDelRequest) {
		return shopsDao.shopPlateproductDel(shopsPlateproductDelRequest);
	}

	/**
	 * 添加店铺板块商品
	 */
	@Override
	public int addPrpducts(ShopsPlateproductAddRequest shopsPlateproductAddRequest) {
		return shopsDao.addPrpducts(shopsPlateproductAddRequest);
	}

	
	/**
	 * 获取商品列表
	 */
	@Override
	public Page<ShopsProductListResponse> shopsProductList(
			ShopsProductListRequest shopsProductListRequest) {
		//设置分页数据
		PageHelper.startPage(shopsProductListRequest.getPage(),shopsProductListRequest.getListPage());
		List<ShopsProductListResponse> list = shopsDao.shopsProductList(shopsProductListRequest);
		//获得总条数
		PageInfo<ShopsProductListResponse> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
				
		Page<ShopsProductListResponse> rl = new Page<ShopsProductListResponse>();
		rl.setData(list);
		rl.setCount(total);
		return rl;
	}

	
	/**
	 * 店铺商品列表
	 */
	@Override
	public Page<ShopsProductResponse> shopsProduct(ShopsProductRequest shopsProductRequest) {
		//设置分页数据
		PageHelper.startPage(shopsProductRequest.getPage(),shopsProductRequest.getListPage());
		List<ShopsProductResponse> list = shopsDao.shopsProduct(shopsProductRequest);
		//获得总条数
		PageInfo<ShopsProductResponse> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
						
		Page<ShopsProductResponse> rl = new Page<ShopsProductResponse>();
		rl.setData(list);
		rl.setCount(total);
		return rl;
	}

	
	
	/**
	 * 店铺商品列表
	 */
	@Override
	public int shopDel(ShopsDelRequest shopsDelRequest) {
		return shopsDao.shopDel(shopsDelRequest);
	}

	
	/**
	 * 添加店铺商品
	 */
	@Override
	public int shopAdd(ShopsAddRequest shopsAddRequest) {
		shopsAddRequest.setCreatedTime(new Date());
		int[] productIds = shopsAddRequest.getProductIds();
		String shops_id = shopsAddRequest.getShopsId();
		String recommend = shopsAddRequest.getRecommend();
		for(int i=0;i<=productIds.length-1;i++){
			int product_id = productIds[i];
			int j = shopsDao.countShopProduct( Integer.valueOf(shops_id), product_id);
			if(j == 0){
				shopsDao.shopAdd(Integer.valueOf(shops_id), product_id, Integer.valueOf(recommend));
			}
		}
		return 0;
	}

	
	/**
	 * 店铺订单列表
	 */
	@Override
	public Page<ShopsOrderResponse> shopsOrder(Page<ShopsOrderResponse> page,ShopsOrderRequest shopsOrderRequest) {
		//数据过滤
		shopsOrderRequest.getSqlMap().put("sql", dataScopeFilter(shopsOrderRequest.getToken(),"u"));
						
		PageHelper.startPage(shopsOrderRequest.getPage(),shopsOrderRequest.getListPage());
		List<ShopsOrderResponse> list = shopsDao.shopsOrder(shopsOrderRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}

	
	/**
	 * 店铺商品评论列表
	 */
	public Page<AppraiseListResponse> appraiseList(Page<AppraiseListResponse> page,AppraiseListRequest appraiseListRequest){
		//数据过滤
		appraiseListRequest.getSqlMap().put("sql", dataScopeFilter(appraiseListRequest.getToken(),"u"));
						
		PageHelper.startPage(appraiseListRequest.getPage(),appraiseListRequest.getListPage());
		List<AppraiseListResponse> list = shopsDao.appraiseList(appraiseListRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}
	
	
	/**
	 * 店铺商品评论删除
	 */
	public int appraiseDel(AppraiseDelRequest appraiseDelRequest) {
		return shopsDao.appraiseDel(appraiseDelRequest);
	}

	
	/**
	 * 店铺轮播图列表
	 */
	@Override
	public Page<CarouselShopsListResponse> carouselShopsList(Page<CarouselShopsListResponse> page,
			CarouselShopsListRequest carouselShopsListRequest) {
		//数据过滤
		carouselShopsListRequest.getSqlMap().put("sql", dataScopeFilter(carouselShopsListRequest.getToken(),"u"));
								
		PageHelper.startPage(carouselShopsListRequest.getPage(),carouselShopsListRequest.getListPage());
		List<CarouselShopsListResponse> list = shopsDao.carouselShopsList(carouselShopsListRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}

	
	
	/**
	 * 店铺轮播图增改
	 */
	@Override
	public int carouselShopsSave(CarouselShopsSaveRequest carouselShopsSaveRequest) {
		if(carouselShopsSaveRequest.isNewRecord()) {
			return shopsDao.carouselShopsAdd(carouselShopsSaveRequest);
		}else {
			return shopsDao.carouselShopsUpdate(carouselShopsSaveRequest);
		}
	}

	/**
	 * 店铺轮播图删除
	 */
	@Override
	public int carouselShopsDel(CarouselShopsDelRequest carouselShopsDelRequest) {
		return shopsDao.carouselShopsDel(carouselShopsDelRequest);
	}

	
	/**
	 * 店铺订单列表
	 */
	@Override
	public Page<TbShopsOrderResponse> shopsOrderList(Page<TbShopsOrderResponse> page, TbShopsOrderRequest tbShopsOrderRequest) {
		//数据过滤
		tbShopsOrderRequest.getSqlMap().put("sql", dataScopeFilter(tbShopsOrderRequest.getToken(),"tu"));
										
		PageHelper.startPage(tbShopsOrderRequest.getPage(),tbShopsOrderRequest.getListPage());
		List<TbShopsOrderResponse> list = shopsDao.shopsOrderList(tbShopsOrderRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}

	
	/**
	 * 店铺板块增改
	 */
	@Override
	public int shopPlateSave(ShopsPlateSaveRequest shopsPlateSaveRequest) {
		if(shopsPlateSaveRequest.isNewRecord()) {
			return shopsDao.shopPlateAdd(shopsPlateSaveRequest);
		}else {
			return shopsDao.shopPlateUpdate(shopsPlateSaveRequest);
		}
	}

	
	/**
	 * 店铺板块删除
	 */
	@Override
	public int shopsPlateSaveDel(ShopsPlateDelRequest shopsPlateDelRequest) {
		return shopsDao.shopsPlateSaveDel(shopsPlateDelRequest);
	}

	
	
	/**
	 * 店铺提交申请
	 * @throws Exception 
	 */
	@Override
	public int examineSub(ExamineRequest examineRequest) throws Exception {
		examineRequest.setUserId(CommonUtils.getUserAttribute(examineRequest.getToken(),"id"));
		//开店审核
		if(examineRequest.getType() == 1) {
			//设置店铺地址经纬度
			Map<String,String> lngOrLat = CommonUtils.getLngOrLat(examineRequest.getExShopsRequest().getAddress());
			examineRequest.getExShopsRequest().setLng(lngOrLat.get("lng"));
			examineRequest.getExShopsRequest().setLat(lngOrLat.get("lat"));
			
			//设置店主id
			examineRequest.getExShopsRequest().setUserId(CommonUtils.getUserAttribute(examineRequest.getToken(),"userId"));
			
			examineRequest.setData(JSON.toJSONString(examineRequest.getExShopsRequest()));
			return shopsDao.examineSub(examineRequest);
		}
		//店铺上传商品审核
		else if(examineRequest.getType() == 2) {
			//店铺名
			examineRequest.getProductShopsRequest().setShopsName(shopsDao.getShopsName(examineRequest.getProductShopsRequest().getShopsId()));
			examineRequest.setData(JSON.toJSONString(examineRequest.getProductShopsRequest()));
			return shopsDao.examineSub(examineRequest);
		}
		//店铺首页产品图组合
		else if(examineRequest.getType() == 3) {
			//店铺名
			examineRequest.getShopsCommendRequest().setShopsName(shopsDao.getShopsName(examineRequest.getShopsCommendRequest().getShopsId()));
			examineRequest.setData(JSON.toJSONString(examineRequest.getShopsCommendRequest()));
			return shopsDao.examineSub(examineRequest);
		}
		//店铺首页轮播图表单
		else if(examineRequest.getType() == 4) {
			//店铺名
			examineRequest.getCarouselShopsRequest().setShopsName(shopsDao.getShopsName(examineRequest.getCarouselShopsRequest().getShopsId()));
			//商品名
			examineRequest.getCarouselShopsRequest().setProductName(shopsDao.getProductName(examineRequest.getCarouselShopsRequest().getProductId()));
			examineRequest.setData(JSON.toJSONString(examineRequest.getCarouselShopsRequest()));
			return shopsDao.examineSub(examineRequest);
		}
		//店铺订单对外发布权限
		else if(examineRequest.getType() == 5) {
			examineRequest.setData("{}");
			return shopsDao.release(examineRequest);
		}
		return 0;
	}

	
	/**
	 * 店铺审核列表
	 */
	@Override
	public Page<ExamineResponse> examineList(Page<ExamineResponse> page, ExamineListRequest examineListRequest) {
		//数据过滤
		examineListRequest.getSqlMap().put("sql", dataScopeFilter(examineListRequest.getToken(),"tu"));
												
		PageHelper.startPage(examineListRequest.getPage(),examineListRequest.getListPage());
		List<ExamineResponse> list = shopsDao.examineList(examineListRequest);
		//把data数据转化成对象
		for (ExamineResponse examineRes : list) {
			//开店审核
			JSONObject jsonObj = JSON.parseObject(examineRes.getData());
			if(examineRes.getType() == 1) {
				ExShopsResponse sr = new ExShopsResponse();
				sr.setAddress(jsonObj.getString("address"));
				sr.setDescribe(jsonObj.getString("describe"));
				sr.setLicenses(CommonUtils.toStringArray(jsonObj.getString("licenses")));
				sr.setNational(jsonObj.getString("national"));
				sr.setAvatarUrl(jsonObj.getString("avatarUrl"));
				sr.setShopsName(jsonObj.getString("shopsName"));
				sr.setUserPhone(jsonObj.getString("userPhone"));
				sr.setUserPhotos(CommonUtils.toStringArray(jsonObj.getString("userPhotos")));
				examineRes.setExShopsResponse(sr);
			}
			//店铺选择商品详情表单
			else if(examineRes.getType() == 2) {
				ProductShopsResponse ps = new ProductShopsResponse();
				ps.setShopsName(jsonObj.getString("shopsName"));
				ps.setProducts(shopsDao.productList(CommonUtils.toStringArray(jsonObj.getString("productIds"))));
				examineRes.setProductShopsResponse(ps);
			}
			//店铺首页产品图组合详情表单
			else if(examineRes.getType() == 3) {
				ShopsCommendResponse sc = new ShopsCommendResponse();
				sc.setGroupName(jsonObj.getString("groupName"));
				sc.setShopsName(jsonObj.getString("shopsName"));
				sc.setGroupPrice(jsonObj.getDouble("groupPrice"));
				sc.setProducts(shopsDao.productList(CommonUtils.toStringArray(jsonObj.getString("productIds"))));
				examineRes.setShopsCommendResponse(sc);
			}
			//店铺首页轮播图详情表单
			else if(examineRes.getType() == 4) {
				CarouselShopsResponse ca = new CarouselShopsResponse();
				ca.setPath(jsonObj.getString("path"));
				ca.setShopsName(jsonObj.getString("shopsName"));
				ca.setProductName(jsonObj.getString("productName"));
				examineRes.setCarouselShopsResponse(ca);
			}
			//店铺订单对外发布权限审核
			else if(examineRes.getType() == 5) {
				//这个审核没有请求数据
			}
		}
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}

	
	/**
	 * 店铺审核操作
	 * @throws Exception 
	 */
	@Override
	public void isAgree(ConfirmRequest confirmRequest) throws Exception {
		TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        
        try {
        	ExamineResponse er = shopsDao.examineData(confirmRequest.getId());
            if(er == null) {
            	throw new Exception("错误的id");
            }
        	if(er.getState() != 0) {
    			throw new Exception("已处理过的审核不能再次处理");
    		}
        	//通过审核
        	if(confirmRequest.getType() == 1) {
        		JSONObject jsonObj = JSON.parseObject(er.getData());
        		//开店申请
        		if(er.getType() == 1) {
        			ShopsEntity se = new ShopsEntity();
        			se.setUserId(jsonObj.getString("userId"));
        			se.setAddress(jsonObj.getString("address"));
        			se.setLat(jsonObj.getString("lat"));
        			se.setLng(jsonObj.getString("lng"));
        			se.setShopsName(jsonObj.getString("shopsName"));
        			se.setAvatarUrl(jsonObj.getString("avatarUrl"));
        			se.setNational(jsonObj.getString("national"));
        			se.setDescribe(jsonObj.getString("describe"));
        			se.setLicenses(jsonObj.getString("licenses"));
        			se.setUserPhotos(jsonObj.getString("userPhotos"));
        			se.setUserPhone(jsonObj.getString("userPhone"));
        			shopsDao.addShops(se);//添加店铺
        		}
        		//店铺上传商品审核
        		else if(er.getType() == 2) {
        			ProductShopsEntity ps = new ProductShopsEntity();
        			ps.setShopsId(jsonObj.getString("shopsId"));
        			ps.setProductIds(CommonUtils.toStringArray(jsonObj.getString("productIds")));
        			ps.setRecommend(1);  //默认都设为推荐
        			System.out.println(ps.getUpdateDate());
        			shopsDao.addProductShops(ps);  //添加店铺商品
        		}
        		//店铺首页产品图组合审核
        		else if(er.getType() == 3) {
        			ShopsCommendEntity sc = new ShopsCommendEntity();
        			sc.setShopsId(jsonObj.getString("shopsId"));
        			sc.setStatus(1);  //默认启用
        			sc.setGroupName(jsonObj.getString("groupName"));
        			sc.setGroupPrice(jsonObj.getInteger("groupPrice"));
        			sc.setProductIds(jsonObj.getString("productIds"));
        			shopsDao.addShopsCommend(sc);  //添加店铺首页产品图组合
        		}
        		//店铺首页轮播图审核
        		else if(er.getType() == 4) {
        			CarouselShopsEntity cs = new CarouselShopsEntity();
        			cs.setProductId(jsonObj.getString("productId"));
        			cs.setPath(jsonObj.getString("path"));
        			cs.setShopsId(jsonObj.getString("shopsId"));
        			shopsDao.addCarouselShops(cs);   //添加店铺首页轮播图
        		}
        		//店铺订单对外发布权限审核
        		else if(er.getType() == 5) {
        			shopsDao.setRelease(er.getUserId());
        		}
        		else {
        			throw new Exception("申请类型不存在");
        		}
        		//更改状态
        		shopsDao.updateState(confirmRequest);
        		
        	//拒绝
        	}else if(confirmRequest.getType() == 2){
        		//更改状态
        		shopsDao.updateState(confirmRequest);
        	}else {
        		throw new Exception("错误的请求");
        	}
    		transactionManager.commit(status);
		} catch (Exception e) {
			transactionManager.rollback(status);
			e.printStackTrace();
			if(e.getMessage() != null || !e.getMessage().equals("")) {
				throw new Exception(e.getMessage());
			}else {
				throw new Exception("店铺审核操作时遇到异常");				
			}
		}
	}

	
	/**
	 * 店铺订单操作
	 * @throws Exception 
	 */
	@Override
	public int shopsOrderSave(ShopsOrderSaveRequest shopsOrderSaveRequest) throws Exception {
		String publishStatus = shopsDao.getOrder(shopsOrderSaveRequest);
		if(publishStatus==null || publishStatus.equals("")) {
			throw new Exception("店铺id不存在");
		}
		JSONObject obj = JSON.parseObject(JedisConnectUtil.hget(Global.getConfig("login.userDb"), shopsOrderSaveRequest.getToken()));
		String id = obj.getString("id");
		if(obj.getLong("roleId") != 1) {
			if(shopsDao.isReleaseOrder(id) == 1) {
				throw new Exception("您暂没有发布订单权限，请联系管理员申请");
			}
		}
		return shopsDao.updateOrder(shopsOrderSaveRequest);
	}

	
	/**
	 * 店铺商品评论增改
	 * @throws Exception 
	 */
	@Override
	public void appraiseSave(AppraiseSubRequest appraiseSubRequest) throws Exception {
		if(appraiseSubRequest.isNewRecord()) {
			//评分
			appraiseSubRequest.setMatching(5);
			appraiseSubRequest.setDeliveryspeed(5);
			appraiseSubRequest.setScore(5);
			appraiseSubRequest.setServicequality(5);
			appraiseSubRequest.setServiceattitude(5);
			//商品sku
			String attribute = shopsDao.sku(appraiseSubRequest.getTbId());
			JSONArray details = JSON.parseObject(attribute).getJSONArray("details");
			String attr = details.getString(0);
			appraiseSubRequest.setAttr(attr);
			//头像名称
			appraiseSubRequest.setUrl(CommonUtils.getImg());
			appraiseSubRequest.setUserName(CommonUtils.getName());
			shopsDao.appraiseAdd(appraiseSubRequest);
		}else {
			shopsDao.appraiseUpdate(appraiseSubRequest);
		}
		
	}

	@Override
	public Page<ShopsProductListResponse> searchShopsProduct(SearchShopsProductRequest searchShopsProductRequest) {
		//设置分页数据
		PageHelper.startPage(searchShopsProductRequest.getPage(),searchShopsProductRequest.getListPage());
		List<ShopsProductListResponse> list = shopsDao.searchShopsProduct(searchShopsProductRequest);
		//获得总条数
		PageInfo<ShopsProductListResponse> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
				
		Page<ShopsProductListResponse> rl = new Page<ShopsProductListResponse>();
		rl.setData(list);
		rl.setCount(total);
		return rl;
	}

}
