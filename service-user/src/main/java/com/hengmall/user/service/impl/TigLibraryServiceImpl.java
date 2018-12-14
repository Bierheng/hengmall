package com.server.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.server.dao.TigLibraryDao;
import com.server.entity.tigLibrary.Product;
import com.server.entity.tigLibrary.TigLibraryAddReq;
import com.server.entity.tigLibrary.TigLibraryDelReq;
import com.server.entity.tigLibrary.TigLibraryRes;
import com.server.service.TigLibraryService;
import com.server.utils.CommonUtils;
import com.server.utils.JedisConnectUtil;

/**
 * 标签库管理 ServiceImpl
 * @author Administrator
 *
 */
@Service
public class TigLibraryServiceImpl implements TigLibraryService {

	@Autowired
	private TigLibraryDao tigLibraryDao;

	@Autowired
    private PlatformTransactionManager transactionManager;
	
	/**
	 * 标签库列表1
	 */
	@Override
	public List<TigLibraryRes> tigLibraryList() {
		//原始数据  tigLibraryList
		List<TigLibraryRes> tigLibraryList = null;
		if(JedisConnectUtil.existsKey("tig_library")) {
			Map<String,String> map = JedisConnectUtil.hgetAll("tig_library");
			tigLibraryList = new ArrayList<TigLibraryRes>();
			for(Map.Entry<String, String> entry : map.entrySet()) {
				TigLibraryRes t = new TigLibraryRes();
				JSONObject obj = JSON.parseObject(entry.getValue());
				t.setId(obj.getString("id"));
				t.setName(obj.getString("name"));
				t.setParentId(obj.getString("parentId"));
				t.setIcon(obj.getString("icon"));
				tigLibraryList.add(t);
			}
		}else {
			tigLibraryList = tigLibraryDao.tigLibraryList();
			for (TigLibraryRes tigLibraryRes : tigLibraryList) {
				String json = JSON.toJSONString(tigLibraryRes);
				JedisConnectUtil.hsetnx("tig_library", tigLibraryRes.getId(), json);
			}
		}
		
		
		//最终结果
		List<TigLibraryRes> res = new ArrayList<TigLibraryRes>();
		//所有根节点
		for (TigLibraryRes listDao : tigLibraryList) {
			if(listDao.getParentId().equals("0")) {
				res.add(listDao);
			}
		}
		//为根节点设置下级节点
		for(TigLibraryRes listRes : res) {
			listRes.setChildren(getChild(listRes.getId(),tigLibraryList));
		}
		return res;
	}

	
	/**
	 * 递归查找子节点
	 * @param id  当前节点id
	 * @param classifyList  要查找的列表
	 * @return List<TigLibraryRes>
	 */
	private List<TigLibraryRes> getChild(String id,List<TigLibraryRes> tigLibraryList){
		//子节点
		List<TigLibraryRes> childList = new ArrayList<TigLibraryRes>();
		for (TigLibraryRes ify : tigLibraryList) {
			if(ify.getParentId().equals(id)) {
				childList.add(ify);
			}
		}
		//循环子节点
		for(TigLibraryRes ify : childList) {
			//递归
			ify.setChildren(getChild(ify.getId(),tigLibraryList));
		}
		//退出
		if(childList.size() == 0) {
			return null;
		}
		return childList;
	}
	
	
	/**
	 * 递归查找子节点
	 * @param id  当前节点id
	 * @param classifyList  要查找的列表
	 * @return 子节点id
	 */
	@SuppressWarnings("unused")
	private Set<String> getChildId(String id,List<TigLibraryRes> tigLibraryList){
		Set<String> childId = new HashSet<String>();
		for (TigLibraryRes ify : tigLibraryList) {
			if(ify.getParentId().equals(id)) {
				childId.add(ify.getId());
				Set<String> getChildId = getChildId(ify.getId(),tigLibraryList);
				childId.addAll(getChildId);
			}
		}
		return childId;
	}


	/**
	 * 标签库添加
	 */
	@Override
	public int tigLibraryAdd(TigLibraryAddReq tigLibraryAddReq) {
		if(tigLibraryAddReq.getPid() == null || tigLibraryAddReq.getPid().equals("")) {
			tigLibraryAddReq.setPid("0");
		}
		//清空标签库缓存
		JedisConnectUtil.hdel("tig_library");
		if(tigLibraryAddReq.isNewRecord()) {
			tigLibraryDao.tigLibraryAdd(tigLibraryAddReq);
			return tigLibraryAddReq.getIdT();
		}else {
			tigLibraryDao.tigLibraryUpdate(tigLibraryAddReq);
			return 0;
		}
	}


	/**
	 * 标签库删除
	 * @throws Exception 
	 */
	@Override
	public void tigLibraryDel(TigLibraryDelReq tigLibraryDelReq) throws Exception {
		TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        
        try {
        	if(tigLibraryDao.tigLibrary(tigLibraryDelReq.getId()) == null) {
            	throw new Exception("该标签不存在");
            }
        	Set<String> childIds = new HashSet<>();  //子id
    		childIds.add(tigLibraryDelReq.getId());
    		childIds.addAll(getChildId(tigLibraryDelReq.getId(),tigLibraryDao.tigLibraryList()));
    		
    		//所包含被删除标签的商品
    		List<Product> products = tigLibraryDao.products(childIds.toString().replace(" ", "").replace("[", "").replace("]", ""));
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
    		}
    		//清空标签库缓存
    		JedisConnectUtil.hdel("tig_library",tigLibraryDelReq.getId());
    		tigLibraryDao.tigLibraryDel(childIds);
    		transactionManager.commit(status);
        }catch (Exception e) {
        	transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
}
