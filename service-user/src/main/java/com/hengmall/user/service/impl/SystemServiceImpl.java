package com.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.server.dao.TbLogDao;
import com.server.dao.TbShiroFilterDao;
import com.server.entity.DataTablesResult;
import com.server.entity.TbLog;
import com.server.entity.TbShiroFilter;
import com.server.exception.XmallException;
import com.server.service.SystemService;

/**
 * @author wuhengbin
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private TbShiroFilterDao tbShiroFilterDao;

    @Autowired
    private TbLogDao tbLogDao;


    @Value("${BASE_ID}")
    private String BASE_ID;

    /**
     * 获得shiro过滤链配置
     */
    @Override
    public List<TbShiroFilter> getShiroFilter() {
        List<TbShiroFilter> list=tbShiroFilterDao.queryByOrder();
        if(list==null){
            throw new XmallException("获取shiro过滤链失败");
        }
        return list;
    }

    /**
     * 统计过滤链数目
     */
	@Override
    public int countShiroFilter() {

		int result=tbShiroFilterDao.countTbShiroFilter();
        return result;
    }

	/**
	 * 添加shiro过滤链
	 */
    @Override
    public int addShiroFilter(TbShiroFilter tbShiroFilter) {

        if(tbShiroFilterDao.insert(tbShiroFilter)!=1){
            throw new XmallException("添加shiro过滤链失败");
        }
        return 1;
    }

    /**
     * 更新shiro过滤链
     */
    @Override
    public int updateShiroFilter(TbShiroFilter tbShiroFilter) {

        if(tbShiroFilterDao.updateById(tbShiroFilter)!=1){
            throw new XmallException("更新shiro过滤链失败");
        }
        return 1;
    }

    /**
     * 删除shiro过滤链
     */
    @Override
    public int deleteShiroFilter(int id) {

        if(tbShiroFilterDao.delById(id)!=1){
            throw new XmallException("删除shiro过滤链失败");
        }
        return 1;
    }

    /**
     * 添加日志
     */
    @Override
    public int addLog(TbLog tbLog) {

        if(tbLogDao.insert(tbLog)!=1){
            throw new XmallException("保存日志失败");
        }
        return 1;
    }

    @Override
    public DataTablesResult getLogList(int draw, int start, int length, String search,String orderCol,String orderDir) {

        DataTablesResult result=new DataTablesResult();
        //分页
//       PageHelper.startPage(start/length+1,length);
//       List<TbLog> list = tbLogMapper.selectByMulti("%"+search+"%",orderCol,orderDir);
//        PageInfo<TbLog> pageInfo=new PageInfo<>(list);
//
//       result.setRecordsFiltered((int)pageInfo.getTotal());
//        result.setRecordsTotal(Math.toIntExact(countLog()));
//
//        result.setDraw(draw);
//        result.setData(list);
        return result;
    }

    @Override
    public int countLog() {
    	int result=tbLogDao.countTbLog();
        return result;
    }

    @Override
    public int deleteLog(int id) {

        if(tbLogDao.delById(id)!=1){
            throw new XmallException("删除日志失败");
        }
        return 1;
    }
}
