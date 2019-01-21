package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hengmall.user.model.platform.OrderDelRequest;
import com.hengmall.user.model.platform.OrderRequest;
import com.hengmall.user.model.platform.OrderResponse;
import com.hengmall.user.model.platform.OrderSaveRequest;
import com.hengmall.user.model.platform.PlatformDelRequest;
import com.hengmall.user.model.platform.PlatformLvResponse;
import com.hengmall.user.model.platform.PlatformProduct;
import com.hengmall.user.model.platform.PlatformRequest;
import com.hengmall.user.model.platform.PlatformResponse;
import com.hengmall.user.model.platform.PlatformSaveRequest;
import com.hengmall.user.model.platform.RightDelRequest;
import com.hengmall.user.model.platform.RightRequest;
import com.hengmall.user.model.platform.RightResponse;
import com.hengmall.user.model.platform.RightSaveRequest;
import com.hengmall.user.model.platform.StateDelRequest;
import com.hengmall.user.model.platform.StateRequest;
import com.hengmall.user.model.platform.StateResponse;
import com.hengmall.user.model.platform.StateSaveRequest;
import com.hengmall.user.model.platform.TagDelRequest;
import com.hengmall.user.model.platform.TagListRequest;
import com.hengmall.user.model.platform.TagListResponse;
import com.hengmall.user.model.platform.TagSaveRequest;

/**
 * 平台管理
 * @author Administrator
 *
 */
@Mapper
public interface PlatformDao {

	/**
	 * 平台模板列表
	 * @param aboutUsRequest
	 * @return
	 */
	@Select("SELECT "
				+ "a.`id` AS 'id',"
				+ "a.`content` AS 'content',"
				+ "a.`type` AS 'type',"
				+ "a.`created_time` AS 'createdTime',"
				+ "a.`order_num` AS 'orderNum',"
				+ "a.`type_id` AS 'typeId',"
				+ "a.`shops_id` AS 'shops_id',"
				+ "c.`name` AS 'typeName',"
				+ "a.`platform_ids` AS 'platformIds'"
			+ " FROM "
				+ "platform_plate a"
			+ " LEFT JOIN s_category c ON a.type_id = c.id"
			+ " ORDER BY a.`order_num`")
	List<PlatformResponse> platformList(PlatformRequest platformRequest);
	
	
	/**
	 * 平台模板列表里的商品名称
	 * @param strings
	 * @return
	 */
	@Select("<script>"
				+ "SELECT "
					+ "a.id AS 'id',"
					+ "a.name AS 'productName',"
					+ "a.headimg AS 'headimg'"
				+ " FROM s_product a WHERE a.id IN "
					+ "<foreach collection='array' item='id' open='(' close=')' separator=','>"
						+ "#{id}"
					+ "</foreach>"
		  + "</script>")
	List<PlatformProduct> plName(String[] strings);
	
	
	/**
	 * 获取一级分类列表
	 * @param platformLvRequest
	 * @return
	 */
	@Select("SELECT "
			+ "a.`id` AS 'id',"
			+ "a.`name` AS 'name'"
		+ " FROM "
			+ "s_category a"
		+ " WHERE a.level = 1")
	List<PlatformLvResponse> lvOneList();
	
	
	/**
	 * 平台模板添加
	 * @return
	 */
	@Insert("INSERT INTO "
			+ "platform_plate "
		+ "("
			+ "content,"
			+ "type,"
			+ "created_time,"
			+ "order_num,"
			+ "type_id,"
			+ "platform_ids,"
			+ "shops_id"
		+ ") "
			+ "VALUES "
		+ "("
			+ "#{content},"
			+ "#{type},"
			+ "#{updateDate},"
			+ "((SELECT t.max_ordernum FROM (SELECT MAX( order_num ) AS 'max_ordernum' FROM platform_plate ) AS t) + 10),"
			+ "#{typeId},"
			+ "#{platformIds},"
			+ "#{shops_id}"
		+ ")")
	int platformAdd(PlatformSaveRequest platformSaveRequest);
	
	/**
	 * 平台模板修改
	 * @param platformSaveRequest
	 * @return
	 */
	@Update("UPDATE platform_plate "
		   + "SET "
			   	+ "content=#{content},"
			   	+ "type=#{type},"
			   	+ "order_num=#{orderNum},"
			   	+ "type_id=#{typeId}, "
			   	+ "platform_ids=#{platformIds}, "
				+ "shops_id=#{shops_id} "
		   + " WHERE id=#{id}")
	int platformUpdate(PlatformSaveRequest platformSaveRequest);
	
	
	/**
	 * 平台模板删除
	 * @param platformSaveRequest
	 * @return
	 */
	@Delete("DELETE FROM platform_plate WHERE id=#{id}")
	int platformDel(PlatformDelRequest platformDelRequest);
	
	
	/**
	 * 产品货源地国籍列表
	 * @param stateRequest
	 * @return
	 */
	@Select("SELECT "
			+ "a.`id` AS 'id',"
			+ "a.`state_url` AS 'stateUrl',"
			+ "a.`state_name` AS 'stateName',"
			+ "a.`created_time` AS 'createdTime'"
		+ " FROM "
			+ "tb_state a")
	List<StateResponse> stateList(StateRequest stateRequest);
	
	
	/**
	 * 产品货源地国籍添加
	 * @param stateSave
	 * @return
	 */
	@Insert("INSERT INTO tb_state (state_url,state_name,created_time) VALUES (#{stateUrl},#{stateName},#{updateDate})")
	int stateAdd(StateSaveRequest stateSave);
	
