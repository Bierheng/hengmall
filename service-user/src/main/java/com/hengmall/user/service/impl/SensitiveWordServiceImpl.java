package com.hengmall.user.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hengmall.user.dao.SensitiveWordDao;
import com.hengmall.user.model.persistence.Page;
import com.hengmall.user.model.sensitive.SensitiveDelRequest;
import com.hengmall.user.model.sensitive.SensitiveWordNewRequest;
import com.hengmall.user.model.sensitive.SensitiveWordRequest;
import com.hengmall.user.model.sensitive.SensitiveWordResponse;
import com.hengmall.user.service.SensitiveWordService;

/**
 * 敏感词库serviceImpl
 * @author Administrator
 *
 */
@Service("sensitiveWordService")
public class SensitiveWordServiceImpl implements SensitiveWordService {

	@Autowired
	private SensitiveWordDao sensitiveWordDao;
	
	@Override
	public Set<String> setSensitiveWord() {
		return sensitiveWordDao.setSensitiveWord();
	}

	@Override
	public Page<SensitiveWordResponse> getList(SensitiveWordRequest sensitiveWordRequest) {
		//设置分页数据
		PageHelper.startPage(sensitiveWordRequest.getPage(),sensitiveWordRequest.getListPage());
		List<SensitiveWordResponse> list = sensitiveWordDao.getList(sensitiveWordRequest);
		//获得总条数
		PageInfo<SensitiveWordResponse> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
				
		Page<SensitiveWordResponse> rl = new Page<SensitiveWordResponse>();
		rl.setData(list);
		rl.setCount(total);
		return rl;
	}

	
	/**
	 * 新增敏感词
	 */
	@Override
	public int newSensitiveWord(SensitiveWordNewRequest sensitiveWordNewRequest) {
		return sensitiveWordDao.newSensitiveWord(sensitiveWordNewRequest);
	}

	/**
	 * 删除敏感词
	 */
	@Override
	public int delSensitiveWord(SensitiveDelRequest sensitiveDelRequest) {
		return sensitiveWordDao.delSensitiveWord(sensitiveDelRequest);
	}

}
