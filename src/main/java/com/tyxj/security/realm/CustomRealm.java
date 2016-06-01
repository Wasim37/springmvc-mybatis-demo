package com.tyxj.security.realm;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.tyxj.bean.Function;
import com.tyxj.bean.Role;
import com.tyxj.bean.User;
import com.tyxj.common.MD5Util;
import com.tyxj.common.ObjectUtils;
import com.tyxj.common.SystemConstants;
import com.tyxj.security.cache.RedisCache;
import com.tyxj.service.IFunctionService;
import com.tyxj.service.IRoleService;
import com.tyxj.service.IUserService;

public class CustomRealm extends AuthorizingRealm implements Realm, InitializingBean {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IFunctionService functionService;
	
	@Autowired
	private RedisCache<String, Object> redisCache;

	private static final Logger LOGGER = Logger.getLogger(CustomRealm.class);

	public CustomRealm() {
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User loginUser = userService.getUserByName(token.getUsername());
		if (ObjectUtils.isEmpty(loginUser)) {
			throw new UnknownAccountException();
		}
		if(!loginUser.getPassWord().equals(MD5Util.md5s(String.valueOf(token.getPassword())))){
			throw new IncorrectCredentialsException();
		}
		redisCache.put(SystemConstants.getUserSessionKey(loginUser.getUserName()), loginUser);
		return new SimpleAuthenticationInfo(loginUser.getUserName(), loginUser.getPassWord(), getName());
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LOGGER.info("授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String username = (String) super.getAvailablePrincipal(principals);
		User user = userService.getUserByName(username);
		if (user == null) {
			return info;
		}
		List<Role> roleList = roleService.getRolesByUserId(user.getUserId());
		StringBuilder sb = new StringBuilder();
		for (Role role : roleList) {
			info.addRole(role.getRoleCode());
			sb.append(role.getRoleId()).append(",");
		}
		if (sb.length() > 0) {
			List<Function> perms = functionService.getFuncNamesByRoleIds(sb.substring(0, sb.length() - 1));
			for (Function function : perms) {
				info.addStringPermission(function.getFunCode());
			}
			redisCache.put(SystemConstants.getUserFunctionCacheKey(user.getUserName()), perms);
		}
		redisCache.put(SystemConstants.getUserRolesCacheKey(user.getUserName()), roleList);
		return info;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		super.clearCachedAuthorizationInfo(principals);
		super.clearCache(principals);
		super.clearCachedAuthenticationInfo(principals);
		redisCache.remove(SystemConstants.getUserFunctionCacheKey(principal));
		redisCache.remove(SystemConstants.getUserRolesCacheKey(principal));
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		redisCache.clear();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		return;
	}

}
