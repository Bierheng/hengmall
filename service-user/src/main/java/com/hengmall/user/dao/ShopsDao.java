package com.server.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Repository;

import com.server.entity.SOrderEntity;
import com.server.entity.ShopsLocation;
import com.server.entity.ShopsTypeBean;
import com.server.entity.ShopsValue;
import com.server.entity.TbShopsType;
import com.server.entity.TbShopsTypeAddReq;
import com.server.entity.Users;
import com.server.entity.common.ProductResponse;
import com.server.entity.manage.product.ProductReq;
import com.server.entity.manage.product.SProductBean;
import com.server.entity.shops.AddShopsRequest;
import com.server.entity.shops.AppraiseDelRequest;
import com.server.entity.shops.AppraiseListRequest;
import com.server.entity.shops.AppraiseListResponse;
import com.server.entity.shops.AppraiseSubRequest;
import com.server.entity.shops.CarouselShopsDelRequest;
import com.server.entity.shops.CarouselShopsListRequest;
import com.server.entity.shops.CarouselShopsListResponse;
import com.server.entity.shops.CarouselShopsSaveRequest;
import com.server.entity.shops.SearchShopsProductRequest;
import com.server.entity.shops.ShopsAddRequest;
import com.server.entity.shops.ShopsAttentionRequest;
import com.server.entity.shops.ShopsAttentionResponse;
import com.server.entity.shops.ShopsDelRequest;
import com.server.entity.shops.ShopsOrderRequest;
import com.server.entity.shops.ShopsOrderResponse;
import com.server.entity.shops.ShopsOrderSaveRequest;
import com.server.entity.shops.ShopsPlateDelRequest;
import com.server.entity.shops.ShopsPlateRequest;
import com.server.entity.shops.ShopsPlateResponse;
import com.server.entity.shops.ShopsPlateSaveRequest;
import com.server.entity.shops.ShopsPlateproductAddRequest;
import com.server.entity.shops.ShopsPlateproductDelRequest;
import com.server.entity.shops.ShopsPlateproductRequest;
import com.server.entity.shops.ShopsPlateproductResponse;
import com.server.entity.shops.ShopsProductListRequest;
import com.server.entity.shops.ShopsProductListResponse;
import com.server.entity.shops.ShopsProductRequest;
import com.server.entity.shops.ShopsProductResponse;
import com.server.entity.shops.ShopsRequest;
import com.server.entity.shops.ShopsUserRequest;
import com.server.entity.shops.ShopsUserResponse;
import com.server.entity.shops.TbShopsOrderRequest;
import com.server.entity.shops.TbShopsOrderResponse;
import com.server.entity.shops.UpdateShopsRequest;
import com.server.entity.shops.examine.ConfirmRequest;
import com.server.entity.shops.examine.ExamineListRequest;
import com.server.entity.shops.examine.ExamineRequest;
import com.server.entity.shops.examine.ExamineResponse;
import com.server.entity.shops.examine.operation.CarouselShopsEntity;
import com.server.entity.shops.examine.operation.ProductShopsEntity;
import com.server.entity.shops.examine.operation.ShopsCommendEntity;
import com.server.entity.shops.examine.operation.ShopsEntity;
import com.server.entity.shops.shopEntity.ShopsResponse;
import com.server.entity.tigLibrary.TigLibraryAddReq;

@Repository
public interface ShopsDao {

	/**
	 * @Title: 通过店铺主用户查询他所有的订单，这里需要注意子订单的问题，目前每个订单都是拆开来实现的
	 * @Description: 
	 */
	  @Select(
	  "select * from tb_shops_order  a left join s_order b on a.order_id = b.id where a.user_id = #{userId}") 
	  List<SOrderEntity> queryByUserId(@Param("userId") int userId);
	 
		/**
		 * 店铺主用户查询他所有的订单总数
		 * @return
		 */
		@Select("select count(*) from tb_shops_order where user_id = #{userId} ")
		int countShopsOrder(@Param("userId") int userId);
		
	  /**
	  * @Title: 通过店铺主用户查询他所有的会员
	  * @Description: 
	  */
	  @Select(
	  "select * from tb_shops_member  a left join users b on a.shops_id = b.id where a.shops_id = #{userId}") 
	  List<Users> queryMemberByUserId(@Param("userId") int userId);
	  
	  /**
	  * @Title: 通过店铺主用户查询他所有的商品
	  * @Description: 
	  */
	  @Select(
	  "select * from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{userId}") 
	  List<SProductBean> queryProductByUserId(@Param("userId") int userId);
	  
	  /**
	  * @Title: 通过店铺id查询他所有的标签
	  * @Description: 
	  */
	  @Select(
	  "select id,parent_id,name,icon,level,type_id,shops_id,type_ids as type_idsArr from tb_shops_type where shops_id = #{shops_id} ") 
	  List<TbShopsType> queryTypeByUserId(@Param("shops_id") int shops_id);
	  
	  /**
		 * 店铺标签库添加
		 * @param tigLibraryAddReq
		 * @return
		 */
		@Insert("<script>"
				+ "INSERT INTO"
				+ "	tb_shops_type"
				+ " ("
					+ "parent_id,"
					+ "name,"
					+ "icon,"
					+ "level,"
					+ "type_id,"
					+ "shops_id,"
					+ "type_ids"
				+ ") VALUES ("
					+ "#{pid},"
					+ "#{name},"
					+ "#{icon},"
					+ "(SELECT IFNULL((SELECT t.`level` FROM tig_library t WHERE t.id = #{pid}),0)+1) ,"
					+ "#{type_id},"
					+ "#{shops_id},"
					+ "#{type_idsArr}"
				+ ")"
			  + "</script>")
		@Options(useGeneratedKeys = true, keyProperty = "idT", keyColumn = "idT")
		int tbShopsTypeAdd(TbShopsTypeAddReq tbShopsTypeAddReq);
		
