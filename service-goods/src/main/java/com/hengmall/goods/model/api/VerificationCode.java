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
public class VerificationCode implements Serializable{

    @ApiModelProperty(value = "手机号")
    private String phone;

}
