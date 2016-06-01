package com.tyxj.security.cache;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.tyxj.common.SerializeUtils;

/**
 * @author 
 * @project 
 * @create_date 2014-5-6 下午5:35:07
 */
public class RedisSessionDAO extends AbstractSessionDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionDAO.class);
	
	/**
	 * shiro-redis的session对象前缀
	 */
	private RedisManager redisManager;
	
	@Value("${session.timeout}")
	private int sessionTimeout;
	
	/**
	 * The Redis key prefix for the sessions 
	 */
	private String keyPrefix = "shiro_session:";
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}
	
	/**
	 * save session
	 * @param session
	 * @throws UnknownSessionException
	 */
	private void saveSession(Session session) throws UnknownSessionException{
		if(session == null || session.getId() == null){
			LOGGER.error("session or session id is null");
			return;
		}
		
		byte[] key = getByteKey(session.getId());
		byte[] value = SerializeUtils.serialize(session);	
		this.redisManager.set(key, value,sessionTimeout);
	}

	@Override
	public void delete(Session session) {
		if(session == null || session.getId() == null){
			LOGGER.error("session or session id is null");
			return;
		}
		redisManager.del(this.getByteKey(session.getId()));

	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();
		
		Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");
		if(keys != null && !keys.isEmpty()){
			for(byte[] key:keys){
				Session s = (Session)SerializeUtils.deserialize(redisManager.get(key));
				sessions.add(s);
			}
		}
		
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);  
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId == null){
			LOGGER.error("session id is null");
			return null;
		}
		
		return (Session)SerializeUtils.deserialize(redisManager.get(this.getByteKey(sessionId)));
	}
	
	/**
	 * 获得byte[]型的key
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(Serializable sessionId){
		byte[] bt = null;
		String preKey = this.keyPrefix + sessionId;
		 try {
			 bt = preKey.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.info("RedisSessionDAO--getByteKey-error:" + e.getMessage(), e);
		}
		 return bt;
	}

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
		
		/**
		 * 初始化redisManager
		 */
		this.redisManager.init();
	}

	/**
	 * Returns the Redis session keys
	 * prefix.
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key 
	 * prefix.
	 * @param keyPrefix The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
	
	
}
