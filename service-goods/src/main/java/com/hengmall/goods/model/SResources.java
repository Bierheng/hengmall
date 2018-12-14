package com.hengmall.goods.model;

/**
 * 特别用于返回给后台商品页面图片数据的特殊格式模版
 */
public class SResources {

	// 1:图片,2:视频
	public static final int Img_Type = 1;
	public static final int Video_Type = 2;

	private int id;
	private String url; // 资源地址，对象存储地址
	private String name; // 1:图片,2:视频
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ProductBean{");
		buffer.append("id='").append(id).append("'");
		buffer.append("url='").append(url).append("'");
		buffer.append("name='").append(name).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getImgType() {
		return Img_Type;
	}

	public static int getVideoType() {
		return Video_Type;
	}
}
