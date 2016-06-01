package com.tyxj.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: HttpResponseUtil
 * @Description: 响应通用处理类
 * @author guosheng.zhu
 * @date 2011-12-21 下午05:52:40
 */
public class HttpResponseUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpResponseUtil.class);
	
	// ajax请求简单响应常量定义
	public static final String AJAX_RESP_SUCCESS = "success";
	public static final String AJAX_RESP_ERROR = "error";
	public static final String AJAX_RESP_TRUE = "true";
	public static final String AJAX_RESP_FALSE = "false";
	public static final String AJAX_RESP_EXCEPTION = "exception";

	/**
	 * @Title: send
	 * @Description: 发送简单字符串响应
	 * @param @param response
	 * @param @param data
	 * @param @param encoding
	 * @param @throws UnsupportedEncodingException
	 * @param @throws IOException
	 * @return void
	 */
	public static void send(HttpServletResponse response, String data,
			String encoding) {
		try {
			response.getOutputStream().write(data.getBytes(encoding));
		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
		}
	}

	/**
	 * @Title: send
	 * @Description: 发送简单字符串响应，默认UTF8编码
	 * @param @param response
	 * @param @param data
	 * @return void
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendWithUTF8(HttpServletResponse response, String data) {
		send(response, data, "utf-8");
	}
}
