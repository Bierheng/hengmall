package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel
public class DelCollection {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "需要删除的收藏夹主键ID集合")
    List<Integer> ids;
}
