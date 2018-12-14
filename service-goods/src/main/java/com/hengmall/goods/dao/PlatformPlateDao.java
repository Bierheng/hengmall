package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.PlatformInfoEntity;
import com.hengmall.goods.model.PlatformPlateEntity;
import com.hengmall.goods.model.ProductReq;

@Repository
public interface PlatformPlateDao {
	
	@Select("select * from platform_plate where type_id = #{typeId} order by order_num")
	List<PlatformPlateEntity> queryPlatformPlateList(@Param("typeId") int typeId);
	
	@Select("select b.id as productId ,b.name as productName ,b.headimg as url ,b.price/100 as price "
			+ "from platform_plateproduct a left join s_product b on a.product_id = b.id"
			+ "  where platform_plate_id = #{plateId} ")
	List<ProductReq> queryPlatformPlateProductList(int plateId);
	
	@Select("select b.id as productId ,a.name as productName ,a.headimg as url ,a.price/100 as price from s_product a left join tb_shops_product b on a.id = b.product_id where b.shops_id = #{shopsId} and b.product_id in ( ${platform_ids} )")
	List<ProductReq> queryPlatformPlateProductListBYshops(@Param("shopsId") int shopsId,@Param("platform_ids") String platform_ids);
	
	@Select("select * from platform_info limit 1")
	PlatformInfoEntity queryPlatformInfo();
}
