package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.SProductEntity;
import com.hengmall.goods.model.ShopsOrderListEntity;
import com.hengmall.goods.model.api.CollectionListResp;
import com.hengmall.goods.model.api.MyFootprintListResp;
import com.hengmall.goods.model.api.ProductListResp;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
@Mapper
public interface SProductDao {

    //按请求级别查询对应的商品
    @Deprecated
    @Select("select * from s_product c,s_category a, rel_product_category b where a.id = b.category_id and " +
            "c.id=b.product_id and a.`level` = #{level}")
    List<SProductEntity> findByLevel(@Param("level") int level);


    //根据商品类别主键ID查询商品信息,带有排序功能
    @Select("select * from s_product a,(select * from rel_product_category where " +
            "category_id=(select id from s_category where id = #{id} limit 1)) b where a.id = b.product_id order by ${order_desc} desc")
    List<ProductListResp> queryBySCategoryId2(@Param("id") int id,@Param("order_desc") String order_desc);
    
    //根据商品类别主键ID查询商品信息，不带有排序功能
    @Select("select * from s_product a,(select * from rel_product_category where " +
            "category_id=(select id from s_category where id = #{id} limit 1)) b where a.id = b.product_id")
    List<SProductEntity> queryBySCategoryId(@Param("id") int id);


    //商品搜索 keys关键词
    @Select("select b.id as id,a.price/100*1.0 as price, a.name as name,  a.headimg as headimg, b.shops_id as shops_id,b.id as shops_product_id  from s_product a left join tb_shops_product b on a.id = b.product_id  where a.`name` like '%${keys}%'")
    List<ProductListResp> findByKeys(@Param("keys") String keys);


    //根据ID查询数据
    @Select("select a.*,b.shops_id as shops_id,b.id as shops_product_id  from s_product a left join tb_shops_product b on a.id = b.product_id where b.id = #{id} limit 1")
    SProductEntity queryById(@Param("id") int id);
    
    //根据ID查询数据
    @Select("select a.*,b.shops_id as shops_id,b.id as shops_product_id  from s_product a left join tb_shops_product b on a.id = b.product_id where b.id = #{id} limit 1")
    ProductListResp queryByIdResp(@Param("id") int id);
    
    //根据ID查询数据
    @Select("select state_name from tb_state where id = #{id}")
    String queryStateNameById(@Param("id") int id);

    //根据ID查询数据
    @Select("select price from s_product where id = #{id} limit 1")
    Integer queryPrice(@Param("id") int id);


    //根据user_id 和 支付状态 查询
    @Select("select * from s_product c,s_order_extra a,s_order b where a.order_id = b.id and b.`status` = #{status} " +
            "and c.id = a.product_id and b.user_id = #{user_id} limit #{offset},#{limit}")
    List<SProductEntity> queryPaymentByUserId(@Param("status") int status, @Param("user_id") int user_id,
                                              @Param("offset") int offset, @Param("limit") int limit);


    //查询是否发货商品
    @Select("select a.product_id id,c.name,c.headimg,a.price,a.`status` payment_status," +
            "date(b.created_time)created_time,a.order_id order_id,a.appraise_status,a.attrs,b.out_trade_no ,a.shops_id,c.price_type   " +
            "from s_order_extra a,s_order b,s_product c,tb_shops_product d where a.order_id = b.id and a.product_id = d.id and c.id = d.product_id " +
            "and b.user_id = #{user_id} and a.`status` = #{order_status} and " +
            "b.hide_status=0 and a.shops_id = #{shops_id} and b.out_trade_no = #{out_trade_no}  order by b.updated_time desc limit #{offset},#{limit}")
    List<SProductEntity> queryDeliverByUserId(@Param("user_id") int user_id, @Param("order_status") int order_status, @Param("offset") int offset,
                                              @Param("limit") int limit,@Param("shops_id") int shops_id,@Param("out_trade_no") String out_trade_no);
    
    /*@Select("select c.id,c.name,c.headimg,a.price,a.`status` receiving_status,b.`status` payment_status,date(b.created_time)created_time," +
            "a.order_id order_id,a.appraise_status from s_product c,s_order_extra a,s_order b where a.order_id = b.id and " +
            "b.`status` = #{order_status} and c.id = a.product_id and a.`status` = #{deliver_status} " +
            "and b.user_id = #{user_id} and b.hide_status = 0 order by b.created_time limit #{offset},#{limit}")
    List<SProductEntity> queryDeliverByUserId(@Param("order_status") int order_status, @Param("deliver_status") int deliver_status,
                                              @Param("user_id") int user_id, @Param("offset") int offset,
                                              @Param("limit") int limit);
*/

    //根据用户id查询所有订单
  /*  @Select("select c.id,c.name,c.headimg,a.price,a.`status` receiving_status,b.`status` payment_status,date(b.created_time)created_time," +
            "a.order_id order_id,a.appraise_status from s_product c,s_order_extra a,s_order b where a.order_id = b.id and c.id = a.product_id " +
            "and b.user_id = #{user_id} and b.hide_status = 0 order by b.created_time limit #{offset},#{limit}")
    List<SProductEntity> queryOrderByUserId(@Param("user_id") int user_id, @Param("offset") int offset,
                                            @Param("limit") int limit);*/
    
