package com.tyxj.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author zhr
 * @project video
 * @create_date 2014-5-7 下午3:32:16
 */
public class InitConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(InitConfig.class);
	
	static Properties prop = new Properties();
	static {
		try {
			InputStream in = InitConfig.class.getResourceAsStream("/init.properties");
			prop.load(in);
		} catch (IOException e) {
			LOGGER.error("load properties file error:"+e.getMessage(),e);
		}
	}

	public static String get(String key) {
		if (prop.containsKey(key)) {
			return prop.get(key).toString();
		}
		return "";
	}
}
