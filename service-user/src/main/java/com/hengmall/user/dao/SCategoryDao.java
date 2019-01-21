package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hengmall.user.model.SCategory;
import com.hengmall.user.model.SCategoryEntity;
import com.hengmall.user.model.TbGoodsTag;

/**
 * Created by Administrator on 2018/5/24.
 */
@Mapper
public interface SCategoryDao {

	// 按请求级别查询
	@Select("select * from s_category where level=#{level} limit 10")
	List<SCategory> queryByLevel(@Param("level") int level);

	@Select("select * from s_category where pid = #{id}")
	List<SCategory> queryByPid(@Param("id") int id);
	
	@Select("select a.id, a.name, a.level, a.pid, a.icon1, a.icon2 ,b.name as pname,"
			+ "a.shops_id AS 'shops_id',"
			+ "s.shops_name AS 'shops_name' from s_category a "
			+ "join s_category b on a.pid = b.id "
			+ "LEFT JOIN shops_location s ON a.shops_id = s.id"
			+ " where a.`level` = #{grade} order by b.name")
	List<SCategory> queryByPidTwo(@Param("grade") int grade);
	
	// 根据id查询
	@Select("select id, name, level, pid, icon1, icon2 ,name ,shops_id ,shops_name from s_category where id = #{id}")
	SCategory queryById(@Param("id") int id);
	
	// 根据id查询
	@Select("select * from s_category ")
	List<SCategory> queryByIdAll();
	
	// 按请求级别查询
	@Select("select * from s_category where level=#{level}")
	List<SCategory> queryByLevelAll(@Param("level") int level);
	
	/**
	 * @Title: 根据分类查询商品标签列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select gt.id,gt.tag_name,gt.tag_code,gt.type_id,gt.create_time from tb_goods_tag gt ",
			" where gt.type_id = #{typeid} order by gt.create_time desc ", "</script>" })
	List<TbGoodsTag> productTagList(@Param("typeid") int typeid);
	
	@Insert("insert into s_category ( name, level,  pid, icon1, icon2,shops_id)"
			+ "values ( #{insert.name}, #{insert.level}, #{insert.pid}, #{insert.icon1}, #{insert.icon2},#{insert.shopsId})")
	int insert(@Param("insert") SCategoryEntity insert);
	
	@Update("update s_category set name = #{record.name},level = #{record.level},pid = #{record.pid},shops_id = #{record.shopsId}"
			+ "icon1 = #{record.icon1},icon2 = #{record.icon2} where id=#{record.id}")
	int updateById(@Param("record") SCategoryEntity record);
	
	// 根据主键id删除数据
	@Delete("delete from s_category where id = #{id}")
	int delById(@Param("id") int id);
}
