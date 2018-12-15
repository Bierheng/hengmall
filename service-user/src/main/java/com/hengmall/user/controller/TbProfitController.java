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
import com.hengmall.user.model.record.ProfitListRequest;
import com.hengmall.user.model.record.ProfitResponse;
import com.hengmall.user.service.TbProfitService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "流水记录", description = "流水记录")
@RestController
@RequestMapping("/record/")
public class TbProfitController {
	
	final static Logger log= LoggerFactory.getLogger(ShopsController.class);

	@Autowired
	private TbProfitService tbProfitService;
	
	@RequestMapping(value = "profitList",method = RequestMethod.POST)
    @ApiOperation(value = "分销收益记录列表" ,response = ProfitResponse.class)
    public Ajax<Page<ProfitResponse>> profitList(@RequestBody @Valid ProfitListRequest profitListRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/record/profitList】,{}" );
    	Ajax<Page<ProfitResponse>> ajax = new Ajax<>();
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
    			Page<ProfitResponse> profitList = tbProfitService.profitList(new Page<ProfitResponse>(),profitListRequest);
    			ajax.setCode(0);
    			ajax.setData(profitList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取分销收益记录列表失败！");
			return ajax;
		}
    }
	
}
