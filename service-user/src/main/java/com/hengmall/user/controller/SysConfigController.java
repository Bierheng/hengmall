package com.hengmall.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.hengmall.user.model.sys.ConfigDelRequest;
import com.hengmall.user.model.sys.ConfigListRequest;
import com.hengmall.user.model.sys.ConfigListResponse;
import com.hengmall.user.model.sys.ConfigSaveRequest;
import com.hengmall.user.service.SysConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "系统配置", description = "系统配置")
@RestController
@RequestMapping("/sys/")
public class SysConfigController {
	
	final static Logger log= LoggerFactory.getLogger(ShopsController.class);

	@Autowired
	private SysConfigService sysConfigService;
	
	@RequestMapping(value = "configList",method = RequestMethod.POST)
    @ApiOperation(value = "系统配置列表" ,response = ConfigListResponse.class)
    public Ajax<Page<ConfigListResponse>> configList(@RequestBody @Valid ConfigListRequest configListRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/sys/configList】,{}" );
    	Ajax<Page<ConfigListResponse>> ajax = new Ajax<>();
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
    			Page<ConfigListResponse> configList = sysConfigService.configList(new Page<ConfigListResponse>(),configListRequest);
    			ajax.setCode(0);
    			ajax.setData(configList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取系统配置列表失败！");
			return ajax;
		}
    }
	
	
	@RequestMapping(value = "configSave",method = RequestMethod.POST)
    @ApiOperation(value = "系统配置添加,修改" ,response = Ajax.class)
    public Ajax<Object> configSave(@RequestBody @Valid ConfigSaveRequest configSaveRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/sys/configSave】,{}" );
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
    			sysConfigService.configSave(configSaveRequest);
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg(e.getMessage());
			return ajax;
		}
    }
	
	
	@RequestMapping(value = "configDel",method = RequestMethod.POST)
    @ApiOperation(value = "系统配置删除" ,response = Ajax.class)
    public Ajax<Object> configDel(@RequestBody @Valid ConfigDelRequest configDelRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/sys/configDel】,{}" );
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
    			sysConfigService.configDel(configDelRequest);
    			ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg(e.getMessage());
			return ajax;
		}
    }
}
