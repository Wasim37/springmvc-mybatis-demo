package com.tyxj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyxj.bean.Role;
import com.tyxj.bean.User;
import com.tyxj.bean.UserExample;
import com.tyxj.bean.UserRole;
import com.tyxj.bean.UserRoleExample;
import com.tyxj.bean.enums.EnumUserStatus;
import com.tyxj.common.Limit;
import com.tyxj.common.MD5Util;
import com.tyxj.common.ObjectUtils;
import com.tyxj.common.SystemConstants;
import com.tyxj.common.exception.UserNotLoginException;
import com.tyxj.dao.mapper.UserMapper;
import com.tyxj.dao.mapper.UserRoleMapper;
import com.tyxj.security.cache.RedisCache;
import com.tyxj.service.IRoleService;
import com.tyxj.service.IUserService;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	protected RedisCache<String, Object> redisCache;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserRoleMapper userRoleMapper;
	
	@Autowired
	private IRoleService roleService;

	@Override
	public User get(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public User getUserByName(String username) {
		UserExample example = new UserExample();
		example.createCriteria().andUserNameEqualTo(username);
		List<User> users = userMapper.selectByExample(example);
		if (ObjectUtils.isNotEmpty(users)) {
			return users.get(0);
		}
		return null;
	}
	
	@Override
	public User getLoginUser() {
		Object userName = SecurityUtils.getSubject().getPrincipal();
		if (ObjectUtils.isEmpty(userName)) {
			throw new UserNotLoginException("geted username is null,please relogin");
		}
		Object obj = redisCache.get(SystemConstants.getUserSessionKey((String) userName));
		if (obj == null) {
			throw new UserNotLoginException("redis cache is null, please relogin");
		}
		return (User) obj;
		
	}
	
	@Override
	public List<User> getPageList(User user, Limit limit) {
		limit.setStart((limit.getPageId()-1)*limit.getSize());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("limit", limit);
		return userMapper.getPageList(map);
	}

	@Override
	public Map<String, Integer> getTotalCountAndPage(User user, Limit limit) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("user", user);
		int totalCount = userMapper.getTotalCount(map);
		int totalPage = totalCount/limit.getSize() + (totalCount%limit.getSize()==0 ? 0 : 1);
		Map<String, Integer> totalMap = new HashMap<String, Integer>();
		totalMap.put("totalCount", totalCount);
		totalMap.put("totalPage", totalPage);
		return totalMap;
	}

	@Override
	public int delUser(String userId) {
		User user = userMapper.selectByPrimaryKey(Integer.parseInt(userId));
		user.setUserStatus((byte)EnumUserStatus.PAUSE.getStatus());
		return userMapper.updateByPrimaryKey(user);
	}

	@Override
	public int addUser(User user, String roleIds) {
		User userTmp = user;
		userTmp.setPassWord(MD5Util.md5s(user.getPassWord()));
		userTmp.setDigest(user.getPassWord());
		userTmp.setCreateTime(new Date());
		userTmp.setUserStatus((byte)EnumUserStatus.NORMAL.getStatus());
		int userAddEffectNum = userMapper.insert(userTmp);
		
		if (userAddEffectNum > 0 && ObjectUtils.isNotEmpty(roleIds)) {
			String[] roles = roleIds.split(",");	
			for(String roleId : roles){
				UserRole userRole = new UserRole();
				userRole.setUserId(user.getUserId());
				userRole.setRoleId(Integer.parseInt(roleId));
				userRole.setCreateTime(new Date());
				userRoleMapper.insert(userRole);
			}
		}
		return 1;
	}

	@Override
	public int uptUser(User user, String roleIds) {
		UserRoleExample urExample = new UserRoleExample();
		urExample.createCriteria().andUserIdEqualTo(user.getUserId());
		userRoleMapper.deleteByExample(urExample);
		
		if (ObjectUtils.isNotEmpty(roleIds)) {
			String[] roles = roleIds.split(",");	
			for(String roleId : roles){
				UserRole userRole = new UserRole();
				userRole.setUserId(user.getUserId());
				userRole.setRoleId(Integer.parseInt(roleId));
				userRole.setCreateTime(new Date());
				userRoleMapper.insert(userRole);
			}
		}
		return 1;
	}

	@Override
	public void resetPwd(Integer userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		user.setPassWord(MD5Util.md5s(SystemConstants.DEFAULT_PASSWORD));
		user.setDigest(SystemConstants.DEFAULT_PASSWORD);
		userMapper.updateByPrimaryKeySelective(user);
	}
	

}
