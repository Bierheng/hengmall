package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel
public class CombineOrderResp {

    @ApiModelProperty(value = "正在拼单人数")
    int totalNum;
    @ApiModelProperty(value = "拼单列表")
    List<CombineOrderList> combineOrderList;

}
