package com.tyxj.common;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: BasePathFactory
 * @Description: 路径工厂类
 * @author guosheng.zhu
 * @date 2011-6-2 下午04:39:46
 */
public class BasePathFactory {

	/**
	 * 获取根路径
	 * 
	 * @return
	 */
	public static String getBasePath() {
		String path = BasePathFactory.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath();
		if (path.indexOf("WEB-INF") > 0) {
			path = path.substring(0, path.indexOf("WEB-INF/classes"));
		} else {
			return null;
		}
		return path;
	}

	/**
	 * @Title: getClassPath
	 * @Description: 获得classpath(........../WebRoot/WEB-INF/classes/)
	 * @param @return
	 * @return String
	 */
	public static String getClassPath() {
		return BasePathFactory.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	}

	/**
	 * @Title: getWebRootPath
	 * @Description: 获取URL请求路径
	 * @param @param request
	 * @param @return
	 * @return String
	 */
	public static String getWebRootPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
	}

	/**
	 * @Title: getResourcePath
	 * @Description: 获取资源文件路径
	 * @param @param resourceName
	 * @param @return
	 * @return String
	 */
	public static String getResourcePath(String resourceName) {
		return BasePathFactory.class.getResource("//" + resourceName).getPath();
	}
}
