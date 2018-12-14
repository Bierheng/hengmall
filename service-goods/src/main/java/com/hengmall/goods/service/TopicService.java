package com.hengmall.goods.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.hengmall.goods.model.DataTablesResult;
import com.hengmall.goods.model.TbTopic;
import com.hengmall.goods.model.TbTopicAppraise;

public interface TopicService {

	/**
	 * (前端)获取推荐话题分类列表
	 * @param type
	 * @param userId
	 * @return
	 */
	List<TbTopic> topicRecommendList(int type ,int userId);
	/**
	 * (前端)获取话题分类列表，i=1为文章，i=2为视频
	 * @param i
	 * @param userId
	 * @return
	 */
	List<TbTopic> topicListType(int i,int userId);
	/**
	 * (前端)获取话题分类列表，分类为最热，以点赞数以及评论数排序
	 * @param i
	 * @param userId
	 * @return
	 */
	List<TbTopic> topicHotList(int userId);
	
	/**
	 * (前端)获取话题分类列表 分类为1：卖家，2:买家
	 * @param i
	 * @param userId
	 * @return
	 */
	List<TbTopic> topicListPublisher(int i,int userId);

	/**
	 * (前端)获取话题主的话题列表
	 * @param i
	 * @param userId
	 * @return
	 */
	List<TbTopic> topicListByPublisher(int i,int userId);
	
	/**
	 * 点赞
	 * @param topicId
	 * @param userId
	 * @return
	 */
	int praise(int topicId, int userId);
	
    /**
     * 获得话题列表,分类为最新
     * @param parentId
     * @return
     */
    DataTablesResult getTbTopicList(int draw ,int length,int userId) throws Exception;
    
    /**
     * 编辑话题
     * @param tbItemCat
     * @return
     */
    int updateTbTopic(TbTopic tbTopic) throws Exception;
    
    /**
     * 通过id获取话题
     * @param id
     * @return
     */
    TbTopic getTbTopicById(Integer id) throws Exception;

	/**
	 * 用于获取话题列表
	 * @param draw 
	 * @param length
	 * @return
	 * @throws Exception
	 */
	DataTablesResult getTbTopicList1(int draw, int length) throws Exception;

	/**
	 * 评论或者回复评论
	 * @param topicId
	 * @param userId
	 * @param appraiseUserId
	 * @param context
	 * @return
	 */
	TbTopicAppraise appraise(int topicId, int userId, int appraiseUserId, String context);

	/**
	 * 根据token获取用户ID
	 * @param token
	 * @return
	 */
	int searchUser(String token);

	/**
	 * 搜索功能，目前只支持文章内容的模糊查询
	 * @param context
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	List<TbTopic> searchTopicList(String context)throws UnsupportedEncodingException;

	/**
	 * 获取评论列表
	 * @param topicId
	 * @param length 
	 * @param draw 
	 * @return
	 */
	List<TbTopicAppraise> getTopicAppraise(int topicId, int userId, int draw, int length);

	/**
	 * 在话题列表的点赞
	 * @param topicId
	 * @param userId
	 * @return
	 */
	int appraisePraise(int appraiseId, int userId);

	/**
	 * 判断是否已经点赞
	 * @param topicId
	 * @param userId
	 * @return
	 */
	boolean isPraise(int topicId, int userId);

	/**
	 * 统计点赞数量
	 * @param topicId
	 * @return
	 */
	int countPraise(int topicId);

	/**
	 * 统计点赞数量
	 * @param appraiseId
	 * @return
	 */
	int countAppraisePraise(int appraiseId);
	
	/**
	 * 判断是否点赞
	 * @param appraiseId
	 * @param userId
	 * @return
	 */
	boolean isAppraisePraise(int appraiseId, int userId);
	
	/**
	 * 获取评论子评论列表
	 * @param appraiseId
	 * @param userId
	 * @return
	 */
	List<TbTopicAppraise> getAppraiseChildList(int appraiseId, int userId,int draw, int length);

	TbTopicAppraise queryAppraisePraise(int appraiseId);
}
