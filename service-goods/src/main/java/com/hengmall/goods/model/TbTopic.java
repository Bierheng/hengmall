package com.hengmall.goods.model;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.hengmall.goods.model.api.ProductListResp;

import io.swagger.annotations.ApiModelProperty;

public class TbTopic {
	
    private Integer id;
	@ApiModelProperty(value = "话题类型，1:文章，2:视频")
    private Integer topic_type;
	@ApiModelProperty(value = "缩略图")
    private List<Integer> thumbnail;
	@ApiModelProperty(value = "缩略图")
    private String thumbnailList;
	@ApiModelProperty(value = "文章")
    private JSONArray article;
	@ApiModelProperty(value = "文章")
    private String articleJson;
	@ApiModelProperty(value = "是否推荐,0:否；1：是")
    private Integer is_recommend;
	@ApiModelProperty(value = "创建时间")
    private Date create_time;
	@ApiModelProperty(value = "发布人类型，1：是店主，2:不是店主")
    private Integer publisher_type;
	@ApiModelProperty(value = "点赞数")
    private Integer praise_num;
	@ApiModelProperty(value = "发布人ID")
    private Integer publisher_id;
	@ApiModelProperty(value = "发布人名称")
    private String username;
	@ApiModelProperty(value = "发布人头像")
    private String file;
	@ApiModelProperty(value = "评论数")
    private Integer appraise_num;
	@ApiModelProperty(value = "查看数")
    private Integer see_num;
	@ApiModelProperty(value = "店铺ID")
    private Integer shops_id;
	@ApiModelProperty(value = "文章标题")
    private String title;
	@ApiModelProperty(value = "是否已点赞,0:没点赞，1：已点赞")
    private Integer is_appraise;
	@ApiModelProperty(value = "缩略图数据数组")
    private List<SResources> thumbnailIds;
	@ApiModelProperty(value = "视频")
    private String video;
	@ApiModelProperty(value = "话题商品数据数组")
    private List<ProductListResp> productList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer getShops_id() {
		return shops_id;
	}

	public void setShops_id(Integer shops_id) {
		this.shops_id = shops_id;
	}

	public List<ProductListResp> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductListResp> productList) {
		this.productList = productList;
	}

	public String getThumbnailList() {
		return thumbnailList;
	}

	public void setThumbnailList(String thumbnailList) {
		this.thumbnailList = thumbnailList;
	}

	public String getArticleJson() {
		return articleJson;
	}

	public void setArticleJson(String articleJson) {
		this.articleJson = articleJson;
	}

	public List<SResources> getThumbnailIds() {
		return thumbnailIds;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setThumbnailIds(List<SResources> thumbnailIds) {
		this.thumbnailIds = thumbnailIds;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Integer getTopic_type() {
		return topic_type;
	}

	public void setTopic_type(Integer topic_type) {
		this.topic_type = topic_type;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIs_appraise() {
		return is_appraise;
	}

	public void setIs_appraise(Integer is_appraise) {
		this.is_appraise = is_appraise;
	}

	public List<Integer> getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(List<Integer> thumbnail) {
		this.thumbnail = thumbnail;
	}

	public JSONArray getArticle() {
		return article;
	}

	public void setArticle(JSONArray article) {
		this.article = article;
	}

	public Integer getIs_recommend() {
		return is_recommend;
	}

	public void setIs_recommend(Integer is_recommend) {
		this.is_recommend = is_recommend;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getPublisher_type() {
		return publisher_type;
	}

	public void setPublisher_type(Integer publisher_type) {
		this.publisher_type = publisher_type;
	}

	public Integer getPraise_num() {
		return praise_num;
	}

	public void setPraise_num(Integer praise_num) {
		this.praise_num = praise_num;
	}

	public Integer getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(Integer publisher_id) {
		this.publisher_id = publisher_id;
	}

	public Integer getAppraise_num() {
		return appraise_num;
	}

	public void setAppraise_num(Integer appraise_num) {
		this.appraise_num = appraise_num;
	}

	public Integer getSee_num() {
		return see_num;
	}

	public void setSee_num(Integer see_num) {
		this.see_num = see_num;
	}
}