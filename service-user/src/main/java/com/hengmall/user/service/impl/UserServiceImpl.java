package com.hengmall.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hengmall.user.dao.TbPermissionDao;
import com.hengmall.user.dao.TbRoleDao;
import com.hengmall.user.dao.TbRolePermDao;
import com.hengmall.user.dao.TbUserDao;
import com.hengmall.user.dao.UsersDao;
import com.hengmall.user.dao.WalletDetailsDao;
import com.hengmall.user.dto.RoleDto;
import com.hengmall.user.exception.XmallException;
import com.hengmall.user.model.DataTablesResult;
import com.hengmall.user.model.TbPermission;
import com.hengmall.user.model.TbRole;
import com.hengmall.user.model.TbRolePerm;
import com.hengmall.user.model.TbUser;
import com.hengmall.user.model.UsersEntity;
import com.hengmall.user.service.UserService;
import com.hengmall.user.util.JedisConnectUtil;
import com.hengmall.user.util.MD5Util;
import com.hengmall.user.util.config.Global;

/**
 * @author wuhengbin
 */
@Service
public class UserServiceImpl implements UserService {

//    private static final Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private TbUserDao tbUserDao;
    @Autowired
    private TbRoleDao tbRoleDao;
    @Autowired
    private TbPermissionDao tbPermissionDao;
    @Autowired
    private TbRolePermDao tbRolePermDao;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private WalletDetailsDao walletDetailsDao;
    /**
     * 通过用户名获取用户
     */
    @Override
    public TbUser getUserByUsername(String username) {    
    	TbUser tbUser = tbUserDao.queryByusername(username);
    	List<TbPermission> tbPermissionList = tbUserDao.tbPermissionList(tbUser.getRoleId());
    	tbUser.setPermissions(tbPermissionList);
        return tbUser;
    }

    /**
     * 通过用户名获取角色
     * @param username
     * @return
     */
    @Override
    public Set<String> getRoles(String username) {

        return tbUserDao.getRoles(username);
    }

    /**
     * 通过用户名获取权限
     */
    @Override
    public Set<String> getPermissions(String username) {

        return tbUserDao.getPermissions(username);
    }

    /**
     * 获取角色列表
     */
    @Override
    public DataTablesResult getRoleList(int draw , int length) {

        DataTablesResult result=new DataTablesResult();
        List<RoleDto> list=new ArrayList<>();
        int countRole = tbRoleDao.countTbRole();
        //分页
        //紧跟着分页器后面的第一个查询会被分页
        PageHelper.startPage(draw,length);
        List<TbRole> list1=tbRoleDao.selectForList();
        PageInfo<RoleDto> pageInfo=new PageInfo<>(list);
       
        result.setRecordsFiltered((int)pageInfo.getEndRow());
        result.setRecordsTotal(Math.toIntExact(countRole));
        result.setSuccess(true);
        result.setDraw(draw);
        
        if(list1==null){
            throw new XmallException("获取角色列表失败");
        }
        
//        List<Integer> tbRoleList = new ArrayList<>();
        for(TbRole tbRole:list1){
            RoleDto roleDto=new RoleDto();
            roleDto.setId(tbRole.getId());
            roleDto.setName(tbRole.getName());
            roleDto.setDescription(tbRole.getDescription());
            List<Integer> permissions=tbUserDao.getPermsIdByRoleId(tbRole.getId());
            roleDto.setRoles(permissions);
            list.add(roleDto);
        }
        result.setData(list);
        return result;
    }

    /**
     * 获取所有角色
     */
    @Override
    public List<TbRole> getAllRoles() {

        List<TbRole> list=tbRoleDao.selectForList();
        if(list==null){
            throw new XmallException("获取所有角色列表失败");
        }
        return list;
    }

    /**
     * 添加角色
     */
    @Override
    @Transactional
    public int addRole(TbRole tbRole) {

        if(getRoleByRoleName(tbRole.getName())!=null){
            throw new XmallException("该角色名已存在");
        }
        if(tbRoleDao.insert(tbRole)==0){
            throw new XmallException("添加角色失败");
        }
        if(tbRole.getRoles()!=null){
        	tbRolePermDao.insert(tbRole);
        }
        return 1;
    }

