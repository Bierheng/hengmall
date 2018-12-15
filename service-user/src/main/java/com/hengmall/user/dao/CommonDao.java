package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.common.CategoryListRequest;
import com.hengmall.user.model.common.CategoryListResponse;
import com.hengmall.user.model.common.ProductRequest;
import com.hengmall.user.model.common.ProductResponse;
import com.hengmall.user.model.common.combineSale.CombineSaleDelRequest;
import com.hengmall.user.model.common.combineSale.CombineSaleRequest;
import com.hengmall.user.model.common.combineSale.CombineSaleResponse;
import com.hengmall.user.model.common.combineSale.CombineSaleSaveRequest;

/**
 * 公共Dao
 * @author Administrator
 *
 */
@Repository
public interface CommonDao {

	/**
	 * 获取商品分类列表
	 * @param categoryListRequest
	 * @return
	 */
	@Select("<script>"
			+ "SELECT "
				+ "a.id AS 'id',"
				+ "a.name AS 'name',"
				+ "a.level AS 'level',"
				+ "a.pid AS 'pid',"
				+ "a.is_hot AS 'isHot',"
				+ "a.icon1 AS 'icon1',"
				+ "a.icon2 AS 'icon2',"
				+ "a.created_time AS 'createdTime'"
			+ " FROM "
			+ "s_category a"
			+ "<where>"
				+ "<if test='level != null and level != \"\"'>"
					+ "a.level = #{level}"
				+ "</if>"
			+ "</where>"
			+ "</script>")
	List<CategoryListResponse> categoryList(CategoryListRequest categoryListRequest);
	
	
	/**
	 * 获取平台商品列表
	 * @param productReq
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
			+ " WHERE "
				+ "a.status = 1"
			+ "</script>")
	List<ProductResponse> productList(ProductRequest productReq);
	
	
	
	/**
	 * 拼单商品列表
	 * @param combineSaleRequest
	 * @return
	 */
	@Select("SELECT "
			+ "a.id AS 'id',"
			+ "a.product_id AS 'productId',"
			+ "a.title AS 'title',"
			+ "a.price AS 'price',"
			+ "a.icon AS 'icon',"
			+ "a.combine_num AS 'combineNum',"
			+ "a.still_need AS 'stillNeed',"
			+ "a.status AS 'status',"
			+ "a.created_time AS 'createdTime',"
			+ "a.updated_time AS 'updatedTime',"
			+ "a.shops_id AS 'shopsId',"
			+ "p.name AS 'productName',"
			+ "l.shops_name AS 'shopsName'"
			+ " FROM "
			+ "combine_sale a"
			+ " LEFT JOIN s_product p ON a.product_id = p.id"
			+ " LEFT JOIN shops_location l ON a.shops_id = l.id")
	List<CombineSaleResponse> combineSaleList(CombineSaleRequest combineSaleRequest);
	
	
	/**
	 * 获取店铺下拉
	 * @param combineSaleRequest
	 * @return
	 */
	@Select("SELECT "
			+ "a.id AS 'id',"
			+ "a.shops_name AS 'name'"
			+ " FROM "
			+ "shops_location a")
	List<com.hengmall.user.model.common.combineSale.Select> getShops(CombineSaleRequest combineSaleRequest);
	
	
	/**
	 * 获取店铺商品下拉
	 * @param combineSaleRequest
	 * @return
	 */
	@Select("SELECT "
			+ "p.id AS 'id',"
			+ "p.name AS 'name'"
			+ " FROM "
			+ "tb_shops_product a"
			+ " LEFT JOIN s_product p ON a.product_id = p.id"
			+ " WHERE "
			+ "a.shops_id = #{shopsId}")
	List<com.hengmall.user.model.common.combineSale.Select> getShopsProduct(@Param("combineSaleRequest") CombineSaleRequest combineSaleRequest,
			@Param("shopsId") String shopsId);


	/**
	 * 拼单商品增加
	 * @param combineSaleSaveRequest
	 */
	@Insert("INSERT INTO combine_sale ("
			+ "product_id,"
			+ "title,"
			+ "price,"
			+ "icon,"
			+ "combine_num,"
			+ "still_need,"
			+ "status,"
			+ "created_time,"
			+ "shops_id"
			+ ") VALUES ("
			+ "#{productId},"
			+ "#{title},"
			+ "(SELECT price FROM s_product WHERE id = #{productId}),"
			+ "(SELECT headimg FROM s_product WHERE id = #{productId}),"
			+ "#{combineNum},"
			+ "#{stillNeed},"
			+ "#{status},"
			+ "#{updateDate},"
			+ "#{shopsId}"
			+ ")")
	int combineSaleAdd(CombineSaleSaveRequest combineSaleSaveRequest);


	/**
	 * 拼单商品修改
	 * @param combineSaleSaveRequest
	 */
	@Update("UPDATE combine_sale SET "
			+ "product_id = #{productId},"
			+ "title = #{title},"
			+ "combine_num = #{combineNum},"
			+ "still_need = #{stillNeed},"
			+ "status = #{status},"
			+ "updated_time = #{updateDate},"
			+ "shops_id = #{shopsId}"
			+ " WHERE "
			+ "id = #{id}")
	int combineSaleUpdate(CombineSaleSaveRequest combineSaleSaveRequest);


	/**
	 * 拼单商品删除
	 * @param combineSaleDelRequest
	 * @return
	 */
	@Delete("DELETE FROM combine_sale WHERE id = #{id}")
	int combineSaleDel(CombineSaleDelRequest combineSaleDelRequest);
}
