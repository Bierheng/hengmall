package com.server.controller.rest;

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

import com.server.entity.api.Ajax;
import com.server.entity.tigLibrary.TigLibraryRes;
import com.server.entity.tigLibrary.TigLibraryAddReq;
import com.server.entity.tigLibrary.TigLibraryDelReq;
import com.server.service.TigLibraryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 标签库管理NEW Controller
 * @author Administrator
 *
 */
@Api(value = "标签库", description = "标签库")
@RestController
public class TigLibraryController {
	
	final static Logger log= LoggerFactory.getLogger(TigLibraryController.class);

	@Autowired
	private TigLibraryService tigLibraryService;
	
	@RequestMapping(value = "/tigLibrary/tigLibraryList",method = RequestMethod.POST)
    @ApiOperation(value = "标签库列表" ,response = TigLibraryRes.class)
    public Ajax<List<TigLibraryRes>> tigLibraryList()throws Exception{
    	log.info("客户端请求数据【/productClassify/classifyList】,{}" );
    	Ajax<List<TigLibraryRes>> ajax = new Ajax<>();
    	try {
    		List<TigLibraryRes> tigLibraryList = tigLibraryService.tigLibraryList();
			ajax.setCode(0);
			ajax.setData(tigLibraryList);
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取标签库列表失败！");
			return ajax;
		}
    }
	
	@RequestMapping(value = "/tigLibrary/recache",method = RequestMethod.POST)
    @ApiOperation(value = "标签库列表" ,response = TigLibraryRes.class)
    public Ajax<?> recache()throws Exception{
    	log.info("客户端请求数据【/productClassify/classifyList】,{}" );
    	Ajax<List<TigLibraryRes>> ajax = new Ajax<>();
    	try {
    		List<TigLibraryRes> tigLibraryList = tigLibraryService.tigLibraryList();
			ajax.setCode(0);
			ajax.setData(tigLibraryList);
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取标签库列表失败！");
			return ajax;
		}
    }
	
	@RequestMapping(value = "/tigLibrary/tigLibrarySave",method = RequestMethod.POST)
    @ApiOperation(value = "标签库添加、修改" ,response = Ajax.class)
    public Ajax<Object> tigLibraryAdd(@RequestBody @Valid TigLibraryAddReq tigLibraryAddReq,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/tigLibrary/tigLibraryAdd】,{}" );
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
    		}else{
    			int id = tigLibraryService.tigLibraryAdd(tigLibraryAddReq);
    			ajax.setData(id);
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("标签库添加、修改失败");
			return ajax;
		}
    }
	
	
	@RequestMapping(value = "/tigLibrary/tigLibraryDel",method = RequestMethod.POST)
    @ApiOperation(value = "标签库删除" ,response = Ajax.class)
    public Ajax<Object> tigLibraryDel(@RequestBody @Valid TigLibraryDelReq tigLibraryDelReq,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/tigLibrary/tigLibraryDel】,{}" );
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
    		}else{
    			tigLibraryService.tigLibraryDel(tigLibraryDelReq);
    		}
    		ajax.setCode(0);
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg(e.getMessage());
			return ajax;
		}
    }
}
