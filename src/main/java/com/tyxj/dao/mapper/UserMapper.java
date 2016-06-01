package com.tyxj.dao.mapper;

import com.tyxj.bean.User;
import com.tyxj.bean.UserExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 分页查询用户
     * @Title: getPageList
     * @author WangX
     * @Date: 2016年5月18日 上午11:24:18
     * @param @param map
     * @param @return
     * @return List<User>
     */
	List<User> getPageList(Map<String, Object> map);

	/**
	 * 根据条件查询用户总数
	 * @Title: getTotalCount
	 * @author WangX
	 * @Date: 2016年5月18日 上午11:37:12
	 * @param @param map
	 * @param @return
	 * @return int
	 */
	int getTotalCount(Map<String, Object> map);

}