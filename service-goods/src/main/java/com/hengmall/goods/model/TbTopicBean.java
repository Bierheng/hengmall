package com.hengmall.goods.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class TbTopicBean {
	
	 private Integer id;
	@ApiModelProperty(value = "话题类型，1:文章，2:视频")
    private Integer topic_type;
	@ApiModelProperty(value = "缩略图")
    private String thumbnail;
	@ApiModelProperty(value = "文章")
    private String article;
	@ApiModelProperty(value = "是否推荐,0:否；1：是")
    private Integer is_recommend;
	@ApiModelProperty(value = "创建时间")
    private Date create_time;
	@ApiModelProperty(value = "发布人类型，1：卖家，2:买家")
    private Integer publisher_type;
	@ApiModelProperty(value = "点赞数")
    private Integer praise_num;
	@ApiModelProperty(value = "发布人ID")
    private Integer publisher_id;
	@ApiModelProperty(value = "评论数")
    private Integer appraise_num;
	@ApiModelProperty(value = "查看数")
    private Integer see_num;
	@ApiModelProperty(value = "文章标题")
    private String title;
	@ApiModelProperty(value = "视频")
    private String video;
	@ApiModelProperty(value = "是否已点赞,0:没点赞，1：已点赞")
    private Integer is_appraise;
	
	
	

    public Integer getIs_appraise() {
		return is_appraise;
	}

	public void setIs_appraise(Integer is_appraise) {
		this.is_appraise = is_appraise;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    
	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTopic_type() {
		return topic_type;
	}

	public void setTopic_type(Integer topic_type) {
		this.topic_type = topic_type;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
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