package com.tyxj.dao.mapper;

import com.tyxj.bean.Role;
import com.tyxj.bean.RoleExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> getRolesByUserId(Integer userId);

    /**
     * 分页查询
     * @Title: getPageList
     * @author WangX
     * @Date: 2016年5月18日 下午3:57:44
     * @param @param map
     * @param @return
     * @return List<Role>
     */
	List<Role> getPageList(Map<String, Object> map);

	/**
	 * 按条件查询数量总数
	 * @Title: getTotalCount
	 * @author WangX
	 * @Date: 2016年5月18日 下午3:58:11
	 * @param @param map
	 * @param @return
	 * @return int
	 */
	int getTotalCount(Map<String, Object> map);
}