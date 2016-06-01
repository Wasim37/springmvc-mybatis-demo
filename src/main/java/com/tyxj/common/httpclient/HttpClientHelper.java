package com.tyxj.common.httpclient;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.log4j.Logger;

/** 
 * @ClassName: HttpClientHelper 
 * @Description: http请求类
 * @author 
 * @date 2014年8月22日 上午11:02:53 
 *  
 */

public class HttpClientHelper {

	private static final Logger LOGGER = Logger.getLogger(HttpClientHelper.class);
	
	 /**
     * Get方法传送消息
     * 
     * @param url
     *            连接的URL
     * @param queryString
     *            请求参数串
     * @return 服务器返回的信息
     * @throws Exception
     */
	 public static String httpGet(String url, String queryString){
		BHttpClient httpClient = new BHttpClient();
		String responseResult = "";
		try {
			responseResult = httpClient.httpGet(url, queryString);
		} catch (Exception e) {
			LOGGER.error("httpGet--error", e);
		} finally {
			httpClient.shutdownConnection();
			
		}
		return responseResult;
	 }
	 
	/**
	 * Post方法传送消息
	 * 
	 * @param url
	 *            连接的URL
	 * @param queryString
	 *            请求参数串
	 * @return 服务器返回的信息
	 * @throws Exception
	 */
	public static String httpPost(String url, String queryString) {
		BHttpClient httpClient = new BHttpClient();
		String responseResult = "";
		
		try {
			responseResult = httpClient.httpPost(url, queryString);
		} catch (Exception e) {
			LOGGER.error("httpGet--error", e);
		}finally {
			httpClient.shutdownConnection();
		}

		return responseResult;
	}
	
	/***
	 * 
	* @Title: httpPost 
	* @author:     echao.wang
	* Create at:   2014年11月27日 下午3:16:20 
	* @Description: post请求
	* @param @param url
	* @param @param queryString
	* @param @param cookieToken
	* @param @return  
	* @return String
	 */
	public static String httpPost(String url, String queryString,String cookieToken) {
		BHttpClient httpClient = new BHttpClient();
		String responseResult = "";
		
		try {
			responseResult = httpClient.httpPost(url, queryString,cookieToken);
		} catch (Exception e) {
			LOGGER.error("httpGet--error", e);
		}finally {
			httpClient.shutdownConnection();
		}
		return responseResult;
	}
	
	/**
     * Post方法传送消息
     * 
     * @param url
     *            连接的URL
     * @param queryString
     *            请求参数串
     * @return 服务器返回的信息
     * @throws Exception
     */
    public static String httpPost(String url, String queryString,List<NameValuePair> nameValuePairList){
    	BHttpClient httpClient = new BHttpClient();
		String responseResult = "";
		
		try {
			responseResult = httpClient.httpPost(url, queryString, nameValuePairList);
		} catch (Exception e) {
			LOGGER.error("httpGet--error", e);
		}finally {
			httpClient.shutdownConnection();
		}
		return responseResult;
    }
    
	/**
	 * Post方法传送消息
	 * 
	 * @param url
	 *            连接的URL
	 * @param queryString
	 *            请求参数串
	 * @return 服务器返回的信息
	 * @throws Exception
	 */
	public static String httpPostWithFile(String url, String queryString,List<NameValuePair> files) {

		BHttpClient httpClient = new BHttpClient();
		String responseResult = "";
		
		try {
			responseResult = httpClient.httpPostWithFile(url, queryString, files);
		} catch (Exception e) {
			LOGGER.error("httpGet--error", e);
		}finally {
			httpClient.shutdownConnection();
		}
		return responseResult;
	}
	
	
}
