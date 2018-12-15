package com.hengmall.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hengmall.user.model.api.Ajax;
import com.hengmall.user.model.persistence.Page;
import com.hengmall.user.model.platform.OrderDelRequest;
import com.hengmall.user.model.platform.OrderRequest;
import com.hengmall.user.model.platform.OrderResponse;
import com.hengmall.user.model.platform.OrderSaveRequest;
import com.hengmall.user.model.platform.PlatformDelRequest;
import com.hengmall.user.model.platform.PlatformLvResponse;
import com.hengmall.user.model.platform.PlatformRequest;
import com.hengmall.user.model.platform.PlatformResponse;
import com.hengmall.user.model.platform.PlatformSaveRequest;
import com.hengmall.user.model.platform.RightDelRequest;
import com.hengmall.user.model.platform.RightRequest;
import com.hengmall.user.model.platform.RightResponse;
import com.hengmall.user.model.platform.RightSaveRequest;
import com.hengmall.user.model.platform.StateDelRequest;
import com.hengmall.user.model.platform.StateRequest;
import com.hengmall.user.model.platform.StateResponse;
import com.hengmall.user.model.platform.StateSaveRequest;
import com.hengmall.user.model.platform.TagDelRequest;
import com.hengmall.user.model.platform.TagListRequest;
import com.hengmall.user.model.platform.TagListResponse;
import com.hengmall.user.model.platform.TagSaveRequest;
import com.hengmall.user.service.PlatformService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "平台管理", description = "平台管理")
@RestController
public class PlatformController{

    final static Logger log= LoggerFactory.getLogger(PlatformController.class);
    
    @Autowired
    private PlatformService platformService;
    
