package com.hengmall.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hengmall.user.model.TbTopic;
import com.hengmall.user.model.TbTopicAppraise;
import com.hengmall.user.model.TbTopicBean;
import com.hengmall.user.model.TbTopicImg;
import com.hengmall.user.model.Users;

/**
 * Created by Administrator on 2018/5/24.
 */
@Mapper
public interface TbTopicDao {


	// 根据ID查询数据
	@Select("select * from tb_topic where id = #{id} limit 1")
	TbTopic queryById(@Param("id") int id);
	
	// 根据ID查询数据
	@Select("select * from tb_topic where id = #{id} limit 1")
	TbTopicBean queryByIdAll(@Param("id") int id);
	
	/**
	 * @Title: 查询话题列表 分类为最新 返回给后台使用
	 * @Description: TODO
	 */
	@Select({ "<script>", "select id, topic_type, thumbnail, article, is_recommend, create_time, publisher_type, praise_num, publisher_id, appraise_num, see_num,title,video from tb_topic",
			"order by create_time desc ", "</script>" })
	List<TbTopicBean> topicList();
	
	/**
	 * @Title: 查询话题列表 分类为最新，返回给前端使用
	 * @Description: TODO
	 */
	@Select({ "<script>", "select id, topic_type, thumbnail, article, is_recommend, create_time, publisher_type, praise_num, publisher_id, appraise_num, see_num,title,video,(select count(*) from tb_topic_praise where user_id = #{userId} and topic_id = a.id) as is_appraise from tb_topic a",
			"order by create_time desc ", "</script>" })
	List<TbTopicBean> topicListNew(@Param("userId") int userId);

	/**
	 * @Title: 获取推荐的话题列表
	 * @Description: TODO
	 */
	@Select({ "<script>", "select id, topic_type, thumbnail, article, is_recommend, create_time, publisher_type, praise_num, publisher_id, appraise_num, see_num,title,video, (select count(*) from tb_topic_praise where user_id = #{userId} and topic_id = a.id) as is_appraise from tb_topic a",
			" where is_recommend = '1' order by create_time desc ", "</script>" })
	List<TbTopicBean> topicRecommendList(@Param("userId") int userId);
	
	/**
	 * @Title: 获取推荐的话题列表分类为1:文章，2:视频，3:周边
	 * @Description: TODO
	 */
	@Select({ "<script>", "select id, topic_type, thumbnail, article, is_recommend, create_time, publisher_type, praise_num, publisher_id, appraise_num, see_num,title,video,(select count(*) from tb_topic_praise where user_id = #{userId} and topic_id = a.id) as is_appraise from tb_topic a",
			" where topic_type = #{type} order by create_time desc ", "</script>" })
	List<TbTopicBean> topicListType(@Param("type") int type,@Param("userId") int userId);
	
	/**
	 * @Title: 获取推荐的话题列表分类为1：卖家，2:买家
	 * @Description: TODO
	 */
	@Select({ "<script>", "select id, topic_type, thumbnail, article, is_recommend, create_time, publisher_type, praise_num, publisher_id, appraise_num, see_num,title,video,(select count(*) from tb_topic_praise where user_id = #{userId} and topic_id = a.id) as is_appraise from tb_topic a",
			" where publisher_type = #{type} order by create_time desc ", "</script>" })
	List<TbTopicBean> topicListPublisher(@Param("type") int type,@Param("userId") int userId);
	
