package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hengmall.user.model.record.ProfitListRequest;
import com.hengmall.user.model.record.ProfitResponse;

/**
 * 流水记录
 * @author Administrator
 *
 */
@Mapper
public interface TbProfitDao {

	/**
	 * 分销收益记录列表
	 * @param configListRequest
	 * @return
	 */
	@Select("<script>"
			+ "SELECT "
			+ "a.profit_type AS 'profitType',"
			+ "a.profit_money AS 'profitMoney',"
			+ "u.username AS 'userName',"
			+ "a.create_time AS 'cretateTime'"
			+ " FROM "
			+ "tb_profit a"
			+ " LEFT JOIN ${platform}.tb_user u ON a.user_id = u.id"
			+ " order by a.create_time desc"
		  + "</script>")
	List<ProfitResponse> profitList(ProfitListRequest profitListRequest);

}
