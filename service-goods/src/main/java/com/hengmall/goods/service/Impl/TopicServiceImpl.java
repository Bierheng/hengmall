package com.hengmall.goods.service.Impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hengmall.goods.dao.TbTopicDao;
import com.hengmall.goods.model.DataTablesResult;
import com.hengmall.goods.model.SResources;
import com.hengmall.goods.model.SResourcesEntity;
import com.hengmall.goods.model.TbTopic;
import com.hengmall.goods.model.TbTopicAppraise;
import com.hengmall.goods.model.TbTopicBean;
import com.hengmall.goods.model.TbTopicImg;
import com.hengmall.goods.model.Users;
import com.hengmall.goods.model.api.ProductListResp;
import com.hengmall.goods.service.TopicService;


@Service
public class TopicServiceImpl implements TopicService {

	 final static Logger log= LoggerFactory.getLogger(TopicServiceImpl.class);
	
    @Autowired
    private  TbTopicDao tbTopicDao;


	@Override
	@Transactional
	public TbTopic getTbTopicById(Integer id) throws Exception {
		TbTopicBean tbTopicBean = tbTopicDao.queryByIdAll(id);
		TbTopic tbTopic = new TbTopic();
		tbTopic.setAppraise_num(tbTopicBean.getAppraise_num());
		String jsonArrayStr = tbTopicBean.getArticle().toString();
		jsonArrayStr = new String(jsonArrayStr.getBytes("ISO-8859-1"),"utf-8");
		JSONArray jsonArray = JSON.parseArray(jsonArrayStr);
		tbTopic.setArticle(jsonArray);
		tbTopic.setCreate_time(tbTopicBean.getCreate_time());
		tbTopic.setId(tbTopicBean.getId());
		tbTopic.setIs_recommend(tbTopicBean.getIs_recommend());
		tbTopic.setPraise_num(tbTopicBean.getPraise_num());
		tbTopic.setPublisher_id(tbTopic.getPublisher_id());
		tbTopic.setPublisher_type(tbTopicBean.getPublisher_type());
		tbTopic.setSee_num(tbTopicBean.getSee_num() +1 );
		String jsonArrayStr2 = tbTopicBean.getThumbnail();
		jsonArrayStr2 = jsonArrayStr2.replace(" ", "").substring(1, jsonArrayStr2.replace(" ", "").length()-1);
		String[] strArr = jsonArrayStr2.split(",");
		List<Integer> list = new ArrayList<>();
		for(String str :  strArr){
			list.add(Integer.valueOf(str));
		}
		tbTopic.setThumbnail(list);
		tbTopic.setTitle(tbTopicBean.getTitle());
		tbTopic.setTopic_type(tbTopicBean.getTopic_type());
		List<ProductListResp> productList =  tbTopicDao.queryProductById(id);
		tbTopic.setProductList(productList);
		return tbTopic;
	}
	