		/**
		 * 标签库修改
		 * @param tigLibraryAddReq
		 * @return
		 */
		@Update("<script>"
				+ "UPDATE tb_shops_type"
				+ " SET "
				+ "parent_id = #{pid},"
				+ "name = #{name},"
				+ "icon = #{icon},"
				+ "level = (SELECT a.lv + 1 FROM ((SELECT IFNULL( ( SELECT t.`level` FROM tig_library t WHERE t.id = #{pid} ), 0 )  AS 'lv' )) a) ,"
				+ "type_id = #{type_id},"
				+ "type_ids = #{type_idsArr},"
				+ "shops_id = #{shops_id} "
				+ " WHERE id = #{id}"
			  + "</script>")
		int tbShopsTypeUpdate(TbShopsTypeAddReq tbShopsTypeAddReq);
		
		/**
		 * 检查标签库是否存在
		 * @param id
		 * @return
		 */
		@Select("SELECT id FROM tb_shops_type WHERE id = #{id}")
		String query(int id);
		
		  /**
		  * @Title: 通过店铺id查询标签
		  * @Description: 
		  */
		  @Select(
		  "select id,parent_id,name,icon,level,type_id,shops_id,type_ids as type_idsArr from tb_shops_type where id = #{id} ") 
		  TbShopsType queryTypeById(@Param("id") int id);
		
		/**
		 * 标签库删除
		 * @param tigLibraryDelReq
		 * @return
		 */
		@Delete("<script>DELETE FROM tb_shops_type "
				+ "<where>"
					+ "id IN"
					+ "<foreach collection='childIds' item='childId' open='(' close=')' separator=','>"
						+ "#{childId}"
					+ "</foreach>"
				+ "</where>"
				+ "</script>")
		int tbShopsTypeDel(@Param("childIds") Set<String> childIds);
	  
	  /**
	  * @Title: 通过店铺主用户查询他所有的爆款商品
	  * @Description: 
	  */
	  @Select(
	  "select * from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{userId} order by b.salesvolume") 
	  List<SProductBean> queryProductByUserIdForsale(@Param("userId") int userId);
	  
	  /**
	  * @Title: 通过店铺主用户查询他所有的爆商品(默认1：综合，2：销量，3：价格升序,4：价格降序)
	  * @Description: 
	  */
	  @Select(
	  "select b.id as productId ,b.name as productName ,b.headimg as url ,b.price/100 as price from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{userId} order by b."+"${order_desc} desc") 
	  List<ProductReq> queryProductByOrderDesc(@Param("userId") int userId,@Param("order_desc") String order_desc);
	 
	  /**
	  * @Title: 通过店铺主用户查询他所有的爆商品(4：价格降序)
	  * @Description: 
	  */
	  @Select(
	  "select b.id as productId ,b.name as productName ,b.headimg as url ,b.price/100 as price from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{userId} order by b."+"${order_desc} asc") 
	  List<ProductReq> queryProductByOrderDesc2(@Param("userId") int userId,@Param("order_desc") String order_desc);
	  
	  /**
	  * @Title: 通过店铺主用户查询他所有的爆商品(默认1：综合，2：销量，3：价格升序,4：价格降序) 带有分类关键词
	  * @Description: 
	  */
	  @Select(
	  "select b.id as productId ,b.name as productName ,b.headimg as url ,b.price/100 as price from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{userId} and a.type_id = #{typeId} order by b."+"${order_desc} desc") 
	  List<ProductReq> queryProductByOrderDesc3(@Param("userId") int userId,@Param("order_desc") String order_desc,@Param("typeId") int typeId);
	  
	  /**
	  * @Title: 通过店铺主用户查询他所有的爆商品(4：价格降序) 带有分类关键词
	  * @Description: 
	  */
	  @Select(
	  "select b.id as productId ,b.name as productName ,b.headimg as url ,b.price/100 as price from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{userId} and a.type_id = #{typeId} order by b."+"${order_desc} asc") 
	  List<ProductReq> queryProductByOrderDesc4(@Param("userId") int userId,@Param("order_desc") String order_desc,@Param("typeId") int typeId);
	  
	  /**
	  * @Title: 通过店铺主用户查询他所有能显示的商品分类
	  * @Description: 
	  */
	  @Select("select a.type_id as typeId , b.name as typeName ,b.icon1 as url,a.shops_id as shopsId FROM tb_shops_type a LEFT JOIN s_category b ON a.type_id = b.id"
	  		+ "  WHERE shops_id = #{userId} and type_id in (select DISTINCT type_id from tb_shops_product a left "
	  		+ "join s_product b on a.product_id = b.id where a.shops_id = #{userId} order by b.salesvolume) ") 
	  List<ShopsTypeBean> queryProductTypeByUserId(@Param("userId") int userId);
	  
	/**
	 * @Title: 通过店铺主用户查询他所有的商品总数
	 * @Description: 
	 */
	  @Select( "select count(*) from tb_shops_product where shops_id = #{userId}") 
	  int countProductByUserId(@Param("userId") int userId);
	 
	/**
	 * 店铺主用户查询他所有的会员总数
	 * @return
	 */
	@Select("select count(*) from tb_shops_member where shops_id = #{userId} ")
	int countMemberOrder(@Param("userId") int userId);
	
	/**
	 * @Title: 通过店铺主用户查询他所有佣金
	 * @Description: 
	 */
	  @Select(
	  "select * from tb_shops_value where shops_id = #{userId}") 
	  List<ShopsValue> queryValueByUserId(@Param("userId") int userId);
			
	/**
	 * @Title: 通过店铺主用户查询他所有佣金
	 * @Description: 
	 */
	  @Select(
	  "select count(value) from tb_shops_value where shops_id = #{userId}") 
	  int countValueByUserId(@Param("userId") int userId);
	  
	/**
	 * @Title: 查询店铺信息
	 * @Description: 
	 */
	  @Select(
	  "select * from shops_location where user_id = #{userId}") 
	  ShopsLocation queryShopsByUserId(@Param("userId") int userId);
	  
	/**
	 * @Title: 查询粉丝数
	 * @Description: 
	 */
	  @Select(
	  "select COUNT(*) from shops_attention where user_id = #{id}") 
	  int queryShopsFans(@Param("id") Integer id);
	  
