package com.tyxj.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component("baseConfig")
public class BaseConfig {
	 private static final Logger LOGGER = LoggerFactory.getLogger(BaseConfig.class);
	 
	static Properties prop = new Properties();
	public static final Date START_TIME = new Date();
	static {
        
		try {
			InputStream in = BaseConfig.class.getResourceAsStream("/exception.properties");
			prop.load(in);
			in = BaseConfig.class.getResourceAsStream("/init.properties");
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
