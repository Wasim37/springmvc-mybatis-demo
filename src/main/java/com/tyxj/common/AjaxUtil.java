package com.tyxj.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class AjaxUtil {

    /**
      * 直接打印异常到页面 统一用json格式 {error:true,status:'500',msg:'授权失败',entity:{}} 	
      * @author Administrator
      * @create_date 2014-5-7 下午3:45:32
      * @param tx
      * @param msg
      * @param response
      * @
     */
	public static void ajaxError(Throwable tx,String msg,HttpServletResponse response) throws IOException{
		AjaxResponse<String> ajaxResponse = new AjaxResponse<String>();
		ajaxResponse.setError(true);
		ajaxResponse.setMsg(msg);
		ajaxResponse.setDetailMsg(tx.getMessage());
		ajaxResponse.setStatus("500");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(JsonUtil.objectToJson(ajaxResponse));
	}
	/**
	 * 返回ajax需要格式的json字符串 用于@ResponseBody
	 * @author Administrator
	 * @create_date 2014-5-7 下午3:45:33
	 * @param tx
	 * @param msg
	 * @param response
	 * @return 
	 * @
	 */
	public static String ajaxError(Throwable tx,String msg) {
		AjaxResponse<String> ajaxResponse = new AjaxResponse<String>();
		ajaxResponse.setError(true);
		ajaxResponse.setMsg(msg);
		ajaxResponse.setDetailMsg(tx.getMessage());
		ajaxResponse.setStatus("500");
		return JsonUtil.objectToJson(ajaxResponse);
	}
	/**
	 * 返回ajax需要格式的json字符串 用于@ResponseBody
	 * @author Administrator
	 * @create_date 2014-5-7 下午3:45:33
	 * @param tx
	 * @param msg
	 * @param response
	 * @return 
	 * @
	 */
	public static String ajaxFail(String msg) {
		AjaxResponse<String> ajaxResponse = new AjaxResponse<String>();
		ajaxResponse.setError(true);
		ajaxResponse.setMsg(msg);
		ajaxResponse.setStatus("500");
		return JsonUtil.objectToJson(ajaxResponse);
	}
	/**
	 * 返回ajax需要格式的json字符串 用于@ResponseBody
	 * @author Administrator
	 * @create_date 2014-5-7 下午3:45:33
	 * @param tx
	 * @param msg
	 * @param response
	 * @return 
	 * @
	 */
	public static String ajaxSuccess(String msg){
		AjaxResponse<String> ajaxResponse = new AjaxResponse<String>();
		ajaxResponse.setError(false);
		ajaxResponse.setMsg(msg);
		ajaxResponse.setStatus("200");
		return JsonUtil.objectToJson(ajaxResponse);
	}
	/**
	 * 返回ajax需要格式的json字符串 用于@ResponseBody
	 * @author Administrator
	 * @create_date 2014-5-7 下午3:45:33
	 * @param tx
	 * @param msg
	 * @param response
	 * @return 
	 * @
	 */
	public static String ajaxSuccess(AjaxResponse<?> ajaxResponse) {
		ajaxResponse.setError(false);
		ajaxResponse.setStatus("200");
		return JsonUtil.objectToJson(ajaxResponse);
	}
	
	/**
	 * 返回ajax需要格式的json字符串 用于@ResponseBody
	 * @Title: ajaxSuccess
	 * @author WangX
	 * @Date: 2016年5月18日 上午10:22:39
	 * @param @param list
	 * @param @param totalCount
	 * @param @param totalPage
	 * @param @return
	 * @return String
	 */
	public static String ajaxSuccess(List<?> list, int totalCount, int totalPage){
		AjaxResponse<List<?>> ajaxResponse = new AjaxResponse<List<?>>();
		ajaxResponse.setError(false);
		ajaxResponse.setResult(list);
		ajaxResponse.setTotalCount(totalCount);
		ajaxResponse.setTotalPage(totalPage);
		ajaxResponse.setStatus("200");
		return JsonUtil.objectToJson(ajaxResponse);
	}
	
	/**
	 * 返回ajax需要格式的json字符串 用于@ResponseBody
	 * @Title: ajaxSuccess
	 * @author LiDu
	 * @Date: 2016年5月18日 上午10:22:39
	 * @param @param list
	 * @param @param totalCount
	 * @param @return
	 * @return String
	 */
	public static String ajaxSuccess(Object obj, int totalCount){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>();
		ajaxResponse.setError(false);
		ajaxResponse.setResult(obj);
		ajaxResponse.setTotalCount(totalCount);
//		ajaxResponse.setTotalPage(totalPage);
		ajaxResponse.setStatus("200");
		return JsonUtil.objectToJson(ajaxResponse);
	}
}