	/**
	 * @Title: 判断是否已关注
	 * @Description: 
	 */
	  @Select(
	  "select COUNT(*) from shops_attention where shops_id = #{id} and user_id = #{userId}") 
	 int queryIsShopsFans(@Param("id") Integer id,@Param("userId") int userId);
	  
	  /**
	   * 根据分类ID查询下属的商品List
	   * @param storeId
	   * @param typeList
	   * @return
	   */
	  @Select(
	  "select b.id as productId ,b.name as productName ,b.headimg as url ,b.price/100 as price "
	  + "from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{storeId} and a.type_id = #{typeId}") 
	 List<ProductReq> queryShopsTypeList(@Param("storeId") int storeId,@Param("typeId") int typeId);
	  
	  
	  /**
	   * 获取店铺列表
	   * @param userId
	   * @return
	   */
	  @Select("<script>"
	  		+ "SELECT "
	  			+ "a.`id` AS \"id\","
	  			+ "a.`address` AS \"address\","
	  			+ "a.`created_time` AS \"createdTime\","
	  			+ "a.`user_id` AS \"userId\","
	  			+ "a.`lat` AS \"lat\","
	  			+ "a.`lng` AS \"lng\","
	  			+ "a.`shops_name` AS \"shopsName\","
	  			+ "a.`avatar_url` AS \"avatarUrl\","
	  			+ "a.`national` AS \"national\","
	  			+ "a.`fans_num` AS \"fansNum\","
	  			+ "a.`describe` AS \"describe\","
	  			+ "a.`state` AS \"state\""
	  		+" FROM "
	  			+"shops_location a"
	  			+ " LEFT JOIN ${platform}.tb_user u ON a.`user_id` = u.`user_id`"
	  		+ " WHERE "
	  			+ "a.state = #{state}"
	  			+ "<if test='shopsId != null and shopsId != \"\"'>"
	  				+"AND a.id = #{shopsId}"
	  			+ "</if>"
	  			+ "${sqlMap.sql}"
	  	+ "</script>")
	  List<ShopsResponse> shopList(ShopsRequest shopsRequest);
	  
	  
	  /**
	   * 获取店铺粉丝列表
	   * @param shopsRequest
	   * @return
	   */
	  @Select("<script>"
	  		+ "SELECT "
	  			+ "l.shops_name AS 'shopsName',"
	  			+ "u.id AS 'userId',"
	  			+ "u.nickname AS 'nickname',"
	  			+ "u.avatar_url AS 'avatarUrl',"
	  			+ "u.gender AS 'gender',"
	  			+ "u.city AS 'city',"
	  			+ "u.province AS 'province',"
	  			+ "u.country AS 'country'"
	  		+ " FROM "
	  			+ "shops_attention a"
	  			+ " LEFT JOIN shops_location l ON a.shops_id = l.id"
	  			+ " LEFT JOIN users u ON a.user_id = u.id"
	  		+ " WHERE "
	  			+ "state = #{state}"
	  			+ "<if test='shopsId != null and shopsId != \"\"'>"
	  				+"AND l.id = #{shopsId}"
	  			+ "</if>"
	  		+ "</script>")
	  List<ShopsAttentionResponse> shopAttentionList(ShopsAttentionRequest shopsUsersRequest);
	  
	  
	  @Insert("<script>"
	  			+ "INSERT "
	  			+ "INTO shops_location("
	  				+ "`address`,"
	  				+ "`created_time`,"
	  				+ "`user_id`,"
	  				+ "`lat`,"
	  				+ "`lng`,"
	  				+ "`shops_name`,"
	  				+ "`avatar_url`,"
	  				+ "`national`,"
	  				+ "`describe`"
	  				+ ") VALUES "
	  				+ "("
	  				+ "#{address},"
	  				+ "#{updateDate},"
	  				+ "#{userId},"
	  				+ "#{lat},"
	  				+ "#{lng},"
	  				+ "#{shopsName},"
	  				+ "#{avatarUrl},"
	  				+ "#{national},"
	  				+ "#{describe}"
	  				+ ")"
	  		+ "</script>")
	  int newShop(AddShopsRequest addShopsRequest);
	  
	  
	  
	  /**
	   * 修改店铺
	   * @param updateShopsRequest
	   * @return
	   */
	  @Update("<script>"
	  			+ "UPDATE shops_location"
	  			+ "<set>"
	  				+"state = 0,"
		  			+ "<if test='address != null and address != \"\"' >"
		  					+"address = #{address},"
		  			+ "</if>"
		  			+ "<if test='shopsName != null and shopsName != \"\"' >"
		  					+"shops_name = #{shopsName},"
		  			+ "</if>"
		  			+ "<if test='national != null and national != \"\"' >"
		  					+"national = #{national},"
		  			+ "</if>"
		  			+ "<if test='describe != null and describe != \"\"' >"
		  					+"`describe` = #{describe},"
		  			+ "</if>"
		  			+ "<if test='lat != null and lat != \"\"' >"
		  					+"lat = #{lat},"
		  			+ "</if>"
		  			+ "<if test='lng != null and lng != \"\"' >"
		  					+"lng = #{lng},"
		  			+ "</if>"
		  			+ "<if test='avatarUrl != null and avatarUrl != \"\"' >"
		  					+"avatar_url = #{avatarUrl},"
		  			+ "</if>"
	  			+"</set>"
		  		+"<where>"
	  				+"id = #{id}"
		  		+ "</where>"
	  		+ "</script>")
	  int updateShop(UpdateShopsRequest updateShopsRequest);
	  
	  
	  
