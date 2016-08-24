package com.tyxj.controller.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyxj.bean.User;
import com.tyxj.common.AjaxUtil;
import com.tyxj.common.EncryptPropertyPlaceholderConfigurer;
import com.tyxj.common.MD5Util;
import com.tyxj.dao.mapper.UserMapper;
import com.tyxj.security.cache.RedisCache;
import com.tyxj.security.realm.CustomRealm;
import com.tyxj.service.IUserService;

/**
 * @ClassName: SecurityController
 * @Description: 系统验证模块  登录登出等
 * @author WangX
 * @Date: 2016年5月16日 上午10:21:54
 *
 */
@Controller
public class SecurityController{

	private static final Logger LOG = LoggerFactory.getLogger(SecurityController.class);
	
	@Resource
	private CustomRealm customRealm;
	
	@Resource
	private IUserService userService; 
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	protected RedisCache<String, Object> redisCache;
	
	/**
	 * 用户登录
	 * @Title: doUserLogin
	 * @author WangX
	 * @Date: 2016年5月16日 下午21:26:22
	 * @Description: 
	 * @param @param user
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doUserLogin(User user, HttpServletRequest request, Model model) {
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		customRealm.clearCachedAuthorizationInfo(user.getUserName());
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
		String errMsg = "";
		try {
			currentUser.login(token);
		} catch (UnknownAccountException uae) {
			errMsg = "账户不存在";
		} catch (IncorrectCredentialsException ice) {
			errMsg = "密码不正确";
		} catch (LockedAccountException lae) {
			errMsg = "账户已锁定";
		} catch (ExcessiveAttemptsException eae) {
			errMsg = "用户名或密码错误次数过多";
		} catch (AuthenticationException ae) {
			//其他自定义错误
			errMsg = "用户名或密码不正确";
		}
		if(!"".equals(errMsg)){
			token.clear();
			return AjaxUtil.ajaxFail(errMsg);
		}
		return AjaxUtil.ajaxSuccess("登录成功");
	}
	
	/**
	 * 登出操作
	 * @Title: doUserLogout
	 * @author WangX
	 * @Date: 2016年5月16日 下午4:21:47
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String doUserLogout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		return AjaxUtil.ajaxSuccess("登出成功");
	}
	
	/**
	 * 重置密码
	 * @Title: resetPwd
	 * @author WangX
	 * @Date: 2016-5-31 下午3:55:56
	 * @param @param userId
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/resetPwd")
	public String resetPwd(Integer userId, HttpServletRequest request) {
		userService.resetPwd(userId);
		return AjaxUtil.ajaxSuccess("操作成功");
	}
	
	/**
	 * 修改密码
	 * @Title: changePwd
	 * @author WangX
	 * @Date: 2016-5-31 下午4:52:34
	 * @param @param oldPwd
	 * @param @param reNewPwd
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/changePwd")
	public String changePwd(String oldPwd, String newPwd, HttpServletRequest request) {
		User loginUser = userService.getLoginUser();
		if (!MD5Util.md5s(oldPwd).equals(loginUser.getPassWord())) {
			return AjaxUtil.ajaxFail("原密码错误!");
		}
		loginUser.setPassWord(MD5Util.md5s(newPwd));
		loginUser.setDigest(newPwd);
		userMapper.updateByPrimaryKeySelective(loginUser);
		//修改密码成功，清空登录信息，要求用户重新登录
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return AjaxUtil.ajaxSuccess("修改成功");
	}
	
}
