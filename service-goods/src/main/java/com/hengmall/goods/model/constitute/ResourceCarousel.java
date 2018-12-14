package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 资源文件图片或视频
 */
@Data
@ApiModel
public class ResourceCarousel implements Serializable {

    @ApiModelProperty(value = "资源类型；1：图片，2：视频")
    int type;
    @ApiModelProperty(value = "图片地址")
    String path;
}
