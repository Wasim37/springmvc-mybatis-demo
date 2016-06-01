package com.tyxj.beanstalk;

import com.tyxj.beanstalk.BeanstalkException;


/**
 * @author 
 *
 */
public class BeanstalkException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5427161060543990905L;
	
	public BeanstalkException () {
		this(null, null);
	}
	
	public BeanstalkException(String message) {
		this(message, null);
	}
	
	public BeanstalkException(String message, Exception cause) {
		super(message, cause);
	}
	
	public BeanstalkException(Exception cause) {
		this(null, cause);
	}
}
