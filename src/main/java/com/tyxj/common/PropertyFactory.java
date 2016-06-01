package com.tyxj.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tyxj.common.BasePathFactory;


/**
 * @ClassName: PropertyFactory
 * @Description: 属性加载工具类
 * @author guosheng.zhu
 * @date 2011-6-10 下午08:08:37
 */
public class PropertyFactory {

	private static final Logger LOGGER = Logger.getLogger(PropertyFactory.class);

	private static Map<String, Properties> propMap = null;

	/**
	 * @Title: getProperty
	 * @Description: 获取属性数据
	 * @param @param filePath
	 * @param @param name
	 * @param @return
	 * @return String
	 */
	public static String getProperty(String filePath, String name) {
		if (propMap == null) {
			propMap = new HashMap<String, Properties>();
		}
		String key = getFileNameFromPath(filePath);
		if (!propMap.containsKey(key)) {
			Properties prop = new Properties();
			try {
				InputStream is = new BufferedInputStream(new FileInputStream(
						filePath));
				prop.load(is);
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				LOGGER.error(e.getStackTrace(), e);
				return null;
			}
			propMap.put(key, prop);
			return prop.getProperty(name);
		}
		return propMap.get(key).getProperty(name);
	}

	/**
	 * @Title: getFileNameFromPath
	 * @Description: 根据路径解析文件名
	 * @param @param path
	 * @param @return
	 * @return String
	 */
	private static String getFileNameFromPath(String path) {
		int location = path.lastIndexOf("/");
		if (location == -1) {
			return null;
		}
		return path.substring(location + 1);
	}

	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
	}

	public static String getImageBasePath() {
		return getProperty(BasePathFactory.getResourcePath("other.properties"),
				"picpath");
	}

	public static String getBasePathCourse() {
		return getProperty(BasePathFactory.getResourcePath("init.properties"),
				"basepath.course");
	}

	public static String getBasePathUC() {
		return getProperty(BasePathFactory.getResourcePath("init.properties"),
				"basepath.uc");
	}

	public static String getBasePathCPS() {
		return getProperty(BasePathFactory.getResourcePath("init.properties"),
				"basepath.cps");
	}

	public static String getBasePathBBC() {
		return getProperty(BasePathFactory.getResourcePath("init.properties"),
				"basepath.bbc");
	}

	public static String getBasePathAdmin() {
		return getProperty(BasePathFactory.getResourcePath("init.properties"),
				"basepath.admin");
	}

	public static String getSmsUc() {
		return getProperty(BasePathFactory.getResourcePath("init.properties"),
				"sms.uc");
	}

	public static String getSmsPwd() {
		return getProperty(BasePathFactory.getResourcePath("init.properties"),
				"sms.pwd");
	}

	public static String getSmsPid() {
		return getProperty(BasePathFactory.getResourcePath("init.properties"),
				"sms.pid");
	}
	
	public static String getRedisIp() {
		return getProperty(BasePathFactory.getResourcePath("redis.properties"), "redis.ip");
	}
	
	public static String getRedisPort() {
		return getProperty(BasePathFactory.getResourcePath("redis.properties"), "redis.port");
	}

	public static String getPathCourse() {
		return getProperty(BasePathFactory.getResourcePath("init.properties"),
				"tcpath.bbccourse");
	}

	public static String getPathUC() {
		return getProperty(BasePathFactory.getResourcePath("init.properties"),
				"tcpath.bbcuc");
	}

	public static String getPathBBC() {
		return getProperty(BasePathFactory.getResourcePath("init.properties"),
				"tcpath.bbc");
	}

	public static String getDocBasePath() {
		return getProperty(BasePathFactory.getResourcePath("other.properties"),
				"docpath");
	}
	
	/***
	 * 
	* @Title: getSearchQuestionUrl 
	* @Description: 获取搜索试题接口的URL地址
	* @author echao.wang
	* @param @return  
	* @return String
	 */
	public static String getSearchQuestionUrl(){
		return getProperty(BasePathFactory.getResourcePath("other.properties"),
				"questionsearchurl");
	}
	
	/***
	 * 
	* @Title: getQuestionCheckUrl 
	* @Description: 获取纠错提交数据接口
	* @author echao.wang 
	* @param @return  
	* @return String
	 */
	public static String getQuestionCheckUrl(){
		return getProperty(BasePathFactory.getResourcePath("other.properties"), "questioncheckurl");
	}
	
	/***
	 * 
	* @Title: getKnowlegeSearchUrl 
	* @Description: 获取同知识点考题
	* @param @return  
	* @return String
	 */
	public static String getKnowlegeSearchUrl(){
		return getProperty(BasePathFactory.getResourcePath("other.properties"), "knowledgesearchurl");
	}
		/**
	 * 
	* @Title: getPaperSearchUrl 
	* @Description: 获取搜索试卷接口 
	* @param @return  
	* @return String
	 */
	public static String getPaperSearchUrl(){
		return getProperty(BasePathFactory.getResourcePath("other.properties"), "papersearchurl");
	}
		/***
	 * 
	* @Title: getSolrUrl 
	* @Description: 获取solr服务端地址 
	* @param @return  
	* @return String
	 */
	public static String getSolrUrl(){
		return getProperty(BasePathFactory.getResourcePath("init.properties"), "solr.url");
	}
	
	/**
	 * 
	* @Title: getPaperDetailUrl 
	* @Description: 获取试卷详细信息接口 
	* @param @return  
	* @return String
	 */
	public static String getPaperDetailUrl(){
		return getProperty(BasePathFactory.getResourcePath("other.properties"), "paperdetailurl");
	}
	
	public static String getOpenofficePath(){
		return getProperty(BasePathFactory.getResourcePath("other.properties"), "openoffice_path");
	}
	
	public static String getSWFToolsPath(){
		return getProperty(BasePathFactory.getResourcePath("other.properties"), "swftools_path");
	}
	
	public static String getFastDfsPath(){
		return getProperty(BasePathFactory.getResourcePath("init.properties"), "fastdfs.basePath");
	}
	
	public static String getUploadMaxSize(){
		return getProperty(BasePathFactory.getResourcePath("init.properties"), "upload.maxSize");
	}
	
	public static String getMonitorMaxMsg(){
		return getProperty(BasePathFactory.getResourcePath("init.properties"), "monitor.maxmsg");
	}
	
	public static String getMonitorTel(){
		return getProperty(BasePathFactory.getResourcePath("init.properties"), "monitor.telephone");
	}
	
	public static String getMonitorDelay(){
		return getProperty(BasePathFactory.getResourcePath("init.properties"), "monitor.delay");
	}
	
	public static String getMonitorPeriod(){
		return getProperty(BasePathFactory.getResourcePath("init.properties"), "monitor.period");
	}
	
	public static String getInquireRepeatIndexPath(){
		return getProperty(BasePathFactory.getResourcePath("other.properties"), "inquirerepeat.index.path");
	}
	
	public static String getSystemUserId(){
		return getProperty(BasePathFactory.getResourcePath("other.properties"), "system.user.id");
	}
	

}
