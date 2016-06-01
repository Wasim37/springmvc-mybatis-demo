package com.tyxj.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tyxj.beanstalk.BeanstalkClient;
import com.tyxj.beanstalk.BeanstalkException;
import com.tyxj.beanstalk.BeanstalkPool;

/** 
 * @ClassName: BeanstalkUtil 
 * @Description: 
 * @author issac zhu
 * @date 2014-3-1 下午02:10:40 
 */
public class BeanstalkdUtil {

	private static final Log LOGGER = LogFactory.getLog(BeanstalkdUtil.class);
	private static BeanstalkPool pool = null;
	private BeanstalkClient client = null;
	
	 public static void initializer(String ip, int port, int poolSize){
		 initialPool(ip, port, poolSize);
	 }
	
	 public static void initializer(String ip, int port, int poolSize, String tube){
		 initialPool(ip, port, poolSize, tube);
	 }
	 
	 public static void initialPool(String ip, int port, int poolSize){
		 pool = new BeanstalkPool(ip, port, poolSize, null);
	 }
	 
	 public static void initialPool(String ip, int port, int poolSize, String tube){
		 pool = new BeanstalkPool(ip, port, poolSize, tube);
	 }
	 
	 public BeanstalkClient getClient(){
		 try {
			client = pool.getClient();
		} catch (BeanstalkException e) {
			LOGGER.error("get client error:"+e.getMessage(), e);
		}
    	 return client;
	 }
	

}
