package com.tyxj.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyxj.bean.Function;
import com.tyxj.bean.Role;
import com.tyxj.common.AjaxUtil;
import com.tyxj.common.Limit;
import com.tyxj.common.ObjectUtils;
import com.tyxj.service.IFunctionService;
import com.tyxj.service.IRoleService;
import com.tyxj.service.IUserRoleService;
import com.tyxj.service.IUserService;

/**
 * @ClassName: RoleController
 * @Description: 角色权限模块
 * @author WangX
 * @Date: 2016年5月19日 下午4:16:32
 *
 */
@RequestMapping("/role")
@Controller
public class RoleController{
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IRoleService roleService;
	
	@Resource
	private IFunctionService functionService;
	
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
	@ResponseBody
	@RequestMapping("/page")
	public String getPage(Role role, Limit limit, HttpServletRequest request) {
		List<Role> roleList = roleService.getPageList(role, limit);
		Map<String, Integer> map = roleService.getTotalCountAndPage(role, limit);
		return AjaxUtil.ajaxSuccess(roleList, map.get("totalCount"), map.get("totalPage"));
	}
	
	/**
	 * 角色删除
	 * @Title: delRole
	 * @author WangX
	 * @Date: 2016年5月18日 下午4:24:40
	 * @param @param roleId
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/del")
	public String delRole(String roleId, HttpServletRequest request){
		int effectNum = roleService.delRole(roleId);
		if(effectNum > 0){
			return AjaxUtil.ajaxSuccess("删除成功");
		} else {
			return AjaxUtil.ajaxFail("删除失败");
		}
	}
	
	/**
	 * 角色新增
	 * @Title: addRole
	 * @author WangX
	 * @Date: 2016年5月19日 下午2:47:58
	 * @param @param role
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/add")
	public String addRole(Role role, HttpServletRequest request){
		if(ObjectUtils.isNotEmpty(roleService.getRoleByName(role.getRoleName()))){
			return AjaxUtil.ajaxFail("角色名称重复,请重新填写");
		} else {
			if(roleService.addRole(role.getRoleName()) > 0){
				return AjaxUtil.ajaxSuccess("新增成功");
			} else {
				return AjaxUtil.ajaxFail("新增失败");
			}
		}
	}
	
	/**
	 * 更新角色名称
	 * @Title: uptRole
	 * @author WangX
	 * @Date: 2016年5月19日 下午3:26:29
	 * @param @param role
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/upt")
	public String uptRole(Role role, HttpServletRequest request){
		if(ObjectUtils.isNotEmpty(roleService.getRoleByName(role.getRoleName()))){
			return AjaxUtil.ajaxFail("角色名称重复,请重新填写");
		} else {
			if(roleService.uptRole(role) > 0){
				return AjaxUtil.ajaxSuccess("更新成功");
			} else {
				return AjaxUtil.ajaxFail("新增失败");
			}
		}
	}
	
	/**
	 * 初始化加载功能数据
	 * @param userId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/init")
	public String getRoleList(Integer userId, HttpServletRequest request) {
		List<Function> functionList = functionService.getAllFunction();
		return AjaxUtil.ajaxSuccess(functionList, 0, 0);
	}
	
	/**
	 * 根据角色id获取权限
	 * @Title: getFuncByRoleId
	 * @author WangX
	 * @Date: 2016年5月19日 下午4:11:57
	 * @param @param roleId
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/view")
	public String getFuncByRoleId(Integer roleId, HttpServletRequest request) {
		List<Function> functionList = functionService.getFuncNamesByRoleIds(roleId.toString());
		return AjaxUtil.ajaxSuccess(functionList, 0, 0);
	}
	
	/**
	 * 编辑角色权限
	 * @Title: editRoleFunc
	 * @author WangX
	 * @Date: 2016年5月19日 下午6:19:17
	 * @param @param roleId
	 * @param @param menuIds
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@RequestMapping("/editRoleFunc")
	@ResponseBody
	public String editRoleFunc(Integer roleId,String menuIds,HttpServletRequest request){
		if(functionService.editRoleFunc(roleId, menuIds)>0){
			return  AjaxUtil.ajaxSuccess("角色权限修改成功");
		} else {
			return  AjaxUtil.ajaxFail("角色权限修改失败");
		}
	}
	
}
