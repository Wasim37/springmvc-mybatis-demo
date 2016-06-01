package com.tyxj.beanstalk;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tyxj.beanstalk.BeanstalkClient;
import com.tyxj.beanstalk.BeanstalkConnection;
import com.tyxj.beanstalk.BeanstalkDisconnectedException;
import com.tyxj.beanstalk.BeanstalkException;
import com.tyxj.beanstalk.BeanstalkJob;
import com.tyxj.beanstalk.BeanstalkPool;
import com.tyxj.common.ObjectUtils;

import java.io.ByteArrayOutputStream;
import java.util.Date;

public class BeanstalkClient {

	private static final Log LOGGER = LogFactory.getLog(BeanstalkClient.class);
	
	protected BeanstalkConnection con;
	private boolean inited = false;
	//this will tell the pool to reap it when returned
	boolean reap = false; 
	
	protected String addr;
	protected int port;
	protected String tube;
	
	/**
	 * these variables are only used in the pool
	 */
	Date inUseSince = null;
	Date lastUsed = null;
	
	BeanstalkPool pool = null;
	
	public static void main(String...strings) throws Exception{
		BeanstalkPool pool = new BeanstalkPool("192.168.100.184", 11302, 10000);
		BeanstalkClient client = null;
		client = pool.getClient();
		client.useTube("QUESTION_ANSWER_QUEUE_1_1");
		client.put(0, 5, 7200, ObjectUtils.int2Byte(19864550));
		client.close();
	}
	
	public BeanstalkClient(BeanstalkConnection con) {
		this.con = con;
		this.inited = true;
	}
	
	public BeanstalkClient(String addr, int port) {
		this(addr, port, null);
	}
	
	public BeanstalkClient(String addr, int port, String tube) {
		this.addr = addr;
		this.port = port;
		this.tube = tube;
	}
	
	public BeanstalkClient(String addr, int port, String tube, BeanstalkPool pool) {
		this.addr = addr;
		this.port = port;
		this.tube = tube;
		this.pool = pool;
	}

	/**
	 * 连接池有问题
	 * will return the connection to the pool, or close the underlying socket if this
	 * did not come from a pool
	 */
	public void close1() {
		if (this.pool == null) {
			if (this.con != null) {
				this.con.close();
			}
			return;
		}
		pool.done(this);
	}
	
	/**
	 * 用这个方式代替close1()方式
	   * @author zhr
	   * @create_date 2014-6-6 下午12:19:39
	 */
	public void close() {
		if (this.con != null) {
			this.con.close();
		}
		return;
	}
	
	private void init() throws BeanstalkException{
		if (inited) {
			return;
		}
		
		try {
			this.inited = true;
			this.con = new BeanstalkConnection();
			this.con.connect(addr, port);
			if (this.tube != null) {
				this.useTube(tube);
				this.watchTube(tube);
				//remove the default tube from watchlist
				this.ignoreTube("default"); 
			}
		} catch (BeanstalkException x) {
			throw x;
		} 
	}
	
	public void useTube(String tube) throws BeanstalkException{
		try {			
			this.init();
			con.write("use " + tube + "\r\n");
			String line = con.readControlResponse();
			LOGGER.debug(line);
			if (line.startsWith("USING")) {
				return;
			}
			throw new BeanstalkException(line);
		} catch (BeanstalkDisconnectedException x) {
			//reap that shit..
			this.reap = true; 
			throw x;
		} catch (BeanstalkException x) {
			throw x;
		}
	}
	
	public void watchTube(String tube) throws BeanstalkException{
		try {			
			this.init();
			con.write("watch " + tube + "\r\n");
			
			String line = con.readControlResponse();
			LOGGER.debug(line); 
			
			if (line.startsWith("WATCHING")) {
				return;
			}
			throw new BeanstalkException(line);
		} catch (BeanstalkDisconnectedException x) {
			//reap that shit..
			this.reap = true; 
			throw x;
		} catch (BeanstalkException x) {
			throw x;
		}
	}
	
	public void ignoreTube(String tube) throws BeanstalkException{
		try {			
			this.init();
			con.write("ignore " + tube + "\r\n");
			String line = con.readControlResponse();
			LOGGER.debug(line);
			
			if (line.startsWith("WATCHING")) {
				return;
			}
			throw new BeanstalkException(line);
		} catch (BeanstalkDisconnectedException x) {
			//reap that shit..
			this.reap = true; 
			throw x;
		} 
	}
	
	/**
	 * stats for the current tube
	 * @throws BeanstalkException
	*/ 
	public String tubeStats() throws BeanstalkException {
		return this.tubeStats(this.tube);
	}
	
