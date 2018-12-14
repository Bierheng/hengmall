package com.server.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.server.controller.BaseController;
import com.server.entity.DataTablesResult;
import com.server.entity.DelBean;
import com.server.entity.RelCarouselEntity;
import com.server.entity.Result;
import com.server.entity.SCouponEntity;
import com.server.entity.TbProductTag;
import com.server.entity.api.Ajax;
import com.server.entity.manage.product.ListProductReq;
import com.server.entity.manage.product.ListProductResp;
import com.server.entity.manage.product.ProductBean;
import com.server.entity.manage.product.ProductIdReq;
import com.server.entity.manage.product.ProductReq;
import com.server.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "商城后台-商品管理接口", description = "商城后台(pc后台，小程序代理和供应商后台管理)-商品管理接口")
@RestController
@RequestMapping("manage/product/")
public class ManageController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ManageController.class);

	@Autowired
	private ProductService productService;
	
	
	/**
	 * @Title: 获取商品列表
	 * @Description: 
	 */
	@ApiOperation(value = "商品列表", notes = "展示商品列表", response = ListProductResp.class, httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "getProductList", method = RequestMethod.POST)
	public Ajax<ListProductResp> getProductList(@RequestBody(required = false) ListProductReq listProductReq) {
		logger.info("【manage/product/getProductList】,{}", String.valueOf(listProductReq));
		try {
			Ajax<ListProductResp> result = productService.getProductList(listProductReq);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.toString());
			Ajax<ListProductResp> result = new Ajax<ListProductResp>();
			result.setErrMsg("获取商品列表失败");
			return result;
		}
	}
	
	/**
	 * @Title: 获取仓库商品列表
	 * @Description: 
	 */
	@ApiOperation(value = "仓库商品列表", notes = "展示仓库商品列表", response = ListProductResp.class, httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "getWareProductList", method = RequestMethod.POST)
	public Ajax<ListProductResp> getWareProductList(@RequestBody(required = false) ListProductReq listProductReq) {
		logger.info("【manage/product/getWareProductList】,{}", String.valueOf(listProductReq));
		try {
			Ajax<ListProductResp> result = productService.getWareProductList(listProductReq);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.toString());
			return null;
		}
	}

	/**
	 * @return 
	 * @Title: 根据分类ID获取商品列表
	 * @Description: 
	 */
	@ApiOperation(value = "根据分类ID获取商品列表", notes = "根据一级分类获取下一级分类列表或者商品列表", response = ProductBean.class, httpMethod = "POST")
	@ResponseBody
	@RequestMapping(value = "getProductByList", method = RequestMethod.POST)
	public  DataTablesResult getProductByList(@RequestBody ProductIdReq product )throws Exception {
		logger.info("客户端请求数据【manage/product/getProductList】,{}");
		try {
			DataTablesResult result = productService.getProductByList(product);
			 return result;
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("分类ID获取商品列表");
			return result;
		}
	}	
	
	/**
	 * @return 
	 * @Title: 新增商品及商品详情
	 * @Description: 
	 */
	@ApiOperation(value = "新增或修改商品及商品详情", notes = "新增或修改商品及商品详情", response = ProductBean.class, httpMethod = "POST")
	@ResponseBody
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public  DataTablesResult addProduct(@RequestBody ProductBean product)throws Exception {
		logger.info("客户端请求数据【manage/product/addProduct】,{}", product.toString());
		DataTablesResult result = new DataTablesResult();
		try {
			result = productService.addProduct(product);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setError("新增或修改商品失败");
			return result;
		}
	}
	
	/**
	 * @return 
	 * @Title: 商品下架
	 * @Description: 
	 */
	@ApiOperation(value = "将商品下架", notes = "商品下架", response = ProductBean.class, httpMethod = "POST")
	@ResponseBody
	@RequestMapping(value = "pullOffProduct", method = RequestMethod.POST)
	public  DataTablesResult pullOffProduct(@RequestBody ProductReq productReq)throws Exception {
		logger.info("客户端请求数据【manage/product/pullOffProduct】,{}", productReq.toString());
		DataTablesResult result = new DataTablesResult();
		int productId = productReq.getProductId();
		try {
			result = productService.pullOffProduct(productId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setError("新增或修改商品失败");
			return result;
		}
	}
	
	/**
	 * @return 
	 * @Title: 商品搜索
	 * @Description: 
	 */
	@ApiOperation(value = "商品搜索", notes = "商品搜索", response = ProductBean.class, httpMethod = "POST")
	@ResponseBody
	@RequestMapping(value = "searchProduct", method = RequestMethod.POST)
	public  DataTablesResult searchProduct(@RequestBody ProductReq productReq)throws Exception {
		logger.info("客户端请求数据【manage/product/searchProduct】,{}", productReq.toString());
		DataTablesResult result = new DataTablesResult();
		String productName = productReq.getProductName();
		try {
			result = productService.searchProduct(productName);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setError("新增或修改商品失败");
			return result;
		}
	}
	
	/**
	 * @Title: 获取指定商品ID的所有信息
	 * @Description:
	 */
	@RequestMapping(value = "queryProductById",method = RequestMethod.POST)
    @ApiOperation(value = "根据ID获取指定商品的全部信息", notes = "根据ID获取指定商品的全部信息")
    public JSONObject queryProductById(@RequestBody ProductReq product){
    	logger.info("客户端请求数据【manage/product/queryProductById】,{}", product.toString());
    	JSONObject json = new JSONObject();
    	try {
    		json = productService.queryProductById(product);
            return json;
		} catch (Exception e) {
			e.printStackTrace();
    		json.put("success", false);
			return json;
		}
    }
	
	@ApiOperation(value = "删除商品", notes = "删除商品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = Ajax.class)
	@ResponseBody
	@RequestMapping(value = "delProduct", method = RequestMethod.POST)
	public DataTablesResult delProduct(@RequestBody ProductBean product) throws Exception {
		logger.info("客户端请求数据（manage/product/delProduct）：{}", product.toString());
		DataTablesResult result = new DataTablesResult();
		try {
			result = productService.delProduct(product);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setError("删除商品失败！");
			return result;
		}
	}
	
	/**
	 * @Title: 查询商品所属的标签
	 * @Description: 
	 */
	@ApiOperation(value = "查询商品所属的标签", notes = "查询商品所属的标签", response = TbProductTag.class, httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "productTagList", method = RequestMethod.POST)
	public DataTablesResult queryProductTag(int productId)throws Exception {
		logger.info("【manage/product/productTagList】,{}", String.valueOf(productId));
			DataTablesResult result = new DataTablesResult();
			try {
				result = productService.queryProductTag(productId);
	            return result;
			} catch (Exception e) {
				e.printStackTrace();
				result.setSuccess(false);
				return result;
			}
	}
	
	/**
	 * @Title: 获取指定分类下的商品所属的标签
	 * @Description: 
	 */
    @RequestMapping(value = "queryTypeTag",method = RequestMethod.POST)
    @ApiOperation(value = "获取指定分类下的商品所属的标签")
    public DataTablesResult queryTypeTag(int typeId){	
    	logger.info("【manage/product/queryTypeTag】,{}", String.valueOf(typeId));
    	DataTablesResult result = new DataTablesResult();
    	try {
    		result = productService.queryTypeTag(typeId);
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			return result;
		}
    }
	
    /**
     * 获取商品记录总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "count",method = RequestMethod.GET)
    @ApiOperation(value = "获取商品记录总数")
    public Result<Object> countProduct() throws Exception{
    	logger.info("【manage/product/count】,{}");
    	Result<Object> result = new Result<Object>();
    	try {
    		result = productService.countProduct();
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
    
    /**
     * 获取仓库商品记录总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "countWareProduct",method = RequestMethod.GET)
    @ApiOperation(value = "获取仓库商品记录总数")
    public Result<Object> countWareProduct() throws Exception{
    	logger.info("【manage/product/countWareProduct】,{}");
    	Result<Object> result = new Result<Object>();
    	try {
    		result = productService.countWareProduct();
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
    
    
    /** 
     * 获取所有轮播图列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "carouselList",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有轮播图列表" ,response = RelCarouselEntity.class)
    public DataTablesResult carouselList(int draw , int length)throws Exception {
    	logger.info("【manage/product/carouselList】,{}");
    	DataTablesResult result = new DataTablesResult();
    	try {
    		result = productService.carouselList(draw, length);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setError("获取所有轮播图列表失败！");
			return result;
		}
    }

    /**
     * 添加或修改轮播图
     * @param relCarousel  轮播图的实体类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addCarousel",method = RequestMethod.POST)
    @ApiOperation(value = "添加或修改轮播图")
    public Result<Object> addCarousel(@RequestBody RelCarouselEntity relCarousel)throws Exception{
    	logger.info("客户端请求数据（manage/product/addCarousel）：{}", relCarousel.toString());
    	Result<Object> result = new Result<Object>();
    	try {
    		result = productService.addCarousel(relCarousel);
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrMsg("添加或修改轮播图失败！");
			return result;
		}
    }

    /**
     * 删除轮播图
     * @param ids   id数组
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/brand/delBrand",method = RequestMethod.POST)
    @ApiOperation(value = "删除轮播图")
    public Result<Object> delBrand(@RequestBody DelBean id)throws Exception{
    	logger.info("客户端请求数据（manage/product/addCarousel）：{}", id.toString());
		Result<Object> result = new Result<Object>();
    	try {
    		int ids = id.getIds();
    		result = productService.delBrand(ids);
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrMsg("删除轮播图失败！");
			return result;
		}
    }
    
    /**
     * 获取轮播图记录总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "countCarousel",method = RequestMethod.GET)
    @ApiOperation(value = "获取轮播图记录总数")
    public Result<Object> countCarousel() throws Exception{
    	logger.info("客户端请求数据（manage/product/countCarousel）：{}");
    	Result<Object> result = new Result<Object>();
    	try {
    		result = productService.countCarousel();
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
    
    /** 
     * 获取所有优惠券列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "couponList",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有优惠券列表" ,response = SCouponEntity.class)
    public DataTablesResult couponList(int draw , int length)throws Exception {
    	logger.info("客户端请求数据（manage/product/couponList）：{}");
    	DataTablesResult result = new DataTablesResult();
    	try {
    		result = productService.couponList(draw, length);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setError("获取所有优惠券列表失败！");
			return result;
		}
    }

    /**
     * 添加或修改优惠券
     * @param sCouponEntity  优惠券的实体类
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "addCoupon",method = RequestMethod.POST)
    @ApiOperation(value = "添加或修改优惠券")
    public Result<Object> addCoupon(@RequestBody SCouponEntity sCouponEntity)throws Exception{
    	logger.info("客户端请求数据【manage/product/addCoupon】,{}", sCouponEntity.toString());
    	Result<Object> result = new Result<Object>();
    	try {
    		 result = productService.addCoupon(sCouponEntity);
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrMsg("添加或修改优惠券失败！");
			return result;
		}

    }

    /**
     * 删除优惠券
     * @param ids   id数组
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delCoupon",method = RequestMethod.POST)
    @ApiOperation(value = "删除优惠券")
    public Result<Object> delCoupon(@RequestBody DelBean id)throws Exception{
    	logger.info("客户端请求数据（manage/product/delCoupon）：{}"+id.toString());
    	Result<Object> result = new Result<Object>();
    	try {
    		int ids = id.getIds();
    		result = productService.delCoupon(ids);
            return result;
		} catch(Exception e){
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrMsg("删除优惠券失败！");
			return result;
		}
    }
    
    /**
     * 获取优惠券记录总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "countCoupon",method = RequestMethod.GET)
    @ApiOperation(value = "获取优惠券记录总数")
    public Result<Object> countCoupon() throws Exception{
    	logger.info("客户端请求数据（manage/product/countCoupon）：{}");
    	Result<Object> result = new Result<Object>();
    	try {
    		result = productService.countCoupon();
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
}
