package com.tyxj.cache;

import java.io.Serializable;

/** 
 * @ClassName: RedisConstant 
 * @Description: redis key 定义
 * @author issac zhu
 * @date 2014-1-16 上午11:59:38 
 *  
 */

public class RedisConstant implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 7017372372807388966L;
	
	/**
	 * 队列优先级key
	 */
	public static final String QUEUE_PRIORITY_LIST_KEY = "QUEUE_PRIORITY_LIST_KEY";
	
	/**
	 * 系统常量
	 */
	public static final String SYSTEMCONSTANT_COMMONTYPE = "systemconstant_commonType";
	
	/**
	 * 系统初始系统参数
	 */
	public static final String SYSINIT_CONFIG = "sysinit_config";
	
}
