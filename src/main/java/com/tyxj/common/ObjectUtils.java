package com.tyxj.common;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * ***********************************
 * @author Jon Chiang
 * @project aider
 * @create_date 2013-10-11  下午9:55:55
 * ***********************************
 */
public class ObjectUtils {
	private static final Logger LOGGER = Logger.getLogger(ObjectUtils.class);
	/**
	 * 集合是否为空
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return
	 * ***********************************
	 */
	public static boolean isEmpty(Collection<?> s) {
		if (null == s) {
			return true;
		}
		return s.isEmpty();
	}

	/**
	 * map是否为空
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return
	 * ***********************************
	 */
	public static boolean isEmpty(Map<?, ?> s) {
		if (null == s) {
			return true;
		}
		return s.isEmpty();
	}

	/**
	 * 字符串是否为空
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return
	 * ***********************************
	 */
	public static boolean isEmpty(String s) {
		if (null == s) {
			return true;
		}
		return s.toString().trim().length() <= 0;
	}

	/**
	 * 对象是否为空
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return
	 * ***********************************
	 */
	public static <T> boolean isEmpty(T s) {
		if (null == s) {
			return true;
		}
		return false;

	}

	/**
	 * 对象是否为空
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return
	 * ***********************************
	 */
	public static <T> boolean isEmpty(T[] s) {
		if (null == s) {
			return true;
		}
		return Array.getLength(s) < 1;
	}

	/**
	 * 集合不为空
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return
	 * *********************************** 
	 */
	public static boolean isNotEmpty(Collection<?> s) {
		if (null == s) {
			return false;
		}
		return !s.isEmpty();
	}

	/**
	 * map不为空
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return
	 * ***********************************
	 */
	public static boolean isNotEmpty(Map<?, ?> s) {
		if (null == s) {
			return false;
		}
		return !s.isEmpty();
	}

	/**
	 * 字符串不为空
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return
	 * ***********************************
	 */
	public static boolean isNotEmpty(String s) {
		if (null == s) {
			return false;
		}
		return s.toString().trim().length() > 0;
	}

	/**
	 * int 大于等于0
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return
	 * ***********************************
	 */
	public static boolean isNotEmpty(Integer s) {
		if (null == s) {
			return false;
		}
		return s >= 0;
	}

	/**
	 * 对象是否为空
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:09
	 * @param t
	 * @return
	 * ***********************************
	 */
	public static <T> boolean isNotEmpty(T s) {
		if (null == s) {
			return false;
		}
		if (s instanceof Integer) {
			Integer i = (Integer) s;
			return i >= 0;
		}
		return !isEmpty(s);
	}

	/**
	 * 转换list为 id列表
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:18
	 * @param t
	 * @return
	 * ***********************************
	 */
	public static <T> String listToString(Collection<T> t, String keyName) {
		String methodName = "";
		StringBuilder keys = new StringBuilder();
		try {
			for (T t2 : t) {
				methodName = "get" + keyName.substring(0, 1).toUpperCase() + keyName.substring(1);
				Method method = t2.getClass().getDeclaredMethod(methodName);
				Object res = method.invoke(t2);
				if (null != res) {
					keys.append(res);
					keys.append(",");
				}
			}
		} catch (Exception e) {
			LOGGER.error("listToString---errror:"+e.getMessage(), e);
		}
		if (keys.length() > 0) {
			return keys.substring(0, keys.length() - 1);
		} else {
			return "";
		}
	}
	/**
	 * 转换list为 id列表
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:18
	 * @param t
	 * @return
	 * ***********************************
	 */
	public static <T> String arrayToString(T[] t) {
		StringBuilder keys = new StringBuilder();
		for (T t2 : t) {
			keys.append(t2);
			keys.append(",");
		}
		if (keys.length() > 0) {
			return keys.substring(0, keys.length() - 1);
		} else {
			return "";
		}
	}

