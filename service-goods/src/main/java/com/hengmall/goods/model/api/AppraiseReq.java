package com.hengmall.goods.model.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class AppraiseReq extends ApiRequest {

    @ApiModelProperty(value = "需要评价的商品ID")
    int id;
    @ApiModelProperty(value = "订单主键ID")
    int orderId;
    @ApiModelProperty(value = "描述相符")
    int matching;
    @ApiModelProperty(value = "发货速度")
    int deliverySpeed;
    @ApiModelProperty(value = "商品星级评分")
    int score;
    @ApiModelProperty(value = "评价内容")
    String context;
    @ApiModelProperty(value = "服务质量星级平分")
    int serviceQuality;
    @ApiModelProperty(value = "配送员服务态度星级平分")
    int serviceAttitude;
    @ApiModelProperty(value = "文件上传后的资源地址")
    List<String> accessUrls;
}
