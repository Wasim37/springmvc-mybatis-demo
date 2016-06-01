package com.tyxj.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * @ClassName: Initializer
 * @Description: tomcat 启动初始化类
 * @author admin
 * @date 2011-10-10 下午06:50:35
 */
public class Initializer extends HttpServlet {

	private static final long serialVersionUID = 2556855575925044091L;

	private static final Logger LOGGER = Logger.getLogger(Initializer.class);

	/**
	 * tomcat启动初始化处理
	 */
	public void init() throws ServletException {
		try {
			//初始化redis
			JedisUtil.initializer(PropertyFactory.getRedisIp(), Integer.parseInt(PropertyFactory.getRedisPort()));
			LOGGER.info("redis初始化完成"); 
			
			//加载系统初始化数据
			
		} catch (Exception e) {
			LOGGER.error("初始化连接池出错" + e.getMessage(), e);
		}
	}

}
