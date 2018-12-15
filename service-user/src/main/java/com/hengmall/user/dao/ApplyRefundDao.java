package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.ApplyRefund;
import com.hengmall.user.model.order.DispatchRequest;
import com.hengmall.user.model.order.DispatchResponse;
import com.hengmall.user.model.order.OrderDistributionRequest;
import com.hengmall.user.model.order.OrderDistributionResponse;
import com.hengmall.user.model.order.OrderLiveRequest;
import com.hengmall.user.model.order.OrderLiveResponse;
import com.hengmall.user.model.order.SaveUserRequest;
import com.hengmall.user.model.order.UsersRequest;
import com.hengmall.user.model.order.UsersResponse;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface ApplyRefundDao {
	
	/**
	 * @Title: 查询退款订单列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select id, user_id,name,phone,refund_reasons,status,refuse_reason,created_time,updated_time,approval_user,approval_time,shops_id,order_id from apply_refund ",
			"order by created_time desc", "</script>" })
	List<ApplyRefund> refundList();
       
	// 根据主键id修改数据
	@Update("update apply_refund set `status` = #{edit.status},refuse_reason =#{edit.refuse_reason},updated_time =now(),approval_user=#{edit.approval_user},approval_time=now()"
	+"where id = #{edit.id}")
	void updateById(@Param("edit") ApplyRefund applyRefund);

	/**
	 * @Title: 查询退款订单数据
	 * @Description: TODO
	 */
	@Select({ "<script>", "select * from apply_refund where id=#{id}",
			"order by created_time desc", "</script>" })
	ApplyRefund getRefundById(@Param("id") int id);
	
	/**
	 * 获取退款订单记录总数
	 * @return
	 */
	@Select("select count(*) from apply_refund")
	int countRefund();
	
	
	/**
	 * 分销收益订单列表
	 * @param orderDistributionRequest
	 * @return
	 */
	@Select("<script>"
			+ "SELECT "
				+ "a.`id` AS 'id',"
				+ "a.insert_date AS 'insertDate',"
				+ "a.pay AS 'pay',"
				+ "a.status AS 'status',"
				+ "a.head_portrait AS 'headPortrait',"
				+ "a.nickname AS 'nickname',"
				+ "a.profit AS 'profit',"
				+ "a.order_id AS 'orderId',"
				+ "s.shops_name AS 'shopsName'"
			+ " FROM "
				+ "s_order_distribution a"
			+ " LEFT JOIN shops_location s ON a.shops_id = s.id"
			+ " LEFT JOIN ${platform}.tb_user u ON s.user_id = u.user_id"
			+ "<where>"
				+ "${sqlMap.sql}"
			+ "</where>"
			+ "order by a.insert_date DESC"
		  + "</script>")
	List<OrderDistributionResponse> distributionList(OrderDistributionRequest orderDistributionRequest);
	
	
	/**
	 * 直播推销员订单列表
	 * @param orderLiveRequest
	 * @return
	 */
	@Select("<script>"
			+ "SELECT "
				+ "a.id AS 'id',"
				+ "a.product_name AS 'productName',"
				+ "a.status AS 'status',"
				+ "a.insert_date AS 'insertDate',"
				+ "a.money AS 'money',"
				+ "a.order_id AS 'orderId',"
				+ "u.nickname AS 'userName'"
			+ " FROM "
				+ "s_order_live a"
				+ " LEFT JOIN users u ON a.user_id = u.id"
				+ " LEFT JOIN ${platform}.tb_user tu ON a.user_id = tu.user_id"
				+ "<where>"
					+ "${sqlMap.sql}"
				+ "</where>"
				+ "order by a.insert_date DESC"
		  + "</script>")
	List<OrderLiveResponse> liveList(OrderLiveRequest orderLiveRequest);
	
	
	/**
	 * 派单管理列表
	 * @param dispatchRequest
	 * @return
	 */
	@Select("<script>"
			+ "SELECT "
			+ "a.id AS 'id',"
			+ "s.shops_name AS 'shopsName',"
			+ "a.product_name AS 'productName',"
			+ "a.price AS 'price',"
			+ "a.num AS 'num',"
			+ "a.status AS 'status',"
			+ "a.created_time AS 'createdTime',"
			+ "a.tracking_num AS 'trackingNum',"
			+ "a.express AS 'express',"
			+ "a.pay_no AS 'payNo',"
			+ "a.publish_status AS 'publishStatus',"
			+ "a.dispatch_status AS 'dispatchStatus',"
			+ "u.id AS 'usersResponse.id',"
			+ "u.username AS 'usersResponse.username'"
			+ " FROM "
			+ "s_order_extra a"
			+ " LEFT JOIN shops_location s ON a.shops_id = s.id"
			+ " LEFT JOIN s_extra_user e ON a.id = e.order_extra_id"
			+ " LEFT JOIN ${platform}.tb_user u ON e.platform_user_id = u.id"
			+ "<where>"
				+ "a.publish_status = 2"
			+ "</where>"
			+ "order by a.created_time desc"
		  + "</script>")
	List<DispatchResponse> dispatchList(DispatchRequest dispatchRequest);
	
	
	
	/**
	 * 派单员列表
	 * @param usersRequest
	 * @return
	 */
	@Select("<script>"
			+ "SELECT "
			+ "a.id AS 'id',"
			+ "a.username AS 'username'"
			+ " FROM "
			+ "${platform}.tb_user a"
			+ " WHERE "
			+ "a.role_id = 10"
			+ " order by a.username"
			+ "</script>")
	List<UsersResponse> usersList(UsersRequest usersRequest);
	
	
	/**
	 * 检查订单与用户是否存在
	 * @param id
	 * @return
	 */
	@Select("SELECT count(a.id) FROM s_order_extra a WHERE a.id = #{id}")
	int inspectOrder(String id);
	@Select("SELECT count(a.id) FROM ${platform}.tb_user a WHERE a.id = #{id}")
	int inspectUser(String id);
	
	
	/**
	 * 派单
	 * @param saveUserRequest
	 * @return
	 */
	@Insert("INSERT INTO s_extra_user (order_extra_id,platform_user_id) VALUE (#{orderId},#{userId})")
	int saveUser(SaveUserRequest saveUserRequest);
	
	/**
	 * 改变状态为  已派单
	 * @param id
	 * @return
	 */
	@Update("UPDATE s_order_extra SET dispatch_status = 2 WHERE id = #{id}")
	int saveState(String id);
	
	
	/**
	 * 当前用户是否在线
	 * @param id
	 * @return
	 */
	@Select("SELECT onLine FROM ${platform}.tb_user WHERE id = #{id}")
	int isOnLine(String id);
}
