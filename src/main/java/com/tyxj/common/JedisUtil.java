package com.tyxj.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedis;

/** 
 * @ClassName: JedisUtil 
 * @Description: jedis工具类
 * @author guosheng.zhu
 * @date 2013-5-3 下午06:37:07 
 *  
 */

public class JedisUtil {

	private static final Logger LOGGER = Logger.getLogger(JedisUtil.class);
	
	 /** 非切片客户端链接 */
    private static Jedis jedis;

    /** 非切片链接池 */
    private static JedisPool jedisPool;

    /** 切片客户端链接 */
    private static ShardedJedis shardedJedis;
    
    private static int MAX_ACTIVE = 1024;
    private static int MAX_IDLE = 200;
    private static int MAX_WAIT = 10000;
    private static boolean TEST_ON_BORROW = true;
    private static boolean TEST_ON_RETURN = true;
    
    
    public JedisUtil(){
    	
    }
    
    public static void initializer(String ip, int port){
    	initialPool(ip, port);
    	//initialShardedPool(ip, port);
    	//shardedJedis = shardedJedisPool.getResource();
    	jedis = jedisPool.getResource();
    }
	
    private static void initialPool(String ip, int port) {
        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxActive(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
//        config.setMaxWait(MAX_WAIT);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        config.setTestOnReturn(TEST_ON_RETURN);
//        config.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_GROW);
        jedisPool = new JedisPool(config, ip, port, 10000);
    }
    
   /* private static void initialPool(String ip, int port, int maxActive, int maxIdle, int maxWait, boolean testOnBorrow) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWait(maxWait);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(TEST_ON_RETURN);
        config.setWhenExhaustedAction(GenericObjectPool.WHEN_EXHAUSTED_GROW);
        jedisPool = new JedisPool(config, ip, port, 10000);
    }
    
    
    初始化切片池 
    private static void initialShardedPool(String mip, int mport) {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWait(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo(mip, mport, "master"));

        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }
    
    初始化主从切片池 
    private static void initialShardedPool(String mip, int mport, String sip, int sport) {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWait(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo(mip, mport, "master"));
        shards.add(new JedisShardInfo(sip, sport, "slave"));

        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }*/
	
    public static Pipeline getPipeline(){
    	return jedis.pipelined();
    }
    
    public static Jedis getJedis(){
    	jedis = jedisPool.getResource();
    	return jedis;
    }
    
    public static JedisPool getJedisPool(){
    	return jedisPool;
    }
    
    public static ShardedJedis getShardedJedis(){
    	return shardedJedis;
    }
    
    public void setSharded(final String key, final String value){
    	shardedJedis.set(key, value);
    }
    
    public void setSharded(final byte[] key, final byte[] value){
    	shardedJedis.set(key, value);
    }
    
    public void setexSharded(final String key, final String value, final int expireTime){
    	shardedJedis.setex(key, expireTime, value);
    }
    
    public void setexSharded(final byte[] key, final byte[] value, final int expireTime){
    	shardedJedis.setex(key, expireTime, value);
    }
    
    public String getSharded(final String key){
    	return shardedJedis.get(key);
    }
    
    public byte[] getSharded(final byte[] key){
    	return shardedJedis.get(key);
    }
    
    
    public void delSharded(final String key){
    	shardedJedis.del(key);
    }
    
    public void set(final String key, final String value){
    	jedis.set(key, value);
    }
    
    public void set(final byte[] key, final byte[] value){
    	jedis.set(key, value);
    }
    
    public void setObject(final String key, final Object value){
    	try {
			jedis.set(key.getBytes(StandardCharsets.UTF_8), getBytesFromObject(value));
		} catch (Exception e) {
			LOGGER.error("setobject--error", e);
		}
    }
    
    public void setex(final String key, final String value, final int expireTime){
    	jedis.setex(key, expireTime, value);
    }
    
    public void setex(final byte[] key, final byte[] value, final int expireTime){
    	jedis.setex(key, expireTime, value);
    }
    
    public String get(final String key){
    	return jedis.get(key);
    }
    
    public byte[] get(final byte[] key){
    	return jedis.get(key);
    }
    
    public void del(final byte[] key){
    	jedis.del(key);
    }
    
    public void del(final String key){
    	jedis.del(key);
    }
    
    public void flashDb(){
    	jedis.flushDB();
    }
    
    public void flashMasterDb(){
    	shardedJedis.getShard("master").flushDB();
    }
    
    public void flashSlaveDb(){
    	shardedJedis.getShard("slave").flushDB();
    }
    
    public static Object getObjectFromBytes(byte[] objBytes) throws IOException, ClassNotFoundException{ 
        if (objBytes == null || objBytes.length == 0){ 
            return null; 
        } 
        ByteArrayInputStream bi = new ByteArrayInputStream(objBytes); 
        ObjectInputStream oi = new ObjectInputStream(bi); 
        return oi.readObject(); 
    } 
    
    public static byte[] getBytesFromObject(Object obj) throws IOException{ 
        if (obj == null){ 
            return null; 
        } 
        ByteArrayOutputStream bo = new ByteArrayOutputStream(); 
        ObjectOutputStream oo = new ObjectOutputStream(bo); 
        oo.writeObject(obj); 
        return bo.toByteArray(); 
    } 
    
}
