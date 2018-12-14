package com.server.controller.rest;

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

import com.alibaba.fastjson.JSONObject;
import com.server.entity.Brands;
import com.server.entity.DataTablesResult;
import com.server.entity.DelBean;
import com.server.entity.FlashSale;
import com.server.entity.FlashSaleReq;
import com.server.entity.FlashSaleTimeLiness;
import com.server.entity.Result;
import com.server.entity.TbGoodsTag;
import com.server.entity.api.Ajax;
import com.server.entity.common.CategoryListRequest;
import com.server.entity.common.CategoryListResponse;
import com.server.entity.common.ProductRequest;
import com.server.entity.common.ProductResponse;
import com.server.entity.common.combineSale.CombineSaleDelRequest;
import com.server.entity.common.combineSale.CombineSaleRequest;
import com.server.entity.common.combineSale.CombineSaleResponse;
import com.server.entity.common.combineSale.CombineSaleSaveRequest;
import com.server.entity.manage.product.isnew.ShopsRightEntity;
import com.server.entity.persistence.Page;
import com.server.service.CommonService;
import com.server.utils.CommonUtils;
import com.server.utils.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wuhengbin
 */
@RestController
@Api(description = "公共基础管理")
public class CommonController {

    final static Logger log= LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;

    /** 获取所有品牌列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/brand/list",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有品牌列表" ,response = Brands.class)
    public DataTablesResult getBrandList(int draw , int length)throws Exception {
    	log.info("客户端请求数据【/brand/list】,{}");
    	try {
    		if(CommonUtils.judge(String.valueOf(draw))&&CommonUtils.judge(String.valueOf(length))){
    			DataTablesResult result=commonService.getGoodsBrandList(draw , length);
    			return result;
    		}else{
        		DataTablesResult result = new DataTablesResult();
    			result.setSuccess(false);
    			result.setError("传入参数为空！");
    			return result;
    		}

		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("获取所有品牌列表失败！");
			return result;
		}
    }

    /**
     * 添加或修改品牌
     * @param tbGoodsBrand  品牌的实体类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/brand/addType",method = RequestMethod.POST)
    @ApiOperation(value = "添加或修改品牌")
    public Result<Object> addBrand(@RequestBody Brands tbGoodsBrand)throws Exception{
    	log.info("客户端请求数据【/brand/addType】,{}" + tbGoodsBrand.toString());
    	try {
        	if(CommonUtils.judge(String.valueOf(tbGoodsBrand.getId()))){
        		commonService.updateGoodsBrand(tbGoodsBrand);
        	}else{
        		commonService.addGoodsBrand(tbGoodsBrand);
        	}
            return new ResultUtil<Object>().setData(null);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("添加或修改品牌失败！");
			return result;
		}

    }

    /**
     * 删除品牌
     * @param ids   id数组
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/brand/delBrand",method = RequestMethod.POST)
    @ApiOperation(value = "删除品牌")
    public Result<Object> delBrand(@RequestBody DelBean id)throws Exception{
    	log.info("客户端请求数据【/brand/addType】,{}" + id.toString());
    	try {
    			int ids = id.getIds();
                int result=commonService.deleteGoodsBrand(ids);
                if(result==0){
                    return new ResultUtil<Object>().setErrorMsg("删除失败！");
                }

            return new ResultUtil<Object>().setData(null);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("删除品牌失败！");
			return result;
		}

    }

    /**获取所有标签列表    
     * 
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tag/list",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有标签列表" ,response = TbGoodsTag.class)
    public DataTablesResult getTagList(int draw , int length)throws Exception{
    	log.info("客户端请求数据【/tag/list】,{}");
    	try {
    		if(CommonUtils.judge(String.valueOf(draw))&&CommonUtils.judge(String.valueOf(length))){
                DataTablesResult result=commonService.getGoodsTagList(draw ,length);
                return result;
    		}else{
        		DataTablesResult result = new DataTablesResult();
    			result.setSuccess(false);
    			result.setError("传入参数为空！");
    			return result;
    		}
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("获取所有标签列表失败！");
			return result;
		}

    }

    /**
     * 添加或修改标签    当id没有值时调用新增方法有值时调用修改方法
     * @param tbGoodsTag  标签的实体类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tag/addTag",method = RequestMethod.POST)
    @ApiOperation(value = "添加或修改标签")
    public Result<Object> addTag(@RequestBody TbGoodsTag tbGoodsTag) throws Exception{
    	log.info("客户端请求数据【/tag/addTag】,{}" +tbGoodsTag.toString());
    	try {
        	if(CommonUtils.judge(String.valueOf(tbGoodsTag.getId()))){
        		commonService.updateGoodsTag(tbGoodsTag);
        	  }else{
        		commonService.addGoodsTag(tbGoodsTag);
        	  }
                return new ResultUtil<Object>().setData(null);
		} catch (Exception e){
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("添加或修改标签失败！");
			return result;
		}

    }
    
    /**
     * 删除标签
     * @param ids   要被删除的ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tag/delTag",method = RequestMethod.POST)
    @ApiOperation(value = "删除标签")
    public Result<Object> delTag(@RequestBody DelBean ids) throws Exception{
    	log.info("客户端请求数据【/tag/addTag】,{}" +ids.toString());
    	try {
    			int id = ids.getIds();
                int result=commonService.deleteGoodsTag(id);
                if(result==0){
                    return new ResultUtil<Object>().setErrorMsg("删除失败！");
                }
            return new ResultUtil<Object>().setData(null);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("添加或修改标签失败！");
			return result;
		}

    }
    
    /**
     * 获取标签记录总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tag/count",method = RequestMethod.GET)
    @ApiOperation(value = "获取标签记录总数")
    public Result<Object> countTag() throws Exception{
    	log.info("客户端请求数据【/tag/count】,{}");
    	try {
    		int result = commonService.countTag();
            return new ResultUtil<Object>().setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
    
    /**
     * 获取品牌记录总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/brand/count",method = RequestMethod.GET)
    @ApiOperation(value = "获取品牌记录总数")
    public Result<Object> countBrands() throws Exception{
    	log.info("客户端请求数据【/brand/count】,{}");
    	try {
    		int result = commonService.countBrands();
            return new ResultUtil<Object>().setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
    
    /**
     * 获取抢购时间段总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/flashSaleTime/count",method = RequestMethod.GET)
    @ApiOperation(value = "获取抢购时间段总数")
    public Result<Object> countFlashSaleTimeLiness() throws Exception{
    	log.info("客户端请求数据【/flashSaleTime/count】,{}");
    	try {
    		Result<Object> result = commonService.countFlashSaleTimeLiness();
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
    
    /**
     * 获取抢购活动记录总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/flashSale/count",method = RequestMethod.GET)
    @ApiOperation(value = "获取抢购活动记录总数")
    public Result<Object> countFlashSale() throws Exception{
    	log.info("客户端请求数据【/flashSale/count】,{}");
    	try {
    		Result<Object> result = commonService.countFlashSale();
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
    
    /** 获取所有抢购活动列表
     *   
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/flashSale/list",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有抢购活动列表" ,response = Brands.class)
    public DataTablesResult getFlashSaleList(int draw , int length)throws Exception {
    	log.info("客户端请求数据【/flashSale/list】,{}");
    	try {
    		if(CommonUtils.judge(String.valueOf(draw))&&CommonUtils.judge(String.valueOf(length))){
    			DataTablesResult result=commonService.getFlashSaleList(draw , length);
    			return result;
    		}else{
        		DataTablesResult result = new DataTablesResult();
    			result.setSuccess(false);
    			result.setError("传入参数为空！");
    			return result;
    		}
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("获取所有抢购活动列表失败！");
			return result;
		}
    }

    /**
     * 添加或修改抢购活动
     * @param tbGoodsBrand  品牌的实体类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/flashSale/addType",method = RequestMethod.POST)
    @ApiOperation(value = "添加或修改抢购活动")
    public Result<Object> addFlashSale(@RequestBody FlashSale flashSale)throws Exception{
    	log.info("客户端请求数据【/flashSale/addType】,{}"+flashSale.toString());
    	try {
        	if(CommonUtils.judge(String.valueOf(flashSale.getId()))){
        		if(!CommonUtils.judge(String.valueOf(flashSale.getId()))){
        		}
        		 commonService.updateFlashSale(flashSale);
        	}else{
        		 commonService.addFlashSale(flashSale);
        	}
            return new ResultUtil<Object>().setData(null);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("添加或修改抢购活动失败！");
			return result;
		}
    }

    /**
     * 删除抢购活动
     * @param ids   id数组
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/flashSale/delFlashSale",method = RequestMethod.POST)
    @ApiOperation(value = "删除抢购活动")
    public Result<Object> delFlashSale(@RequestBody DelBean id)throws Exception{
    	log.info("客户端请求数据【/flashSale/addType】,{}"+id.toString());
    	try {
    			int ids = id.getIds();
                int result=commonService.delFlashSale(ids);
                if(result==0){
                    return new ResultUtil<Object>().setErrorMsg("删除失败！");
                }
            return new ResultUtil<Object>().setData(null);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("删除抢购活动失败！");
			return result;
		}
    }
    
    /** 获取所有抢购活动时间段列表  
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/flashSaleTime/list",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有抢购活动时间段列表" ,response = FlashSaleTimeLiness.class)
    public DataTablesResult getFlashSaleTimeList(int draw , int length)throws Exception {
    	log.info("客户端请求数据【/flashSaleTime/list】,{}");
    	try {
    		if(CommonUtils.judge(String.valueOf(draw))&&CommonUtils.judge(String.valueOf(length))){
    			DataTablesResult result=commonService.getFlashSaleTimeList(draw , length);
    			return result;
    		}else{
        		DataTablesResult result = new DataTablesResult();
    			result.setSuccess(false);
    			result.setError("传入参数为空！");
    			return result;
    		}
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("获取所有抢购活动列表失败！");
			return result;
		}
    }

    /**
     * 添加或修改抢购时间段活动
     * @param flashSaleTime  抢购时间段的实体类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/flashSaleTime/addType",method = RequestMethod.POST)
    @ApiOperation(value = "添加或修改抢购时间段活动")
    public Result<Object> addFlashSaleTime(@RequestBody FlashSaleTimeLiness flashSaleTime)throws Exception{
    	log.info("客户端请求数据【/flashSaleTime/list】,{}"+flashSaleTime.toString());
    	try {
        	if(CommonUtils.judge(String.valueOf(flashSaleTime.getId()))){
        		 commonService.updateFlashSaleTime(flashSaleTime);
        	}else{
        		 commonService.addFlashSaleTime(flashSaleTime);
        	}
            return new ResultUtil<Object>().setData(null);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("添加或修改抢购活动段失败！");
			return result;
		}
    }

	/**
	 * @Title: 获取指定抢购活动ID的所有信息
	 * @Description:TOD
	 */
	@RequestMapping(value = "/flashSale/getInfo",method = RequestMethod.POST)
    @ApiOperation(value = "根据ID获取指定抢购活动的全部信息", notes = "根据ID获取指定抢购活动的全部信息")
    public JSONObject queryFlashSaleById(@RequestBody FlashSaleReq flashSale){
		log.info("客户端请求数据【/flashSale/getInfo】,{}", flashSale.toString());
    	JSONObject json = new JSONObject();
    	int id = flashSale.getId();
		try {
			json = commonService.getFlashSaleInfo(id);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", false);
		}
		return json;
	}
    