	  /**
	   * 店铺会员列表
	   * @param shopsUserRequest
	   * @return
	   */
	  @Select("<script>"
		  		+ "SELECT "
		  			+ "l.shops_name AS 'shopsName',"
		  			+ "u.id AS 'userId',"
		  			+ "u.nickname AS 'nickname',"
		  			+ "u.avatar_url AS 'avatarUrl',"
		  			+ "u.gender AS 'gender',"
		  			+ "u.city AS 'city',"
		  			+ "u.province AS 'province',"
		  			+ "u.country AS 'country',"
		  			+ "a.created_time AS 'createdTime',"
		  			+ "a.status AS 'status'"
		  		+ " FROM "
		  			+ "tb_shops_member a"
		  			+ " LEFT JOIN shops_location l ON a.shops_id = l.id"
		  			+ " LEFT JOIN users u ON a.user_id = u.id"
		  		+ " WHERE "
		  			+ "state = #{state}"
		  			+ "<if test='shopsId != null and shopsId != \"\"'>"
		  				+"AND l.id = #{shopsId}"
		  			+ "</if>"
		  		+ "</script>")
	  List<ShopsUserResponse> shopUserList(ShopsUserRequest shopsUserRequest);

	  
	  
	  /**
	   * 店铺板块列表
	   * @param shopsPlateRequest
	   * @return
	   */
	  @Select("<script>"
		  		+ "SELECT "
		  			+ "a.id AS 'id',"
		  			+ "a.type AS 'type',"
		  			+ "a.content AS 'content',"
		  			+ "a.order_no AS 'orderNo',"
		  			+ "a.shops_id AS 'shopsId',"
		  			+ "l.shops_name AS 'shopsName'"
		  		+ " FROM "
		  			+ "tb_shops_plate a"
		  			+ " LEFT JOIN shops_location l ON a.shops_id = l.id"
		  			+ " LEFT JOIN ${platform}.tb_user u ON l.`user_id` = u.`user_id`"
		  			+ "<where>"
		  				+ "<if test='shopsName != null and shopsName != \"\"'>"
		  					+ "AND l.shops_name LIKE concat('%',#{shopsName},'%')"
		  				+ "</if>"
		  				+ "${sqlMap.sql}"
		  			+ "</where>"
		  			+" ORDER BY shopsName,orderNo"
		  		+ "</script>")
	  List<ShopsPlateResponse> shopPlateList(ShopsPlateRequest shopsPlateRequest);
	  
	  
	  /**
	   * 店铺板块商品列表
	   * @param shopsPlateproductRequest
	   * @return
	   */
	  @Select("<script>"
		  		+ "SELECT "
		  			+ "a.id AS 'id',"
		  			+ "p.name AS 'productName',"
		  			+ "p.headimg AS 'headimg',"
		  			+ "p.price AS 'price',"
		  			+ "p.stock AS 'stock',"
		  			+ "p.status AS 'status',"
		  			+ "l.shops_name AS 'shopsName'"
		  		+ " FROM "
		  			+ "tb_shops_plateproduct a"
		  			+ " LEFT JOIN s_product p ON a.product_id = p.id"
		  			+ " LEFT JOIN shops_location l ON a.shops_id = l.id"
		  			+ "<if test='plateId != null and plateId != \"\"'>"
		  			+ " WHERE "
		  				+"a.plate_id = #{plateId} and p.name is not null "
		  			+ "</if>"
		  		+ "</script>")
	  List<ShopsPlateproductResponse> shopPlateproductList(ShopsPlateproductRequest shopsPlateproductRequest);
	  
	  
	  /**
	   * 移除店铺板块商品
	   * @param shopsPlateproductDelRequest
	   * @return
	   */
	  @Delete("DELETE FROM tb_shops_plateproduct WHERE id = #{id}")
	  int shopPlateproductDel(ShopsPlateproductDelRequest shopsPlateproductDelRequest);
	  
	  
	  /**
	   * 添加店铺板块商品
	   * @param shopsPlateproductAddRequest
	   * @return
	   */
	  @Insert("<script>"
	  			+ "INSERT INTO tb_shops_plateproduct (product_id,plate_id,shops_id) VALUES"
	  			+ "<foreach collection='productIds' index='index' item='productId' separator=','>"
	  				+ "(#{productId},#{plateId},#{shopsId})"
	  			+ "</foreach>"
	  		+ "</script>")
	  int addPrpducts(ShopsPlateproductAddRequest shopsPlateproductAddRequest);
	  
	  
	  /**
	   * 获取商品列表
	   * @return
	   */
	  @Select("<script>"
	  			+ "SELECT "
	  				+"a.`id` AS 'id',"
	  				+ "a.`name` AS 'name',"
	  				+ "a.`headimg` AS 'headimg',"
	  				+ "a.`attribute` AS 'attribute',"
	  				+ "a.`price` AS 'price',"
	  				+ "a.`stock` AS 'stock',"
	  				+ "a.`status` AS 'status',"
	  				+ "a.`ctime` AS 'ctime',"
	  				+ "a.`description` AS 'description',"
	  				+ "(SELECT t.id FROM tb_shops_product t WHERE t.shops_id= #{shopsId} AND t.product_id = a.id) AS 'tbId'"
	  			+ " FROM "
	  				+ "s_product a"
	  			+ "<where>"
	  				+ "<choose>"
	  					+ "<when test='plateId != null and plateId != \"\"'>"
	  							+"a.id NOT IN(SELECT product_id FROM tb_shops_plateproduct WHERE plate_id = #{plateId} AND shops_id = #{shopsId})"
	  					+ "</when>"
	  					+ "<when test='shopsId != null and shopsId != \"\"'>"
	  						+ "<choose>"
	  							+ "<when test='oneself'>"
	  								+ "a.id NOT IN(SELECT product_id FROM tb_shops_product WHERE shops_id = #{shopsId})"
	  							+ "</when>"
	  							+ "<otherwise>"
	  								+ "a.id IN(SELECT product_id FROM tb_shops_product WHERE shops_id = #{shopsId})"
	  							+ "</otherwise>"
	  						+ "</choose>"
	  					+ "</when>"
	  				+ "</choose>"
	  				+ "<if test='products != null and products != \"\" and products.length &gt; 0'>"
	  					+ " AND a.id NOT IN "
	  					+ "<foreach collection='products' item='productid' open='(' close=')' separator=','>"
	  						+ "#{productid}"
	  					+ "</foreach>"
	  				+ "</if>"
	  			+ "</where>"
	  			+ " ORDER BY ctime DESC"
	  		+ "</script>")
	  List<ShopsProductListResponse> shopsProductList(ShopsProductListRequest shopsProductListRequest);
	  
	  
	  /**
	   * 店铺商品列表
	   * @param shopsProductRequest
	   * @return
	   */
	  @Select("<script>"
	  			+ "SELECT "
	  				+ "a.`id` AS 'tbId',"
	  				+"a.`product_id` AS 'id',"
	  				+ "a.`shops_id` AS 'shopsId',"
	  				+ "l.`shops_name` AS 'shopsName',"
	  				+ "p.`name` AS 'productName',"
	  				+ "p.`headimg` AS 'headimg',"
	  				+ "a.`created_time` AS 'createdTime',"
	  				+ "a.`recommend` AS 'recommend',"
	  				+ "s.`name` AS 'typeName'"
	  			+ " FROM "
	  				+ "tb_shops_product a"
	  				+ " LEFT JOIN s_category s ON a.type_id = s.id"
	  				+ " LEFT JOIN shops_location l ON a.shops_id = l.id"
	  				+ " LEFT JOIN s_product p ON a.product_id = p.id"
	  			+ " WHERE "
	  				+ "a.shops_id = #{shopsId}"
	  			+ " ORDER BY a.created_time DESC"
	  		+ "</script>")
	  List<ShopsProductResponse> shopsProduct(ShopsProductRequest shopsProductRequest);
	  
