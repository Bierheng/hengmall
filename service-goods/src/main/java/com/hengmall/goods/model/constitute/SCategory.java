package com.hengmall.goods.model.constitute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品分类模块 实体类
 */
@Data
@ApiModel
public class SCategory implements Serializable {

    @ApiModelProperty(value = "主键ID，用于后台交互")
    private int id;
    @ApiModelProperty(value = "类别名称")
    private String name;
    @ApiModelProperty(value = "图标，小图，嵌入在首页")
    private String icon1;
    @ApiModelProperty(value = "店铺ID")
    private int shops_id;
}
