package com.tyxj.common;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * @author Jon Chiang
 * @project video
 * @create_date 2014-5-16 下午3:41:58
 */
public class Pagination<T> extends TagSupport {

	private static final long serialVersionUID = 1L;

	//分页对象
	private PageResult<T> pageResult;

	public int doStartTag() throws JspException {
		if (ObjectUtils.isEmpty(pageResult) || pageResult.getTotalCount() < 1) {
			printPage("");
			return SKIP_BODY;
		}
		int pageId = pageResult.getPageIndex();
		// 拼写要输出到页面的HTML文本
		StringBuilder sb = new StringBuilder("<div class='pagination' ");
		sb.append(" size='");
		sb.append(pageResult.getPageSize());
		sb.append("'><ul>");

		sb.append("<li><a href='javascript:;' pageId='");
		sb.append(1);
		sb.append("'");
		if (!pageResult.getHasPrevious()) {
			sb.append(" class='disabled'");
		}
		sb.append(">首页</a></li>");
		sb.append("<li><a  href='javascript:;' pageId='");
		sb.append(pageResult.getPrev());
		sb.append("'");
		if (!pageResult.getHasPrevious()) {
			sb.append(" class='disabled'");
		}
		sb.append(">上一页</a></li>");
		int rangeS = pageId - 5;
		int rangeE = pageId + 4;
		if (rangeS < 1) {
			rangeE = rangeE - rangeS;
			rangeS = 1;
		}
		if (rangeE > pageResult.getPageCount()) {
			rangeE = pageResult.getPageCount();
		}
		//后置
		for (int i = rangeS; i <= rangeE; i++) {
			if (i == pageId) {
				sb.append("<li><a href='javascript:;' class='current disabled'>");
			} else {
				sb.append("<li><a href='javascript:;'>");
			}
			sb.append(i);
			sb.append("</a></li>");
		}

		sb.append("<li><a href='javascript:;' pageId='");
		sb.append(pageResult.getNext());
		sb.append("'");
		if (!pageResult.getHasNext()) {
			sb.append(" class='disabled'");
		}
		sb.append(">下一页</a></li>");
		sb.append("<li><a href='javascript:;' pageId='");
		sb.append(pageResult.getPageCount());
		sb.append("'");
		if (!pageResult.getHasNext()) {
			sb.append(" class='disabled'");
		}
		sb.append(">尾页</a></li>");
		sb.append("<input class='' type='hidden' id='pageId' name='pageId'/></ul>");
		sb.append("<span class='pageinfo'>共<em>");
		sb.append(pageResult.getPageCount());
		sb.append("</em>页&nbsp;&nbsp;共");
		sb.append(pageResult.getTotalCount());
		sb.append("条信息</span>");
		sb.append("</div>");
		// 把生成的HTML输出到响应中
		printPage(sb.toString());
		// 本标签主体为空,所以直接跳过主体
		return SKIP_BODY; 
	}

	private void printPage(String sb) throws JspException {
		try {
			pageContext.getOut().println(sb);
		} catch (IOException e) {
			throw new JspException(e);
		}
	}

	public PageResult<T> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult<T> pageResult) {
		this.pageResult = pageResult;
	}
}