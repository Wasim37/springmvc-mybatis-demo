package com.tyxj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyxj.bean.Function;
import com.tyxj.bean.FunctionExample;
import com.tyxj.bean.RoleExample;
import com.tyxj.bean.RoleFunc;
import com.tyxj.bean.RoleFuncExample;
import com.tyxj.common.ObjectUtils;
import com.tyxj.dao.mapper.FunctionMapper;
import com.tyxj.dao.mapper.RoleFuncMapper;
import com.tyxj.service.IFunctionService;

@Service
public class FunctionServiceImpl implements IFunctionService{
	
	@Autowired
	FunctionMapper functionMapper;
	
	@Autowired
	RoleFuncMapper roleFuncMapper;

	@Override
	public List<Function> getFuncNamesByRoleIds(String roleIds) {
		return functionMapper.getFuncNamesByRoleIds(roleIds);
	}

	@Override
	public List<Function> getAllFunction() {
		FunctionExample example = new FunctionExample();
		example.setOrderByClause("fun_level,fun_pid,fun_seq_no asc");
		return functionMapper.selectByExample(example);
	}

	@Override
	public int editRoleFunc(Integer roleId, String menuIds) {
		RoleFuncExample example = new RoleFuncExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		roleFuncMapper.deleteByExample(example);
		
		if(ObjectUtils.isNotEmpty(menuIds) && !"".equals(menuIds)){
			for(String menuId : menuIds.split(",")){
				RoleFunc roleFunc = new RoleFunc();
				roleFunc.setRoleId(roleId);
				roleFunc.setFuncId(Integer.parseInt(menuId));
				roleFuncMapper.insert(roleFunc);
			}
		}
		return 1;
	}

	@Override
	public List<Function> getFunctionByUserId(Integer userId) {
		return functionMapper.getFunctionByUserId(userId);
	}

}
