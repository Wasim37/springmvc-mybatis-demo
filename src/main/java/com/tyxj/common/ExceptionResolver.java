package com.tyxj.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: BBCExceptionResolver
 * @Description: 异常统一处理类
 * @author guosheng.zhu
 * @date 2011-6-15 上午10:09:42
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	private static final Logger LOGGER = Logger.getLogger(HandlerExceptionResolver.class);

	/**
	 * 处理异常跳转
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String context = request.getContextPath();
		String url = request.getRequestURI().substring(context.length() + 1);

		String param = request.getQueryString();
		if (param != null && param.length() > 0) {
			url += ("?" + param);
		}
		LOGGER.error("异常跳转: " + url, ex);

		return getFrontError();
	}

	private ModelAndView getFrontError() {
		return new ModelAndView("error");
	}
}
