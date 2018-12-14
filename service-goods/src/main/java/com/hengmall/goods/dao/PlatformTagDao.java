package com.hengmall.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hengmall.goods.model.PlatformTagEntity;

@Repository
public interface PlatformTagDao {
	
	@Select("select * from platform_tag limit 3")
	List<PlatformTagEntity> queryPlatformTagList();
	
}
