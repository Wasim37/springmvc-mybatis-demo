package com.tyxj.service;

import java.util.List;

import com.tyxj.bean.Function;
import com.tyxj.bean.Role;

public interface IFunctionService {

	/**
	 * 根据角色ids获取权限
	 * @Title: getFuncNamesByRoleIds
	 * @author WangX
	 * @Date: 2016年5月17日 上午10:01:07
	 * @param @param roleIds
	 * @param @return
	 * @return List<Function>
	 */
	List<Function> getFuncNamesByRoleIds(String roleIds);

	/**
	 * 获取所有功能列表
	 * @Title: getAllFunction
	 * @author WangX
	 * @Date: 2016年5月19日 下午3:51:54
	 * @param @return
	 * @return List<Function>
	 */
	List<Function> getAllFunction();

	/**
	 * 修改角色权限
	 * @Title: editRoleFunc
	 * @author WangX
	 * @Date: 2016年5月19日 下午6:23:47
	 * @param @param roleId
	 * @param @param menuIds
	 * @param @return
	 * @return int
	 */
	int editRoleFunc(Integer roleId, String menuIds);

	/**
	 * 根据用户id获取功能列表
	 * @Title: getFunctionByUserId
	 * @author WangX
	 * @Date: 2016年5月20日 下午3:39:18
	 * @param @param userId
	 * @param @return
	 * @return List<Function>
	 */
	List<Function> getFunctionByUserId(Integer userId);


}
