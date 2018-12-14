package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */
@ApiModel
public class ReceiveCouponReq extends ApiRequest{

    @ApiModelProperty(value = "优惠券主键ID")
    int id;
    @ApiModelProperty(value = "优惠券所属店铺ID")
    int shops_id;


    @Override
    public String toString() {
        return "ReceiveCouponReq{" +
                "id=" + id +
                '}'+"，"+super.toString();
    }

    @Override
    public String getToken() {
        return super.getToken();
    }

    @Override
    public void setToken(String token) {
        super.setToken(token);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public int getShops_id() {
		return shops_id;
	}

	public void setShops_id(int shops_id) {
		this.shops_id = shops_id;
	}
    
}
