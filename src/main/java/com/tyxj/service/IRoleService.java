package com.tyxj.service;

import java.util.List;
import java.util.Map;

import com.tyxj.bean.Role;
import com.tyxj.common.Limit;

public interface IRoleService {

	/**
	 * 根据用户id获取角色
	 * @Title: getRolesByUserId
	 * @author WangX
	 * @Date: 2016年5月17日 上午10:00:43
	 * @param @param userId
	 * @param @return
	 * @return List<Role>
	 */
	List<Role> getRolesByUserId(Integer userId);

	/**
	 * 查询所有角色
	 * @Title: getAllRole
	 * @author WangX
	 * @Date: 2016年5月18日 上午9:52:36
	 * @param @return
	 * @return List<Role>
	 */
	List<Role> getAllRole();

	/**
	 * 分页查询
	 * @Title: getPageList
	 * @author WangX
	 * @Date: 2016年5月18日 下午3:55:36
	 * @param @param role
	 * @param @param limit
	 * @param @return
	 * @return List<Role>
	 */
	List<Role> getPageList(Role role, Limit limit);

	/**
	 * 获取页面总数和数量总数
	 * @Title: getTotalCountAndPage
	 * @author WangX
	 * @Date: 2016年5月18日 下午3:56:05
	 * @param @param role
	 * @param @param limit
	 * @param @return
	 * @return Map<String,Integer>
	 */
	Map<String, Integer> getTotalCountAndPage(Role role, Limit limit);

	/**
	 * 角色删除
	 * @Title: batchDelete
	 * @author WangX
	 * @Date: 2016年5月18日 下午4:28:16
	 * @param @param roleId
	 * @param @return
	 * @return int
	 */
	int delRole(String roleId);

	/**
	 * 根据名称获取角色
	 * @Title: getRoleByName
	 * @author WangX
	 * @Date: 2016年5月19日 下午2:39:29
	 * @param @param roleName
	 * @param @return
	 * @return Role
	 */
	Role getRoleByName(String roleName);

	/**
	 * 新增角色
	 * @Title: addRole
	 * @author WangX
	 * @Date: 2016年5月19日 下午2:44:02
	 * @param @param roleName
	 * @param @return
	 * @return int
	 */
	int addRole(String roleName);

	/**
	 * 更新角色名称
	 * @Title: uptRole
	 * @author WangX
	 * @Date: 2016年5月19日 下午3:27:24
	 * @param @param role
	 * @param @return
	 * @return int
	 */
	int uptRole(Role role);

}
