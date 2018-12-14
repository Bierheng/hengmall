package com.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.server.entity.RelProductCategoryEntity;
import com.server.entity.SProductEntity;
import com.server.entity.TbGoodsTag;
import com.server.entity.TbGoodsTagBean;
import com.server.entity.TbProductTag;
import com.server.entity.api.ProductListResp;
import com.server.entity.common.combineSale.CombineSaleRequest;
import com.server.entity.common.combineSale.CombineSaleResponse;
import com.server.entity.manage.product.ProductBean2;
import com.server.entity.manage.product.ProductDetailBean;
import com.server.entity.manage.product.SProductBean;
import com.server.entity.manage.product.isnew.ShopsRightEntity;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface SProductDao {

	// 按请求级别查询对应的商品
	@Select("select * from s_product c,s_category a, rel_product_category b where a.id = b.categoryid and "
			+ "c.id=b.productid and a.`level` = #{level}")
	List<SProductEntity> findByLevel(@Param("level") int level);

	// 根据商品类别主键ID查询商品信息
	@Select("select * from s_product a,(select * from rel_product_category where "
			+ "categoryid=(select id from s_category where id = #{id} limit 1)) b where a.id = b.productid")
	List<SProductEntity> queryBySCategoryId(@Param("id") int id);
	
	// 商品搜索 名称模糊查询
	@Select("select * from s_product where `name` like '%${name}%'")
	List<SProductBean> findByName(@Param("name") String name);

	// 根据ID查询数据
	@Select("select *,"
			+ "`endimg` AS 'endimg',"
			+ "`tagids` AS 'tagids',"
			+ "`price_type` AS 'priceType',"
			+ "`salesvolume_false` AS 'salesvolumeFalse',"
			+ "`state_id` AS 'stateId'"
			+ " from s_product  where id = #{id} limit 1")
	ProductBean2 queryById(@Param("id") int id);
	
	/**
	 * 商品特色服务
	 * @param rights
	 * @return
	 */
	@Select("<script>"
			+ "SELECT "
				+ "a.id AS 'id',"
				+ "a.url AS 'url',"
				+ "a.name AS 'name'"
			+ " FROM "
			+ "shops_right a"
		  + "</script>")
	List<ShopsRightEntity> getRights();
	
	// 根据ID查询数据
	@Select("select * from s_product where id = #{id} limit 1")
	SProductBean queryById2(@Param("id") int id);

	// 根据user_id 和 支付状态 查询
	@Select("select * from s_product c,s_order_extra a,s_order b where a.orderid = b.id and b.`status` = #{status} "
			+ "and c.id = a.productid and b.userid = #{user_id} limit #{offset},#{limit}")
	List<SProductEntity> queryPaymentByUserId(@Param("status") int status, @Param("user_id") int user_id,
			@Param("offset") int offset, @Param("limit") int limit);

	// 查询是否发货商品
	@Select("select c.id,c.name,c.headimg,a.price,a.`status` receiving_status,b.`status` payment_status from s_product c,s_order_extra a,s_order b where a.orderid = b.id and "
			+ "b.`status` = #{order_status} and c.id = a.productid and a.`status` = #{deliver_status} "
			+ "and b.userid = #{user_id} order by b.ctime limit #{offset},#{limit}")
	List<SProductEntity> queryDeliverByUserId(@Param("order_status") int order_status,
			@Param("deliver_status") int deliver_status, @Param("user_id") int user_id, @Param("offset") int offset,
			@Param("limit") int limit);

	// 根据用户id查询所有订单
	@Select("select c.id,c.name,c.headimg,a.price,a.`status` receiving_status,b.`status` payment_status "
			+ "from s_product c,s_order_extra a,s_order b where a.orderid = b.id and c.id = a.productid "
			+ "and b.userid = #{user_id} order by b.ctime limit #{offset},#{limit}")
	List<SProductEntity> queryOrderByUserId(@Param("user_id") int user_id, @Param("offset") int offset,
			@Param("limit") int limit);

	/**
	 * @Title: 查询商品列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select id,name,headimg,price,attribute,stock,status,allowrefund,ctime,"
			+ "salesvolume_false AS 'salesvolumeFalse'"
			+ " from s_product"
			+ "<where>"
				+ "status = 1"
			+ "</where>",
			"order by ctime desc limit #{page},#{pagesize}", "</script>" })
	List<SProductBean> productList(Map<String, Object> params);
	
	/**
	 * @Title: 查询需要进行自动上架的商品列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select * from s_product",
			" where `status` = '0' and goodsontype = '3' order by  ctime desc ", "</script>" })
	List<SProductBean> autoProductList();
	
	/**
	 * @Title: 查询根据一级ID获取简短商品列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select id,name from s_product where onetypeid = #{onetypeid} order by ctime desc", "</script>" })
	List<SProductBean> shortProductList(@Param("onetypeid") int onetypeid);
	
	/**
	 * @Title: 查询根据一级ID获取简短商品列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select id,name from s_product where twotypeid = #{twotypeid} order by ctime desc", "</script>" })
	List<SProductBean> shortProductListTwe(@Param("twotypeid") int twotypeid);
	
	/**
	 * @Title: 查询仓库商品列表(即状态为下架的商品)
	 * @Description: TODO
	 */
	@Select({ "<script>", "select id,name,headimg,price,attribute,stock,status,allowrefund,ctime from s_product",
			"where status = 0 order by ctime desc limit #{page},#{pagesize}", "</script>" })
	List<SProductBean> wareProductList(Map<String, Object> params);
	
	/**
	 * 获取仓库商品记录总数
	 * @return
	 */
	@Select("select count(*) from s_product where status = 0 ")
	int countWareProduct();
	
	/**
	 *  新增商品
	 * @param id  商品ID	
	 * @param product  商品数据
	 * 这里在使用json存入数据库时需要注意类型以及更换变量标示符，同时加转义符
	 */
	@Insert("insert into s_product(`name`,headimg,price,stock,status,allowrefund,ctime,attribute,description,oldprice,coinoffset,code,freight,goodsontype,goodsontime,ispresale,saledelivertime,saledeliverdate,limitnum,endimg,"
			+ "tagids,"
			+ "price_type,"
			+ "rights,"
			+ "salesvolume_false,"
			+ "state_id"
			+ ")"
			+ "values(#{name},#{headimg},#{price},#{stock},#{status},#{allowrefund},now(),convert("+"\'${attribute}\'"+" using utf8mb4),convert("+"\'${description}\'"+" using utf8mb4),"
			+ "#{oldprice},#{coinoffset},#{code},#{freight},#{goodsontype},#{goodsontime},#{ispresale},#{saledelivertime},#{saledeliverdate},#{limitnum},#{endimg},"
			+ "#{tagids},"
			+ "#{priceType},"
			+ "#{rights},"
			+ "#{salesvolumeFalse},"
			+ "#{stateId}"
			+ ")")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(SProductBean product);
	
	/**
	 *  新增商品 (没有json值)
	 * @param id  商品ID	
	 * @param product  商品数据
	 * 这里在使用json存入数据库时需要注意类型以及更换变量标示符，同时加转义符
	 */
	@Insert("insert into s_product(`name`,headimg,price,stock,status,allowrefund,ctime,description,oldprice,coinoffset,code,freight,goodsontype,goodsontime,ispresale,saledelivertime,saledeliverdate,limitnum,endimg)"
			+ "values(#{name},#{headimg},#{price},#{stock},#{status},#{allowrefund},now(),convert("+"\'${description}\'"+" using utf8mb4),"
			+ "#{oldprice},#{coinoffset},#{code},#{freight},#{goodsontype},#{goodsontime},#{ispresale},#{saledelivertime},#{saledeliverdate},#{limitnum},#{endimg})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertNoJson(SProductBean product);
	
	// 根据主键id删除数据
	@Delete("delete from s_product where id = #{id}")
	void delById(@Param("id") int id);
	
	// 根据主键id删除数据
	@Delete("delete from rel_product_category where product_id = #{id}")
	void delCategoryById(@Param("id") int id);
	
	// 根据主键id删除数据
	@Delete("delete from rel_product_detail where product_id = #{id}")
	void delDetailById(@Param("id") int id);
	
	// 根据主键id删除数据
	@Delete("delete from rel_carousel where product_id = #{id}")
	void delCarouselById(@Param("id") int id);
	
	// 根据主键id删除数据
	@Delete("delete from ads where product_id = #{id}")
	void delAdsById(@Param("id") int id);
	
	// 根据主键id删除数据
	@Delete("delete from flash_sale where product_id = #{id}")
	void delFlashById(@Param("id") int id);
	
	// 根据主键id删除数据
	@Delete("delete from rel_collection where product_id = #{id}")
	void delCollectionById(@Param("id") int id);
	
       
	// 根据主键id修改数据
	@Update("update s_product set `name`=#{edit.name},headimg = #{edit.headimg},"
			+ "price =#{edit.price} ,oldprice=#{edit.oldprice},stock=#{edit.stock},status=#{edit.status},allowrefund=#{edit.allowrefund},"
			+ "attribute=convert("+"\'${edit.attribute}\'"+" using utf8mb4),description=convert("+"\'${edit.description}\'"+" using utf8mb4),"
			+ "coinoffset=#{edit.coinoffset},`code`=#{edit.code},"
			+ "freight=#{edit.freight},goodsontype=#{edit.goodsontype},goodsontime=#{edit.goodsontime},ispresale=#{edit.ispresale},"
			+ "saledelivertime=#{edit.saledelivertime},saledeliverdate=#{edit.saledeliverdate},limitnum=#{edit.limitnum},"
			+ "endimg=#{edit.endimg},"
			+ ""
			+ "tagids = #{edit.tagids},"
			+ "price_type = #{edit.priceType},"
			+ "rights = #{edit.rights},"
			+ "salesvolume_false = #{edit.salesvolumeFalse},"
			+ "state_id = #{edit.stateId}"
			+ "  where id = #{edit.id}")
	void updateById(@Param("edit") SProductBean editProduct);
	
	// 根据主键id修改数据(没有json值)
	@Update("update s_product set`name`=#{edit.name},headimg = #{edit.headimg},"
			+ "price =#{edit.price} ,oldprice=#{edit.oldprice},stock=#{edit.stock},status=#{edit.status},allowrefund=#{edit.allowrefund},"
			+ "description=convert("+"\'${edit.description}\'"+" using utf8mb4),coinoffset=#{edit.coinoffset},`code`=#{edit.code},"
			+ "freight=#{edit.freight},goodsontype=#{edit.goodsontype},goodsontime=#{edit.goodsontime},ispresale=#{edit.ispresale},"
			+ "saledelivertime=#{edit.saledelivertime},saledeliverdate=#{edit.saledeliverdate},limitnum=#{edit.limitnum} "
			+ "  where id = #{edit.id}")
	void updateByIdNoJson(@Param("edit") SProductBean editProduct);
	
	// 根据主键id修改数据(没有任何json值)
	@Update("update s_product set `name`=#{edit.name},headimg = #{edit.headimg},"
			+ "price =#{edit.price} ,oldprice=#{edit.oldprice},stock=#{edit.stock},status=#{edit.status},allowrefund=#{edit.allowrefund},"
			+ "coinoffset=#{edit.coinoffset},`code`=#{edit.code},"
			+ "freight=#{edit.freight},goodsontype=#{edit.goodsontype},goodsontime=#{edit.goodsontime},ispresale=#{edit.ispresale},"
			+ "saledelivertime=#{edit.saledelivertime},saledeliverdate=#{edit.saledeliverdate},limitnum=#{edit.limitnum}"
			+ "  where id = #{edit.id}")
	void updateByIdNoJsonAll(@Param("edit") SProductBean editProduct);
	
	/**
	 *  新增商品详情
	 * @param id  商品ID	
	 * @param productDetail  商品详情数据
	 */
	@Insert("insert into rel_product_detail(product_id,resources_id,type)"
			+ "values(#{productDetail.productid},#{productDetail.resourcesid},#{productDetail.type})")
	void insertProductDetail(@Param("productDetail") ProductDetailBean productDetailBean);
	
	/**
	 *  修改商品详情
	 * @param id  商品ID	
	 * @param productDetail  商品详情数据
	 */
	@Update("update rel_product_detail set product_id =#{productDetail.productid},resources_id=#{productDetail.resourcesid},type=#{productDetail.type}"
			+"where id = #{productDetail.id}")
	void updateProductDetail(@Param("productDetail") ProductDetailBean productDetailBean);
	
	// 根据主键id删除商品详情数据
	@Delete("delete from rel_product_detail where id = #{id}")
	void deleteProductDetail(@Param("id") int id);
	
	// 根据主键id删除商品详情数据
	@Delete("delete from rel_product_detail where resources_id = #{id}")
	void deleteProductDetailByResourceId(@Param("id") int id);
	
	
	/**
	 * 获取订单记录总数
	 * @return
	 */
	@Select("select count(*) from s_product")
	int countProduct();
	
	/**
	 *  新增商品所属属性
	 * @param id  商品ID	
	 * @param productDetail  商品详情数据
	 */
	@Insert("insert into tb_product_tag(product_id,tag_id)"
			+ "values(#{productId},#{tagId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertProductTag(TbProductTag tbProductTag);
	
	// 根据主键id删除数据
	@Delete("delete from tb_product_tag where tag_id = #{id}")
	void deleteProductTag(@Param("id") int id);
	
	/**
	 * @Title: 查询商品所属标签列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select DISTINCT gt.id,gt.tag_name,gt.tag_code,gt.create_time,pg.tag_id,pg.product_id from tb_goods_tag gt left join tb_product_tag pg",
			" on gt.id= pg.tag_id where pg.product_id = #{productid} order by gt.create_time desc ", "</script>" })
	List<TbGoodsTagBean> productTagList(@Param("productid") int productid);
	
	/**
	 *  新增商品上架记录表
	 * @param productId  商品ID	
	 * @param categoryId  商品所属二级分类ID
	 */
	@Insert("insert into rel_product_category(product_id,category_id)"
			+ "values(#{productid},#{categoryid})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertProductCategory(RelProductCategoryEntity relProductCategoryEntity);
	
	/**
	 *  删除商品上架记录表
	 * @param productId  商品ID	
	 * @param categoryId  商品所属二级分类ID
	 */
	@Delete("delete from rel_product_category where product_id = #{productid} and category_id = #{categoryid} ")
	void deleteProductCategory(RelProductCategoryEntity relProductCategoryEntity);
	
	//获取指定分类下的商品所属的标签
	@Select( "select id, tag_name, tag_code, type_id, create_time, mod_time from tb_goods_tag  where type_id = #{typeId}")
	List<TbGoodsTag> selectByTypeId(@Param("typeId") int typeId);

	
	
	   //查询一级类目商品
    @Select("select id,name,headimg,price/100 price from s_product where id in(select product_id from rel_product_category " +
            "where category_id= #{category_id}) and `status`!=0 order by ${order_desc} desc")
    List<ProductListResp> queryByCategoryId(@Param("category_id") int category_id,
                                            @Param("order_desc") String order_desc);
    
    //根据ID查询数据
    @Select("select a.*,b.shops_id as shops_id,b.id as shops_product_id  from s_product a left join tb_shops_product b on a.id = b.product_id where b.id = #{id} limit 1")
    SProductEntity queryNewById(@Param("id") int id);
    
    //根据ID查询数据
    @Select("select a.*,b.shops_id as shops_id,b.id as shops_product_id  from s_product a left join tb_shops_product b on a.id = b.product_id where a.id = #{id} and shops_id = #{shops_id} limit 1")
    SProductEntity queryNewById2(@Param("id") int id,@Param("shops_id") int shops_id);
    
}