	  /**
	   * 经过搜索的店铺商品列表
	   * @param shopsProductRequest
	   * @return
	   */
	  @Select("<script>"
	  			+ "SELECT "
	  				+"a.`id` AS 'id',"
	  				+ "a.`name` AS 'name',"
	  				+ "a.`headimg` AS 'headimg',"
	  				+ "a.`attribute` AS 'attribute',"
	  				+ "a.`price` AS 'price',"
	  				+ "a.`stock` AS 'stock',"
	  				+ "a.`status` AS 'status',"
	  				+ "a.`ctime` AS 'ctime',"
	  				+ "a.`description` AS 'description',"
	  				+ "(SELECT t.id FROM tb_shops_product t WHERE t.shops_id= #{shopsId} AND t.product_id = a.id) AS 'tbId'"
	  			+ " FROM "
	  				+ "s_product a"
	  			+ "<where>"
	  				+ "<choose>"
	  					+ "<when test='plateId != null and plateId != \"\"'>"
	  							+"a.id NOT IN(SELECT product_id FROM tb_shops_plateproduct WHERE plate_id = #{plateId} AND shops_id = #{shopsId})"
	  					+ "</when>"
	  					+ "<when test='shopsId != null and shopsId != \"\"'>"
	  						+ "<choose>"
	  							+ "<when test='oneself'>"
	  								+ "a.id NOT IN(SELECT product_id FROM tb_shops_product WHERE shops_id = #{shopsId})"
	  							+ "</when>"
	  							+ "<otherwise>"
	  								+ "a.id IN(SELECT product_id FROM tb_shops_product WHERE shops_id = #{shopsId})"
	  							+ "</otherwise>"
	  						+ "</choose>"
	  					+ "</when>"
	  				+ "</choose>"
	  				+ "<if test='products != null and products != \"\" and products.length &gt; 0'>"
	  					+ " AND a.id NOT IN "
	  					+ "<foreach collection='products' item='productid' open='(' close=')' separator=','>"
	  						+ "#{productid}"
	  					+ "</foreach>"
	  				+ "</if>"
	  				+ " and a.`name` like '%${name}%' "
	  			+ "</where>"
	  			+ " ORDER BY ctime DESC"
	  		+ "</script>")
	  List<ShopsProductListResponse> searchShopsProduct(SearchShopsProductRequest searchShopsProductRequest);
	  
	  /**
	   * 店铺商品列表
	   * @param shopsDelRequest
	   * @return
	   */
	  @Delete("DELETE FROM tb_shops_product WHERE id=#{id}")
	  int shopDel(ShopsDelRequest shopsDelRequest);
	  
	  
	  /**
	   * 添加店铺商品
	   * @param shopsAddRequest
	   * @return
	   * 
	   * 备注：后来把商品分类去除了，把原来的“(SELECT sp.twotypeid FROM s_product sp WHERE sp.id = #{productId})” 改为  “-1”
	   */
	  @Insert("<script>"
	  			+ "INSERT INTO tb_shops_product (shops_id,product_id,created_time,recommend,type_id) VALUES"
	  				+ "(#{shops_id},#{product_id},now(),#{recommend},-1)"  
	  		+ "</script>")
	  int shopAdd(@Param("shops_id") int shops_id , @Param("product_id") int product_id,@Param("recommend") int recommend);
	  
	  /**
	   * 添加店铺商品
	   * @param shopsAddRequest
	   * @return
	   * 
	   * 备注：后来把商品分类去除了，把原来的“(SELECT sp.twotypeid FROM s_product sp WHERE sp.id = #{productId})” 改为  “-1”
	   */
	  @Insert("<script>"
	  			+ " select SELECT COUNT(*) FROM tb_shops_product WHERE shops_id =#{shops_id} and product_id = #{product_id}"
	  		+ "</script>")
	  int countShopProduct(@Param("shops_id") int shops_id , @Param("product_id") int product_id);
	  
