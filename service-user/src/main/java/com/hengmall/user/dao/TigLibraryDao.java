package com.hengmall.user.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hengmall.user.model.tigLibrary.Product;
import com.hengmall.user.model.tigLibrary.TigLibraryAddReq;
import com.hengmall.user.model.tigLibrary.TigLibraryRes;

/**
 * 标签库管理 Dao
 * @author Administrator
 *
 */
@Mapper
public interface TigLibraryDao {

	/**
	 * 标签库列表
	 * @param tigLibraryReq
	 * @return
	 */
	@Select("<script>"
			+ "SELECT "
				+ "a.id AS 'id',"
				+ "a.parent_id AS 'parentId',"
				+ "a.icon AS 'icon',"
				+ "a.name AS 'name'"
			+ " FROM "
				+ " tig_library a"
				+ " ORDER BY a.name"
			+ "</script>")
	List<TigLibraryRes> tigLibraryList();
	
	
	/**
	 * 检查标签库是否存在
	 * @param id
	 * @return
	 */
	@Select("SELECT a.id FROM tig_library a WHERE a.id = #{id}")
	String tigLibrary(String id);
	
	/**
	 * 检查标签库是否存在
	 * @param id
	 * @return
	 */
	@Select("SELECT id,parent_id as parentId,`name`,icon,level  FROM tig_library  WHERE id = #{id}")
	TigLibraryRes query(int id);
	
	
	/**
	 * 所有关联商品的标签
	 * @param tagids
	 * @return
	 */
	@Select("SELECT "
			+ "a.id AS 'id',"
			+ "a.tagids AS 'tagids'"
		+ " FROM "
			+ "s_product a"
		+ " WHERE "
			+ "a.tagids LIKE CONCAT('%',#{tagids},'%')")
	List<Product> products(String tagids);

	/**
	 * 标签库添加
	 * @param tigLibraryAddReq
	 * @return
	 */
	@Insert("<script>"
			+ "INSERT INTO"
			+ "	tig_library"
			+ " ("
				+ "parent_id,"
				+ "name,"
				+ "icon,"
				+ "level"
			+ ") VALUES ("
				+ "#{pid},"
				+ "#{name},"
				+ "#{icon},"
				+ "(SELECT IFNULL((SELECT t.`level` FROM tig_library t WHERE t.id = #{pid}),0)+1)"
			+ ")"
		  + "</script>")
	@Options(useGeneratedKeys = true, keyProperty = "idT", keyColumn = "idT")
	int tigLibraryAdd(TigLibraryAddReq tigLibraryAddReq);
	
	/**
	 * 标签库修改
	 * @param tigLibraryAddReq
	 * @return
	 */
	@Update("<script>"
			+ "UPDATE tig_library"
			+ " SET "
			+ "parent_id = #{pid},"
			+ "name = #{name},"
			+ "icon = #{icon},"
			+ "level = (SELECT a.lv + 1 FROM ((SELECT IFNULL( ( SELECT t.`level` FROM tig_library t WHERE t.id = #{pid} ), 0 )  AS 'lv' )) a)"
			+ "WHERE id = #{id}"
		  + "</script>")
	int tigLibraryUpdate(TigLibraryAddReq tigLibraryAddReq);
	
	
	/**
	 * 标签库删除
	 * @param tigLibraryDelReq
	 * @return
	 */
	@Delete("<script>DELETE FROM tig_library "
			+ "<where>"
				+ "id IN"
				+ "<foreach collection='childIds' item='childId' open='(' close=')' separator=','>"
					+ "#{childId}"
				+ "</foreach>"
			+ "</where>"
			+ "</script>")
	int tigLibraryDel(@Param("childIds") Set<String> childIds);
	
	
	/**
	 * 更新商品关联的标签
	 * @param id
	 * @param tagids
	 * @return
	 */
	@Update("UPDATE s_product SET tagids = #{tagids} WHERE id = #{id}")
	int updateProduct(@Param("id") String id,@Param("tagids") String tagids);
}
