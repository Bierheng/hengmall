package com.hengmall.goods.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.hengmall.goods.dao.ShopsDao;
import com.hengmall.goods.model.DataTablesResult;
import com.hengmall.goods.model.ProductReq;
import com.hengmall.goods.model.ShopCarouselEntity;
import com.hengmall.goods.model.ShopsInfo;
import com.hengmall.goods.model.ShopsLocation;
import com.hengmall.goods.model.ShopsPlate;
import com.hengmall.goods.model.ShopsTypeBean;
import com.hengmall.goods.service.ShopsService;
import com.hengmall.goods.util.CommonUtils;

@Service
public class ShopsServiceImpl implements ShopsService {

	 final static Logger log= LoggerFactory.getLogger(ShopsServiceImpl.class);
	
    @Autowired
    private ShopsDao shopsDao;

	@Override
	public DataTablesResult getProductList(int draw, int length, int userId) {
		DataTablesResult result = new DataTablesResult();
		PageHelper.startPage(draw,length);
		List<ProductReq> list = shopsDao.queryProductByUserIdForsale(userId);
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
	public List<ProductReq> queryByOrderStatus(int storeId, int orderStatus,List<Integer> typeIdList) {
        //排序标识；默认1：综合，2：销量，3：价格升序,4：价格降序,带有分类ID数组
        String order_desc = "name";
        List<ProductReq> list = new ArrayList<>();
        if(!"null".equals(String.valueOf(typeIdList))){
        	List<ProductReq> list2 = new ArrayList<>();
        	for(int typeId : typeIdList){
                if (orderStatus == 1) {
                    order_desc = "id";
                     list = shopsDao.queryProductByOrderDesc3(storeId, order_desc,typeId);
                     list2.addAll(list);
                } else if (orderStatus == 2) {
                    order_desc = "salesvolume";
                     list =  shopsDao.queryProductByOrderDesc3(storeId, order_desc,typeId);
                     list2.addAll(list);
                } else if (orderStatus == 3) {
                    order_desc = "price";
                     list =  shopsDao.queryProductByOrderDesc3(storeId, order_desc,typeId);
                     list2.addAll(list);
                }else if (orderStatus == 4) {
                    order_desc = "price";
                     list =  shopsDao.queryProductByOrderDesc4(storeId, order_desc,typeId);
                     list2.addAll(list);
                }
        	}
        	return list2;
        }else{
            if (orderStatus == 1) {
                order_desc = "id";
                 list = shopsDao.queryProductByOrderDesc(storeId, order_desc);
            } else if (orderStatus == 2) {
                order_desc = "salesvolume";
                 list =  shopsDao.queryProductByOrderDesc(storeId, order_desc);
            } else if (orderStatus == 3) {
                order_desc = "price";
                 list =  shopsDao.queryProductByOrderDesc(storeId, order_desc);
            }else if (orderStatus == 4) {
                order_desc = "price";
                 list =  shopsDao.queryProductByOrderDesc2(storeId, order_desc);
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

	@Override
	@Transactional
	public int attention(int storeId, int userId) throws Exception {
		int j = shopsDao.searchShops(userId, storeId);
		int i = 0;
		if(j != 1){
		   i =	shopsDao.insertShopsAttention(userId, storeId);
		   ShopsInfo shopsInfo = shopsDao.queryById(storeId);
		   if(!StringUtil.isEmpty(shopsInfo.toString())){
			   shopsInfo.setFansNum(shopsInfo.getFansNum() +1 );
			   shopsInfo.setIsAttention(1);
			   shopsDao.updateById(shopsInfo);
		   }else{
			  throw new Exception("不存在对应店铺");
		   }
		}else {
		   i =	shopsDao.delShopsAttention(userId, storeId);
		   ShopsInfo shopsInfo = shopsDao.queryById(storeId);
		   if(!StringUtil.isEmpty(shopsInfo.toString())){
			   shopsInfo.setFansNum(shopsInfo.getFansNum() -1 );
			   shopsInfo.setIsAttention(2);
			   shopsDao.updateById(shopsInfo);
		   }else{
			  throw new Exception("不存在对应店铺");
		   }
		}
		if(i == 1){
			return 0;
		}else{
			return 1;
		}
	}

	@Override
	public Boolean isAttention(int storeId, int userId) {
		int j = shopsDao.searchShops(userId, storeId);
		if(j != 1){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 
	 * @param shopsId 店铺ID
	 * @return
	 */
	@Override
	public JSONObject getTopProductList(int shopsId) {
		JSONObject obj = new JSONObject();
		List<ShopsPlate> list = shopsDao.queryShopsPlateList(shopsId);
		for(ShopsPlate shopsPlate : list){
			int plateId = shopsPlate.getId();
			List<ProductReq> list2 = shopsDao.queryShopsPlateProductList(plateId,shopsId);
			shopsPlate.setPlateProductList(list2);
		}
		obj.put("shopPlateList", list);
		return obj;
	}

	@Override
	public List<ShopCarouselEntity> getShopsCarousel(int storeId) {
		List<ShopCarouselEntity> list = shopsDao.getShopsCarousel(storeId);
		return list;
	}

}