	/**
	 * 产品货源地国籍删除
	 * @return
	 */
	@Delete("DELETE FROM tb_state WHERE id=#{id}")
	int stateDel(StateDelRequest stateDelRequest);
	
	/**
	 * 产品货源地国籍修改
	 * @param stateSave
	 * @return
	 */
	@Update("UPDATE tb_state SET state_url=#{stateUrl},state_name=#{stateName} WHERE id=#{id}")
	int stateUpdate(StateSaveRequest stateSave);
	
	
	/**
	 * 店铺资质列表
	 * @param rightRequest
	 * @return
	 */
	@Select("SELECT "
			+ "a.`id` AS 'id',"
			+ "a.`url` AS 'url',"
			+ "a.`name` AS 'name',"
			+ "a.`created_time` AS 'createdTime',"
			+ "a.`content` AS 'content',"
			+ "a.`shops_id` AS 'shopsId',"
			+ "l.`shops_name` AS 'shopsName'"
		+ " FROM "
			+ "shops_right a"
			+ " LEFT JOIN shops_location l ON a.shops_id = l.id")
	List<RightResponse> rightList(RightRequest rightRequest);
	
	
	/**
	 * 店铺资质添加
	 * @param rightSaveRequest
	 * @return
	 */
	@Insert("INSERT INTO shops_right (url,name,created_time,content,shops_id) VALUES (#{url},#{name},#{updateDate},#{content},#{shopsId})")
	int rightAdd(RightSaveRequest rightSaveRequest);
	
	
	/**
	 * 店铺资质修改
	 * @param rightSaveRequest
	 * @return
	 */
	@Update("UPDATE shops_right SET url=#{url},name=#{name},content=#{content},shops_id=#{shopsId} WHERE id=#{id}")
	int rightUpdate(RightSaveRequest rightSaveRequest);
	
	
	/**
	 * 店铺资质删除
	 * @return
	 */
	@Delete("DELETE FROM shops_right WHERE id=#{id}")
	int rightDel(RightDelRequest rightDelRequest);
	
	
	/**
	 * 平台标签列表
	 * @param tagListRequest
	 * @return
	 */
	@Select("<script>"
			+ "SELECT "
				+ "a.id AS 'id',"
				+ "a.url AS 'url',"
				+ "a.name AS 'name',"
				+ "a.flag AS 'flag'"
			+ " FROM "
				+ "platform_tag a"
			+"</script>")
	List<TagListResponse> tagList(TagListRequest tagListRequest);
	
	
	/**
	 * 平台标签添加
	 * @param tagSaveRequest
	 * @return
	 */
	@Insert("INSERT INTO platform_tag (url,name,created_time) VALUES (#{url},#{name},#{updateDate})")
	int tagAdd(TagSaveRequest tagSaveRequest);
	
	
	/**
	 * 平台标签修改
	 * @param tagSaveRequest
	 * @return
	 */
	@Update("UPDATE platform_tag SET url = #{url},name = #{name},flag = #{flag} WHERE id = #{id}")
	int tagUpdate(TagSaveRequest tagSaveRequest);
	
	
	/**
	 * 平台标签删除
	 * @param tagDelRequest
	 * @return
	 */
	@Delete("DELETE FROM platform_tag WHERE id = #{id}")
	int tagDel(TagDelRequest tagDelRequest);
	
	
	/**
	 * 定位地图购物信息弹框列表
	 * @param orderRequest
	 * @return
	 */
	@Select("<script>"
				+ " SELECT "
					+ "a.id AS 'id',"
					+ "a.user_name AS 'userName',"
					+ "a.location AS 'location',"
					+ "a.product_name AS 'productName'"
				+ " FROM "
					+ "location_order a"
		  + "</script>")
	List<OrderResponse> orderList(OrderRequest orderRequest);
	
	
	/**
	 * 定位地图购物信息弹框添加
	 * @param orderSaveRequest
	 * @return
	 */
	@Insert("INSERT INTO location_order (user_name,location,product_name,created_time) VALUES (#{userName},#{location},#{productName},#{updateDate})")
	int orderAdd(OrderSaveRequest orderSaveRequest);
	
	
	/**
	 * 定位地图购物信息弹框修改
	 * @param orderSaveRequest
	 * @return
	 */
	@Update("UPDATE location_order SET user_name = #{userName},location = #{location},product_name = #{productName} WHERE id = #{id}")
	int orderUpdate(OrderSaveRequest orderSaveRequest);
	
	
	/**
	 * 定位地图购物信息弹框删除
	 * @param orderDelRequest
	 * @return
	 */
	@Delete("DELETE FROM location_order WHERE id = #{id}")
	int orderDel(OrderDelRequest orderDelRequest);
}
