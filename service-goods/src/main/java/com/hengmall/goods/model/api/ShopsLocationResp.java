package com.hengmall.goods.model.api;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShopsLocationResp {
	
	@ApiModelProperty(value = "店铺ID")
    private Integer shops_id;
	@ApiModelProperty(value = "店铺名称")
    private String shops_name;
	@ApiModelProperty(value = "店铺头像")
    private String avatar_url;
	@ApiModelProperty(value = "订单列表")
    private List<ShoppingListResp> shoppingList;

}