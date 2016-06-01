package com.tyxj.dao.mapper;

import com.tyxj.bean.Function;
import com.tyxj.bean.FunctionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FunctionMapper {
    int countByExample(FunctionExample example);

    int deleteByExample(FunctionExample example);

    int deleteByPrimaryKey(Integer funcId);

    int insert(Function record);

    int insertSelective(Function record);

    List<Function> selectByExample(FunctionExample example);

    Function selectByPrimaryKey(Integer funcId);

    int updateByExampleSelective(@Param("record") Function record, @Param("example") FunctionExample example);

    int updateByExample(@Param("record") Function record, @Param("example") FunctionExample example);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);

    /**
     * 根据角色获取功能
     * @Title: getFuncNamesByRoleIds
     * @author WangX
     * @Date: 2016年5月20日 下午3:40:53
     * @param @param roleIds
     * @param @return
     * @return List<Function>
     */
	List<Function> getFuncNamesByRoleIds(String roleIds);

	/**
	 * 根据用户id获取功能
	 * @Title: getFunctionByUserId
	 * @author WangX
	 * @Date: 2016年5月20日 下午3:40:39
	 * @param @param userId
	 * @param @return
	 * @return List<Function>
	 */
	List<Function> getFunctionByUserId(Integer userId);
}