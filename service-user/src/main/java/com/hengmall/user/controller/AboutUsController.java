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

import com.server.entity.aboutUs.AboutUsResponse;
import com.server.entity.aboutUs.AboutUsSaveRequest;
import com.server.entity.api.Ajax;
import com.server.service.AboutUsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "关于我们", description = "关于我们")
@RestController
public class AboutUsController{

    final static Logger log= LoggerFactory.getLogger(AboutUsController.class);
    
    @Autowired
    private AboutUsService aboutUsService;
    
    /**
     * 关于我们列表
     * @param aboutUsRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/aboutUs/aboutUsList",method = RequestMethod.POST)
    @ApiOperation(value = "关于我们列表" ,response = AboutUsResponse.class)
    public Ajax<List<AboutUsResponse>> aboutUsList()throws Exception{
    	log.info("客户端请求数据【/shops/aboutUsList】,{}" );
    	Ajax<List<AboutUsResponse>> ajax = new Ajax<>();
    	try {
    		List<AboutUsResponse> shopsOrder = aboutUsService.aboutUsList();
			ajax.setCode(0);
			ajax.setData(shopsOrder);
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取关于我们列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 新增或修改关于我们(修改成只有修改)
     * @param aboutUsSaveRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/aboutUs/aboutUsSave",method = RequestMethod.POST)
    @ApiOperation(value = "修改关于我们" ,response = Ajax.class)
    public Ajax<Object> aboutUsList(@RequestBody @Valid AboutUsSaveRequest aboutUsSaveRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/aboutUsSave】,{}" );
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
    			aboutUsService.aboutUsSave(aboutUsSaveRequest);
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("新增或修改关于我们失败！");
			return ajax;
		}
    }
    
    
    /**
     * 删除关于我们
     * @param aboutUsSaveRequest
     * @param result
     * @return
     * @throws Exception
     */
 /*   @RequestMapping(value = "/aboutUs/aboutUsDel",method = RequestMethod.POST)
    @ApiOperation(value = "删除关于我们" ,response = Ajax.class)
    public Ajax<Object> aboutUsDel(@RequestBody @Valid AboutUsDelRequest aboutUsDelRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/shops/aboutUsDel】,{}" );
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
    			aboutUsService.aboutUsDel(aboutUsDelRequest);
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("删除关于我们失败！");
			return ajax;
		}
    }*/
}
