package com.hengmall.user.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.hengmall.user.model.Ads;
import com.hengmall.user.model.DataTablesResult;
import com.hengmall.user.model.DelBean;
import com.hengmall.user.model.Result;
import com.hengmall.user.model.TbTopic;
import com.hengmall.user.model.TbTopicAppraise;

public interface TopicService {
    
    /**
     * 通过id获取活动
     * @param id
     * @return
     */
    Ads getAdsById(Integer id) throws Exception;

    /**
     * 获得活动列表
     * @param parentId
     * @return
     */
    DataTablesResult getAdsList(int draw ,int length) throws Exception;

    /**
     * 添加活动
     * @param tbItemCat
     * @return
     */
    int addAds(Ads ads) throws Exception;

    /**
     * 编辑活动
     * @param tbItemCat
     * @return
     */
    int updateAds(Ads ads) throws Exception;

    /**
     * 删除单个活动
     * @param id
     */
    int deleteAds(Integer id) throws Exception;
    
    /**
     * 获取活动总记录数
     * @return
     * @throws Exception
     */
    int countAds() throws Exception;
    
    /**
     * 获取话题总记录数
     * @return
     * @throws Exception
     */
    int countTopic() throws Exception;
    
    /**
     * 通过id获取话题
     * @param id
     * @return
     */
    TbTopic getTbTopicById(Integer id) throws Exception;

    /**
     * 获得话题列表
     * @param parentId
     * @return
     */
    DataTablesResult getTbTopicList(int draw ,int length,int userId) throws Exception;

    /**
     * 添加话题
     * @param tbItemCat
     * @return
     */
    int addTbTopic(TbTopic tbTopic) throws Exception;

    /**
     * 编辑话题
     * @param tbItemCat
     * @return
     */
    int updateTbTopic(TbTopic tbTopic) throws Exception;

    /**
     * 删除单个话题
     * @param id
     */
    int deleteTbTopic(Integer id) throws Exception;
	
    /**
     * 根据编号删除评论
     * @param ids  评论编号
     * @return
     * @throws Exception
     */
	Result<Object> delById(DelBean ids) throws Exception;
	
	    /**
     * 获取所有评论列表
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
	DataTablesResult queryAppraiseList(int draw, int length) throws Exception;

	/**
	 * (前端)获取推荐话题分类列表
	 * @param type
	 * @param userId
	 * @return
	 */
	List<TbTopic> topicRecommendList(int type ,int userId);
	/**
	 * (前端)获取推荐话题分类列表
	 * @param i
	 * @param userId
	 * @return
	 */
	List<TbTopic> topicListType(int i,int userId);
	/**
	 * (前端)获取推荐话题分类列表
	 * @param i
	 * @param userId
	 * @return
	 */
	List<TbTopic> topicListPublisher(int i,int userId);

	/**
	 * 点赞
	 * @param topicId
	 * @param userId
	 * @return
	 */
	int praise(int topicId, int userId);

	/**
	 * 用于后台获取话题列表
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
	
	Result<Object> countAppraise() throws Exception;
	
	/**
	 * 获取评论子评论列表
	 * @param appraiseId
	 * @param userId
	 * @return
	 */
	List<TbTopicAppraise> getAppraiseChildList(int appraiseId, int userId,int draw, int length);

	TbTopicAppraise queryAppraisePraise(int appraiseId);
}
