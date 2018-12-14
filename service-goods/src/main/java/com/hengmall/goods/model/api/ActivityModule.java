package com.hengmall.goods.model.api;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 活动模块实体类
 */
@Data
@ApiModel
public class ActivityModule implements Serializable{

    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "1：秒杀，2：拼单")
    private int type;
}
