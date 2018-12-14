package com.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.dao.TbProfitDao;
import com.server.entity.persistence.Page;
import com.server.entity.record.ProfitListRequest;
import com.server.entity.record.ProfitResponse;
import com.server.service.TbProfitService;

/**
 * 流水记录
 * @author Administrator
 *
 */
@Service
public class TbProfitServiceImpl implements TbProfitService {

	@Autowired
	private TbProfitDao tbProfitDao;
	
	/**
	 * 分销收益记录列表
	 */
	@Override
	public Page<ProfitResponse> profitList(Page<ProfitResponse> page, ProfitListRequest profitListRequest) {
		PageHelper.startPage(profitListRequest.getPage(),profitListRequest.getListPage());
		List<ProfitResponse> list = tbProfitDao.profitList(profitListRequest);
		page.setData(list);
		page.setCount(new PageInfo<>(list).getTotal());
		return page;
	}
}