	  /**
	   * 店铺订单列表
	   * @param shopsOrderRequest
	   * @return
	   */
	  @Select("<script>"
	  			+ "SELECT "
	  				+"l.`shops_name` AS 'shopsName',"
	  				+ "a.`value` AS 'value',"
	  				+ "u.`nickname` AS 'nickname',"
	  				+ "o.`price` AS 'price',"
	  				+ "o.`paymethod` AS 'paymethod',"
	  				+ "o.`status` AS 'status',"
	  				+ "o.`created_time` AS 'orderCTime',"
	  				+ "a.`created_time` AS 'valCTime'"
	  			+ " FROM "
	  				+ "tb_shops_value a"
	  				+ " LEFT JOIN shops_location l ON a.shops_id = l.id"
	  				+ " LEFT JOIN s_order o ON a.order_id = o.id"
	  				+ " LEFT JOIN users u ON o.user_id = u.id"
	  				+ " LEFT JOIN ${platform}.tb_user u ON l.`user_id` = u.`user_id`"
	  				+ "<where>"
	  					+ "${sqlMap.sql}"
	  					+ " and u.`nickname` is not null"
	  				+ "</where>"
	  			+ " ORDER BY a.created_time DESC"
	  		+ "</script>")
	  List<ShopsOrderResponse> shopsOrder(ShopsOrderRequest shopsOrderRequest);
	  
	  
	  /**
	   * 店铺商品评论列表
	   * @param appraiseListRequest
	   * @return
	   */
	  @Select("<script>"
	  			+ " SELECT "
	  				+ "a.id AS 'id',"
	  				+ "l.id AS 'shopsId',"
	  				+ "l.shops_name AS 'shopsName',"
	  				+ "p.id AS 'productId',"
	  				+ "p.name AS 'productName',"
	  				+ "p.headimg AS 'productImg',"
	  				+ "a.userid AS 'userid',"
	  				+ "a.user_name AS 'userName',"
	  				+ "a.context AS 'context',"
	  				+ "a.ctime AS 'ctime',"
	  				+ "a.like AS 'like',"
	  				+ "a.resources AS 'resources',"
	  				+ "a.matching AS 'matching',"
	  				+ "a.deliveryspeed AS 'deliveryspeed',"
	  				+ "a.score AS 'score',"
	  				+ "a.servicequality AS 'servicequality',"
	  				+ "a.serviceattitude AS 'serviceattitude'"
	  			+ " FROM "
	  				+ "s_appraise a"
	  			+ " LEFT JOIN tb_shops_product s ON a.productid = s.id"
	  			+ " LEFT JOIN s_product p ON s.product_id = p.id"
	  			+ " LEFT JOIN shops_location l ON s.shops_id = l.id"
	  			+ " LEFT JOIN ${platform}.tb_user u ON a.userid = u.user_id"
	  			+ "<where>"
	  				+ "${sqlMap.sql}"
	  			+ "</where>"
	  			+ "order by a.user_name"
	  		+ "</script>")
	  List<AppraiseListResponse> appraiseList(AppraiseListRequest appraiseListRequest);
	  
	  
	  /**
	   * 店铺商品评论删除
	   * @param appraiseDelRequest
	   * @return
	   */
	  @Delete("DELETE FROM s_appraise WHERE id = #{id}")
	  int appraiseDel(AppraiseDelRequest appraiseDelRequest);
	  
	  
	  /**
	   * 店铺轮播图列表
	   * @param carouselShopsListRequest
	   * @return
	   */
	  @Select("<script>"
	  			+ "SELECT "
	  				+ "a.id AS 'id',"
	  				+ "p.name AS 'productName',"
	  				+ "a.product_id AS 'productId',"
	  				+ "a.created_time AS 'createdTime',"
	  				+ "a.path AS 'path',"
	  				+ "a.shops_id AS 'shopsId',"
	  				+ "l.shops_name AS 'shopsName'"
	  			+ " FROM "
	  				+ "rel_carousel_shops a"
	  			+ " LEFT JOIN s_product p ON a.product_id = p.id"
	  			+ " LEFT JOIN shops_location l ON a.shops_id = l.id"
	  			+ " LEFT JOIN ${platform}.tb_user u ON l.`user_id` = u.`user_id`"
	  			+ "<where>"
	  				+ "${sqlMap.sql}"
	  			+ "</where>"
	  		+ "</script>")
	  List<CarouselShopsListResponse> carouselShopsList(CarouselShopsListRequest carouselShopsListRequest);
	  
	  
	  /**
	   * 店铺轮播图新增
	   * @param carouselShopsSaveRequest
	   * @return
	   */
	  @Insert("INSERT INTO "
	  			+ "rel_carousel_shops"
	  		+ "("
		  		+ "product_id,"
		  		+ "created_time,"
		  		+ "path,"
		  		+ "shops_id"
	  		+ ") VALUES ("
		  		+ "#{productId},"
		  		+ "#{updateDate},"
		  		+ "#{path},"
		  		+ "#{shopsId}"
	  		+ ")")
	  int carouselShopsAdd(CarouselShopsSaveRequest carouselShopsSaveRequest);
	  
	  
	  /**
	   * 店铺轮播图修改
	   * @param carouselShopsSaveRequest
	   * @return
	   */
	  @Update("UPDATE rel_carousel_shops SET "
	  		+ "product_id = #{productId},"
	  		+ "path = #{path},"
	  		+ "shops_id = #{shopsId}"
	  		+ " WHERE "
	  		+ "id = #{id}")
	  int carouselShopsUpdate(CarouselShopsSaveRequest carouselShopsSaveRequest);
	  
	  
	  /**
	   * 店铺轮播图删除
	   * @param carouselShopsSaveRequest
	   * @return
	   */
	  @Delete("DELETE FROM rel_carousel_shops WHERE id = #{id}")
	  int carouselShopsDel(CarouselShopsDelRequest carouselShopsDelRequest);
	  
	  
	  /**
	   * 店铺订单列表
	   * @param shopsOrderRequest
	   * @return
	   */
	  @Select("<script>"
	  		+ "SELECT "
	  			+ "l.shops_name AS 'shopsName',"
	  			+ "o.id AS 'id',"
	  			+ "o.product_name AS 'productName',"
	  			+ "o.price AS 'price',"
	  			+ "o.num AS 'num',"
	  			+ "o.status AS 'status',"
	  			+ "o.created_time AS 'createdTime',"
	  			+ "o.tracking_num AS 'trackingNum',"
	  			+ "o.express AS 'express',"
	  			+ "o.pay_no AS 'payNo',"
	  			+ "o.publish_status AS 'publishStatus',"
	  			+ "usr.nickname AS 'userName'"
	  		+ " FROM "
	  			+ "tb_shops_order a"
	  		+ " LEFT JOIN shops_location l ON a.shops_id = l.id"
	  		+ " LEFT JOIN s_order_extra o ON a.order_id = o.id"
	  		+ " LEFT JOIN s_order ord ON o.order_id = ord.id"
	  		+ " LEFT JOIN users usr ON ord.user_id = usr.id"
	  		+ " LEFT JOIN ${platform}.tb_user tu ON l.`user_id` = tu.`user_id`"
	  		+ "<where>"
	  			+ " o.`status` &gt; 0"
	  			+ " ${sqlMap.sql}"
	  		+ "</where>"
	  		+ " order by o.created_time desc"
	  		+ "</script>")
	  List<TbShopsOrderResponse> shopsOrderList(TbShopsOrderRequest tbShopsOrderRequest);
	  
	  
	  /**
	   * 店铺板块新增
	   * @param shopsPlateSaveRequest
	   * @return
	   */
	  @Insert("INSERT INTO "
	  			+ "tb_shops_plate"
	  		+ "("
		  		+ "type,"
		  		+ "content,"
		  		+ "order_no,"
		  		+ "shops_id"
	  		+ ") VALUES ("
		  		+ "#{type},"
		  		+ "#{content},"
		  		+ "(SELECT IFNULL(MAX(sp.order_no),0) FROM tb_shops_plate sp WHERE sp.shops_id = #{shopsId}),"
		  		+ "#{shopsId}"
	  		+ ")")
	  int shopPlateAdd(ShopsPlateSaveRequest shopsPlateSaveRequest);
	  
	  
	  /**
	   * 店铺板块修改
	   * @param shopsPlateSaveRequest
	   * @return
	   */
	  @Update("UPDATE tb_shops_plate SET "
		  		+ "type = #{type},"
		  		+ "content = #{content},"
		  		+ "order_no = #{orderNo}"
		  	+ " WHERE "
		  		+ "id = #{id}")
	  int shopPlateUpdate(ShopsPlateSaveRequest shopsPlateSaveRequest);
	  
	  
	  /**
	   * 店铺板块删除
	   * @param shopsPlateDelRequest
	   * @return
	   */
	  @Delete("DELETE FROM tb_shops_plate WHERE id = #{id}")
	  int shopsPlateSaveDel(ShopsPlateDelRequest shopsPlateDelRequest);
	  
	  
	  /**
	   * 开店审核表单提交
	   * @param examineReq
	   * @return
	   */
	  @Insert("INSERT INTO to_examine("
		  		+ "insert_date,"
		  		+ "user_id,"
		  		+ "type,"
		  		+ "data"
	  		+ ") VALUES ("
	  			+ "#{insertDate},"
	  			+ "#{userId},"
	  			+ "#{type},"
	  			+ "#{data}"
	  		+ ")")
	  int examineSub(ExamineRequest examineRequest);
	  
	  
	  @Insert("INSERT INTO to_examine("
		  		+ "insert_date,"
		  		+ "user_id,"
		  		+ "type,"
		  		+ "data"
	  		+ ") VALUES ("
	  			+ "#{insertDate},"
	  			+ "#{userId},"
	  			+ "#{type},"
	  			+ "#{data}"
	  		+ ")")
	  int release(ExamineRequest examineRequest);
	  
