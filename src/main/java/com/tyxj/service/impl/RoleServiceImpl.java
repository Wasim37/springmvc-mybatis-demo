package com.tyxj.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyxj.bean.Role;
import com.tyxj.bean.RoleExample;
import com.tyxj.bean.RoleFuncExample;
import com.tyxj.bean.UserRoleExample;
import com.tyxj.common.Limit;
import com.tyxj.common.Util;
import com.tyxj.dao.mapper.RoleFuncMapper;
import com.tyxj.dao.mapper.RoleMapper;
import com.tyxj.dao.mapper.UserRoleMapper;
import com.tyxj.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleFuncMapper roleFuncMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	

	@Override
	public List<Role> getRolesByUserId(Integer userId) {
		return roleMapper.getRolesByUserId(userId);
	}

	@Override
	public List<Role> getAllRole() {
		return roleMapper.selectByExample(new RoleExample());
	}

	@Override
	public List<Role> getPageList(Role role, Limit limit) {
		limit.setStart((limit.getPageId()-1)*limit.getSize());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("role", role);
		map.put("limit", limit);
		return roleMapper.getPageList(map);
	}

	@Override
	public Map<String, Integer> getTotalCountAndPage(Role role, Limit limit) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("role", role);
		int totalCount = roleMapper.getTotalCount(map);
		int totalPage = totalCount/limit.getSize() + (totalCount%limit.getSize()==0 ? 0 : 1);
		Map<String, Integer> totalMap = new HashMap<String, Integer>();
		totalMap.put("totalCount", totalCount);
		totalMap.put("totalPage", totalPage);
		return totalMap;
	}

	@Override
	public int delRole(String roleId) {
		//先删roleFunc userRole
		RoleFuncExample roleFuncExample = new RoleFuncExample();
		roleFuncExample.createCriteria().andRoleIdEqualTo(Integer.parseInt(roleId));
		roleFuncMapper.deleteByExample(roleFuncExample);
		UserRoleExample userRoleExample = new UserRoleExample();
		userRoleExample.createCriteria().andRoleIdEqualTo(Integer.parseInt(roleId));
		userRoleMapper.deleteByExample(userRoleExample);
		
		//后删role
		RoleExample example = new RoleExample();
		example.createCriteria().andRoleIdEqualTo(Integer.parseInt(roleId));
		return roleMapper.deleteByExample(example);
	}

	@Override
	public Role getRoleByName(String roleName) {
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andRoleNameEqualTo(roleName);
		List<Role> list = roleMapper.selectByExample(roleExample);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int addRole(String roleName) {
		Role role = new Role();
		role.setCreateTime(new Date());
		role.setRoleCode("");
		role.setRoleName(roleName);
		return roleMapper.insert(role);
	}

	@Override
	public int uptRole(Role role) {
		RoleExample example = new RoleExample();
		example.createCriteria().andRoleIdEqualTo(role.getRoleId());
		List<Role> rolrList = roleMapper.selectByExample(example);
		Role roleTmp = new Role();
		if(!rolrList.isEmpty()){
			roleTmp = rolrList.get(0);
			roleTmp.setRoleName(role.getRoleName());
			roleMapper.updateByPrimaryKey(roleTmp);
		}
		return 1;
	}

}