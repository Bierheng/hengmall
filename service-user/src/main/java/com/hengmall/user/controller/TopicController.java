package com.hengmall.user.controller;

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
import com.hengmall.user.model.Ads;
import com.hengmall.user.model.DataTablesResult;
import com.hengmall.user.model.DelBean;
import com.hengmall.user.model.PraiseReq;
import com.hengmall.user.model.Result;
import com.hengmall.user.model.SAppraiseEntity;
import com.hengmall.user.model.TbTopic;
import com.hengmall.user.model.TbTopicAppraise;
import com.hengmall.user.model.TopicQueryReq;
import com.hengmall.user.model.api.Ajax;
import com.hengmall.user.service.TopicService;
import com.hengmall.user.util.CommonUtils;
import com.hengmall.user.util.ResultUtil;

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
     * 获取所有评论列表
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/appraise/list",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有评论列表",response = SAppraiseEntity.class)
    public DataTablesResult queryAppraiseList(int draw,int length)throws Exception{
    	log.info("客户端请求数据【/appraise/list】,{}");
    	DataTablesResult result = new DataTablesResult();
    	try {
    		result = topicService.queryAppraiseList(draw, length);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setError("获取所有订单列表失败！");
			return result;
		}
    }
    
    /**
     * 根据编号删除评论
     * @param ids  评论编号
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/appraise/delById",method = RequestMethod.POST)
    @ApiOperation(value = "删除评论")
    public Result<Object> delById(@RequestBody DelBean ids) throws Exception{
    	log.info("客户端请求数据【/appraise/delById】,{}"+ids);
    	try {
    		Result<Object> result = topicService.delById(ids);
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("删除评论失败！");
			return result;
		}
    }
    
    /**
     * 获取所有活动列表    
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ads/list",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有活动列表" ,response = Ads.class)
    public DataTablesResult getAdsList(int draw ,int length)throws Exception{
    	log.info("客户端请求数据【/ads/list】,{}");
    	try {
    		if(	CommonUtils.judge(String.valueOf(draw)) && CommonUtils.judge(String.valueOf(length))){
                DataTablesResult result=topicService.getAdsList( draw , length);
                return result;
    		}else{
    	    	DataTablesResult result = new DataTablesResult();
    			result.setSuccess(false);
    			result.setError("传入参数为空！");
    			return result;
    		}
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("获取所有活动列表失败！");
			return result;
		}

    }

    /**
     * 添加或修改活动    当id没有值时调用新增方法有值时调用修改方法
     * @param ads  活动的实体类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ads/addAds",method = RequestMethod.POST)
    @ApiOperation(value = "添加或修改活动" )
    public Result<Object> addAds(@RequestBody Ads ads) throws Exception{
    	log.info("客户端请求数据【/ads/addAds】,{}"+ads.toString());
    	Result<Object> result = new Result<Object>();
    	try {
        	if(CommonUtils.judge( String.valueOf(ads.getId()))){
        		topicService.updateAds(ads);
            	result.setSuccess(true);
    			result.setErrMsg("修改成功！");
            	return result;
        	  }else{
        		topicService.addAds(ads);
              	result.setSuccess(true);
      			result.setErrMsg("添加成功！");
              	return result;
	  	       }
		} catch(Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrMsg("添加或修改活动失败！");
			return result;
		}
    }
    
    /**
     * 删除活动
     * @param ids   要被删除的ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ads/delAds",method = RequestMethod.POST)
    @ApiOperation(value = "删除活动")
    public Result<Object> delAds(@RequestBody DelBean  ids) throws Exception{
    	log.info("客户端请求数据【/ads/addAds】,{}"+ids.toString());
    	try {
    			int id = ids.getIds();
                int result=topicService.deleteAds(id);
                if(result==0){
                    return new ResultUtil<Object>().setErrorMsg("删除失败！");
                }
            return new ResultUtil<Object>().setData(null);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("删除失败！");
			return result;
		}
    }
    
    /**
     * 获取所有话题列表     给后台数据查询使用
     * @param draw  当前页码
     * @param length  页面大小
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tbTopic/list",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有话题列表" ,response = TbTopic.class)
    public DataTablesResult getTbTopicList(int draw ,int length)throws Exception{
    	log.info("客户端请求数据【/tbTopic/list】,{}");
    	try {
    		if(	CommonUtils.judge(String.valueOf(draw)) && CommonUtils.judge(String.valueOf(length))){
	            DataTablesResult result=topicService.getTbTopicList1( draw , length);
	            return result;
    		}else{
    	    	DataTablesResult result = new DataTablesResult();
    			result.setSuccess(false);
    			result.setError("传入参数为空！");
    			return result;
    		}
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			result.setError("获取所有话题列表失败！");
			return result;
		}

    }

    /**
     * 添加或修改话题   当id没有值时调用新增方法有值时调用修改方法
     * @param tbTopic  话题的实体类
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tbTopic/addAds",method = RequestMethod.POST)
    @ApiOperation(value = "添加或修改话题")
    public Result<Object> addTbTopic(@RequestBody TbTopic tbTopic) throws Exception{
    	log.info("客户端请求数据【/tbTopic/addAds】,{}"+ tbTopic.toString());
    	Result<Object> result = new Result<Object>();
    	try {
        	if(CommonUtils.judge(String.valueOf(tbTopic.getId()))){
        		topicService.updateTbTopic(tbTopic);
            	result.setSuccess(true);
    			result.setErrMsg("修改成功！");
            	return result;
        	  }else{
        		topicService.addTbTopic(tbTopic);
              	result.setSuccess(true);
      			result.setErrMsg("添加成功！");
              	return result;
	  	       }
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setErrMsg("添加或修改话题失败！");
			return result;
		}

    }
    
    /**
     * 删除话题
     * @param ids   要被删除的ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tbTopic/delTbTopic",method = RequestMethod.POST)
    @ApiOperation(value = "删除话题")
    public Result<Object> delTbTopic(@RequestBody DelBean id) throws Exception{
    	log.info("客户端请求数据【/tbTopic/delTbTopic】,{}"+ id.toString());
    	try {
    		int ids = id.getIds();
                int result=topicService.deleteTbTopic(ids);
                if(result==0){
                    return new ResultUtil<Object>().setErrorMsg("删除失败！");
                }
            return new ResultUtil<Object>().setData(null);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("删除失败！");
			return result;
		}
    }
    
    /**
     * 获取活动记录总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ads/count",method = RequestMethod.GET)
    @ApiOperation(value = "获取活动记录总数")
    public Result<Object> countAds() throws Exception{
    	log.info("客户端请求数据【/ads/count】,{}");
    	try {
    		int result = topicService.countAds();
            return new ResultUtil<Object>().setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
    
    /**
     * 获取话题记录总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tbTopic/count",method = RequestMethod.GET)
    @ApiOperation(value = "获取话题记录总数")
    public Result<Object> countTbTopic() throws Exception{
    	log.info("客户端请求数据【/tbTopic/count】,{}");
    	try {
    		int result = topicService.countTopic();
            return new ResultUtil<Object>().setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
   
    /**
     * 获取评论记录总数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/appraise/count",method = RequestMethod.GET)
    @ApiOperation(value = "获取评论记录总数")
    public Result<Object> countAppraise() throws Exception{
    	log.info("客户端请求数据【/appraise/count】,{}");
    	try {
    		Result<Object> result = topicService.countAppraise();
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			Result<Object> result = new Result<Object>();
			result.setSuccess(false);
			result.setErrMsg("获取总数失败！");
			return result;
		}
    }
    
    /**
     * 根据类型获取话题分类，前端使用接口
     * @param type  分类类别
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
    				if(type == 3){
    					 i = 1;
    				}else if(type == 4){
    					i = 2;
    				}else if(type == 5){
    					i = 3;
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
    			}else if(type == 6 || type == 7){
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
    			}else{
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
     * 话题内容的搜索
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
