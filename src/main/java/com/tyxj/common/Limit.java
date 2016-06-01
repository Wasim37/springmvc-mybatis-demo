package com.tyxj.common;

import java.io.Serializable;

/**
 * ***********************************
 * @author sandy
 * @project aider
 * @create_date 2013-8-15 上午10:37:27
 * ***********************************
 */
@SuppressWarnings("serial")
public class Limit implements Serializable {

	public static final Limit DEFAILT_LIMIT = Limit.buildLimit(1,12);
	private int size;
	private int pageId;
	private int start;

	/**
	 * 用于 页面&DB分页
	 * @param pageId
	 * @param pageSize
	 * @return
	 */
	public static Limit buildLimit(int pageId, int pageSize) {
		int pageIdTemp = pageId;
		int pageSizeTemp = pageSize;
		if (pageIdTemp <= 0) {
			pageIdTemp = 1;
		}
		if (pageSizeTemp <= 0) {
			pageSizeTemp = 20;// 默认20
		}
		Limit limit = new Limit();
		limit.pageId = pageIdTemp;
		limit.size = pageSizeTemp;
		limit.start = (pageIdTemp - 1) * pageSizeTemp;
		return limit;
	}

	private Limit(int pageId, int size) {
		int pageIdTemp = pageId;
		int pageSizeTemp = size;
		if (pageIdTemp <= 0) {
			pageIdTemp = 1;
		}
		if (pageSizeTemp <= 0) {
			pageSizeTemp = 10;// 默认10
		}
		this.pageId = pageIdTemp;
		this.size = pageSizeTemp;
		this.start= (pageIdTemp - 1) * pageSizeTemp;
	}

	private Limit() {
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		if (size <= 0) {
			size = 10;// 默认10
		}
		if(this.pageId!=0){
			this.start= (this.pageId - 1) * size;
		}
		this.size = size;
	}

	/**
	 * @return the pageId
	 */
	public int getPageId() {
		return pageId;
	}

	/**
	 * @param pageId the pageId to set
	 */
	public void setPageId(int pageId) {
		if (pageId <= 0) {
			pageId = 1;
		}
		if(this.size!=0){
			this.start= (pageId - 1) * this.size;
		}
		this.pageId = pageId;
	}
	
	public void setStart(int start) {
		if(start==0 && size!=0 && pageId!=0){
			start= (pageId - 1) * size;
		}
		this.start = start;
	}

	/**
	 * @return the size
	 */
	public int getStart() {
		return start;
	}
}