    /**
     * 平台模板列表
     * @param aboutUsRequest
     * @param result
     * @return
     * @throws Exception
     */
    //@RequiresPermissions("platform:view")
    @RequestMapping(value = "/platform/platformList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ApiOperation(value = "平台模板列表" ,response = PlatformResponse.class)
    public Ajax<Page<PlatformResponse>> aboutUsList(@RequestBody @Valid PlatformRequest platformRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/platformList】,{}" );
    	Ajax<Page<PlatformResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			Page<PlatformResponse> platformList = platformService.platformList(new Page<PlatformResponse>(),platformRequest);
    			ajax.setCode(0);
    			ajax.setData(platformList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取平台模板列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 获取一级分类列表
     * @param platformRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/lvOneList",method = RequestMethod.POST)
    @ApiOperation(value = "获取一级分类列表" ,response = PlatformLvResponse.class)
    public Ajax<List<PlatformLvResponse>> aboutUsList()throws Exception{
    	log.info("客户端请求数据【/platform/lvOneList】,{}" );
    	Ajax<List<PlatformLvResponse>> ajax = new Ajax<>();
    	try {
    		List<PlatformLvResponse> lvOneList = platformService.lvOneList();
			ajax.setCode(0);
			ajax.setData(lvOneList);
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取一级分类列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 平台模板修改、添加
     * @param platformRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/platformSave",method = RequestMethod.POST)
    @ApiOperation(value = "平台模板修改、添加" ,response = Ajax.class)
    public Ajax<Object> aboutUsSave(@RequestBody @Valid PlatformSaveRequest platformSaveRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/platformSave】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			platformService.platformSave(platformSaveRequest);
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("平台模板修改、添加失败！");
			return ajax;
		}
    }
    
    /**
     * 平台模板删除
     * @param platformSaveRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/platformDel",method = RequestMethod.POST)
    @ApiOperation(value = "平台模板删除" ,response = Ajax.class)
    public Ajax<Object> aboutUsSave(@RequestBody @Valid PlatformDelRequest platformDelRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/platformDel】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			int num = platformService.platformDel(platformDelRequest);
    			if(num < 1) {
    				ajax.setCode(-1);
    				ajax.setErrMsg("平台模板删除失败：没有该平台模板");
    			}
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("平台模板删除失败！");
			return ajax;
		}
    }
    
    
    /**
     * 产品货源地国籍列表
     * @param platformRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/stateList",method = RequestMethod.POST)
    @ApiOperation(value = "产品货源地国籍列表" ,response = StateResponse.class)
    public Ajax<Page<StateResponse>> stateList(@RequestBody @Valid StateRequest stateRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/platformList】,{}" );
    	Ajax<Page<StateResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			Page<StateResponse> stateList = platformService.stateList(new Page<StateResponse>(),stateRequest);
    			ajax.setCode(0);
    			ajax.setData(stateList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取产品货源地国籍列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 产品货源地国籍添加、修改
     * @param platformSaveRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/stateSave",method = RequestMethod.POST)
    @ApiOperation(value = "产品货源地国籍添加、修改" ,response = Ajax.class)
    public Ajax<Object> stateSave(@RequestBody @Valid StateSaveRequest stateSave,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/stateSave】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			platformService.stateSave(stateSave);
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("产品货源地国籍添加、修改失败！");
			return ajax;
		}
    }
    
    
    /**
     * 产品货源地国籍删除
     * @param stateDelRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/stateDel",method = RequestMethod.POST)
    @ApiOperation(value = "产品货源地国籍删除" ,response = Ajax.class)
    public Ajax<Object> stateSave(@RequestBody @Valid StateDelRequest stateDelRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/stateDel】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			platformService.stateDel(stateDelRequest);
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("产品货源地国籍删除失败！");
			return ajax;
		}
    }
    
    
    /**
     * 店铺资质列表
     * @param stateRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/rightList",method = RequestMethod.POST)
    @ApiOperation(value = "店铺资质列表" ,response = RightResponse.class)
    public Ajax<Page<RightResponse>> rightList(@RequestBody @Valid RightRequest rightRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/rightList】,{}" );
    	Ajax<Page<RightResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			Page<RightResponse> rightList = platformService.rightList(new Page<RightResponse>(),rightRequest);
    			ajax.setCode(0);
    			ajax.setData(rightList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取店铺资质列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 店铺资质添加、修改
     * @param stateSave
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/rightSave",method = RequestMethod.POST)
    @ApiOperation(value = "店铺资质添加、修改" ,response = Ajax.class)
    public Ajax<Object> rightSave(@RequestBody @Valid RightSaveRequest rightSaveRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/rightSave】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			platformService.rightSave(rightSaveRequest);
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("店铺资质添加、修改失败！");
			return ajax;
		}
    }
    
    
    /**
     * 店铺资质删除
     * @param rightSaveRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/rightDel",method = RequestMethod.POST)
    @ApiOperation(value = "店铺资质删除" ,response = Ajax.class)
    public Ajax<Object> rightDel(@RequestBody @Valid RightDelRequest rightDelRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/rightDel】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			platformService.rightDel(rightDelRequest);
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("店铺资质删除失败！");
			return ajax;
		}
    }
    
    
    
    /**
     * 平台标签列表
     * @param rightRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/tagList",method = RequestMethod.POST)
    @ApiOperation(value = "平台标签列表" ,response = TagListResponse.class)
    public Ajax<Page<TagListResponse>> tagList(@RequestBody @Valid TagListRequest tagListRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/tagList】,{}" );
    	Ajax<Page<TagListResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			Page<TagListResponse> tagList = platformService.tagList(new Page<TagListResponse>(),tagListRequest);
    			ajax.setCode(0);
    			ajax.setData(tagList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取平台标签列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 平台标签添加、修改
     * @param rightDelRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/tagSave",method = RequestMethod.POST)
    @ApiOperation(value = "平台标签添加、修改" ,response = Ajax.class)
    public Ajax<Object> tagSave(@RequestBody @Valid TagSaveRequest tagSaveRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/tagSave】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			platformService.tagSave(tagSaveRequest);
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("平台标签添加、修改失败！");
			return ajax;
		}
    }
    
    
    /**
     * 平台标签删除
     * @param tagSaveRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/tagDel",method = RequestMethod.POST)
    @ApiOperation(value = "平台标签删除" ,response = Ajax.class)
    public Ajax<Object> tagDel(@RequestBody @Valid TagDelRequest tagDelRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/tagDel】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			int num = platformService.tagDel(tagDelRequest);
    			if(num < 1) {
    				ajax.setCode(-1);
    				ajax.setErrMsg("平台标签删除失败：没有这个标签");
    			}
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("平台标签删除失败！");
			return ajax;
		}
    }

    
    /**
     * 定位地图购物信息弹框列表
     * @param tagListRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/orderList",method = RequestMethod.POST)
    @ApiOperation(value = "定位地图购物信息弹框列表" ,response = OrderResponse.class)
    public Ajax<Page<OrderResponse>> orderList(@RequestBody @Valid OrderRequest orderRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/orderList】,{}" );
    	Ajax<Page<OrderResponse>> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			Page<OrderResponse> orderList = platformService.orderList(new Page<OrderResponse>(),orderRequest);
    			ajax.setCode(0);
    			ajax.setData(orderList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取定位地图购物信息弹框列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 定位地图购物信息弹框添加、修改
     * @param tagSaveRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/orderSave",method = RequestMethod.POST)
    @ApiOperation(value = "定位地图购物信息弹框添加、修改" ,response = Ajax.class)
    public Ajax<Object> tagSave(@RequestBody @Valid OrderSaveRequest orderSaveRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/orderSave】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			platformService.orderSave(orderSaveRequest);
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("定位地图购物信息弹框添加、修改失败！");
			return ajax;
		}
    }
    
    
    /**
     * 定位地图购物信息弹框删除
     * @param tagDelRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/platform/orderDel",method = RequestMethod.POST)
    @ApiOperation(value = "定位地图购物信息弹框删除" ,response = Ajax.class)
    public Ajax<Object> orderDel(@RequestBody @Valid OrderDelRequest orderDelRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/platform/orderDel】,{}" );
    	Ajax<Object> ajax = new Ajax<>();
    	try {
    		if(result.hasErrors()) {
    			StringBuffer sb = new StringBuffer();
    			List<ObjectError> errorList = result.getAllErrors();
                for(ObjectError error : errorList){
                	sb.append(error.getDefaultMessage() + "!");
                }
                ajax.setCode(-1);
    			ajax.setErrMsg(sb.toString());
    		}else {
    			int num = platformService.orderDel(orderDelRequest);
    			if(num < 1) {
    				ajax.setCode(-1);
    				ajax.setErrMsg("定位地图购物信息弹框删除删除失败：没有这条数据");
    			}
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("定位地图购物信息弹框删除删除失败！");
			return ajax;
		}
    }
}