	  /**
	   * 店铺审核列表
	   * @param examineReq
	   * @return
	   */
	  @Select("<script>"
	  		+ "SELECT "
	  			+ "a.id AS 'id',"
	  			+ "a.insert_date AS 'insertDate',"
	  			+ "a.update_date AS 'updateDate',"
	  			+ "a.type AS 'type',"
	  			+ "a.state AS 'state',"
	  			+ "a.data AS 'data',"
	  			+ "tu.username AS 'userName'"
	  		+ " FROM "
	  			+ "to_examine a"
	  		+ " LEFT JOIN ${platform}.tb_user tu ON a.user_id = tu.id"
	  		+ "<where>"
	  			+ " ${sqlMap.sql}"
	  		+ "</where>"
	  		+ "order by a.insert_date desc"
	  		+ "</script>")
	  List<ExamineResponse> examineList(ExamineListRequest examineListRequest);
	  
	  /**
	   * 店铺审核单条
	   * @param id
	   * @return
	   */
	  @Select("<script>"
		  		+ "SELECT "
		  			+ "a.id AS 'id',"
		  			+ "a.insert_date AS 'insertDate',"
		  			+ "a.update_date AS 'updateDate',"
		  			+ "a.type AS 'type',"
		  			+ "a.state AS 'state',"
		  			+ "a.data AS 'data',"
		  			+ "a.user_id AS 'userId',"
		  			+ "tu.username AS 'userName'"
		  		+ " FROM "
		  			+ "to_examine a"
		  		+ " LEFT JOIN ${platform}.tb_user tu ON a.user_id = tu.user_id"
		  		+ "<where>"
		  			+ " a.id = #{id}"
		  		+ "</where>"
		  		+ "</script>")
	  ExamineResponse examineData(String id);
	  
	  
	  /**
	   * 根据id数组找商品
	   * @param productIds
	   * @return
	   */
	  @Select("<script>"
	  		+ "SELECT "
	  			+ "a.id AS 'id',"
	  			+ "a.name AS 'name',"
	  			+ "a.headimg AS 'headimg',"
	  			+ "a.price AS 'price',"
	  			+ "a.stock AS 'stock'"
	  		+ " FROM "
	  			+ "s_product a"
	  		+ "<where>"
	  			+ "a.id IN"
		  		+ "<foreach collection='array' index='productIds' item='id' open='(' close=')' separator=','>"
		  			+ "#{id}"
		  		+ "</foreach >"
	  		+ "</where>"
	  		+ "</script>")
	  List<ProductResponse> productList(String[] productIds);
	  
	  
	  /**
	   * 获取店铺名
	   * @param shopsId
	   * @return
	   */
	  @Select("SELECT a.shops_name FROM shops_location a WHERE a.id = #{shopsId}")
	  String getShopsName(String shopsId);
	  
	  
	  /**
	   * 获取商品名
	   * @param shopsId
	   * @return
	   */
	  @Select("SELECT a.name FROM s_product a WHERE a.id = #{productId}")
	  String getProductName(String productId);
	  

