package com.tyxj.common;

import java.io.Serializable;

/**
 * @ClassName: SystemConstants
 * @Description: 常量类
 * @author WangX
 * @Date: 2016年5月16日 下午8:09:45
 *
 */
public class SystemConstants {
	
	//登录用户前缀
	public static final String DEFAULT_PASSWORD = "123456_fnd";
	
	//用户session key
	public static final String getUserSessionKey(Serializable userId) {
		return "USER_SESSION_KEY_" + userId;
	}

	//用户 roles key
	public static final String getUserRolesCacheKey(Serializable userId) {
		return "USER_ROLES_KEY_" + userId;
	}

	//用户 function key
	public static final String getUserFunctionCacheKey(Serializable userId) {
		return "USER_FUNCTION_KEY_" + userId;
	}
}
