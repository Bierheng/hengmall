package com.server.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.server.dao.SensitiveWordDao;
import com.server.entity.persistence.Page;
import com.server.entity.sensitive.SensitiveDelRequest;
import com.server.entity.sensitive.SensitiveWordNewRequest;
import com.server.entity.sensitive.SensitiveWordRequest;
import com.server.entity.sensitive.SensitiveWordResponse;
import com.server.service.SensitiveWordService;

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
