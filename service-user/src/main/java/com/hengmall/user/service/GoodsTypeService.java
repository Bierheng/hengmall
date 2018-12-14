package com.server.service;

import com.server.entity.DataTablesResult;
import com.server.entity.SCategory;
import com.server.entity.SCategoryEntity;

/**
 * @author wuhengbin
 */
public interface GoodsTypeService {
	
    /**
     * 通过id获取
     * @param id
     * @return
     */
	SCategory getGoodsTypeById(Integer id);

    /**
     * 获得分类树
     * @param parentId
     * @return
     */
    DataTablesResult getGoodsTypeList();

    /**
     * 添加分类
     * @param tbItemCat
     * @return
     */
    int addGoodsType(SCategoryEntity tbGoodsType);

    /**
     * 编辑分类
     * @param tbItemCat
     * @return
     */
    int updateGoodsType(SCategoryEntity tbGoodsType);

    /**
     * 删除单个分类
     * @param id
     */
    int deleteGoodsType(Integer id);
    
    /**
     * 获得分类一级列表
     * @param parentId
     * @return
     */
	DataTablesResult getGoodsTypeListOne(int grade);

    /**
     * 获得分类二级列表
     * @param parentId
     * @return
     */
	DataTablesResult getGoodsTypeListTwo(int id);

}
