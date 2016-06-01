package com.tyxj.beanstalk;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tyxj.beanstalk.BeanstalkClient;
import com.tyxj.beanstalk.BeanstalkException;
import com.tyxj.beanstalk.BeanstalkPool;


/**
 * @author 
 *
 */
public class BeanstalkPool {

	private static final Log LOGGER = LogFactory.getLog(BeanstalkPool.class);
	
	Set<BeanstalkClient> clients = new HashSet<BeanstalkClient>();
	int maxClients = 30;
	//max checkout time is 20 minutes.
	long maxUseTime = 20*60*1000; 
	//connection will be removed after no use.
	long maxIdleTime = 24*60*60*1000;
	
	private String addr;
	private int port;
	private String tube = null;
	
	/**
	 * setup a new pool.  
	 * 
	 * @param addr address of the beanstalkd server to connection to
	 * @param port port of the beanstalkd server
	 * @param maxPoolSize maximum number of clients allowed in the pool (0 for infinity)
	 * @param tube All operations for the client will work on the tube.
	 */
	public BeanstalkPool(String addr, int port, int maxPoolSize, String tube) {
		this.addr = addr;
		this.port = port;
		this.maxClients = maxPoolSize;
		this.tube = tube;
	}

	/**
	 * setup a new pool.  
	 * 
	 * @param addr address of the beanstalkd server to connection to
	 * @param port port of the beanstalkd server
	 * @param maxPoolSize maximum number of clients allowed in the pool (0 for infinity)
	 */
	public BeanstalkPool(String addr, int port, int maxPoolSize) {
		this(addr, port, maxPoolSize, null);
	}
	
	/**
	 * returns the number of active clients in the pool
	 * @return
	 */
	public int getPoolSize() {
		return this.clients.size();
	}
	
	/**
	 * 目前采用这种方式，beanstalkd采用getClient1方式还存在问题
	   * @author 
	   * @create_date 2014-6-5 下午4:58:47
	   * @return
	   * @throws BeanstalkException
	 */
	public BeanstalkClient getClient() throws BeanstalkException{
		return new BeanstalkClient(this.addr, this.port);
	}
	
	/**
	 * 连接池有问题，改采用上面的getClient方式
	 * This gets a client from the pool.  will throw a BeanstalkException if 
	 * there are more then the maximum number of clients checked out. 
	 * 
	 * @return
	 */
	public synchronized BeanstalkClient getClient1() throws BeanstalkException{
		/*
		 * synchronized, but should be fast as the client initialization code happens lazily. 
		 */
		
		Set<BeanstalkClient> toRemove = new HashSet<BeanstalkClient>();
		
		Date max = new Date(new Date().getTime() - this.maxUseTime);
		Date maxIdle = new Date(new Date().getTime() - this.maxIdleTime);
		
		BeanstalkClient returnClient = null;
		try {
			
			/*
			 * Here we iterate over all the clients and reap any that need reaping. 
			 * we could restrict this to only loop over once every minute or so.
			 * for now I don't see it being a huge problem.
			 */
			for (BeanstalkClient client : clients) {
				
				
				if (client.inUseSince != null && client.inUseSince.before(max)) {
					client.reap = true;
				}	
				if (client.lastUsed != null && client.lastUsed.before(maxIdle)) {
					client.reap = true;
				}
				if (client.con != null && ! client.con.isOpen()) {
					client.reap = true;
				}
				
				
				if (client.reap) {
					toRemove.add(client);
				} else if (returnClient == null && client.inUseSince == null) {
					//found the useable client.
					client.inUseSince = new Date();
					//reap old connections
					client.lastUsed = new Date();
					returnClient = client;
				}
			}
		} finally {
			for (BeanstalkClient c : toRemove) {
				LOGGER.debug("REAPING Client: " + c);
				c.pool = null;
				this.clients.remove(c);
				c.close();
			}
		}
		if (returnClient != null) {
			return returnClient;
		}
		
		//add a new client if they are all closed.
		if (this.maxClients > 0 && this.clients.size() >= this.maxClients) {
			LOGGER.error("Too many clients in use!");	
			throw new BeanstalkException("To many clients in use");
		}
		BeanstalkClient client = new BeanstalkClient(this.addr, this.port, this.tube, this);
	
		this.clients.add(client);
		client.inUseSince = new Date();
		return client;
	}
	
	/**
	 * returns a client to the pool
	 * @param client
	 */
	public synchronized void done(BeanstalkClient client) {
		client.inUseSince = null;
	}
}
