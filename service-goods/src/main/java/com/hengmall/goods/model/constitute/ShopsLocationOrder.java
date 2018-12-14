package com.hengmall.goods.model.constitute;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2018/5/30.
 */
@Data
@ApiModel
public class ShopsLocationOrder  implements Serializable {

    @ApiModelProperty(value = "用户名")
    private String user_name;
    @ApiModelProperty(value = "店铺名")
    private String shops_name;
    @ApiModelProperty(value = "商品名称")
    private String product_name;
    @ApiModelProperty(value = "购买位置")
    private String location;
    @ApiModelProperty(value = "创建时间")
    private String created_time;

}
