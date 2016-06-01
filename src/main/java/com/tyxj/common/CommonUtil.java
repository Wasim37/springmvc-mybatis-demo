package com.tyxj.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/**
 * @ClassName: CommonUtil
 * @Description: 公共类
 * @author 
 * @date 2014年7月4日 上午11:05:30
 */
public class CommonUtil {

	private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);
	
	/**
	 * 读取实体bean属性值 
	 * @param bean  实体对象
	 * @param propertyName  要取的属性值
	 * @return 返回实体对象的属性值
	 */
	public static Object getPropertyValue(Object bean, String propertyName) {
		Object result = null;
		if ("serialVersionUID".equals(propertyName)) {
			return result;
		}
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(propertyName, bean.getClass());
			Method m = pd.getReadMethod();
			result = m.invoke(bean);
		} catch (Exception ex) {
			LOGGER.error("getPropertyValue----error", ex);
		} 
		return result;
	}

	/**
	 * 设置实体bean的属性值
	 * @param bean 实体对象
	 * @param propertyName 要设置的属性
	 * @param value 属性值
	 */
	public static void setProperty(Object bean, String propertyName, Object value) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(propertyName, bean.getClass());
			Method m = pd.getWriteMethod();
			// 设置属性值
			m.invoke(bean, value);
		} catch (Exception ex) {
			LOGGER.error("getPropertyValue----error", ex);
		}
	}

	public static void print(Object bean) {
		Field[] fields = bean.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			LOGGER.info(fields[i].getName() + "：" + CommonUtil.getPropertyValue(bean, fields[i].getName()) + " | ");
		}
	}

}
