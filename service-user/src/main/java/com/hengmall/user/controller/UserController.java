package com.hengmall.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hengmall.user.model.DataTablesResult;
import com.hengmall.user.model.DelBean;
import com.hengmall.user.model.ResMessage;
import com.hengmall.user.model.Result;
import com.hengmall.user.model.TbPermission;
import com.hengmall.user.model.TbRole;
import com.hengmall.user.model.TbUser;
import com.hengmall.user.service.UserService;
import com.hengmall.user.util.CommonUtils;
import com.hengmall.user.util.JedisConnectUtil;
import com.hengmall.user.util.MD5Util;
import com.hengmall.user.util.ResultUtil;
import com.hengmall.user.util.config.Global;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wuhengbin
 */
@RestController
@Api(description = "管理员管理")
public class UserController {

    final static Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
//    @SystemControllerLog(description="登录系统")
    public Result<Object> login(String username, String password,
                                HttpServletRequest request)throws Exception{
         try {  
		 String passwordMd5 = MD5Util.md5Password(password);
//    		 shiro框架自有的token
    	 UsernamePasswordToken token = new UsernamePasswordToken(username, passwordMd5);  
    	  
         Subject subject = SecurityUtils.getSubject();  
          // 登录，即身份验证  
         subject.login(token);    
         TbUser user = userService.getUserByUsername(token.getUsername()); 
         
         // 在session中存放用户信息  
         //subject.getSession().setAttribute("userLogin", user);
//        用于真正校验接口数据权限的token
         //String newToken = userService.getToken(username); 
         
         String newToken = MD5Util.md5Password(System.currentTimeMillis()+"wall"+username);
         
         //检查用户信息是否存在，存在则先删除
         String oldToken = JedisConnectUtil.hget(Global.getConfig("login.userDb"), username);
         if(oldToken != null) {
        	 JedisConnectUtil.hdel(Global.getConfig("login.userDb"), username);
        	 JedisConnectUtil.hdel(Global.getConfig("login.userDb"), oldToken);
         }
         
         //存放用户信息
         JedisConnectUtil.hsetnx(Global.getConfig("login.userDb"),newToken,JSON.toJSONString(user));
         JedisConnectUtil.hsetnx(Global.getConfig("login.userDb"),username,newToken);
         
         return new ResultUtil<Object>().setData(newToken);
         } catch (Exception e) {
        	 e.printStackTrace();
        	 return new ResultUtil<Object>().setErrorMsg("用户名或密码错误");
         }
    }

