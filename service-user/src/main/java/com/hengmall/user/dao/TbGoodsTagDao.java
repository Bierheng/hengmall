package com.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.server.entity.TbGoodsTag;

/**
 * Created by  wuhengbin on 2018/5/24.
 */
@Repository
public interface TbGoodsTagDao {

	// 根据id查询
	@Select("select * from tb_goods_tag where id = #{id}")
	TbGoodsTag queryById(@Param("id") int id);
	
	// 根据id查询
	@Select("select * from tb_goods_tag")
	List<TbGoodsTag> queryForList();

	// 新增
	@Insert("insert into tb_goods_tag ( tag_name, tag_code, type_id, create_time, mod_time)"
			+ "values ( #{insert.tag_name}, #{insert.tag_code}, "
			+ "#{insert.type_id}, now(), #{insert.mod_time})")
	int insert(@Param("insert") TbGoodsTag insert);

	// 根据主键id删除数据
	@Delete("delete from tb_goods_tag where id = #{id}")
	int delById(@Param("id") int id);
       
	// 根据主键id修改数据
	@Update("update tb_goods_tag set tag_name = #{record.tag_name},"
			+ "tag_code = #{record.tag_code},type_id = #{record.type_id},mod_time = now() WHERE id = #{record.id}")
	int updateById(@Param("record") TbGoodsTag record);
	
	/**
	 * 获取抢购活动总数
	 * @return
	 */
	@Select("select count(*) from tb_goods_tag")
	int countTbGoodsTag();
}
