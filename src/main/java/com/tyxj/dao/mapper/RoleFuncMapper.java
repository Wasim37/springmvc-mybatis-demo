package com.tyxj.dao.mapper;

import com.tyxj.bean.Role;
import com.tyxj.bean.RoleFunc;
import com.tyxj.bean.RoleFuncExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleFuncMapper {
    int countByExample(RoleFuncExample example);

    int deleteByExample(RoleFuncExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleFunc record);

    int insertSelective(RoleFunc record);

    List<RoleFunc> selectByExample(RoleFuncExample example);

    RoleFunc selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleFunc record, @Param("example") RoleFuncExample example);

    int updateByExample(@Param("record") RoleFunc record, @Param("example") RoleFuncExample example);

    int updateByPrimaryKeySelective(RoleFunc record);

    int updateByPrimaryKey(RoleFunc record);

    /**
     * 根据用户id获取角色
     * @Title: getRoleByUserId
     * @author WangX
     * @Date: 2016年5月19日 上午11:28:43
     * @param @param userId
     * @param @return
     * @return List<Role>
     */
	List<Role> getRoleByUserId(Integer userId);
}