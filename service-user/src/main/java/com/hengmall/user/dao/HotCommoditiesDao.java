package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hengmall.user.model.HotCommodities;
import com.hengmall.user.model.manage.product.SProductBean;

/**
 * Created by Administrator on 2018/5/24.
 */
@Repository
public interface HotCommoditiesDao {

	// 根据id查询
	@Select("select * from hot_commodities where id = #{id}")
	HotCommodities queryById(@Param("id") int id);

	// 新增
	@Insert("insert into hot_commodities (`name`,icon,type,created_time)"
			+ "values(#{name},#{icon},#{type},now())")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(HotCommodities hotCommodities);
	
	/**
	 * 获取记录总数
	 * @return
	 */
	@Select("select count(*) from hot_commodities")
	int countCommodities();
	
	/**
	 * @Title: 查询热门分类列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select id,`name`,icon,type,created_time from hot_commodities",
			"order by created_time desc", "</script>" })
	List<SProductBean> productList();

	// 根据主键id删除数据
	@Delete("delete from hot_commodities where id = #{id}")
	void delById(@Param("id") int id);
       
	// 根据主键id修改数据
	@Update("update hot_commodities set `name`=#{edit.name},icon = #{edit.icon},type =#{edit.type}"
			+ "where id = #{edit.id}")
	void updateById(@Param("edit") HotCommodities hotCommodities);

}