	/**
	 *  新增话题
	 * @param id  话题ID	
	 * @param topic  话题数据
	 * 这里在使用json存入数据库时需要注意类型以及更换变量标示符，同时加转义符
	 */
	@Insert("insert into tb_topic(topic_type, thumbnail, article, is_recommend, create_time, publisher_type, praise_num, publisher_id, appraise_num, see_num,title,video)"
			+ "values(#{topic_type},convert("+"\'${thumbnail}\'"+" using utf8mb4), convert("+"\'${article}\'"+" using utf8mb4), #{is_recommend}, "
			+ "now(), #{publisher_type}, #{praise_num}, #{publisher_id}, #{appraise_num}, #{see_num} ,#{title},#{video})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(TbTopic topic);
	
	
	// 根据主键id删除数据
	@Delete("delete from tb_topic where id = #{id}")
	int delById(@Param("id") int id);
       
	// 根据主键id修改数据
	@Update("update tb_topic set topic_type =#{edit.topic_type},thumbnail=convert("+"\'${edit.thumbnail}\'"+" using utf8mb4),is_recommend = #{edit.is_recommend},"
			+ "publisher_type =#{edit.publisher_type} ,praise_num=#{edit.praise_num},publisher_id=#{edit.publisher_id},appraise_num=#{edit.appraise_num},see_num=#{edit.see_num},"
			+ "article=convert("+"\'${edit.article}\'"+" using utf8mb4),title =#{edit.title},video =#{edit.video}"
			+ "  where id = #{edit.id}")
	int updateById(@Param("edit") TbTopic topic);
	
	// 根据主键id修改数据
	@Update("update tb_topic set topic_type =#{edit.topic_type},is_recommend = #{edit.is_recommend},"
			+ "publisher_type =#{edit.publisher_type} ,praise_num=#{edit.praise_num},publisher_id=#{edit.publisher_id},appraise_num=#{edit.appraise_num},see_num=#{edit.see_num},"
			+ "title =#{edit.title},video =#{edit.video}"
			+ "  where id = #{edit.id}")
	int updateById2(@Param("edit") TbTopic topic);
	
	/**
	 * 获取话题记录总数
	 * @return
	 */
	@Select("select count(*) from tb_topic")
	int countTopic();
	
	/**
	 *  新增话题缩略图
	 * @param id  话题ID	
	 * @param productDetail  商品详情数据
	 */
	@Insert("insert into tb_topic_img(topic_id,resources_id,type)"
			+ "values(#{tbTopicImg.topic_id},#{tbTopicImg.resources_id},#{tbTopicImg.type})")
	void insertTopicImg(@Param("tbTopicImg")  TbTopicImg tbTopicImg);
	
	/**
	 *  修改话题缩略图
	 * @param id  话题ID	
	 * @param productDetail  商品详情数据
	 */
	@Update("update tb_topic_img set topic_id =#{tbTopicImg.topic_id},resources_id=#{tbTopicImg.resources_id},type=#{tbTopicImg.type}"
			+"where id = #{tbTopicImg.id}")
	void updateTopicImg(@Param("tbTopicImg") TbTopicImg tbTopicImg);
	
	// 根据主键id删除数据
	@Delete("delete from tb_topic_img where topic_id = #{topic_id} and resources_id = #{resources_id}")
	int delTopicImgById(@Param("topic_id") int topic_id,@Param("resources_id") int resources_id);
	
	/**
	 * 获取话题记录总数
	 * @return
	 */
	@Select("select count(*) from tb_topic_praise where user_id = #{userId} and topic_id = #{topicId}")
	int searchTopic(@Param("userId") int userId,@Param("topicId") int topicId);
	
	/**
	 * 获取话题评论点赞记录总数
	 * @return
	 */
	@Select("select count(*) from tb_appraise_praise where user_id = #{userId} and appraise_id = #{appraiseId}")
	int searchAppraise(@Param("userId") int userId,@Param("appraiseId") int appraiseId);
	
	/**
	 *  新增话题点赞记录
	 * @param id  话题ID	
	 * @param productDetail  商品详情数据
	 */
	@Insert("insert into tb_topic_praise(topic_id,user_id)"
			+ "values(#{topicId},#{userId})")
	int insertTopicPraise(@Param("userId") int userId,@Param("topicId") int topicId);
	
	// 根据主键id删除点赞记录
	@Delete("delete from tb_topic_praise where topic_id = #{topicId} and user_id = #{userId}")
	int delTopicPraiseById(@Param("userId") int userId,@Param("topicId") int topicId);
	
	/**
	 * 获取话题记录总数
	 * @return
	 */
	@Select("select id from users where token = #{token}")
	Users searchUsers(@Param("token") String token);

	/**
	 *  新增话题评论记录
	 * @param userId  用户ID		
	 * @param topicId  话题ID
	 * @param appraiseUserId  被评论人ID
	 * @param context  评论内容
	 * @return
	 */
	@Insert("insert into tb_topic_appraise(topic_id,user_id,create_time,appraise_user,context)"
			+ "values(#{topic_id},#{user_id},now(),#{appraise_user},#{context})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertTopicAppraise(TbTopicAppraise appraise);
	
	/**
	 * 获取话题评论信息
	 * @return
	 */
	@Select("select a.id,a.user_id,a.create_time,a.look,a.appraise_user,a.topic_id,a.context, b.nickname as userName ,b.avatar_url as url from tb_topic_appraise a left join users b on a.user_id = b.id where a.id = #{id} order by create_time asc ")
	TbTopicAppraise queryAppraise(@Param("id") int id);
	
	// 根据主键id修改数据
	@Update("update tb_topic_appraise set look =#{edit.look}"
			+ " where id = #{edit.id}")
	int updateAppraiseById(@Param("edit") TbTopicAppraise appraise);
	
	/**
	 * 查询搜索内容相关数据
	 * @return
	 */
	@Select("select * from tb_topic where `article` like '%${context}%' ")
	List<TbTopicBean> searchTopicList(@Param("context") String context);
	
	/**
	 * 根据话题ID查询所有评论列表
	 * @param topicId
	 * @return
	 */
	@Select("select a.id,a.user_id,a.create_time,a.look,a.appraise_user,a.topic_id,a.context, b.nickname as userName ,b.avatar_url as url,(select count(*) from tb_appraise_praise where user_id = #{userId} and appraise_id = a.id) as is_appraise from tb_topic_appraise a left join users b on a.user_id = b.id where a.topic_id = #{topicId} order by a.create_time asc")
	List<TbTopicAppraise> queryTopicAppraise(@Param("topicId") int topicId,@Param("userId") int userId);
	
	
	/**
	 * 获取话题评论点赞记录总数
	 * @return
	 */
	@Select("select count(*) from tb_appraise_praise where user_id = #{userId} and appraise_id = #{appraiseId}")
	int searchAppraiseTopic(@Param("userId") int userId,@Param("appraiseId") int appraiseId);
	
	/**
	 * 获取话题评论点赞总数
	 * @return
	 */
	@Select("select count(*) from tb_appraise_praise where appraise_id = #{appraiseId}")
	int countAppraisePraise(@Param("appraiseId") int appraiseId);
	
	/**
	 *  新增话题评论点赞记录
	 * @param id  话题ID	
	 * @param productDetail  商品详情数据
	 */
	@Insert("insert into tb_appraise_praise(appraise_id,user_id)"
			+ "values(#{appraiseId},#{userId})")
	int insertAppraisePraise(@Param("userId") int userId,@Param("appraiseId") int appraiseId);
	
	// 根据主键id删除话题评论点赞记录
	@Delete("delete from tb_appraise_praise where appraise_id = #{appraiseId} and user_id = #{userId}")
	int delAppraisePraiseById(@Param("userId") int userId,@Param("appraiseId") int appraiseId);
	
	/**
	 * 获取话题点赞总数
	 * @return
	 */
	@Select("select count(*) from tb_topic_praise where topic_id = #{topicId}")
	int countTopicPraise(@Param("topicId") int topicId);

	
	/**
	 * 根据评论ID查询所有评论子列表
	 * @param appraiseId
	 * @return
	 */
	@Select("select a.id,a.user_id,a.create_time,a.look,a.appraise_user,a.topic_id,a.context, b.nickname as userName ,b.avatar_url as url,(select count(*) from tb_appraise_praise where user_id = #{userId} and appraise_id = a.id) as is_appraise from tb_topic_appraise a left join users b on a.user_id = b.id where a.appraise_user = #{appraiseId} OR a.id= #{appraiseId} order by a.create_time asc")
	List<TbTopicAppraise> queryTopicAppraiseChild(@Param("appraiseId") int appraiseId,@Param("userId") int userId);

	@Select("select * from tb_topic_appraise where id = #{id}")
	TbTopicAppraise queryAppraisePraise(@Param("id") int id);
}
