package com.tyxj.cache;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import com.tyxj.common.JedisUtil;

/** 
 * @ClassName: CacheRoleData 
 * @Description: 
 * @author Administrator
 * @date 2014年7月7日 下午6:30:28 
 *  
 */

public class CacheRoleData extends AbstractCacheBase {

	private static final Logger LOGGER = Logger.getLogger(CacheRoleData.class);
	
	private static CacheRoleData instance = null;
	
	private CacheRoleData(){
		init();
	}
	
	public static CacheRoleData getInstance(){
		if(instance == null){
			instance = new CacheRoleData();
		}
		return instance;
	}
	
	@Override
	public void init() {
		Jedis jedis = null;
		
		try {
			
			
		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
		} finally{
			JedisUtil.getJedisPool().returnResource(jedis);
		}
	}
	
	@Override
	public void refresh() {
		init();
	}

}