    /**
     * 删除抢购时间段活动
     * @param ids   id数组
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/flashSaleTime/delFlashSale",method = RequestMethod.POST)
    @ApiOperation(value = "删除抢购时间段活动")
    public Result<Object> delFlashSaleTime(@RequestBody DelBean id)throws Exception{
    	log.info("客户端请求数据【/flashSaleTime/delFlashSale】,{}"+id.toString());
    	try {
    			int ids = id.getIds();
                int result=commonService.delFlashSaleTime(ids);
                if(result==0){
                    return new ResultUtil<Object>().setErrorMsg("删除失败！");
                }
            return new ResultUtil<Object>().setData(null);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("删除抢购活动时间段失败！");
			return result;
		}
    }
    
    
    /**
     * 获取商品分类列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/common/category",method = RequestMethod.POST)
    @ApiOperation(value = "获取商品分类列表" ,response = CategoryListResponse.class)
    public Ajax<Page<CategoryListResponse>> categoryList(@RequestBody @Valid CategoryListRequest categoryListRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/common/category】,{}" );
    	Ajax<Page<CategoryListResponse>> ajax = new Ajax<>();
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
    			categoryListRequest.setToken(request.getHeader("Authorization"));    				
    			Page<CategoryListResponse> categoryList = commonService.categoryList(new Page<CategoryListResponse>(),categoryListRequest);
    			ajax.setCode(0);
    			ajax.setData(categoryList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取商品分类列表失败！");
			return ajax;
		}
    }
    
    
    /**
     * 获取平台商品列表
     * @param productReq
     * @param result
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/common/productList",method = RequestMethod.POST)
    @ApiOperation(value = "获取平台商品列表" ,response = ProductResponse.class)
    public Ajax<Page<ProductResponse>> productList(@RequestBody @Valid ProductRequest productReq,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/common/productList】,{}" );
    	Ajax<Page<ProductResponse>> ajax = new Ajax<>();
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
    			Page<ProductResponse> productList = commonService.productList(new Page<ProductResponse>(),productReq);
    			ajax.setCode(0);
    			ajax.setData(productList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取商品列表失败！");
			return ajax;
		}
    }
    
    /**
     * 获取商品特色服务
     * @param productReq
     * @param result
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/common/rights",method = RequestMethod.POST)
    @ApiOperation(value = "获取商品特色服务" ,response = ShopsRightEntity.class)
    public Ajax<List<ShopsRightEntity>> rights()throws Exception{
    	log.info("客户端请求数据【/common/rights】,{}" );
    	Ajax<List<ShopsRightEntity>> ajax = new Ajax<>();
    	try {
    		ajax.setCode(0);
    		ajax.setData(commonService.rights());
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取商品特色服务失败！");
			return ajax;
		}
    }
    
    
    @RequestMapping(value = "/common/combineSaleList",method = RequestMethod.POST)
    @ApiOperation(value = "拼单商品列表" ,response = CombineSaleResponse.class)
    public Ajax<Page<CombineSaleResponse>> combineSaleList(@RequestBody @Valid CombineSaleRequest combineSaleRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/common/combineSaleList】,{}" );
    	Ajax<Page<CombineSaleResponse>> ajax = new Ajax<>();
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
    			Page<CombineSaleResponse> combineSaleList = commonService.combineSaleList(new Page<CombineSaleResponse>(),combineSaleRequest);
    			ajax.setCode(0);
    			ajax.setData(combineSaleList);
    		}
    		return ajax;
		} catch (Exception e) {
			e.printStackTrace();
			ajax.setCode(-1);
			ajax.setErrMsg("获取拼单商品列表失败！");
			return ajax;
		}
    }
    
    
    @RequestMapping(value = "/common/combineSaleSave",method = RequestMethod.POST)
    @ApiOperation(value = "拼单商品增改" ,response = Ajax.class)
    public Ajax<Object> combineSaleSave(@RequestBody @Valid CombineSaleSaveRequest combineSaleSaveRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/common/combineSaleSave】,{}" );
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
    			commonService.combineSaleSave(combineSaleSaveRequest);
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
    
    
    @RequestMapping(value = "/common/combineSaleDel",method = RequestMethod.POST)
    @ApiOperation(value = "拼单商品删除" ,response = Ajax.class)
    public Ajax<Object> combineSaleDel(@RequestBody @Valid CombineSaleDelRequest combineSaleDelRequest,BindingResult result,HttpServletRequest request)throws Exception{
    	log.info("客户端请求数据【/common/combineSaleDel】,{}" );
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
    			commonService.combineSaleDel(combineSaleDelRequest);
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
