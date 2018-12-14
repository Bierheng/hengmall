package com.server.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
import com.server.entity.persistence.Page;
import com.server.entity.sensitive.SensitiveDelRequest;
import com.server.entity.sensitive.SensitiveWordNewRequest;
import com.server.entity.sensitive.SensitiveWordRequest;
import com.server.entity.sensitive.SensitiveWordResponse;
import com.server.service.SensitiveWordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "敏感词管理", description = "敏感词管理")
@RestController
public class SensitiveWordController{

    final static Logger log = LoggerFactory.getLogger(SensitiveWordController.class);
    
    @Autowired
  	private SensitiveWordService sensitiveWordService;
    
    
    /**
     * 敏感词列表
     * @param shopsAttentionRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sensitiveWord/getList",method = RequestMethod.POST)
    @ApiOperation(value = "敏感词列表" ,response = SensitiveWordResponse.class)
    public Ajax<Page<SensitiveWordResponse>> shopUserList(@RequestBody @Valid SensitiveWordRequest sensitiveWordRequest,BindingResult result)throws Exception{
    	
    	Subject subject = SecurityUtils.getSubject();
    
    	if (subject.hasRole("超级管理员")) {
			System.out.println("有超级管理员权限");
		}
    	
    	log.info("客户端请求数据【/sensitiveWord/getList】,{}" );
    	Ajax<Page<SensitiveWordResponse>> ajax = new Ajax<>();
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
    			Page<SensitiveWordResponse> getList = sensitiveWordService.getList(sensitiveWordRequest);
    			ajax.setCode(0);
    			ajax.setData(getList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取敏感词列表失败！");
			return ajax;
		}
    }

    
    /**
     * 新增敏感词
     * @param addShopsRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sensitiveWord/newSensitiveWord",method = RequestMethod.POST)
    @ApiOperation(value = "新增敏感词" ,response = Ajax.class)
    public Ajax<Object> newShop(@RequestBody @Valid SensitiveWordNewRequest sensitiveWordNewRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/sensitiveWord/newSensitiveWord】,{}" );
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
    			sensitiveWordService.newSensitiveWord(sensitiveWordNewRequest);
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("新增敏感词失败");
			return ajax;
		}
    }
    
    
    /**
     * 删除敏感词
     * @param addShopsRequest
     * @param result
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sensitiveWord/delSensitiveWord",method = RequestMethod.POST)
    @ApiOperation(value = "删除敏感词" ,response = Ajax.class)
    public Ajax<Object> newShop(@RequestBody @Valid SensitiveDelRequest sensitiveDelRequest,BindingResult result)throws Exception{
    	log.info("客户端请求数据【/sensitiveWord/delSensitiveWord】,{}" );
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
    			int num = sensitiveWordService.delSensitiveWord(sensitiveDelRequest);
    			if(num < 1) {
    				ajax.setCode(-1);
    				ajax.setErrMsg("删除敏感词失败,没有这个敏感词");
    				return ajax;
    			}
        		ajax.setCode(0);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("删除敏感词失败");
			return ajax;
		}
    }
}
