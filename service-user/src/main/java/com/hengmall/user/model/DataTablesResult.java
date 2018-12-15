package com.hengmall.user.model;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wuhengbin on 2018/6/20.
 */
public class DataTablesResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "请求是否成功")
    private Boolean success;
	@ApiModelProperty(value = "页码")
    private int draw;
	@ApiModelProperty(value = "数据总数量")
    private int recordsTotal;
	@ApiModelProperty(value = "数据过滤后数据数")
    private int recordsFiltered;
	@ApiModelProperty(value = "请求错误的内容")
    private String error;
	@ApiModelProperty(value = "请求的返回的数据")
    private List<?> data;

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