	  /**
	   * 更改审核状态
	   * @param state
	   * type:1同意  2拒绝
	   * @return
	   */
	  @Update("UPDATE to_examine SET state = #{type},update_date = #{updateDate} WHERE id = #{id}")
	  int updateState(ConfirmRequest confirmRequest);
	  
	  /**
	   * 添加店铺
	   * @return
	   */
	  @Insert("<script>"
	  		+ "INSERT INTO shops_location ("
	  			+ "address,"
	  			+ "created_time,"
	  			+ "user_id,"
	  			+ "lat,"
	  			+ "lng,"
	  			+ "shops_name,"
	  			+ "avatar_url,"
	  			+ "national,"
	  			+ "`describe`,"
	  			+ "licenses,"
	  			+ "user_photos,"
	  			+ "user_phone"
	  		+ ") VALUES ("
	  			+ "#{address},"
	  			+ "#{updateDate},"
	  			+ "#{userId},"
	  			+ "#{lat},"
	  			+ "#{lng},"
	  			+ "#{shopsName},"
	  			+ "#{avatarUrl},"
	  			+ "#{national},"
	  			+ "#{describe},"
	  			+ "#{licenses},"
	  			+ "#{userPhotos},"
	  			+ "#{userPhone}"
	  		+ ")"
	  		+ "</script>")
	  int addShops(ShopsEntity shopsEntity);
	  
	  
	  /**
	   * 添加店铺商品
	   * @param productShopsEntity
	   * @return
	   */
	  @Insert("<script>"
	  		+ "INSERT INTO tb_shops_product("
	  			+ "shops_id,"
	  			+ "product_id,"
	  			+ "created_time,"
	  			+ "recommend"
	  		+ ") VALUES "
	  			+ "<foreach collection='productIds' index='productIds' item='id' separator=','>"
	  				+ "(#{shopsId},#{id},#{updateDate},#{recommend})"
	  			+ "</foreach>"
	  		+ "</script>")
	  int addProductShops(ProductShopsEntity productShopsEntity);
	  
	  
	  /**
	   * 添加店铺首页产品图组合
	   * @param shopsCommendEntity
	   * @return
	   */
	  @Insert("<script>"
		  		+ "INSERT INTO shops_commend("
		  			+ "shops_id,"
		  			+ "status,"
		  			+ "created_time,"
		  			+ "group_name,"
		  			+ "group_price,"
		  			+ "product_ids"
		  		+ ") VALUES ("
		  			+ "#{shopsId},"
		  			+ "#{status},"
		  			+ "#{updateDate},"
		  			+ "#{groupName},"
		  			+ "#{groupPrice},"
		  			+ "#{productIds}"
		  		+ ")"
		  		+ "</script>")
	  int addShopsCommend(ShopsCommendEntity shopsCommendEntity);
	  
	  
	  /**
	   * 添加店铺首页轮播图
	   * @param carouselShopsEntity
	   * @return
	   */
	  @Insert("<script>"
		  		+ "INSERT INTO rel_carousel_shops("
		  			+ "product_id,"
		  			+ "created_time,"
		  			+ "path,"
		  			+ "shops_id"
		  		+ ") VALUES ("
		  			+ "#{productId},"
		  			+ "#{updateDate},"
		  			+ "#{path},"
		  			+ "#{shopsId}"
		  		+ ")"
		  		+ "</script>")
	  int addCarouselShops(CarouselShopsEntity carouselShopsEntity);
	  
	  
	  /**
	   * 店铺订单对外发布权限    授权
	   * @param id
	   * @return
	   */
	  @Update("UPDATE ${platform}.tb_user SET release_order = 2 WHERE id = #{id}")
	  int setRelease(String id);
	  
	  
	  /**
	   * 获取单条订单对外发布信息
	   * @param shopsOrderSaveRequest
	   * @return
	   */
	  @Select("SELECT publish_status FROM s_order_extra WHERE id = #{id}")
	  String getOrder(ShopsOrderSaveRequest shopsOrderSaveRequest);
	  
	  /**
	   * 查询当前用户  是否允许发布订单 1;否，2：是
	   * @param id
	   * @return
	   */
	  @Select("SELECT release_order FROM ${platform}.tb_user a WHERE a.id = #{id}")
	  int isReleaseOrder(String id);
	  
	  /**
	   * 修改订单对外发布信息  为是
	   * @param shopsOrderSaveRequest
	   * @return
	   */
	  @Update("UPDATE s_order_extra SET publish_status = 2 WHERE id = #{id}")
	  int updateOrder(ShopsOrderSaveRequest shopsOrderSaveRequest);
	  
	  /**
	   * 获取商品sku
	   * @return
	   */
	  @Select("SELECT a.attribute FROM s_product a LEFT JOIN tb_shops_product s ON s.product_id = a.id WHERE s.id = #{id}")
	  String sku(String id);
	  
	  /**
	   * 添加评价
	   * @param appraiseSubRequest
	   * @return
	   */
	  @Insert("INSERT INTO s_appraise ("
	  		+ "productid,"
	  		+ "context,"
	  		+ "ctime,"
	  		+ "matching,"
	  		+ "deliveryspeed,"
	  		+ "score,"
	  		+ "servicequality,"
	  		+ "serviceattitude,"
	  		+ "attr,"
	  		+ "url,"
	  		+ "user_name"
	  		+ ") VALUES ("
	  		+ "#{tbId},"
	  		+ "#{context},"
	  		+ "#{updateDate},"
	  		+ "#{matching},"
	  		+ "#{deliveryspeed},"
	  		+ "#{score},"
	  		+ "#{servicequality},"
	  		+ "#{serviceattitude},"
	  		+ "#{attr},"
	  		+ "#{url},"
	  		+ "#{userName}"
	  		+ ")")
	  int appraiseAdd(AppraiseSubRequest appraiseSubRequest);
	  
	  /**
	   * 修改评价
	   * @param appraiseSubRequest
	   * @return
	   */
	  @Update("")
	  int appraiseUpdate(AppraiseSubRequest appraiseSubRequest);
}