	/**
	 * 该方法用于对话题的实体类数据进行转换，
	 * @param list
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public List<TbTopic> convertTopic(List<TbTopicBean>  list) throws UnsupportedEncodingException {
		TbTopic tbTopic = new TbTopic();
		List<TbTopic> list1 = new ArrayList<>();
		for(TbTopicBean tbTopicBean : list){
			tbTopic = new TbTopic();
			tbTopic.setAppraise_num(tbTopicBean.getAppraise_num());
			String jsonArrayStr = tbTopicBean.getArticle().toString();
			jsonArrayStr = new String(jsonArrayStr.getBytes("ISO-8859-1"),"utf-8");
			JSONArray jsonArray = JSON.parseArray(jsonArrayStr);
			tbTopic.setArticle(jsonArray);
			tbTopic.setCreate_time(tbTopicBean.getCreate_time());
			tbTopic.setId(tbTopicBean.getId());
			tbTopic.setIs_recommend(tbTopicBean.getIs_recommend());
			tbTopic.setPraise_num(tbTopicBean.getPraise_num());
			tbTopic.setPublisher_id(tbTopic.getPublisher_id());
			tbTopic.setPublisher_type(tbTopicBean.getPublisher_type());
			tbTopic.setSee_num(tbTopicBean.getSee_num());
			if(!"[]".equals(tbTopicBean.getThumbnail().toString())){
			String jsonArrayStr2 = tbTopicBean.getThumbnail();
			jsonArrayStr2 = jsonArrayStr2.replace(" ", "").substring(1, jsonArrayStr2.replace(" ", "").length()-1);
			String[] strArr = jsonArrayStr2.split(",");
			List<Integer> list2 = new ArrayList<>();
			for(String str :  strArr){
				list2.add(Integer.valueOf(str));
			}
			tbTopic.setThumbnail(list2);
			}
			tbTopic.setTopic_type(tbTopicBean.getTopic_type());
			tbTopic.setIs_appraise(tbTopicBean.getIs_appraise());
			tbTopic.setTitle(tbTopicBean.getTitle());
			tbTopic.setVideo(tbTopicBean.getVideo());
			list1.add(tbTopic);
		}
		return list1;
	}

	@Override
	@Transactional
	public int praise(int topicId, int userId) {
		int j = tbTopicDao.searchTopic(userId, topicId);
		int i = 0;
		if(j != 1){
		   i =	tbTopicDao.insertTopicPraise(userId, topicId);
		   TbTopic tbTopic = tbTopicDao.queryById(topicId);
		   tbTopic.setPraise_num(tbTopic.getPraise_num() +1 );
		   tbTopicDao.updateById2(tbTopic);
		}else {
		   i =	tbTopicDao.delTopicPraiseById(userId, topicId);
		   TbTopic tbTopic = tbTopicDao.queryById(topicId);
		   tbTopic.setPraise_num(tbTopic.getPraise_num() -1);
		   tbTopicDao.updateById2(tbTopic);
		}
		if(i == 1){
			return 0;
		}else{
			return 1;
		}
	}
	
	@Override
	public boolean isPraise(int topicId, int userId) {
		int j = tbTopicDao.searchTopic(userId, topicId);
		if(j != 1){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public boolean isAppraisePraise(int appraiseId, int userId) {
		int j = tbTopicDao.searchAppraise(userId, appraiseId);
		if(j != 1){
			return false;
		}else{
			return true;
		}
	}

	@Override
	@Transactional
	public TbTopicAppraise appraise(int topicId, int userId, int appraiseUserId, String context) {
		TbTopicAppraise tbTopicAppraise = new TbTopicAppraise();
		tbTopicAppraise.setAppraise_user(appraiseUserId);
		tbTopicAppraise.setContext(context);
		tbTopicAppraise.setUser_id(userId);
		tbTopicAppraise.setTopic_id(topicId);
		tbTopicDao.insertTopicAppraise(tbTopicAppraise);
		TbTopicAppraise appraise = tbTopicDao.queryAppraise(tbTopicAppraise.getId());
	    TbTopic tbTopic = tbTopicDao.queryById(topicId);
	    tbTopic.setAppraise_num(tbTopic.getAppraise_num() +1);
	    tbTopicDao.updateById2(tbTopic);
	    return appraise;
	}

	@Override
	public int searchUser(String token) {
		Users user = tbTopicDao.searchUsers(token);
		int i = 0;
		if(!"null".equals(String.valueOf(user.getId()))){
			i = user.getId();
		}
		return i;
	}

	@Override
	public List<TbTopicAppraise> getTopicAppraise(int topicId,int userId,int draw,int length) {
		PageHelper.startPage(draw,length);
		List<TbTopicAppraise> list = tbTopicDao.queryTopicAppraise(topicId,userId);
//        PageInfo<TbTopicAppraise> pageInfo=new PageInfo<>(list);
		List<TbTopicAppraise> list2 = new ArrayList<>();
		List<TbTopicAppraise> list3 = new ArrayList<>();
		TbTopicAppraise tbTopicAppraise = new TbTopicAppraise();
		for(int i=0; i<list.size();i++){
			//实现逻辑，先循环遍历出所有的子评论，然后再将子评论与相关的主评论进行对应，然后将对应好的子评论放在list里面放入主评论里面
			tbTopicAppraise = list.get(i);
			if(tbTopicAppraise.getAppraise_user() != 0){
				list2.add(tbTopicAppraise);
				list.remove(tbTopicAppraise);
			}else{
				list3.add(tbTopicAppraise);
			}
			Date date = tbTopicAppraise.getCreate_time();
			long time = date.getTime();
			long a = System.currentTimeMillis() - time;
			long b = a/3600000;
			int datenum = (int)Math.ceil(b/24);
			if(datenum <1 ){
				tbTopicAppraise.setDate("1天内");
			}else if(datenum>1 && datenum<90){
				tbTopicAppraise.setDate(datenum+"天前");
			}else{
				tbTopicAppraise.setDate("3个月前");
			}
		}
		//list为被删减后的list
		for(int i=0; i<list3.size();i++){
			List<TbTopicAppraise> list4 = new ArrayList<>();
			tbTopicAppraise = new TbTopicAppraise();
			tbTopicAppraise = list3.get(i);
			for(int j=0; j< list2.size();j++){
				TbTopicAppraise tbTopicAppraise2 = new TbTopicAppraise();
				tbTopicAppraise2 = list2.get(j);
				if(tbTopicAppraise.getId() == tbTopicAppraise2.getAppraise_user()){
					list4.add(tbTopicAppraise2);
				}
			}
			tbTopicAppraise.setChildAppraise(list4);
		}
		return list3;
	}

	@Override
	public int appraisePraise(int appraiseId, int userId) {
		int j = tbTopicDao.searchAppraiseTopic(userId, appraiseId);
		int i = 0;
		if(j != 1){
		   i =	tbTopicDao.insertAppraisePraise(userId, appraiseId);
		   TbTopicAppraise appraise = tbTopicDao.queryAppraise(appraiseId);
		   appraise.setLook(appraise.getLook() +1);
		   tbTopicDao.updateAppraiseById(appraise);
		}else {
		   i =	tbTopicDao.delAppraisePraiseById(userId, appraiseId);
		   TbTopicAppraise appraise = tbTopicDao.queryAppraise(appraiseId);
		   appraise.setLook(appraise.getLook() -1);
		   tbTopicDao.updateAppraiseById(appraise);
		}
		if(i == 1){
			return 0;
		}else{
			return 1;
		}
	}

	@Override
	public int countPraise(int topicId) {
		int a = tbTopicDao.countTopicPraise(topicId);
		return a;
	}
	
	@Override
	public int countAppraisePraise(int appraiseId) {
		int a = tbTopicDao.countAppraisePraise(appraiseId);
		return a;
	}
	
	@Override
	public TbTopicAppraise queryAppraisePraise(int appraiseId) {
		TbTopicAppraise a = tbTopicDao.queryAppraisePraise(appraiseId);
		return a;
	}

	@Override
	public List<TbTopicAppraise> getAppraiseChildList(int appraiseId, int userId,int draw, int length) {
		PageHelper.startPage(draw,length);
		List<TbTopicAppraise> list = tbTopicDao.queryTopicAppraiseChild(appraiseId,userId);
		return list;
	}


	@Override
	public List<TbTopic> topicRecommendList(int type, int userId){
		List<TbTopicBean> list = tbTopicDao.topicRecommendList(userId);
		List<TbTopic> list1 = new ArrayList<>();
		try {
			list1 = convertTopic(list);
	        for( TbTopic tbTopic :list1){
	        	if(tbTopic.getTopic_type() != 3){
	            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), tbTopic.getTopic_type());
	            	tbTopic.setThumbnailIds(list3);
	        	}else{
	            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), 1);
	            	tbTopic.setThumbnailIds(list3);
	        	}
	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return list1;
	}


	@Override
	public List<TbTopic> topicListType(int i ,int userId) {
		List<TbTopicBean> list = tbTopicDao.topicListType(i, userId);
		List<TbTopic> list1 = new ArrayList<>();
		try {
			list1 = convertTopic(list);
	        for( TbTopic tbTopic :list1){
	        	if(tbTopic.getTopic_type() != 3){
	            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), tbTopic.getTopic_type());
	            	tbTopic.setThumbnailIds(list3);
	        	}else{
	            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), 1);
	            	tbTopic.setThumbnailIds(list3);
	        	}

	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return list1;
	}
	
	@Override
	public List<TbTopic> topicHotList(int userId) {
		List<TbTopicBean> list = tbTopicDao.topicHotList(userId);
		List<TbTopic> list1 = new ArrayList<>();
		try {
			list1 = convertTopic(list);
	        for( TbTopic tbTopic :list1){
	        	if(tbTopic.getTopic_type() != 3){
	            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), tbTopic.getTopic_type());
	            	tbTopic.setThumbnailIds(list3);
	        	}else{
	            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), 1);
	            	tbTopic.setThumbnailIds(list3);
	        	}

	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return list1;
	}

	@Override
	public List<TbTopic> topicListPublisher(int i,int userId) {
		List<TbTopicBean> list = tbTopicDao.topicListPublisher(i,userId);
		List<TbTopic> list1 = new ArrayList<>();
		try {
			list1 = convertTopic(list);
	        for( TbTopic tbTopic :list1){
	        	if(tbTopic.getTopic_type() != 3){
	            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), tbTopic.getTopic_type());
	            	tbTopic.setThumbnailIds(list3);
	        	}else{
	            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), 1);
	            	tbTopic.setThumbnailIds(list3);
	        	}

	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return list1;
	}
	
	@Override
	public List<TbTopic> topicListByPublisher(int i,int userId) {
		List<TbTopicBean> list = tbTopicDao.topicListByPublisher(i,userId);
		List<TbTopic> list1 = new ArrayList<>();
		try {
			list1 = convertTopic(list);
	        for( TbTopic tbTopic :list1){
	        	if(tbTopic.getTopic_type() != 3){
	            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), tbTopic.getTopic_type());
	            	tbTopic.setThumbnailIds(list3);
	        	}else{
	            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), 1);
	            	tbTopic.setThumbnailIds(list3);
	        	}

	        }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return list1;
	}


	/**
	 * 获取所有话题列表，分类为最新
	 */
	@Override
	@Transactional
	public DataTablesResult getTbTopicList(int draw ,int length,int userId ) throws Exception {
		DataTablesResult result = new DataTablesResult();
		List<TbTopic> list1 = new ArrayList<>();
		int counts = tbTopicDao.countTopic();
		PageHelper.startPage(draw,length);
		List<TbTopicBean> list = tbTopicDao.topicListNew(userId);
        PageInfo<TbTopicBean> pageInfo=new PageInfo<>(list);
        list1 = convertTopic(list);
        for( TbTopic tbTopic :list1){
        	if(tbTopic.getTopic_type() != 3){
            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), tbTopic.getTopic_type());
            	tbTopic.setThumbnailIds(list3);
        	}else{
            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), 1);
            	tbTopic.setThumbnailIds(list3);
        	}

        }
        result.setRecordsFiltered((int)pageInfo.getEndRow());
        result.setRecordsTotal(Math.toIntExact(counts));
        result.setSuccess(true);
        result.setData(list1);
        result.setDraw(draw);
        return result;
	}


	@Override
	@Transactional
	public int updateTbTopic(TbTopic tbTopic) throws Exception {
		if(tbTopic.getTopic_type() == 2){
			tbTopic.setArticle(new JSONArray());
		}else{
			tbTopic.setVideo(null);
		}
		int result = tbTopicDao.updateById(tbTopic);
		List<Integer> list = tbTopic.getThumbnail();
		judgeTopicList(list, tbTopic.getId());
        if(result!=1){
            throw new Exception("修改话题失败");
        }
		return 1;
	}

	/**
	 * 获取所有话题列表给后段使用
	 */
	@Override
	@Transactional
	public DataTablesResult getTbTopicList1(int draw ,int length) throws Exception {
		DataTablesResult result = new DataTablesResult();
		List<TbTopic> list1 = new ArrayList<>();
		int counts = tbTopicDao.countTopic();
		PageHelper.startPage(draw,length);
		List<TbTopicBean> list = tbTopicDao.topicList();
        PageInfo<TbTopicBean> pageInfo=new PageInfo<>(list);
        list1 = convertTopic(list);
        for( TbTopic tbTopic :list1){
        	if(tbTopic.getTopic_type() != 3){
            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), tbTopic.getTopic_type());
            	tbTopic.setThumbnailIds(list3);
        	}else{
            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), 1);
            	tbTopic.setThumbnailIds(list3);
        	}

        }
        result.setRecordsFiltered((int)pageInfo.getEndRow());
        result.setRecordsTotal(Math.toIntExact(counts));
        result.setSuccess(true);
        result.setData(list1);
        result.setDraw(draw);
        return result;
	}


	@Override
	public List<TbTopic> searchTopicList(String context) throws UnsupportedEncodingException {
		List<TbTopicBean> list = tbTopicDao.searchTopicList(context);
		List<TbTopic> list2 = convertTopic(list);
        for( TbTopic tbTopic :list2){
        	if(tbTopic.getTopic_type() != 3){
            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), tbTopic.getTopic_type());
            	tbTopic.setThumbnailIds(list3);
        	}else{
            	List<SResources> list3 = tbTopicDao.queryByTopicId(tbTopic.getId(), 1);
            	tbTopic.setThumbnailIds(list3);
        	}

        }
		return list2;
	}
	
	@Transactional
	public DataTablesResult judgeTopicList(List<Integer> pictrueIds, int topicId) throws Exception {
		DataTablesResult result = new DataTablesResult();
		//获取数据库当前商品已有的图片
		List<SResourcesEntity> list = tbTopicDao.queryByTopicIdAll(topicId, 1);
		Map<String, SResourcesEntity> pictureIdMap = new HashMap<>();
		Map<String, String> addPictureIdMap = new HashMap<>();
		Map<String, String> DeletePictureIdMap = new HashMap<>();
		for(SResourcesEntity picture : list){
			pictureIdMap.put(String.valueOf(picture.getId()) , picture);
		}
		//通过for循环对比出商品新增的图片
		for(int pictureId : pictrueIds){
			if(!pictureIdMap.containsKey(String.valueOf(pictureId))){
				addPictureIdMap.put(String.valueOf(pictureId),String.valueOf(pictureId));
				TbTopicImg  tbTopicImg = new TbTopicImg();
				tbTopicImg.setTopic_id(topicId);
				tbTopicImg.setResources_id(pictureId);
				tbTopicImg.setType(1);
				tbTopicDao.insertTopicImg(tbTopicImg);
			}else{
				addPictureIdMap.put(String.valueOf(pictureId),String.valueOf(pictureId));
			}
		}
		//通过for循环对比出商品删除的图片
		for(String newTagId : pictureIdMap.keySet()){
			//注意这里的对比map变为前端传回的数组数据
			if(!addPictureIdMap.containsKey(newTagId)){
				DeletePictureIdMap.put(newTagId,newTagId);
				tbTopicDao.delTopicImgById(topicId, Integer.valueOf(newTagId));
				tbTopicDao.deleteResources(Integer.valueOf(newTagId));
			}
		}
        result.setData(null);
        result.setSuccess(true);
        return result;
	}
}
