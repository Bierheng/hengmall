package com.hengmall.goods.model.manage;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: 分页请求参数 
 * @Description: TODO
 * @author Administrator
 * @date 2018年5月28日 下午4:57:30 
 */
public class PageReq
{
	@ApiModelProperty(value = "当前页数")
	protected int page;
	@ApiModelProperty(value = "每页大小")
	protected int pagesize;

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getPagesize()
	{
		return pagesize;
	}

	public void setPagesize(int pagesize)
	{
		this.pagesize = pagesize;
	}
}
