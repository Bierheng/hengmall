package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.ProductReq;
import com.hengmall.goods.model.SOrderEntity;
import com.hengmall.goods.model.ShopCarouselEntity;
import com.hengmall.goods.model.ShopsInfo;
import com.hengmall.goods.model.ShopsLocation;
import com.hengmall.goods.model.ShopsPlate;
import com.hengmall.goods.model.ShopsTypeBean;

@Repository
@Mapper
public interface ShopsDao {

	/**
	 * @Title: 通过店铺主用户查询他所有的订单，这里需要注意子订单的问题，目前每个订单都是拆开来实现的
	 * @Description: 
	 */
	  @Select(
	  "select * from tb_shops_order  a left join s_order b on a.order_id = b.id where a.user_id = #{storeId}") 
	  List<SOrderEntity> queryByUserId(@Param("storeId") int storeId);
	 
		/**
		 * 店铺主用户查询他所有的订单总数
		 * @return
		 */
		@Select("select count(*) from tb_shops_order where user_id = #{storeId} ")
		int countShopsOrder(@Param("storeId") int storeId);
	  
	  /**
	  * @Title: 通过店铺主用户查询他所有的爆款商品
	  * @Description: 
	  */
	  @Select(
	  "select * from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{storeId} order by b.salesvolume") 
	  List<ProductReq> queryProductByUserIdForsale(@Param("storeId") int storeId);
	  
	  /**
	  * @Title: 通过店铺主用户查询他所有的爆商品(默认1：综合，2：销量，3：价格升序,4：价格降序)
	  * @Description: 
	  */
	  @Select(
	  "select a.id as productId ,b.name as productName ,b.headimg as url ,b.price/100 as price from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{storeId} and b.id is not NULL order by b."+"${order_desc} desc") 
	  List<ProductReq> queryProductByOrderDesc(@Param("storeId") int storeId,@Param("order_desc") String order_desc);
	 
	  /**
	  * @Title: 通过店铺主用户查询他所有的爆商品(4：价格降序)
	  * @Description: 
	  */
	  @Select(
	  "select a.id as productId ,b.name as productName ,b.headimg as url ,b.price/100 as price from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{storeId} and b.id is not NULL order by b."+"${order_desc} asc") 
	  List<ProductReq> queryProductByOrderDesc2(@Param("storeId") int storeId,@Param("order_desc") String order_desc);
	  
	  /**
	  * @Title: 通过店铺主用户查询他所有的爆商品(默认1：综合，2：销量，3：价格升序,4：价格降序) 带有分类关键词
	  * @Description: 
	  */
	  @Select(
	  "select a.id as productId ,b.name as productName ,b.headimg as url ,b.price/100 as price from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{storeId} and a.type_id = #{typeId} and b.id is not NULL order by b."+"${order_desc} desc") 
	  List<ProductReq> queryProductByOrderDesc3(@Param("storeId") int storeId,@Param("order_desc") String order_desc,@Param("typeId") int typeId);
	  
	  /**
	  * @Title: 通过店铺主用户查询他所有的爆商品(4：价格降序) 带有分类关键词
	  * @Description: 
	  */
	  @Select(
	  "select a.id as productId ,b.name as productName ,b.headimg as url ,b.price/100 as price from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{storeId} and a.type_id = #{typeId} and b.id is not NULL order by b."+"${order_desc} asc") 
	  List<ProductReq> queryProductByOrderDesc4(@Param("storeId") int storeId,@Param("order_desc") String order_desc,@Param("typeId") int typeId);
	  
	  /**
	  * @Title: 通过店铺主用户查询他所有能显示的商品分类
	  * shopsId 店铺ID
	  * @Description: 
	  */
	  @Select("select a.type_id as typeId , b.name as typeName ,b.icon1 as url,a.shops_id as shopsId FROM tb_shops_type a LEFT JOIN s_category b ON a.type_id = b.id"
	  		+ "  WHERE a.shops_id = #{shopsId} and a.type_id in (select DISTINCT type_id from tb_shops_product a left "
	  		+ "join s_product b on a.product_id = b.id where a.shops_id = #{shopsId} order by b.salesvolume) ") 
	  List<ShopsTypeBean> queryProductTypeByUserId(@Param("shopsId") int shopsId);
	  
	/**
	 * @Title: 通过店铺主用户查询他所有的商品总数
	 * @Description: 
	 */
	  @Select( "select count(*) from tb_shops_product where shops_id = #{storeId}") 
	  int countProductByUserId(@Param("storeId") int storeId);
	  
	/**
	 * @Title: 查询店铺信息
	 * @Description: 
	 */
	  @Select(
	  "select * from shops_location where id = #{shopsId}") 
	  ShopsLocation queryShopsByUserId(@Param("shopsId") int shopsId);
	  
	/**
	 * @Title: 查询粉丝数
	 * @Description: 
	 */
	  @Select(
	  "select COUNT(*) from shops_attention where shops_id = #{id}") 
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
	  "select a.id as productId ,b.name as productName ,b.headimg as url ,b.price/100 as price "
	  + "from tb_shops_product a left join s_product b on a.product_id = b.id where a.shops_id = #{storeId} and a.type_id = #{typeId}") 
	 List<ProductReq> queryShopsTypeList(@Param("storeId") int storeId,@Param("typeId") int typeId);

	  
	/**
	 * 获取是否点赞记录数
	 * @return
	 */
	@Select("select count(*) from shops_attention where user_id = #{userId} and shops_id = #{storeId}")
	int searchShops(@Param("userId") int userId,@Param("storeId")  int storeId);

	/**
	 *  新增店铺关注记录
	 * @param id  话题ID	
	 * @param productDetail  商品详情数据
	 */
	@Insert("insert into shops_attention(shops_id,user_id) values(#{storeId},#{userId})")
	int insertShopsAttention(@Param("userId") int userId,@Param("storeId") int storeId);

	
	@Select("select id, user_id as shopsId , avatar_url as url,fans_num as fansNum,`describe`,national,shops_name as shopsName from shops_location where id = #{storeId}")
	ShopsInfo queryById(@Param("storeId") int storeId);

	
	// 根据主键id修改数据
	@Update("update shops_location set fans_num =#{edit.fansNum}"
			+ "  where id = #{edit.id}")
	int updateById(@Param("edit") ShopsInfo shopsInfo);

	
	// 根据主键id删除点赞记录
	@Delete("delete from shops_attention where shops_id = #{storeId} and user_id = #{userId}")
	int delShopsAttention(@Param("userId") int userId,@Param("storeId") int storeId);
	
	@Select("select * from tb_shops_plate where shops_id = #{shopsId}")
	List<ShopsPlate> queryShopsPlateList(@Param("shopsId") int shopsId);
	
	@Select("select DISTINCT d.id as productId ,d.name as productName ,d.headimg as url ,d.price/100 as price ,d.shops_product_id from tb_shops_plateproduct a left join  "
			+ " (select b.*, c.shops_id as shops_id,c.id as shops_product_id  from s_product b left join tb_shops_product c on b.id = c.product_id AND "
			+ " shops_id = #{shopsId} ) d on a.product_id = d.id  where a.plate_id = #{plateId}  AND d.shops_product_id is not NULL")
	List<ProductReq> queryShopsPlateProductList(@Param("plateId") int plateId,@Param("shopsId") int shopsId);

	@Select("select * from rel_carousel_shops where shops_id = #{storeId}")
	List<ShopCarouselEntity> getShopsCarousel(@Param("storeId") int storeId);
}