	public String tubeStats(String tube) throws BeanstalkException {
		try {			
			this.init();
			String command = "stats-tube " + tube + "\r\n";
			con.write(command);
			
			String line = con.readControlResponse();
			
			if (!line.startsWith("OK")) {
				throw new BeanstalkException(line);
			}
			int numBytes = Integer.parseInt(line.split(" ")[1]);
			return new String(con.readBytes(numBytes));
		} catch (BeanstalkDisconnectedException x) {
			//reap that shit..
			this.reap = true; 
			throw x;
		} 
	}
	/**
	 * Puts a task into the currently used queue (see {@link #useTube(String)}.
	 * @param priority The job priority, from 0 to 2^32. Most urgent = 0, least urgent = 4294967295.
	 * @param delay The time the server will wait before putting the job on the ready queue.
	 * @param ttr The job time-to-run. The server will automatically release the job after this TTR (in seconds)
	 *   after a client reserves it.
	 * @param data The job data.
	 * @return The id of the inserted job.
	 * @throws BeanstalkException If an unexpected response is received from the server, or other unexpected
	 * 	 problem occurs.
	 */
	public long put(long priority, int delay, int ttr, byte[] data) throws BeanstalkException{
		try {			
			this.init();
			String command = "put " + priority + " " + delay + " " + ttr + " " + data.length + "\r\n";
			
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			buf.write(command.getBytes("UTF-8"));
			buf.write(data);
			buf.write("\r\n".getBytes("UTF-8"));
			
			con.write(buf.toByteArray());

			String line = con.readControlResponse();
			if (line.startsWith("INSERTED")) {
				return Long.parseLong(line.replaceAll("[^0-9]", ""));
			}
			
			throw new BeanstalkException(line);
			
		} catch (BeanstalkDisconnectedException x) {
			this.reap = true; 
			throw x;
		} catch (BeanstalkException x) {
			throw x;
		} catch (Exception x) {
			throw new BeanstalkException(x);
		}
	}
	
	public void deleteJob(BeanstalkJob job) throws BeanstalkException {
		deleteJob(job.getId());
	}
	
	public void deleteJob(long id) throws BeanstalkException {
		try {			
			this.init();
			String command = "delete " + id + "\r\n";
			LOGGER.debug(this);
			LOGGER.debug(command);
			con.write(command);
			String line = con.readControlResponse();
			LOGGER.debug(line);
			
			if (line.startsWith("DELETED")) {
				return;	
			}
			throw new BeanstalkException(line);			
		} catch (BeanstalkDisconnectedException x) {
			//reap that shit..
			this.reap = true; 
			throw x;
		}
	}

	/**
	 * Reserves a job from the queue.
	 * @param timeoutSeconds The number of seconds to wait for a job. Null if a job should be reserved
	 *   only if immediately available.
	 * @return The head of the queue, or null if the specified timeout elapses before a job is available.
	 * @throws BeanstalkException If an unexpected response is received from the server, or other unexpected
	 * 	 problem occurs.
	 */
	public BeanstalkJob reserve(Integer timeoutSeconds) throws BeanstalkException{
		try {			
			this.init();
			String command = "reserve\r\n";
			if (timeoutSeconds != null) {
				command = "reserve-with-timeout " + timeoutSeconds + "\r\n";
			}
			
			LOGGER.debug(this);
			LOGGER.debug(command);
			con.write(command);
			String line = con.readControlResponse();
			LOGGER.debug(line);
			
			if (line.startsWith("TIMED_OUT")) {
				return null;
			}
			
			if (!line.startsWith("RESERVED")) {
				throw new BeanstalkException(line);
			}

			String[] tmp = line.split("\\s+");
			long id = Long.parseLong(tmp[1]);
			
			int numBytes= Integer.parseInt(tmp[2]);
			
			LOGGER.debug("ID : " + id);
			LOGGER.debug("numbytes: " + numBytes);
				
			byte[] bytes = con.readBytes(numBytes);
			
			BeanstalkJob job = new BeanstalkJob();
			job.setData(bytes);
			job.setId(id);
			job.setClient(this);
			return job;	
			
		} catch (BeanstalkDisconnectedException x) {
			this.reap = true;
			throw x;
		} catch (BeanstalkException x) {
			throw x;
		} catch (Exception x) {
			throw new BeanstalkException(x);
		}
	}

    public void release(long id, int priority, int delay) throws BeanstalkException {
        try {
            this.init();
            String command = "release " + id + " " + priority + " " + delay + "\r\n";

            LOGGER.debug(this);
            LOGGER.debug(command);
            con.write(command);
            String line = con.readControlResponse();
            LOGGER.debug(line);

            if (!line.startsWith("RELEASED")) {
                throw new BeanstalkException(line);
            }

        } catch (BeanstalkDisconnectedException x) {
        	//reap that shit..
            this.reap = true; 
            throw x;
        } catch (BeanstalkException x) {
            throw x;
        } catch (Exception x) {
            throw new BeanstalkException(x);
        }        
    }
	/**
	 * Releases a job (places it back onto the queue).
	 * @param job The job to release. This job must previously have been reserved.
	 * @param priority The new priority to assign to the released job.
	 * @param delay The number of seconds the server should wait before placing the job onto the ready queue.
	 * @throws BeanstalkException If an unexpected response is received from the server, or other unexpected
	 * 	 problem occurs.
	 */
	public void release(BeanstalkJob job, int priority, int delay) throws BeanstalkException {
	    release(job.getId(), priority, delay);
	}

	/**
	 * Buries a job ("buried" state means the job will not be touched by the server again until "kicked").
	 * @throws BeanstalkException If an unexpected response is received from the server, or other unexpected
	 * 	 problem occurs.
	 * @param job The job to bury. This job must previously have been reserved.
	 * @param priority The new priority to assign to the job.
	 */
	public void bury(BeanstalkJob job, int priority) throws BeanstalkException {
		try {
			this.init();
			String command = "bury " + job.getId() + " " + priority + "\r\n";

			LOGGER.debug(this);
			LOGGER.debug(command);
			con.write(command);
			String line = con.readControlResponse();
			LOGGER.debug(line);

			if (!line.startsWith("BURIED")) {
				throw new BeanstalkException(line);
			}

		} catch (BeanstalkDisconnectedException x) {
			//reap that shit..
			this.reap = true; 
			throw x;
		} catch (BeanstalkException x) {
			throw x;
		} catch (Exception x) {
			throw new BeanstalkException(x);
		}
	}
}


