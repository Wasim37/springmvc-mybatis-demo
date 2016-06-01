package com.tyxj.controller.common;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tyxj.bean.Function;
import com.tyxj.bean.User;
import com.tyxj.common.AjaxUtil;
import com.tyxj.service.IFunctionService;
import com.tyxj.service.IUserService;

@Controller
@RequestMapping("/index")
public class IndexAction{
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IFunctionService functionService;
	
	/**
	 * 初始化菜单
	 * @Title: indexInitMenu
	 * @author WangX
	 * @Date: 2016年5月20日 下午4:06:56
	 * @param @return
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("/init")
	public String indexMenuInit(){
		User loginUser = userService.getLoginUser();
		List<Function> funcList = functionService.getFunctionByUserId(loginUser.getUserId());
		HashMap<String , Object> map = new HashMap<String , Object>();
		map.put("loginUser", loginUser);
		map.put("funcList", funcList);
		return AjaxUtil.ajaxSuccess(map, 0);
	}
	
}