    @Select("select a.product_id id,c.name,c.headimg,a.price,a.`status` payment_status," +
            "date(b.created_time)created_time,a.order_id order_id,a.appraise_status,a.attrs,b.out_trade_no,a.shops_id,c.price_type from s_order_extra a," +
            "s_order b,s_product c,tb_shops_product d where a.order_id = b.id and a.product_id = d.id and c.id = d.product_id and b.hide_status =0 and " +
            "b.user_id = #{user_id} and a.shops_id = #{shops_id} and b.out_trade_no = #{out_trade_no} and b.status >=0 order by b.updated_time desc limit #{offset},#{limit}")
    List<SProductEntity> queryOrderByUserId(@Param("user_id") int user_id, @Param("offset") int offset,
    										@Param("limit") int limit,@Param("shops_id") int shops_id,@Param("out_trade_no") String out_trade_no);

    @Select("select DISTINCT b.out_trade_no ,a.pay_no, a.shops_id as shopsId,b.id AS order_id,b.price AS total_price,a.appraise_status as appraise_status,b.buy_status as buy_status from s_order_extra a," +
            "s_order b,s_product c,tb_shops_product d where a.order_id = b.id and a.product_id = d.id and c.id = d.product_id and b.hide_status =0 and  b.status >=0  and " +
            " b.user_id = #{user_id} order by b.updated_time desc limit #{offset},#{limit}")
    List<ShopsOrderListEntity> queryShopsByUserId(@Param("user_id") int user_id, @Param("offset") int offset,
                                            @Param("limit") int limit);

    //查询user_id的收藏商品列表
    @Select("select c.id as id,a.name,a.headimg,a.price,b.id collection_id from s_product a,rel_collection b, tb_shops_product c " +
            "where c.product_id = a.id and b.product_id = c.id and user_id = #{user_id}")
    List<CollectionListResp> queryCollectionByUserId(@Param("user_id") int user_id);


    //查询user_id的浏览过的商品列表
    @Select("select c.id,a.name,a.headimg,a.price,b.id footprintId,date(b.created_time)browseDate " +
            "from s_product a,rel_footprint b, tb_shops_product c where user_id = #{user_id} and a.id=c.product_id and b.product_id = c.id " +
            "order by b.created_time desc")
    List<MyFootprintListResp> queryFootprintByUserId(@Param("user_id") int user_id);
    
    //查询user_id的浏览过的商品列表
    @Select("select c.id,a.name,a.headimg,a.price,b.id footprintId,date(b.created_time)browseDate " +
            "from s_product a,rel_footprint b, tb_shops_product c where user_id = #{user_id} and a.id=c.product_id and b.product_id = c.id " +
            "order by b.created_time desc limit 6")
    List<MyFootprintListResp> queryGuessByUserId(@Param("user_id") int user_id);

    //查询一级类目商品
    @Select("select id,name,headimg,price/100 price from s_product where id in(select product_id from rel_product_category " +
            "where category_id= #{category_id}) and `status`!=0 order by ${order_desc} desc")
    List<ProductListResp> queryByCategoryId(@Param("category_id") int category_id,
                                            @Param("order_desc") String order_desc);


    //根据SKU索引查询
    @Select("select attribute->'$.details' attribute from s_product a left join tb_shops_product b on a.id = b.product_id where b.id = #{product_id} limit 1")
    SProductEntity queryBySKUIndex(@Param("product_id") int product_id);


    //查询二级类目商品
    @Select("select * from s_product c,s_category a, rel_product_category b where " +
            "a.id = b.category_id and c.id=b.product_id and a.`level` = #{level}")
    List<ProductListResp> findByLevels(@Param("level") int level);


    /**
     * @Title: 查询商品列表
     * @Description: TODO
     */
    @Select({"<script>",
            "select id,name,headimg,price,attribute,stock,status,allowrefund,ctime from s_product",
            "order by ctime desc limit 8",
            "</script>"})
    List<SProductEntity> productList();
    
    /**
     * @Title: 查询商品列表
     * @Description: TODO
     */
    @Select({"<script>",
            "select DISTINCT  a.id,b.`name`,b.headimg,b.price,b.attribute,b.stock,b.status,b.allowrefund,b.ctime from tb_shops_product a left join s_product b on a.product_id = b.id where b.`status` = 1  ",
            "order by b.ctime desc limit 8",
            "</script>"})
    List<SProductEntity> shopProductList();

    /**
     * @Title: 查询商品列表
     * @Description: TODO
     */
    @Select("select b.id,a.name,a.headimg,a.price,a.attribute,a.stock,a.status,a.allowrefund,a.ctime FROM s_product a left join tb_shops_product b on a.id = b.product_id "
    		+ "where (SELECT JSON_CONTAINS(a.tagids,'[\"${tagIdsStr}\"]'))=1 "
            + "order by a.ctime desc limit 8")
	List<SProductEntity> queryByTagIds(@Param("tagIdsStr") String tagIdsStr);
}
