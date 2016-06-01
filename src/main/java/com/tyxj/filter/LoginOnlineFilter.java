package com.tyxj.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;

import com.tyxj.common.ObjectUtils;

public class LoginOnlineFilter implements Filter{
	
	FilterConfig filterConfig = null;
	
	@Override
	public void destroy() {
		this.filterConfig = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String path = ((HttpServletRequest)request).getServletPath();

		((HttpServletResponse)response).addHeader("Expires", "0");
		((HttpServletResponse)response).setHeader("Cache-Control","no-cache");
		if (ObjectUtils.isEmpty(path)
				|| (ObjectUtils.isNotEmpty(path) && (path.indexOf("login") > 0
						|| path.indexOf(".css") > 0 || path.indexOf(".js") > 0
						|| path.indexOf(".png") > 0 || path.indexOf(".gif") > 0
						|| path.indexOf(".woff") > 0
						|| path.indexOf(".ttf") > 0 || path.indexOf(".ico") > 0
						|| path.indexOf(".swf") > 0
						|| path.indexOf("scancopy/paper/materialUpload") >= 0 
						|| path.indexOf(".jpg") > 0
						|| path.indexOf("deviceStatus/receive") > 0
						|| path.indexOf("deviceStatus/validate") > 0))){
			chain.doFilter(request, response);
		}else{
			Object userName = SecurityUtils.getSubject().getPrincipal();
			if(ObjectUtils.isEmpty(userName)){
				((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/html/login.html");
				return;
			}else{
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

	}
}
