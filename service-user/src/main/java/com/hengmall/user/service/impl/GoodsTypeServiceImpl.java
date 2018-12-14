package com.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.dao.SCategoryDao;
import com.server.dao.ShopsDao;
import com.server.entity.DataTablesResult;
import com.server.entity.SCategory;
import com.server.entity.SCategoryEntity;
import com.server.exception.XmallException;
import com.server.service.GoodsTypeService;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
	
	@Autowired
	private SCategoryDao sCategoryDao;
	@Autowired
	private ShopsDao shopsDao;

	@Override
	public SCategory getGoodsTypeById(Integer id) {
		SCategory  sCategory = sCategoryDao.queryById(id);
		return sCategory;
	}

	@Override
	public DataTablesResult getGoodsTypeList() {
		DataTablesResult result=new DataTablesResult();
		List<SCategory> list = sCategoryDao.queryByIdAll();
        result.setSuccess(true);
        result.setData(list);
        return result;
	}
	
	@Override
	public DataTablesResult getGoodsTypeListOne(int grade) {
		DataTablesResult result=new DataTablesResult();
		List<SCategory> list = new ArrayList<SCategory>();
		//二级分类的时候多获取一个一级分类名称的参数
		if(grade == 2){
			 list = sCategoryDao.queryByPidTwo(grade);
		}else{
			 list = sCategoryDao.queryByLevel(grade);
		}
	
        result.setSuccess(true);
        result.setData(list);
        return result;
	}
	
	@Override
	public DataTablesResult getGoodsTypeListTwo(int id) {
		DataTablesResult result=new DataTablesResult();
		if(Integer.valueOf(id) != null){
			List<SCategory> list = sCategoryDao.queryByPid(id);
	        result.setSuccess(true);
	        result.setData(list);
		}else{
            throw new XmallException("商品分类上级编号为空");
        }
        return result;
	}

	@Override
	@Transactional
	public int addGoodsType(SCategoryEntity sCategory) {
		if("null".equals( String.valueOf(sCategory.getPid())) || "0".equals( String.valueOf(sCategory.getPid()))){
			sCategory.setLevel(1);
		}else{
			sCategory.setLevel(2);
		}
		int shopsId = sCategory.getShops_id();
		String shopsName = shopsDao.getShopsName(String.valueOf(shopsId));
		sCategory.setShops_name(shopsName);
		int result = sCategoryDao.insert(sCategory);
        if(result!=1){
            throw new XmallException("添加商品分类失败");
        }
		return result;
	}

	@Override
	@Transactional
	public int updateGoodsType(SCategoryEntity sCategory) {
		try {
			int shopsId = sCategory.getShops_id();
			String shopsName = shopsDao.getShopsName(String.valueOf(shopsId));
			sCategory.setShops_name(shopsName);
			int result = sCategoryDao.updateById(sCategory);
	        if(result!=1){
	            throw new XmallException("修改商品分类失败");
	        }
			return result;
		} catch (Exception e) {
				e.printStackTrace();
			return 0 ;
		}
	}

	@Override
	@Transactional
	public int deleteGoodsType(Integer id) {
		int result =  sCategoryDao.delById(id);
        if(result!=1){
            throw new XmallException("删除商品分类失败");
        }
		return result;
	}
}
