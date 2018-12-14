package com.hengmall.goods.model.api;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class AppraiseListReq extends ApiRequest {
    
    @ApiModelProperty(value = "评价数组")
    List<AppraiseReq> appraiseList;
    @ApiModelProperty(value = "token")
    String token;
}
