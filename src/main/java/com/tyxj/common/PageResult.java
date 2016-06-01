package com.tyxj.common;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = -8689423380671812808L;

	public static final int DEFAULT_PAGE_SIZE = 10;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PageResult.class);

	private int pageIndex;
	private int pageSize;
	private int totalCount;
	private int pageCount;
	private List<T> list;

	public PageResult() {
		pageIndex = 1;
		pageSize = DEFAULT_PAGE_SIZE;
	}

	public PageResult(String pageIndex) {
		this(pageIndex, "");
	}

	public PageResult(String pageIndexStr, String pageSizeStr) {
		int pageIndexTemp = 1;
		int pageSizeTemp = DEFAULT_PAGE_SIZE;
		if (null == pageIndexStr || "".equals(pageIndexStr)) {
			pageIndexTemp = 1;
		} else {
			try {
				pageIndexTemp = Integer.parseInt(pageIndexStr);
			} catch (Exception e) {
				pageIndexTemp = 1;
				LOGGER.info(e.getMessage(), e);
			}
		}

		if (null == pageSizeStr || "".equals(pageSizeStr)) {
			pageSizeTemp = DEFAULT_PAGE_SIZE;
		} else {
			try {
				pageSizeTemp = Integer.parseInt(pageSizeStr);
			} catch (Exception e) {
				pageSizeTemp = DEFAULT_PAGE_SIZE;
				LOGGER.info(e.getMessage(), e);
			}
		}
		if (pageIndexTemp < 1) {
			pageIndexTemp = 1;
		}
		if (pageSizeTemp < 1) {
			pageSizeTemp = 1;
		}
		this.pageIndex = pageIndexTemp;
		this.pageSize = pageSizeTemp;

	}

	public PageResult(int totalCount, Limit limit, List<T> list) {

		this.pageIndex = limit.getPageId();
		this.pageSize = limit.getSize();
		this.list = list;
		this.setTotalCount(totalCount);
	}

	public PageResult(int pageIndex, int pageSize) {
		int pageIndexTemp = pageIndex;
		int pageSizeTemp = pageSize;
		if (pageIndexTemp < 1) {
			pageIndexTemp = 1;
		}
		if (pageSizeTemp < 1) {
			pageSizeTemp = 1;
		}
		this.pageIndex = pageIndexTemp;
		this.pageSize = pageSizeTemp;

	}

	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	public PageResult(int pageIndex) {
		this(pageIndex, DEFAULT_PAGE_SIZE);
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getFirstResult() {
		return (pageIndex - 1) * pageSize;
	}

	public boolean getHasPrevious() {
		return pageIndex > 1;
	}

	public boolean getHasNext() {
		return pageIndex < pageCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
		if (0 == totalCount) {
			if (pageIndex != 1) {
				pageIndex = 1;
			}
		} else {
			if (pageIndex > pageCount) {
				pageIndex = pageCount;
			}
		}
	}

	public int getPrev() {
		return this.pageIndex - 1;
	}

	public int getNext() {
		return this.pageIndex + 1;
	}

	public boolean isEmpty() {
		return 0 == totalCount;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageCount(int countInPage) {
		this.pageCount = countInPage;
	}

}