package com.tyxj.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: CacheBase
 * @Description: 缓存处理抽象基类
 * @author 
 * @date 2011-12-22 下午05:25:47
 */
@SuppressWarnings("unchecked")
abstract class AbstractCacheBase {

	protected Map map = new HashMap();

	/**
	 * @Title: init
	 * @Description: 初始化缓存
	 * @param
	 * @return void
	 */
	public abstract void init();

	/**
	 * @Title: refresh
	 * @Description: 刷新缓存
	 * @param
	 * @return void
	 */
	public abstract void refresh();

	/**
	 * @Title: addOrUpdate
	 * @Description: 通用添加或者更新缓存对象
	 * @param @param key
	 * @param @param value
	 * @return void
	 */
	public void addOrUpdate(Object key, Object value) {
		map.put(key, value);
	}

	/**
	 * @Title: remove
	 * @Description: 通用移除缓存对象
	 * @param @param key
	 * @return void
	 */
	public void remove(Object key) {
		if (map.containsKey(key)) {
			map.remove(key);
		}
	}

	/**
	 * @Title: get
	 * @Description: 通用获取缓存中的对象
	 * @param @param key
	 * @param @return
	 * @return Object
	 */
	public Object get(Object key) {
		return map.get(key);
	}

	/**
	 * @Title: size
	 * @Description: 获取缓存当前容量
	 * @param @return
	 * @return int
	 */
	public int size() {
		return map.size();
	}
}
