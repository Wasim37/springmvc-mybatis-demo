package com.tyxj.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @ClassName: CacheFactoty
 * @Description: 自定义缓存处理
 * @author 
 * @date 2011-12-22 下午04:55:35
 */
@SuppressWarnings("unchecked")
public class CacheFactory {

	private static final Logger LOGGER = Logger.getLogger(CacheFactory.class);

	public static final String CACHE_CITY = "city";
	public static final String CACHE_MODULE = "module";
	public static final String CACHE_PERMISSION = "permission";
	public static final String CACHE_SITEBASIC_SETTING = "basicSetting";
	public static final String CACHE_COMMON_TYPE = "commonType";
	public static final String CACHE_AD = "ad";
	
	
	private static Map map = new HashMap();

	/**
	 * @Title: init
	 * @Description: 缓存初始化
	 * @param
	 * @return void
	 */
	public static void init() {
		//  缓存
		try {
			CacheBaseData.getInstance();
			
			CacheRoleData.getInstance();

		} catch (Exception e) {
			LOGGER.error(e.getStackTrace(), e);
		}
	}

	/**
	 * @Title: getCache
	 * @Description: 获取缓存对象
	 * @param @param cacheKey
	 * @param @return
	 * @return Object
	 */
	public static Object getCache(String cacheKey) {
		return map.get(cacheKey);
	}

}
