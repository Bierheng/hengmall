package com.hengmall.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hengmall.user.model.DataTablesResult;
import com.hengmall.user.model.DelBean;
import com.hengmall.user.model.Result;
import com.hengmall.user.model.SCategoryEntity;
import com.hengmall.user.service.GoodsTypeService;
import com.hengmall.user.util.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wuhengbin
 */
@RestController
@Api(description = "商品类别管理")
public class GoodsTypeController {

    final static Logger log= LoggerFactory.getLogger(GoodsTypeController.class);

    @Autowired
    private GoodsTypeService goodsTypeService;


    @RequestMapping(value = "/goodsType/list",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有类别列表")
    public DataTablesResult getTypeList(){

        DataTablesResult result=goodsTypeService.getGoodsTypeList();
        return result;
    }
    
    @RequestMapping(value = "/goodsType/listOne",method = RequestMethod.GET)
    @ApiOperation(value = "获取一级类别列表")
    public DataTablesResult getTypeListOne(HttpServletRequest request){

    	
        DataTablesResult result=goodsTypeService.getGoodsTypeListOne(1);
        return result;
    }
    
    @RequestMapping(value = "/goodsType/listTwo",method = RequestMethod.GET)
    @ApiOperation(value = "获取二级类别列表")
    public DataTablesResult getTypeListTwo(){

        DataTablesResult result=goodsTypeService.getGoodsTypeListOne(2);
        return result;
    }

    @RequestMapping(value = "/goodsType/listTwoById",method = RequestMethod.GET)
    @ApiOperation(value = "根据一级类别获取二级类别列表")
    public DataTablesResult getTypeListTwoById(int id){

        DataTablesResult result=goodsTypeService.getGoodsTypeListTwo(id);
        return result;
    }

    @RequestMapping(value = "/goodsType/addType",method = RequestMethod.POST )
    @ApiOperation(value = "添加商品分类" ,response = SCategoryEntity.class, httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> addType(@RequestBody SCategoryEntity sCategoryEntity){
    	System.out.println(sCategoryEntity.getId());
    	if("null".equals( String.valueOf(sCategoryEntity.getId())) || "0".equals( String.valueOf(sCategoryEntity.getId())) ){
    		goodsTypeService.addGoodsType(sCategoryEntity);
    	}else{
    		goodsTypeService.updateGoodsType(sCategoryEntity);
    	}
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/goodsType/delType",method = RequestMethod.POST)
    @ApiOperation(value = "删除商品类别")
    public Result<Object> delType(@RequestBody DelBean ids){
    		int id = ids.getIds();
            int result=goodsTypeService.deleteGoodsType(id);
            if(result==0){
                return new ResultUtil<Object>().setErrorMsg("子类别没删除完之前不能删除父类别，不能删除！");
            }
        
        return new ResultUtil<Object>().setData(null);
    }
}
