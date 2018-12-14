package com.hengmall.goods.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel
public class CategoryOrderReq extends PagingReq {

    @ApiModelProperty("请求ID")
    private int id;

    @ApiModelProperty(value = "排序标识；默认1：综合，2：新品，3：销量，4：价格")
    int orderStatus;
    
    @Override
    public String toString() {
        return "CategoryOrderReq{" +
                "id=" + id +
                '}'+"，"+super.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getToken() {
        return super.getToken();
    }

    @Override
    public void setToken(String token) {
        super.setToken(token);
    }

    @Override
    public int getPage() {
        return super.getPage();
    }

    @Override
    public void setPage(int page) {
        super.setPage(page);
    }

    @Override
    public int getLimit() {
        return super.getLimit();
    }

    @Override
    public void setLimit(int limit) {
        super.setLimit(limit);
    }

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
    
}
