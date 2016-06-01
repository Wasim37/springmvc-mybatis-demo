package com.tyxj.security.cache;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.CharSet;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tyxj.common.SerializeUtils;

public class RedisCache<K, V> implements Cache<K, V> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);

	/**
	 * The wrapped Jedis instance.
	 */
	private RedisManager cache;

	/**
	 * The Redis key prefix for the sessions 
	 */
	private String keyPrefix = "redis_cache:";

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

	/**
	 * 通过一个JedisManager实例构造RedisCache
	 */
	public RedisCache(RedisManager cache) {
		if (cache == null) {
			throw new IllegalArgumentException("Cache argument cannot be null.");
		}
		this.cache = cache;
	}

	/**
	 * Constructs a cache instance with the specified
	 * Redis manager and using a custom key prefix.
	 * @param cache The cache manager instance
	 * @param prefix The Redis key prefix
	 */
	public RedisCache(RedisManager cache, String prefix) {

		this(cache);

		// set the prefix
		this.keyPrefix = prefix;
	}

	/**
	 * 获得byte[]型的key
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(K key) {
		if (key instanceof String) {
			String preKey = this.keyPrefix + key;
			return preKey.getBytes(StandardCharsets.UTF_8);
		} else if(key instanceof PrincipalCollection){
			String preKey = this.keyPrefix + key.toString();
			return preKey.getBytes(StandardCharsets.UTF_8);
		}else{
			return SerializeUtils.serialize(key);
		}
	}

	@Override
	public V get(K key) throws CacheException {
		LOGGER.debug("根据key从Redis中获取对象 key [" + key + "]");
		try {
			if (key == null) {
				return null;
			} else {
				byte[] rawValue = cache.get(getByteKey(key));
				@SuppressWarnings("unchecked") V value = (V) SerializeUtils.deserialize(rawValue);
				return value;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
			throw new CacheException();
		}

	}
	public String getStr(String key) throws CacheException {
		LOGGER.debug("根据key从Redis中获取对象 key [" + key + "]");
		try {
			if (key == null) {
				return null;
			} else {
				return cache.get(this.keyPrefix+key);
			}
		} catch (Exception t) {
			throw new CacheException(t);
		}
		
	}

	@Override
	public V put(K key, V value) throws CacheException {
		LOGGER.debug("根据key从存储 key [" + key + "]");
		try {
			cache.set(getByteKey(key), SerializeUtils.serialize(value));
			return value;
		} catch (Exception t) {
			throw new CacheException(t);
		}
	}

	public String putStr(String key, String value) throws CacheException {
		LOGGER.debug("根据key从存储 key [" +this.keyPrefix+ key + "]");
		try {
			cache.set(this.keyPrefix+key, value);
			return value;
		} catch (Exception t) {
			throw new CacheException(t);
		}
	}

	public String put(String key,String value, int expire) throws CacheException {
		LOGGER.debug("根据key从存储 key [" + key + "]");
		try {
			cache.set(this.keyPrefix+key, value, expire);
			return value;
		} catch (Exception t) {
			throw new CacheException(t);
		}
	}

	public String removeString(String key) throws CacheException {
		String delKey = this.keyPrefix+key;
		LOGGER.debug("从redis中删除 key [" + delKey + "]");
		try {
			String previous = cache.get(delKey);
			cache.del(delKey);
			return previous;
		} catch (Exception t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V remove(K key) throws CacheException {
		LOGGER.debug("从redis中删除 key [" + key + "]");
		try {
			V previous = get(key);
			cache.del(getByteKey(key));
			return previous;
		} catch (Exception t) {
			throw new CacheException(t);
		}
	}

	@Override
	public void clear() throws CacheException {
		LOGGER.debug("从redis中删除所有元素");
		try {
			cache.flushDB();
		} catch (Exception t) {
			throw new CacheException(t);
		}
	}

	@Override
	public int size() {
		try {
			return cache.dbSize().intValue();
		} catch (Exception t) {
			throw new CacheException(t);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		try {
			Set<byte[]> keys = cache.keys(this.keyPrefix + "*");
			if (CollectionUtils.isEmpty(keys)) {
				return Collections.emptySet();
			} else {
				Set<K> newKeys = new HashSet<K>();
				for (byte[] key : keys) {
					newKeys.add((K) key);
				}
				return newKeys;
			}
		} catch (Exception t) {
			throw new CacheException(t);
		}
	}

	@Override
	public Collection<V> values() {
		try {
			Set<byte[]> keys = cache.keys(this.keyPrefix + "*");
			if (!CollectionUtils.isEmpty(keys)) {
				List<V> values = new ArrayList<V>(keys.size());
				for (byte[] key : keys) {
					@SuppressWarnings("unchecked") V value = get((K) key);
					if (value != null) {
						values.add(value);
					}
				}
				return Collections.unmodifiableList(values);
			} else {
				return Collections.emptyList();
			}
		} catch (Exception t) {
			throw new CacheException(t);
		}
	}
	/*--------------------------------------------------新增--------------------------------------------------*/
	public String lput(String key,String value) throws CacheException {
		LOGGER.debug("根据key从存储 key [" + key + "]");
		try {
			cache.lpush(key, value);
			return value;
		} catch (Exception t) {
			throw new CacheException(t);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Set<K> keys(String pattern) {
		try {
			Set<String> keys = cache.keysRegex(pattern);
			if (CollectionUtils.isEmpty(keys)) {
				return Collections.emptySet();
			} else {
				Set<K> newKeys = new HashSet<K>();
				for (String key : keys) {
					newKeys.add((K) key);
				}
				return newKeys;
			}
		} catch (Exception t) {
			throw new CacheException(t);
		}
	}
}
