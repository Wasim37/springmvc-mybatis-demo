package com.tyxj.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyxj.bean.Role;
import com.tyxj.bean.User;
import com.tyxj.common.AjaxUtil;
import com.tyxj.common.Limit;
import com.tyxj.common.ObjectUtils;
import com.tyxj.service.IRoleService;
import com.tyxj.service.IUserRoleService;
import com.tyxj.service.IUserService;

/**
 * @ClassName: UserController
 * @Description: 用户模块
 * @author WangX
 * @Date: 2016年5月16日 上午10:21:54
 *
 */
@RequestMapping("/user")
@Controller
public class UserController{
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IRoleService roleService;
	
	@Resource
	private IUserRoleService userRoleService;
	
	/**
	 * 分页查询
	 * @Title: getPage
	 * @author WangX
	 * @Date: 2016年5月18日 上午10:34:47
	 * @param @param user
	 * @param @param page
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@RequestMapping("/page")
	@ResponseBody
	public String getPage(User user, Limit limit, HttpServletRequest request) {
		List<User> userList = userService.getPageList(user, limit);
		Map<String, Integer> map = userService.getTotalCountAndPage(user, limit);
		return AjaxUtil.ajaxSuccess(userList, map.get("totalCount"), map.get("totalPage"));
	}
	
	/**
	 * 用户删除
	 * @Title: delUser
	 * @author WangX
	 * @Date: 2016年5月18日 下午5:26:45
	 * @param @param userId
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/del")
	public String delUser(String userId, HttpServletRequest request){
		int effectNum = userService.delUser(userId);
		if(effectNum > 0){
			return AjaxUtil.ajaxSuccess("删除成功");
		} else {
			return AjaxUtil.ajaxFail("删除失败");
		}
	}
	
	/**
	 * 添加用户
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addUser(User user,String roleIds, HttpServletRequest request) {
		if(ObjectUtils.isNotEmpty(userService.getUserByName(user.getUserName()))){
			return AjaxUtil.ajaxFail("用户名称重复,请重新填写");
		}
		if(userService.addUser(user, roleIds) > 0){
			return AjaxUtil.ajaxSuccess("添加成功");
		} else {
			return AjaxUtil.ajaxFail("添加失败");
		}
	}
	
	/**
	 * 添加用户
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/upt")
	@ResponseBody
	public String uptUser(User user,String roleIds, HttpServletRequest request) {
		int effectNum = userService.uptUser(user, roleIds);
		if(effectNum > 0){
			return AjaxUtil.ajaxSuccess("更新成功");
		} else {
			return AjaxUtil.ajaxFail("更新失败");
		}
	}
	
	/**
	 * 获取权限角色列表
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping("/init")
	@ResponseBody
	public String getRoleList(Integer userId, HttpServletRequest request) {
		List<Role> rolelist = new ArrayList<>();
		if(ObjectUtils.isNotEmpty(userId)){
			rolelist = roleService.getRolesByUserId(userId);
		} else {
			rolelist = roleService.getAllRole();
		}
		return AjaxUtil.ajaxSuccess(rolelist, 0, 0);
	}
}
