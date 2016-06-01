package com.tyxj.dao.mapper;

import com.tyxj.bean.UserRole;
import com.tyxj.bean.UserRoleExample;
import com.tyxj.bean.dto.UserRoleDTO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    /**
     * 根据用户id查询信息
     * @Title: getInfoByUserId
     * @author WangX
     * @Date: 2016年5月18日 上午10:31:16
     * @param @param userId
     * @param @return
     * @return List<UserRoleDTO>
     */
	List<UserRoleDTO> getInfoByUserId(Integer userId);
}