package com.tyxj.common.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.tyxj.common.AjaxUtil;
import com.tyxj.common.BaseConfig;

/**
 * @author 
 * @project 
 * @create_date 2014-5-5 下午3:43:00
 */
public class CustomExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger LOGGER =  LoggerFactory.getLogger(CustomExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

		String msg = BaseConfig.get(ex.getClass().getName());
		
		if(ex instanceof UserInputException){
			msg += ex.getMessage();
			LOGGER.warn("UserInputException ：" + ex.getMessage());
		} else if (ex instanceof AuthenticationException || ex instanceof UnauthenticatedException || ex instanceof AuthenticationException) {
			LOGGER.warn(ex.getClass().getName()+" : " + ex.getMessage());
			if(null!= ex.getCause() && ex.getCause() instanceof UserInputException){
				msg += ex.getCause().getMessage();
			}
		} else {
			LOGGER.error("系统异常：",ex);
		}
		if (isAjaxRequest(request) || isUpload(request)) {
			//ajax 请求
			try {
				AjaxUtil.ajaxError(ex, msg, response);
			} catch (IOException e) {
				LOGGER.info(e.getMessage(), e);
			}
			return new ModelAndView();
		} else {
			//表单请求、jsp请求、普通跳转
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("msg", msg);
			String viewName = this.determineViewName(ex, request);
			//获取配置的错误页面
			if (null == viewName) {
				viewName = "error";
			}
			return new ModelAndView("redirect:/" + viewName, param);
		}
	}

	public boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(header) ? true : false;
	}

	public boolean isUpload(HttpServletRequest request) {
		String header = request.getHeader("Content-Type");
		if (null == header) {
			return false;
		}
		return header.indexOf("multipart/form-data;") >= 0;
	}
}
