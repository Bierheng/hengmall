package com.server.controller.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.server.entity.DataTablesResult;
import com.server.entity.Result;
import com.server.entity.TbShiroFilter;
import com.server.entity.TbUser;
import com.server.service.SystemService;
import com.server.utils.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wuhengbin
 */
@RestController
@Api(description= "系统配置管理")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/sys/shiro/shiroList",method = RequestMethod.GET)
    @ApiOperation(value = "获取shiro过滤链配置")
    public DataTablesResult getShiroList(@ModelAttribute TbUser tbUser){

        DataTablesResult result=new DataTablesResult();
        List<TbShiroFilter> list=systemService.getShiroFilter();
        result.setData(list);
        result.setSuccess(true);
        return result;
    }

    @RequestMapping(value = "/sys/shiro/countShiro",method = RequestMethod.GET)
    @ApiOperation(value = "统计shiro过滤链数")
    public Result<Object> getUserCount(){

        int result=systemService.countShiroFilter();
        return new ResultUtil<Object>().setData(result);
    }

    @RequestMapping(value = "/sys/shiro/addShiro",method = RequestMethod.POST)
    @ApiOperation(value = "添加shiro过滤链")
    public Result<Object> addShiro(@ModelAttribute TbShiroFilter tbShiroFilter){

        systemService.addShiroFilter(tbShiroFilter);
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/sys/shiro/updateShiro",method = RequestMethod.POST)
    @ApiOperation(value = "更新shiro过滤链")
    public Result<Object> updateShiro(@ModelAttribute TbShiroFilter tbShiroFilter){

        systemService.updateShiroFilter(tbShiroFilter);
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/sys/shiro/delShiro",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除shiro过滤链")
    public Result<Object> delShiro(@PathVariable int[] ids){
        for(int id:ids){
            systemService.deleteShiroFilter(id);
        }
        return new ResultUtil<Object>().setData(null);
    }
}