    /**
     * 通过角色名获取角色
     */
    @Override
    public TbRole getRoleByRoleName(String roleName) {
        List<TbRole> list=new ArrayList<>();
        try {
            list=tbRoleDao.queryByRoleName(roleName);
        }catch (Exception e){
            throw new XmallException("通过角色名获取角色失败");
        }
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    /**
     * 判断角色编辑名是否已存在
     */
    @Override
    public boolean getRoleByEditName(int id,String roleName) {

        TbRole tbRole=tbRoleDao.queryById(id);
        TbRole newRole=null;
        if(tbRole==null){
            throw new XmallException("通过ID获取角色失败");
        }
        if(!tbRole.getName().equals(roleName)){
            newRole=getRoleByRoleName(roleName);
        }
        if(newRole==null){
            return true;
        }
        return false;
    }

    /**
     * 更新角色
     */
    @Override
    @Transactional
    public int updateRole(TbRole tbRole) {

        if(!getRoleByEditName(tbRole.getId(),tbRole.getName())){
            throw new XmallException("该角色名已存在");
        }
        if(tbRoleDao.updateById(tbRole)==0){
            throw new XmallException("更新角色失败");
        }
        if(tbRole.getRoles()!=null){
            //删除已有角色-权限
            List<TbRolePerm> list=tbRolePermDao.queryByRoleId(tbRole.getId());
            if(list!=null && list.size() > 0) {
            	tbRolePermDao.delById(list);
            }
            //新增
            if(tbRole.getRoles() != null) {
            	tbRolePermDao.insert(tbRole);
            }
        }else{
            List<TbRolePerm> list=tbRolePermDao.queryByRoleId(tbRole.getId());
            if(list!=null && list.size() > 0) {
            	tbRolePermDao.delById(list);
            }
        }
        return 1;
    }

    /**
     * 删除角色
     */
    @Override
    @Transactional
    public int deleteRole(int id) {

        List<String> list=tbRoleDao.getUsedRoles(id);
        if(list==null){
            throw new XmallException("查询用户角色失败");
        }
        if(list.size()>0){
            return 0;
        }
        if(tbRoleDao.delById(id)!=1){
            throw new XmallException("删除角色失败");
        }
        List<TbRolePerm> list1=tbRolePermDao.queryByRoleId(id);
        if(list1!=null && list1.size() > 0){
            tbRolePermDao.delById(list1);
        }
        return 1;
    }

    /**
     * 统计角色数
     */
    @Override
    public int countRole() {

    	int result=tbRoleDao.countTbRole();
        return result;
    }

    /**
     * 获得所有权限列表
     */
    @Override
    public DataTablesResult getPermissionList(int draw , int length) {

        DataTablesResult result=new DataTablesResult();
        
        long countPermission = tbPermissionDao.countTbPermission();
        //分页
        //紧跟着分页器后面的第一个查询会被分页
        PageHelper.startPage(draw,length);
        List<TbPermission> list=tbPermissionDao.selectForList();
        PageInfo<TbPermission> pageInfo=new PageInfo<>(list);
       
        result.setRecordsFiltered((int)pageInfo.getEndRow());
        result.setRecordsTotal(Math.toIntExact(countPermission));
        result.setSuccess(true);
        result.setDraw(draw);
        
        if(list==null){
            throw new XmallException("获取权限列表失败");
        }
        result.setSuccess(true);
        result.setData(list);
        return result;
    }
    
    /**
     * 获得所有权限列表不分页
     */
    @Override
    public DataTablesResult getPermissionListNo() {

        DataTablesResult result=new DataTablesResult();
        List<TbPermission> list=tbPermissionDao.selectForList();
        result.setSuccess(true);;
        
        if(list==null){
            throw new XmallException("获取权限列表失败");
        }
        result.setSuccess(true);
        result.setData(list);
        return result;
    }

    /**
     * 添加权限
     */
    @Override
    @Transactional
    public int addPermission(TbPermission tbPermission) {

        if(tbPermissionDao.insert(tbPermission)==0){
            throw new XmallException("添加权限失败");
        }
        return 1;
    }

    /**
     * 更新权限
     */
    @Override
    @Transactional
    public int updatePermission(TbPermission tbPermission) {

        if(tbPermissionDao.updateById(tbPermission)==0){
            throw new XmallException("更新权限失败");
        }
        return 1;
    }

    /**
     * 删除权限
     */
    @Override
    @Transactional
    public int deletePermission(int id) {

        if(tbPermissionDao.delById(id)==0){
            throw new XmallException("删除权限失败");
        }
        tbRolePermDao.delById2(id);
        return 1;
    }

    /**
     * 统计权限
     */
    @Override
    public int countPermission() {
        int result=tbPermissionDao.countTbPermission();
        return result;
    }

    /**
     * 获取用户列表
     */
    @Override
    public DataTablesResult getUserList(int draw , int length) {

        DataTablesResult result=new DataTablesResult();
        long countUser = tbUserDao.countTbUser();
        //分页
        //紧跟着分页器后面的第一个查询会被分页
        PageHelper.startPage(draw,length);
        List<TbUser> list=tbUserDao.selectForList();
        PageInfo<TbUser> pageInfo=new PageInfo<>(list);
       
        result.setRecordsFiltered((int)pageInfo.getEndRow());
        result.setRecordsTotal(Math.toIntExact(countUser));
        result.setSuccess(true);
        result.setDraw(draw);
        
        if(list==null){
            throw new XmallException("获取用户列表失败");
        }
        for(TbUser tbUser:list){
            String names="";
            Iterator<String> it=getRoles(tbUser.getUsername()).iterator();
            while (it.hasNext()){
                names+=it.next()+" ";
            }
            tbUser.setPassword("");
            tbUser.setRoleNames(names);
        }
        result.setData(list);
        return result;
    }

	/**
	 * 通过用户名获取
	 */
    @Override
    public boolean getUserByName(String username) {
        TbUser list=tbUserDao.queryByusername(username);
        if(list != null){
            return false;
        }
        return true;
    }

    /**
     * 通过手机获取
     */
    @Override
    public boolean getUserByPhone(String phone) {
        List<TbUser> list=tbUserDao.queryByphone(phone);
        if(list.size()!=0){
            return false;
        }
        return true;
    }

    /**
     * 通过邮件获取
     */
    @Override
    public boolean getUserByEmail(String email) {
        List<TbUser> list=tbUserDao.queryByemaill(email);
        if(list.size()!=0){
            return false;
        }
        return true;
    }

    /**
     * 添加管理员  设置默认密码123456
     */
    @Override
    @Transactional
    public int addUser(TbUser user) {

        if(!getUserByName(user.getUsername())){
            throw new XmallException("用户名已存在");
        }
        if(!getUserByPhone(user.getPhone())){
            throw new XmallException("手机号已存在");
        }
        if(!getUserByEmail(user.getEmail())){
            throw new XmallException("邮箱已存在");
        }
        String md5Pass = DigestUtils.md5DigestAsHex("123456".getBytes());
        user.setPassword(md5Pass);
        user.setState(1);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        if(tbUserDao.insert(user)==0){
            throw new XmallException("添加用户失败");
        }
        return 1;
    }

    /**
     * 通过id获取
     */
    @Override
    public TbUser getUserById(Long id) {

        TbUser tbUser=tbUserDao.queryById(id.intValue());
        if(tbUser==null){
            throw new XmallException("通过ID获取用户失败");
        }
        tbUser.setPassword("");
        return tbUser;
    }

    /**
     * 更新用户
     */
    @Override
    @Transactional
    public int updateUser(TbUser user) {

        TbUser old=tbUserDao.queryById(user.getId().intValue());
        user.setPassword(old.getPassword());
        user.setState(old.getState());
        user.setCreated(old.getCreated());
        user.setUpdated(new Date());
        if(tbUserDao.updateById(user)==0){
            throw new XmallException("更新用户失败");
        }
        return 1;
    }

    /**
     * 更改状态
     */
    @Override
    @Transactional
    public int changeUserState(Long id,int state) {

        TbUser tbUser=tbUserDao.queryById(id.intValue());
        tbUser.setState(state);
        tbUser.setUpdated(new Date());
        if(tbUserDao.updateById(tbUser)==0){
            throw new XmallException("更新用户状态失败");
        }
        return 1;
    }

    /**
     * 修改密码
     */
    @Override
    @Transactional
    public int changePassword(TbUser tbUser) {

    	TbUser old=tbUserDao.queryById(tbUser.getId().intValue());
        String md5Pass = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
        if(old.getPassword().equals(md5Pass)){
        	old.setUpdated(new Date());
            old.setPassword(md5Pass);
            if(tbUserDao.updateById(old)==0){
            	return 0;
            }else{
            	return 1;
            }
        }else{
        	return 0;
        }
    }

    /**
     * 判断编辑用户名
     */
    @Override
    public boolean getUserByEditName(Long id,String username) {

        TbUser tbUser=getUserById(id);
        boolean result=true;
        if(tbUser.getUsername()==null||!tbUser.getUsername().equals(username)){
            result=getUserByName(username);
        }
        return result;
    }

    /**
     * 判断编辑手机
     */
    @Override
    public boolean getUserByEditPhone(Long id,String phone) {

        TbUser tbUser=getUserById(id);
        boolean result=true;
        if(tbUser.getPhone()==null||!tbUser.getPhone().equals(phone)){
            result=getUserByPhone(phone);
        }
        return result;
    }

    /**
     * 判断编辑邮箱
     */
    @Override
    public boolean getUserByEditEmail(Long id,String email) {

        TbUser tbUser=getUserById(id);
        boolean result=true;
        if(tbUser.getEmail()==null||!tbUser.getEmail().equals(email)){
            result=getUserByEmail(email);
        }
        return result;
    }

    /**
     * 删除管理员
     */
    @Override
    @Transactional
    public int deleteUser(Long userId) {
        if(tbUserDao.delById(userId.intValue())==0){
            throw new XmallException("删除用户失败");
        }
        return 1;
    }

    /**
     * 统计管理员
     */
	@Override
	public int countUser() {
		int count = tbUserDao.countTbUser();
		return count;
	}

	/**
	 * 更新用户角色权限列表缓存
	 */
	@Override
	public DataTablesResult changeCache()throws Exception {
//		获取
		DataTablesResult result1 = new DataTablesResult();
        List<TbUser> list=tbUserDao.selectForList();
        for(TbUser user :  list ){
        	String userName = user.getUsername();
        	Set<String> permissionsSet = tbUserDao.getPermissions(userName);
            JedisConnectUtil jedisConnectUtil = new JedisConnectUtil();
            Boolean result = jedisConnectUtil.exists(userName+"token");
            if(result){
            	jedisConnectUtil.del(userName+"token");
            }
             JedisConnectUtil.setList(userName+"token", permissionsSet);
        }

        result1.setSuccess(true);
		return result1;
	}
	@Override
    public Boolean judgeRole(String token,String url){
		String user = JedisConnectUtil.hget(Global.getConfig("login.userDb"), token);
        TbUser tbUser = new TbUser();
        
        JSONObject object = JSON.parseObject(user);
        tbUser.setUsername((String)object.get("username"));
        tbUser.setPhone((String)object.get("phone"));
        tbUser.setEmail((String)object.get("email"));
        tbUser.setSex((String)object.get("sex"));
        tbUser.setAddress((String)object.get("address"));
        tbUser.setState((Integer)object.get("state"));
        tbUser.setDescription((String)object.get("description"));
        tbUser.setFile((String)object.get("file"));
        tbUser.setRoleId(object.getInteger("roleId"));
        
		Set<String> permissions = tbUserDao.getPermissions(tbUser.getUsername());
		Boolean result = permissions.contains(url);
		if(result){
			return result;
    	}else{
    		result = false;
    		throw new XmallException("你没有权限访问该接口或登录已失效");
    	}
    	
    }
	
	/**
	 * 获取token若已经存在就获取缓存中有的，若不存在就产生新的传回
	 * @param userName
	 * @return
	 */
	@Override
    public String getToken(String userName){
        JedisConnectUtil jedisConnectUtil = new JedisConnectUtil();
    	Boolean result =  jedisConnectUtil.exists(userName);
    	if(result){
    		String token = JedisConnectUtil.getnx(userName);
    		 JedisConnectUtil.setnx(token, userName);
    		return token;
    	} else{
    		String token = MD5Util.md5Password(System.currentTimeMillis()+"wall"+userName);
    		JedisConnectUtil.setnx(userName, token);
    		JedisConnectUtil.setnx(token, userName);
    		jedisConnectUtil.expire(userName, 3600*1000*24);
    		jedisConnectUtil.expire(token, 3600*1000*24);
    		return token;
    	}
	}
	
	/**
	 * 获取token若已经存在就获取缓存中有的，若不存在就产生新的传回
	 * @param userName
	 * @return
	 */
	@Override
    public int pursePay(int user_id, int price)throws Exception{
		UsersEntity user = usersDao.queryById(user_id);
		int money = user.getMoney();
		int newMoney = money + price;
		usersDao.addMoney(price, user_id);
		walletDetailsDao.insertToWalletDetails(user_id, price, money, newMoney,1);
		return 0;
	}
}
