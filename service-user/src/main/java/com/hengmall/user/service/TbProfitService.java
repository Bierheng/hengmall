package com.hengmall.user.service;

import com.hengmall.user.model.persistence.Page;
import com.hengmall.user.model.record.ProfitListRequest;
import com.hengmall.user.model.record.ProfitResponse;

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
