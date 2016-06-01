package com.tyxj.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyxj.bean.UserRoleExample;
import com.tyxj.bean.dto.UserRoleDTO;
import com.tyxj.dao.mapper.UserRoleMapper;
import com.tyxj.service.IUserRoleService;

@Service
@Transactional
public class UserRoleServiceImpl implements IUserRoleService{
	
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public List<UserRoleDTO> getInfoByUserId(Integer userId) {
		List<UserRoleDTO> dtoList = userRoleMapper.getInfoByUserId(userId);
		return null;
	}

}
