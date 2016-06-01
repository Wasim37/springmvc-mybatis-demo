package com.tyxj.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SpringContextUtil
 * @Description:获取spring容器，以访问容器中定义的其他bean
 * @author WangX
 * @date May 6, 2014 2:35:22 PM
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	private SpringContextUtil() {
	}

	/**
	 * @Title: getBean
	 * @Description: 获取对象
	 * @param @param beanId
	 * @param @return
	 * @param @throws BeansException
	 * @return Object
	 */
	public static Object getBean(String beanId) throws BeansException {
		return applicationContext.getBean(beanId);
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	// 改方法仅提供做单元测试用
	public static void setActForTest(ApplicationContext act) {
		applicationContext = act;
	}
}