    @RequestMapping(value = "/user/logout",method = RequestMethod.GET)
    @ApiOperation(value = "退出登录")
    public Result<Object> logout(){
    	String token = request.getHeader("Authorization");
    	String user = JedisConnectUtil.hget(Global.getConfig("login.userDb"), token);
    	JSONObject userdata = JSON.parseObject(user);
    	String username = (String)userdata.get("username");
    	JedisConnectUtil.hdel(Global.getConfig("login.userDb"), username);
    	JedisConnectUtil.hdel(Global.getConfig("login.userDb"), token);  //退出清除用户信息
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/user/userInfo",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ApiOperation(value = "获取登录用户信息")
    @ResponseBody
    public Result<TbUser> getUserInfo(String username){
    	String token = request.getHeader("Authorization");

    	//统一拦截器拦截这种错误，故这里注释
    	/*if(!JedisConnectUtil.hexists(Global.getConfig("login.userDb"),token)) {
    		return new ResultUtil<TbUser>().setErrorMsg("访问有误或者登录信息过期");
        }*/
        String userinfo = JedisConnectUtil.hget(Global.getConfig("login.userDb"), token);
        
        if(userinfo == null || userinfo.equals("")) {
        	return new ResultUtil<TbUser>().setErrorMsg(ResMessage.OTHER.code, ResMessage.OTHER.message);
        }
        
        TbUser tbUser = new TbUser();
        
        JSONObject object = JSON.parseObject(userinfo);
        tbUser.setUsername((String)object.get("username"));
        tbUser.setPhone((String)object.get("phone"));
        tbUser.setEmail((String)object.get("email"));
        tbUser.setSex((String)object.get("sex"));
        tbUser.setAddress((String)object.get("address"));
        tbUser.setState((Integer)object.get("state"));
        tbUser.setDescription((String)object.get("description"));
        tbUser.setFile((String)object.get("file"));
        tbUser.setRoleId(object.getInteger("roleId"));
        
        return new ResultUtil<TbUser>().setData(tbUser);
    }

    @RequestMapping(value = "/user/roleList",method = RequestMethod.GET)
    @ApiOperation(value = "获取角色列表")
    public DataTablesResult getRoleList(int draw , int length){

        DataTablesResult result=userService.getRoleList(draw,length);
        return result;
    }

    @RequestMapping(value = "/user/getAllRoles",method = RequestMethod.GET)
    @ApiOperation(value = "获取所有角色")
    public Result<List<TbRole>> getAllRoles(){

        List<TbRole> list=userService.getAllRoles();
        return new ResultUtil<List<TbRole>>().setData(list);
    }

    @RequestMapping(value = "/user/roleName",method = RequestMethod.GET)
    @ApiOperation(value = "判断角色是否已存在")
    public boolean roleName(String name){

        if(userService.getRoleByRoleName(name)!=null){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/user/edit/roleName/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "判断编辑角色是否已存在")
    public boolean roleName(@PathVariable int id,String name){

        return userService.getRoleByEditName(id,name);
    }

    @RequestMapping(value = "/user/addRole",method = RequestMethod.POST)
    @ApiOperation(value = "添加或修改角色")
    public DataTablesResult addRole(@RequestBody TbRole tbRole){
    	DataTablesResult result = new DataTablesResult();
    	if(CommonUtils.judge(String.valueOf(tbRole.getId()))){
    		 userService.updateRole(tbRole);
    		 result.setSuccess(true);
    		 return result;
    	}else{
    		 userService.addRole(tbRole);
    		 result.setSuccess(true);
    		 return result;
    	}
       
    }

    @RequestMapping(value = "/user/delRole",method = RequestMethod.POST)
    @ApiOperation(value = "删除角色")
    public Result<Object> delRole(@RequestBody DelBean ids){
    		int id = ids.getIds();
            int result=userService.deleteRole(id);
            if(result==0){
                return new ResultUtil<Object>().setErrorMsg("id为"+id+"的角色被使用中，不能删除！");
            }
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/user/roleCount",method = RequestMethod.GET)
    @ApiOperation(value = "统计角色总数")
    public Result<Object> getRoleCount(){

        int result=userService.countRole();
        return new ResultUtil<Object>().setData(result);
    }

    @RequestMapping(value = "/user/permissionList",method = RequestMethod.GET)
    @ApiOperation(value = "获取权限列表")
    public DataTablesResult getPermissionList(int draw , int length){
    	if(CommonUtils.judge(String.valueOf(draw)) && CommonUtils.judge(String.valueOf(length))){
            DataTablesResult result=userService.getPermissionList(draw, length);
            return result;
    	}else{
            DataTablesResult result=userService.getPermissionList(1, 1000);
            return result;
    	}
    }
    
    @RequestMapping(value = "/user/permissionListNo",method = RequestMethod.GET)
    @ApiOperation(value = "获取权限列表(不进行分页)")
    public DataTablesResult getPermissionListNo(){
            DataTablesResult result=userService.getPermissionListNo();
            return result;
    }

    @RequestMapping(value = "/user/addPermission",method = RequestMethod.POST)
    @ApiOperation(value = "添加或修改权限")
    public DataTablesResult addPermission(@RequestBody TbPermission tbPermission){
    	DataTablesResult result = new DataTablesResult();
    	if(CommonUtils.judge(String.valueOf(tbPermission.getId()))){
    		userService.updatePermission(tbPermission);
    		result.setSuccess(true);
   		 	return result;
    	}else{
            userService.addPermission(tbPermission);
       		result.setSuccess(true);
   		 	return result;
    	}
    }

    @RequestMapping(value = "/user/delPermission",method = RequestMethod.POST)
    @ApiOperation(value = "删除权限")
    public Result<Object> delPermission(@RequestBody DelBean ids){
    	String url = request.getServletPath();
    	System.out.println("删除操作的请求URL为："+url);
    	int id = ids.getIds();
            userService.deletePermission(id);
    
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/user/permissionCount",method = RequestMethod.GET)
    @ApiOperation(value = "统计权限总数")
    public Result<Object> getPermissionCount(){

        int result=userService.countPermission();
        return new ResultUtil<Object>().setData(result);
    }

    @RequestMapping(value = "/user/userList",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户列表")
    public DataTablesResult getUserList(int draw , int length ){

        DataTablesResult result=userService.getUserList( draw ,  length);
        return result;
    }

//    @RequestMapping(value = "/user/username",method = RequestMethod.GET)
//    @ApiOperation(value = "判断用户名是否存在")
//    public boolean getUserByName(String username){
//
//        return userService.getUserByName(username);
//    }
//
//    @RequestMapping(value = "/user/phone",method = RequestMethod.GET)
//    @ApiOperation(value = "判断手机是否存在")
//    public boolean getUserByPhone(String phone){
//
//        return userService.getUserByPhone(phone);
//    }
//
//    @RequestMapping(value = "/user/email",method = RequestMethod.GET)
//    @ApiOperation(value = "判断邮箱是否存在")
//    public boolean getUserByEmail(String email){
//
//        return userService.getUserByEmail(email);
//    }

    @RequestMapping(value = "/user/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户")
    public DataTablesResult addUser(@RequestBody TbUser tbUser){
    	DataTablesResult result = new DataTablesResult();
    	if(CommonUtils.judge(String.valueOf(tbUser.getId()))){
            userService.updateUser(tbUser);
            result.setSuccess(true);
   		 	return result;
    	}else{
            userService.addUser(tbUser);
            result.setSuccess(true);
   		 	return result;
    	}
    }

//    @RequestMapping(value = "/user/edit/username/{id}",method = RequestMethod.GET)
//    @ApiOperation(value = "判断编辑用户名是否存在")
//    public boolean getUserByEditName(@PathVariable Long id, String username){
//
//        return userService.getUserByEditName(id,username);
//    }
//
//    @RequestMapping(value = "/user/edit/phone/{id}",method = RequestMethod.GET)
//    @ApiOperation(value = "判断编辑手机是否存在")
//    public boolean getUserByEditPhone(@PathVariable Long id, String phone){
//
//        return userService.getUserByEditPhone(id,phone);
//    }
//
//    @RequestMapping(value = "/user/edit/email/{id}",method = RequestMethod.GET)
//    @ApiOperation(value = "判断编辑用户名是否存在")
//    public boolean getUserByEditEmail(@PathVariable Long id, String email){
//
//        return userService.getUserByEditEmail(id,email);
//    }

    @RequestMapping(value = "/user/stop/{id}",method = RequestMethod.PUT)
    @ApiOperation(value = "停用用户")
    public Result<Object> stopUser(@PathVariable Long id){

        userService.changeUserState(id,0);
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/user/start/{id}",method = RequestMethod.PUT)
    @ApiOperation(value = "启用用户")
    public Result<Object> startUser(@PathVariable Long id){

        userService.changeUserState(id,1);
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/user/changePass",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户密码")
    public DataTablesResult changePass(@RequestBody TbUser tbUser){
    	DataTablesResult result = new DataTablesResult();
       int i = userService.changePassword(tbUser);
       if(i == 1){
    	   result.setSuccess(true);
    	   return result;
       }else{
    	   result.setSuccess(false);
    	   result.setError("修改密码失败,旧密码错误！");
    	   return result;
       }
    }

    @RequestMapping(value = "/user/delUser",method = RequestMethod.POST)
    @ApiOperation(value = "删除用户")
    public Result<Object> delUser(@RequestBody  DelBean ids){
    		int id = ids.getIds();
            userService.deleteUser((long)id);
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/user/userCount",method = RequestMethod.GET)
    @ApiOperation(value = "统计用户总数")
    public Result<Object> getUserCount(){

        int result=userService.countUser();
        return new ResultUtil<Object>().setData(result);
    }
    
    @RequestMapping(value = "/user/changeCache",method = RequestMethod.GET)
    @ApiOperation(value = "更新角色权限列表缓存")
    public DataTablesResult changeCache(){
    	try {
            DataTablesResult result=userService.getPermissionListNo();
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			DataTablesResult result = new DataTablesResult();
			result.setSuccess(false);
			return result;
		}
    }
    
	/**
	 * 校验token是否在缓存中存在 若存在则为真
	 * @param token
	 * @return
	 */
    public Boolean judgeToken(String token){
        JedisConnectUtil jedisConnectUtil = new JedisConnectUtil();
    	Boolean result =  jedisConnectUtil.exists(token);
    	return result;
    }
    
/*    @RequestMapping(value = "/user/testToken",method = RequestMethod.GET)
    @ApiOperation(value = "测试token")
    public Result<Object> testToken( String username){
    	String token = getToken(username);
    	System.out.println("获取到的token:"+token);
    	String userName = JedisConnectUtil.getnx(token);
    	System.out.println("获取到的用户:"+userName);
    	Boolean result = judgeToken(token);
    	System.out.println("判断缓存中是否有该token的校验结果"+result);
    	return new ResultUtil<Object>().setData(null);
    	

    }*/
}
