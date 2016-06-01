package com.tyxj.service;

import java.util.List;

import com.tyxj.bean.dto.UserRoleDTO;

public interface IUserRoleService {

	/**
	 * 根据用户id获取数据
	 * @Title: getInfoByUserId
	 * @author WangX
	 * @Date: 2016年5月18日 上午10:25:47
	 * @param @param userId
	 * @param @return
	 * @return List<UserRoleDTO>
	 */
	List<UserRoleDTO> getInfoByUserId(Integer userId);

}
