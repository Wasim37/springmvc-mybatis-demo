package com.tyxj.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.tyxj.common.MD5Util;

public class MessageUtil {

	private static final Logger LOGGER = Logger.getLogger(MessageUtil.class);
	/**
	 * 
	 * 方法描述: 发送短信
	 * 
	 * 创建时间: yxy 2012-5-29 上午08:43:01
	 */
	public static String sendMessage(String telephone, String content, String title) {
		String username = "nrsyb";
		String password = "nrsyb#869";

		String uri = "http://sms.bbkmkt.com.cn/send";
		String result = "";
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(uri);
		method.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		// 设置Http Post数据
		method.addParameter("rand", "11");
		method.addParameter("username", username);
		method.addParameter("password", password);
		method.addParameter("sign", MD5Util.md5s("username=" + username + "&rand=11&key=f688nj-u2836sd#6df6"));
		method.addParameter("apiCode", username);
		method.addParameter("telephone", telephone);
		method.addParameter("content", content);
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(method.getResponseBodyAsStream(),
								"utf-8"));
				String line;
				StringBuilder response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				reader.close();
				result = response.toString();
			}
		} catch (Exception e) {
			LOGGER.error("sendMessage---error: "+e.getMessage(), e);
		} finally {
			method.releaseConnection();
		}
		return result.trim();
	}

}