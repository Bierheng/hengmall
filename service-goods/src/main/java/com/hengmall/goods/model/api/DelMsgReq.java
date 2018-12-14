package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */
@Data
@ApiModel
public class DelMsgReq {

    @ApiModelProperty(value = "token认证")
    String token;
    @ApiModelProperty(value = "需要删除的消息主键ID集合")
    List<Integer> ids;
}
