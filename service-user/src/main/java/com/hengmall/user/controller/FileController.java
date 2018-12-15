package com.hengmall.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hengmall.user.model.api.Ajax;
import com.hengmall.user.model.file.UploadFileReq;
import com.hengmall.user.model.file.UploadFileResq;
import com.hengmall.user.model.manage.product.ListProductResp;
import com.hengmall.user.model.manage.product.UploadProductReq;
import com.hengmall.user.model.manage.product.UploadProductResp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "文件上传", description = "文件上传")
@RestController
@RequestMapping("file/api/")
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	/**
	 * @Title: 上传文件
	 * @Description: TODO
	 */
	@ApiOperation(value = "文件上传", notes = "文件上传", response = ListProductResp.class, httpMethod = "POST", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public Ajax<UploadFileResq> uploadFile(@RequestBody(required = false) UploadFileReq uploadFileReq) {
		logger.info("【file-api-uploadFile】,{}", String.valueOf(uploadFileReq));

		UploadFileResq uploadFileResq = new UploadFileResq();

		logger.info("【file-api-uploadFile】,{}", uploadFileResq.toString());
		return new Ajax<UploadFileResq>(uploadFileResq);
	}

	/**
	 * @Title: 上传商品
	 * @Description: TODO
	 */
	@ApiOperation(value = "上传商品 ", notes = "上传商品 ", response = ListProductResp.class, httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "uploadProduct", method = RequestMethod.POST)
	public Ajax<UploadProductResp> uploadProduct(@RequestBody UploadProductReq uploadProductReq) {
		logger.info("【manage/product/uploadProduct】,{}", uploadProductReq.toString());
//		String name = uploadProductReq.getName();
		// int pagesize = listProductReq.getPagesize();

		// Map<String, Object> param = new HashMap<>();
		// param.put("page", page);
		// param.put("pagesize", pagesize);
		//
		// List<SProductEntity> productList = sProductDao.productList(param);
		// List<ProductBean> list = new LinkedList<>();
		// if (productList != null && productList.size() > 0)
		// {
		// for (SProductEntity sProductEntity : productList)
		// {
		// ProductBean productBean = new ProductBean();
		// productBean.setId(sProductEntity.getId());
		// productBean.setName(sProductEntity.getName());
		// productBean.setHeadimg(sProductEntity.getHeadimg());
		// productBean.setPrice(sProductEntity.getPrice());
		// productBean.setStock(sProductEntity.getStock());
		// productBean.setAllowrefund(sProductEntity.getAllowrefund());
		// productBean.setStatus(sProductEntity.getStatus()); // 状态：1上架，0下架
		// productBean.setAttribute(sProductEntity.getAttr()); // 尺寸大小、颜色
		// productBean.setCtime(sProductEntity.getCtime()); // 发布时间
		// list.add(productBean);
		// }
		// }
		UploadProductResp uploadProductResp = new UploadProductResp();
		// productListResp.setProductList(list);
		// productListResp.setTotal(param.containsKey("total") ?
		// Integer.parseInt(String.valueOf(param.get("total"))) : 0);
		//
		logger.info("【manage/product/uploadProduct】,{}", uploadProductResp.toString());
		return new Ajax<UploadProductResp>(uploadProductResp);
	}
}