	/**
	 * 转换list为 id列表
	 * ***********************************
	 * @author Jon Chiang
	 * @create_date 2013-10-11 下午10:03:18
	 * @param t
	 * @return
	 * ***********************************
	 */
	public static <T> String listToString(Collection<T> t) {
		StringBuilder keys = new StringBuilder();
		for (T t2 : t) {
			keys.append(t2);
			keys.append(",");
		}
		if (keys.length() > 0) {
			return keys.substring(0, keys.length() - 1);
		} else {
			return "";
		}
	}

	public static String toFirstLetterUpperCase(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		String firstLetter = str.substring(0, 1).toUpperCase();
		return firstLetter + str.substring(1, str.length());
	}

	/**
	  * 整型转换为4位字节数组 
	  * @author Jon Chiang
	  * @create_date 2015-1-27 下午5:11:58
	  * @param intValue
	  * @return
	 */
	public static byte[] int2Byte(int intValue) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (intValue >> 8 * (3 - i) & 0xFF);
		}
		return b;
	}

	/**
	  * 4位字节数组转换为整型 
	  * @author Jon Chiang
	  * @create_date 2015-1-27 下午5:11:47
	  * @param b
	  * @return
	 */
	public static int byte2Int(byte[] b) {
		int intValue = 0;
		for (int i = 0; i < b.length; i++) {
			intValue += (b[i] & 0xFF) << (8 * (3 - i));
		}
		return intValue;
	}

	/**
	  * @author Jon Chiang
	  * @create_date 2014-8-7 上午10:16:59
	  * @param score
	  * @return
	  */
	public static Float parseFloat(String score) {
		if (isNotEmpty(score) && isDouble(score)) {
				return Float.valueOf(score);
		}
		return 0f;
	}

	/**
	 * @author Jon Chiang
	 * @create_date 2014-8-7 上午10:16:59
	 * @param score
	 * @return
	 */
	public static Integer parseInt(String score) {
		if (isNotEmpty(score) && isDouble(score)) {
				return Integer.valueOf(score);
		}
		return 0;
	}

	public static final Pattern INTEGER_PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");

	/**
	 * 
	  * @author Jon Chiang
	  * @create_date 2014-8-7 上午10:23:15
	  * @param str
	  * @return
	 */
	public static boolean isInteger(String str) {
		return INTEGER_PATTERN.matcher(str).matches();
	}

	/* 
	 *
	 * @param str 
	 * @return 
	*/
	public static final Pattern FLOAT_PATTERN = Pattern.compile("^[-\\+]?[.\\d]*$");

	/**
	  *  判断是否为浮点数，包括double和float 
	  * @author Jon Chiang
	  * @create_date 2014-8-7 上午10:22:54
	  * @param str传入的字符串 
	  * @return是浮点数返回true,否则返回false 
	 */
	public static boolean isDouble(String str) {
		return FLOAT_PATTERN.matcher(str).matches();
	}

	/**
	  * @author Jon Chiang
	  * @create_date 2014-8-8 上午11:26:33
	  * @param difficulty
	  * @return
	  */
	public static byte stringToByte(String difficulty) {

		if (ObjectUtils.isNotEmpty(difficulty) && difficulty.length() == 1) {
				return Byte.valueOf(difficulty);
		}
		return (byte) 0;
	}

	/**
	  * @author Jon Chiang
	  * @create_date 2014-9-1 下午5:26:29
	  * @param paperIdSb
	  * @return
	  */
	public static String setToString(Set<Integer> set) {
		if (isEmpty(set)) {
			return "";
		}
		String ids = set.toString();
		return ids.substring(1, ids.length() - 1);
	}

	/** 判断 a 是否在【a,b,c】集合中
	  * 
	  * @author Jon Chiang
	  * @create_date 2015-1-27 下午5:12:27
	  * @param org
	  * @param compArray
	  * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> boolean isIn(T org,T ...compArray) {
		for (T t : compArray) {
			if(t.equals(org)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 数字和字母混合
	 */
	public static final Pattern NUMBER_ALPHA_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");
	public static boolean isNumberAlphaFix(String str){
		return NUMBER_ALPHA_PATTERN.matcher(str).matches();
	}
}
