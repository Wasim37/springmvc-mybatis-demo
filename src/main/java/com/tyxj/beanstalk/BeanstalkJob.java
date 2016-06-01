package com.tyxj.beanstalk;

import com.tyxj.beanstalk.BeanstalkClient;

/**
 * @author 
 *
 */
public class BeanstalkJob {
	
	private byte[] data;
	private long id;
	BeanstalkClient client = null;
	
	public BeanstalkClient getClient() {
		return this.client;
	}
	public void setClient(BeanstalkClient client) {
		this.client = client;
	}
	public byte[] getData() {
		return this.data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
