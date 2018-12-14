package com.server.service;

import com.server.entity.persistence.Page;
import com.server.entity.record.ProfitListRequest;
import com.server.entity.record.ProfitResponse;

/**
 * 流水记录
 * @author Administrator
 *
 */
public interface TbProfitService {

	/**
	 * 分销收益记录列表
	 * @param page
	 * @param configListRequest
	 * @return
	 */
	Page<ProfitResponse> profitList(Page<ProfitResponse> page,ProfitListRequest profitListRequest);
	
}
