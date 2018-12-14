package com.hengmall.goods.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.hengmall.goods.model.DataTablesResult;
import com.hengmall.goods.model.PraiseReq;
import com.hengmall.goods.model.TbTopic;
import com.hengmall.goods.model.TbTopicAppraise;
import com.hengmall.goods.model.TopicQueryReq;
import com.hengmall.goods.model.api.Ajax;
import com.hengmall.goods.service.TopicService;
import com.hengmall.goods.util.CommonUtils;
import com.hengmall.goods.util.PublicUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wuhengbin
 */
@RestController
@Api(description = "话题管理")
public class TopicController {

    final static Logger log= LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TopicService topicService;
    
    
    /**
     * 根据类型获取话题分类，前端使用接口，需要增多一个参数，话题主是否是店主
     * @param type  分类类别，推荐，最新，最热，文章，视频
     * @param draw  当前页面数
     * @param length 一个页面显示的数据条数
     * @param token  用户的token
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/tbTopic/queryListByType",method = RequestMethod.POST)
    @ApiOperation(value = "(前端)根据类型获取话题分类",response = TbTopic.class)
    public Ajax<?> queryListByType(@RequestBody TopicQueryReq topicQueryReq)throws Exception{
    	log.info("客户端请求数据【/tbTopic/queryListByType】,{}"+topicQueryReq.toString());
		List<TbTopic> list = new ArrayList<>();
		List<TbTopic> list2 = new ArrayList<>();
		DataTablesResult result2 = new DataTablesResult();
		JSONObject obj = new JSONObject();
		Ajax<JSONObject> result = new Ajax<>();
		
    	int type = 0;
    	String token = new String();
    	int draw = 0;
    	int length = 0;

    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getToken()))){
    		token = topicQueryReq.getToken();
    	}
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getDraw()))){
    		draw = topicQueryReq.getDraw();
    	}
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getLength()))){
    		length = topicQueryReq.getLength();
    	}
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getType()))){
    		type = topicQueryReq.getType();
    	}

    	try {
			int userId = 0;
			if(!StringUtil.isEmpty(token)){
				userId = topicService.searchUser(token);
	    		if(userId == 0){
	   				result.setCode(-9);
	   				result.setErrMsg("用户token有误");
	   				return result;
	    		}
			}else{
				result.setCode(-9);
   				result.setErrMsg("用户token有误");
   				return result;
			}
    			if(type == 1){
    				 list = topicService.topicRecommendList(type,userId);
    				 result.setCode(0);
    				 obj.put("List", list);
    				 result.setData(obj); 
					 return result;
    			}else if(type == 2){
    				if(CommonUtils.judge(String.valueOf(draw))&&CommonUtils.judge(String.valueOf(length))){
    					result2 = topicService.getTbTopicList(draw,length,userId);
    				}else{
    					result2 = topicService.getTbTopicList(1,8,userId);
    				}
   				 	result.setCode(0);
   				 	obj.put("List", result2.getData());
   				 	result.setData(obj); 
					return result;
    			}else if(type > 2 && type < 6){
    				int i = 1;
					if(type == 4){
    					i = 1;
    				}else if(type == 5){
    					i = 2;
    				}
    				list = topicService.topicListType(i,userId);
    				if(i == 1){
    					for(TbTopic tbTopic :list){
    						if(tbTopic.getIs_recommend() == 1){
    							list2.add(tbTopic);
    							int index = list.indexOf(tbTopic);
    							list.remove(index);
    						}
    					}
    				}
    				obj.put("List", list);
    				obj.put("List2", list2);
    				result.setData(obj); 
    				result.setCode(0);
    				return result;
    			}if(type == 3){
	   				list = topicService.topicHotList(userId);
	   				result.setCode(0);
	   				obj.put("List", list);
	   				result.setData(obj); 
					return result;
				}
/*    			else if(type == 6 || type == 7){
    				int i = 1;
    				if(type == 6){
    					 i = 1;
    				}else if(type == 7){
    					i = 2;
    				}
    				list = topicService.topicListPublisher(i,userId);
	   				obj.put("List", list);
	   				result.setData(obj); 
   				 	result.setCode(0);
   				 	return result;
    			}*/
    			else{
    				result.setCode(1);
    				result.setErrMsg("分类类型有误");
    				return result;
    			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(-9);
			return result;
		}
    }
    
    /**
     *  话题点赞
     * @param topicId  话题ID
     * @param token    用户Token用于确定是哪个用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tbTopic/praise",method = RequestMethod.POST)
    @ApiOperation(value = "(前端)话题点赞")
    public Ajax<?> praise(@RequestBody TopicQueryReq topicQueryReq)throws Exception{
    	log.info("客户端请求数据【/tbTopic/praise】,{}"+topicQueryReq.toString());
    	Ajax<PraiseReq> result = new Ajax<>();
    	int topicId = 0;
    	String token = new String();
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getToken()))){
    		token = topicQueryReq.getToken();
    	}
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getTopicId()))){
    		topicId = topicQueryReq.getTopicId();
    	}

    	try {
    		//此处的逻辑暂时为，拿到这两个ID去指定的表中查询是否有对应的记录，如有就删除，如果没有就新建，同时需要对表的两个两个字段设为联合主键来防止重复添加
			int userId = 0;
			if(!StringUtil.isEmpty(token)){
				userId = topicService.searchUser(token);
	    		if(userId == 0){
	   				result.setCode(-9);
	   				result.setErrMsg("用户token有误");
	   				return result;
	    		}
			}else{
				result.setCode(-9);
   				result.setErrMsg("用户token有误");
   				return result;
			}
    		int i = topicService.praise(topicId,userId);
    		if(i == 0){
    			result.setCode(0);
    		}else{
    			result.setCode(1);
    		}
    		PraiseReq praiseReq = new PraiseReq();
    		Boolean a = topicService.isPraise(topicId, userId);
    		int b = topicService.countPraise(topicId);
    		praiseReq.setPraiseNum(b);
    		System.out.println("该话题的点赞数为："+b);
    		if(a){
    			praiseReq.setIs_praise(1);
    		}else{
    			praiseReq.setIs_praise(0);
    		}
    		result.setData(praiseReq);
        	return result;
		} catch (Exception e){
			e.printStackTrace();
			result.setCode(1);
			return result;
		}
    }
    
    /**
     *  话题内的评论点赞
     * @param topicId  话题ID
     * @param token    用户Token用于确定是哪个用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tbTopic/appraisePraise",method = RequestMethod.POST)
    @ApiOperation(value = "(前端)话题内的评论点赞")
    public Ajax<?> appraisePraise(@RequestBody TopicQueryReq topicQueryReq)throws Exception{
    	log.info("客户端请求数据【/tbTopic/appraisePraise】,{}"+topicQueryReq.toString());
    	Ajax<PraiseReq> result = new Ajax<>();
    	
    	int appraiseId = 0;
    	String token = new String();

    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getAppraiseId()))){
    		appraiseId = topicQueryReq.getAppraiseId();
    	}
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getToken()))){
    		token = topicQueryReq.getToken();
    	}

    	try {
        	if(!StringUtil.isEmpty(topicQueryReq.getToken())){
       		 	token = topicQueryReq.getToken();
	       	}else if(!"null".equals(String.valueOf(topicQueryReq.getAppraiseId()))){
	       		appraiseId = topicQueryReq.getAppraiseId();
	       	}else{
	   			result.setCode(1);
	   			result.setErrMsg("参数错误");
	   			return result;
	       	}
    		//此处的逻辑暂时为，拿到这两个ID去指定的表中查询是否有对应的记录，如有就删除，如果没有就新建，同时需要对表的两个两个字段设为联合主键来防止重复添加
			int userId = 0;
			if(!StringUtil.isEmpty(token)){
				userId = topicService.searchUser(token);
	    		if(userId == 0){
	   				result.setCode(-9);
	   				result.setErrMsg("用户token有误");
	   				return result;
	    		}
			}else{
				result.setCode(-9);
   				result.setErrMsg("用户token有误");
   				return result;
			}
    		int i = topicService.appraisePraise(appraiseId,userId);
    		PraiseReq praiseReq = new PraiseReq();
    		Boolean a = topicService.isAppraisePraise(appraiseId, userId);
    		int b = topicService.countAppraisePraise(appraiseId);
    		praiseReq.setPraiseNum(b);
    		System.out.println("该话题的点赞数为："+b);
    		if(a){
    			praiseReq.setIs_praise(1);
    		}else{
    			praiseReq.setIs_praise(0);
    		}
    		if(i == 0){
    			result.setCode(0);
    		}else{
    			result.setCode(1);
    		}
    		result.setData(praiseReq);
        	return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			return result;
		}
    }
    
	/**
	 * 话题评论或回复
	 * @param topicId  话题ID	
	 * @param userId   用户ID
	 * @param appraiseUserId  被评论人ID(用于当评论是针对某人进行而不是针对主话题进行时使用)
	 * @param context  评论内容
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/tbTopic/appraise",method = RequestMethod.POST)
    @ApiOperation(value = "(前端)话题评论",response = TbTopicAppraise.class)
    public Ajax<?> appraise(@RequestBody TopicQueryReq topicQueryReq)throws Exception{
    	log.info("客户端请求数据【/tbTopic/appraise】,{}"+topicQueryReq.toString());
    	Ajax<TbTopicAppraise> result = new Ajax<>();
    	
    	int topicId = 0;
    	String token = new String();
    	String context = new String();

    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getToken()))){
    		token = topicQueryReq.getToken();
    	}

    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getLength()))){
    		topicId = topicQueryReq.getTopicId();
    	}
    	
    	int appraiseUserId = 0;
		try {
			//此处的逻辑暂时为，直接进行对应的评论新建
			int userId = 0;
			if(!StringUtil.isEmpty(token)){
				userId = topicService.searchUser(token);
	    		if(userId == 0){
	   				result.setCode(-9);
	   				result.setErrMsg("用户token有误");
	   				return result;
	    		}
			}else{
				result.setCode(-9);
   				result.setErrMsg("用户token有误");
   				return result;
			}
    	if("null".equals(String.valueOf(topicQueryReq.getAppraiseUserId()))){
    		//对话题进行评论时，topicId不为空
    		appraiseUserId = 0;
    		if(!"null".equals(String.valueOf(topicQueryReq.getAppraiseUserId()))){
    			topicId = topicQueryReq.getTopicId();
	    		TbTopicAppraise appraise= topicService.appraise(topicId,userId,appraiseUserId,context);
				TbTopic  tbTopic = topicService.getTbTopicById(topicId);
				tbTopic.setAppraise_num(tbTopic.getAppraise_num() + 1);
				topicService.updateTbTopic(tbTopic);
				result.setData(appraise);
    		}else if(!"null".equals(String.valueOf(topicQueryReq.getTopicId()))){
	    			topicId = topicQueryReq.getTopicId();
		    		TbTopicAppraise appraise= topicService.appraise(topicId,userId,appraiseUserId,context);
					TbTopic  tbTopic = topicService.getTbTopicById(topicId);
					tbTopic.setAppraise_num(tbTopic.getAppraise_num() + 1);
					topicService.updateTbTopic(tbTopic);
					result.setData(appraise);
    		}else{
    			result.setCode(1);
    			result.setErrMsg("话题ID为空时，被评论的ID也为空");
    		}
    	}else{
    		appraiseUserId = topicQueryReq.getAppraiseUserId();
    		TbTopicAppraise newappraise = topicService.queryAppraisePraise(appraiseUserId);
    		topicId = newappraise.getTopic_id();
    		TbTopicAppraise appraise= topicService.appraise(topicId,userId,appraiseUserId,context);
			TbTopic  tbTopic = topicService.getTbTopicById(topicId);
			tbTopic.setAppraise_num(tbTopic.getAppraise_num() + 1);
			topicService.updateTbTopic(tbTopic);
			result.setData(appraise);
    	}
	    	return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			return result;
		}
    }
    
	/**
	 * 话题评论列表
	 * @param topicId  话题ID	
	 * @param userId   用户ID
	 * @param appraiseUserId  被评论人ID(用于当评论是针对某人进行而不是针对主话题进行时使用)
	 * @param context  评论内容
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/tbTopic/appraiseList",method = RequestMethod.POST)
    @ApiOperation(value = "(前端)话题评论列表",response = TbTopicAppraise.class)
    public Ajax<?> appraiseList(@RequestBody TopicQueryReq topicQueryReq)throws Exception{
    	log.info("客户端请求数据【/tbTopic/appraiseList】,{}"+topicQueryReq.toString());
    	int topicId = 0;
    	String token = new String();
    	int draw = 0;
    	int length = 0;
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getToken()))){
    		token = topicQueryReq.getToken();
    	}
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getDraw()))){
    		draw = topicQueryReq.getDraw();
    	}
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getLength()))){
    		length = topicQueryReq.getLength();
    	}
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getTopicId()))){
    		topicId = topicQueryReq.getTopicId();
    	}
    	
		Ajax<List<TbTopicAppraise>> result = new Ajax<>();
		try {
			//此处的逻辑暂时为，先获取所有评论再进行子评论的的处理
			int userId = 0;
			if(!StringUtil.isEmpty(token)){
				userId = topicService.searchUser(token);
	    		if(userId == 0){
	   				result.setCode(-9);
	   				result.setErrMsg("用户token有误");
	   				return result;
	    		}
			}else{
				result.setCode(-9);
   				result.setErrMsg("用户token有误");
   				return result;
			}

			List<TbTopicAppraise> list = topicService.getTopicAppraise(topicId,userId,draw,length);
			System.out.println("查询列表返回的结果："+list);
			result.setCode(0);
			result.setData(list);
	    	return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			return result;
		}
    }
    
    @RequestMapping(value = "/tbTopic/appraiseChildList",method = RequestMethod.POST)
    @ApiOperation(value = "(前端)话题评论子评论列表",response = TbTopicAppraise.class)
    public Ajax<?> appraiseChildList(@RequestBody TopicQueryReq topicQueryReq)throws Exception{
    	log.info("客户端请求数据【/tbTopic/appraiseChildList】,{}"+topicQueryReq.toString());
    	int appraiseId = 0;
    	String token = new String();
    	int draw = 0;
    	int length = 0;
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getAppraiseId()))){
    		appraiseId = topicQueryReq.getAppraiseId();
    	}
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getToken()))){
    		token = topicQueryReq.getToken();
    	}
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getDraw()))){
    		draw = topicQueryReq.getDraw();
    	}
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getLength()))){
    		length = topicQueryReq.getLength();
    	}
    	
		Ajax<List<TbTopicAppraise>> result = new Ajax<>();
		try {
			//此处的逻辑暂时为，先获取所有评论再进行子评论的的处理
			int userId = 0;
			if(!StringUtil.isEmpty(token)){
				userId = topicService.searchUser(token);
	    		if(userId == 0){
	   				result.setCode(-9);
	   				result.setErrMsg("用户token有误");
	   				return result;
	    		}
			}else{
				result.setCode(-9);
   				result.setErrMsg("用户token有误");
   				return result;
			}
			List<TbTopicAppraise> list = topicService.getAppraiseChildList(appraiseId,userId, draw,  length);
			result.setCode(0);
			result.setData(list);
	    	return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			return result;
		}
    }
    
    /**
     * 查看话题主的话题列表
     * @param topicId   话题ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tbTopic/queryTopicPublisher",method = RequestMethod.POST)
    @ApiOperation(value = "(前端)查看话题主的话题列表" ,response = TbTopic.class)
    public Ajax<?> queryTopicPublisher(@RequestBody TopicQueryReq topicQueryReq)throws Exception{
    	log.info("客户端请求数据【/tbTopic/queryTopic】,{}"+topicQueryReq.toString());
    	int topicId = 0;
    	String token = new String();
    	
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getToken()))){
    		token = topicQueryReq.getToken();
    	}

    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getTopicId()))){
    		topicId = topicQueryReq.getTopicId();
    	}
    	
		Ajax<List<TbTopic>> result = new Ajax<>();
		//此处的逻辑暂时为，直接进行对应的根据ID进行查询 TODO
		try {
			int userId = 0;
			if(!StringUtil.isEmpty(token)){
				userId = topicService.searchUser(token);
	    		if(userId == 0){
	   				result.setCode(-9);
	   				result.setErrMsg("用户token有误");
	   				return result;
	    		}
			}else{
				result.setCode(-9);
   				result.setErrMsg("用户token有误");
   				return result;
			}
			//获取话题主的所有发布过的话题，类似朋友圈
			List<TbTopic> list = new ArrayList<>();
			TbTopic  tbTopic = topicService.getTbTopicById(topicId);
			if(PublicUtil.isEmpty(tbTopic)){
				throw new Exception("话题id有误");
			}
			int publisherId = tbTopic.getPublisher_id();
			list = topicService.topicListByPublisher(publisherId, userId);
			result.setCode(0);
			result.setData(list);
	    	return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			return result;
		}
    }
    
    /**
     * 话题内容查看
     * @param topicId   话题ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tbTopic/queryTopic",method = RequestMethod.POST)
    @ApiOperation(value = "(前端)话题内容查看" ,response = TbTopic.class)
    public Ajax<?> queryTopic(@RequestBody TopicQueryReq topicQueryReq)throws Exception{
    	log.info("客户端请求数据【/tbTopic/queryTopic】,{}"+topicQueryReq.toString());
    	int topicId = 0;
    	String token = new String();
    	
    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getToken()))){
    		token = topicQueryReq.getToken();
    	}

    	if(CommonUtils.judge(String.valueOf(topicQueryReq.getTopicId()))){
    		topicId = topicQueryReq.getTopicId();
    	}
    	
		Ajax<TbTopic> result = new Ajax<>();
		//此处的逻辑暂时为，直接进行对应的根据ID进行查询
		try {
			int userId = 0;
			if(!StringUtil.isEmpty(token)){
				userId = topicService.searchUser(token);
	    		if(userId == 0){
	   				result.setCode(-9);
	   				result.setErrMsg("用户token有误");
	   				return result;
	    		}
			}else{
				result.setCode(-9);
   				result.setErrMsg("用户token有误");
   				return result;
			}
			TbTopic  tbTopic = topicService.getTbTopicById(topicId);
    		topicService.updateTbTopic(tbTopic);
    		Boolean a = topicService.isPraise(topicId, userId);
    		if(a){
    			tbTopic.setIs_appraise(1);
    		}else{
    			tbTopic.setIs_appraise(0);
    		}
			result.setCode(0);
			result.setData(tbTopic);
	    	return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			return result;
		}
    }
    
    /**
     * TODO 话题内容的搜索,需要完善
     * @param context   需要搜索的内容
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tbTopic/searchTopic",method = RequestMethod.POST)
    @ApiOperation(value = "(前端)话题内容的搜索")
    public Ajax<?> searchTopic (@RequestBody TopicQueryReq topicQueryReq)throws Exception{
    	log.info("客户端请求数据【/tbTopic/searchTopic】,{}"+topicQueryReq.getContext().toString());
    	String context = topicQueryReq.getContext();
		Ajax<List<TbTopic>> result = new Ajax<>();
		//此处的逻辑暂时为，直接进行对应的根据ID进行查询
		try {
    		String token = new String();
        	if(CommonUtils.judge(String.valueOf(topicQueryReq.getToken()))){
        		token = topicQueryReq.getToken();
        	}
			int userId = 0;
			if(!StringUtil.isEmpty(token)){
				userId = topicService.searchUser(token);
	    		if(userId == 0){
	   				result.setCode(-9);
	   				result.setErrMsg("用户token有误");
	   				return result;
	    		}
			}else{
				result.setCode(-9);
   				result.setErrMsg("用户token有误");
   				return result;
			}
			List<TbTopic> list = topicService.searchTopicList(context);
			result.setCode(0);
			result.setData(list);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(1);
			return result;
		}
    }
}
