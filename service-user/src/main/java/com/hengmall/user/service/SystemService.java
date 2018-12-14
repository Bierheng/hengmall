package com.server.service;

import java.util.List;

import com.server.entity.DataTablesResult;
import com.server.entity.TbLog;
import com.server.entity.TbShiroFilter;

/**
 * @author wuhengbin
 */
public interface SystemService {

    /**
     * 获得shiro过滤链配置
     * @return
     */
    List<TbShiroFilter> getShiroFilter();

    /**
     * 统计过滤链数目
     * @return
     */
    int countShiroFilter();

    /**
     * 添加shiro过滤链
     * @param tbShiroFilter
     * @return
     */
    int addShiroFilter(TbShiroFilter tbShiroFilter);

    /**
     * 更新shiro过滤链
     * @param tbShiroFilter
     * @return
     */
    int updateShiroFilter(TbShiroFilter tbShiroFilter);

    /**
     * 删除shiro过滤链
     * @param id
     * @return
     */
    int deleteShiroFilter(int id);

    /**
     * 添加日志
     * @param tbLog
     * @return
     */
    int addLog(TbLog tbLog);

    /**
     * 获取日志列表
     * @param draw
     * @param start
     * @param length
     * @param search
     * @param orderCol
     * @param orderDir
     * @return
     */
    DataTablesResult getLogList(int draw, int start, int length, String search,String orderCol,String orderDir);

    /**
     * 统计日志数量
     * @return
     */
    int countLog();

    /**
     * 删除日志
     * @param id
     * @return
     */
    int deleteLog(int id);
}
