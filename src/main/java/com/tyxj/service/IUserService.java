package com.tyxj.service;

import java.util.List;
import java.util.Map;

import com.tyxj.bean.User;
import com.tyxj.common.Limit;

public interface IUserService {
	
	/**
	 * 根据主键ID获取用户
	 * @Title: get
	 * @author WangX
	 * @Date: 2016年5月16日 下午3:09:09
	 * @param @param id
	 * @param @return
	 * @return User
	 */
	User get(Integer id);
	
	/**
	 * 根据用户名获取用户
	 * @Title: getUserByName
	 * @author WangX
	 * @Date: 2016年5月16日 下午3:08:21
	 * @param @param userName
	 * @param @return
	 * @return User
	 */
	User getUserByName(String userName);
	
	/**
	 * 获取当前登录用户
	 * @Title: getLoginUser
	 * @author WangX
	 * @Date: 2016年5月16日 下午7:53:18
	 * @param @return
	 * @return User
	 */
	User getLoginUser();

	/**
	 * 分页查询用户
	 * @Title: getPageList
	 * @author WangX
	 * @Date: 2016年5月18日 上午11:19:52
	 * @param @param user
	 * @param @param limit
	 * @param @return
	 * @return List<User>
	 */
	List<User> getPageList(User user, Limit limit);

	/**
	 * 根据条件获取页面总数和数据总数
	 * @Title: getTotalCount
	 * @author WangX
	 * @Date: 2016年5月18日 下午3:17:22
	 * @param @param user
	 * @param @param limit
	 * @param @return
	 * @return Map<String,Integer>
	 */
	Map<String, Integer> getTotalCountAndPage(User user, Limit limit);

	/**
	 * 用户删除
	 * @Title: delUser
	 * @author WangX
	 * @Date: 2016年5月18日 下午5:27:13
	 * @param @param userId
	 * @param @return
	 * @return int
	 */
	int delUser(String userId);

	/**
	 * 新增用户
	 * @Title: addUser
	 * @author WangX
	 * @Date: 2016年5月19日 上午10:57:53
	 * @param @param user
	 * @param @param roleIds
	 * @param @return
	 * @return int
	 */
	int addUser(User user, String roleIds);

	/**
	 * 更新用户
	 * @Title: uptUser
	 * @author WangX
	 * @Date: 2016年5月19日 下午2:08:42
	 * @param @param user
	 * @param @param roleIds
	 * @param @return
	 * @return int
	 */
	int uptUser(User user, String roleIds);

	/**
	 * 用户重置密码
	 * @Title: resetPwd
	 * @author WangX
	 * @Date: 2016-5-31 下午3:56:06
	 * @param @param userId
	 * @return void
	 */
	void resetPwd(Integer userId);
	
	
}
