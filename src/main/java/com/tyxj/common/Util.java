package com.tyxj.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * @ClassName: ProtoUtil
 * @Description: 转换工具类
 * @author guosheng.zhu
 * @date 2011-12-13 下午03:51:16
 */
@SuppressWarnings("unchecked")
public class Util {

	private static final Logger LOGGER = Logger.getLogger(Util.class);
	
	public static java.util.Date addYear(java.util.Date date, int year) {
		GregorianCalendar gdate = new GregorianCalendar();
		gdate.setTime(date);
		gdate.add(GregorianCalendar.YEAR, year);
		return gdate.getTime();
	}

	public static java.util.Date addMonth(java.util.Date date, int month) {
		GregorianCalendar gdate = new GregorianCalendar();
		gdate.setTime(date);
		gdate.add(GregorianCalendar.MONTH, month);
		return gdate.getTime();
	}

	public static java.util.Date addDay(java.util.Date date, int day) {
		GregorianCalendar gdate = new GregorianCalendar();
		gdate.setTimeInMillis(date.getTime());
		gdate.add(GregorianCalendar.DAY_OF_MONTH, day);
		return gdate.getTime();
	}

	public static java.util.Date addSecond(java.util.Date date, int second) {
		GregorianCalendar gdate = new GregorianCalendar();
		gdate.setTimeInMillis(date.getTime());
		gdate.add(GregorianCalendar.SECOND, second);
		return gdate.getTime();
	}
	
	public static java.util.Date addMinute(java.util.Date date, int minute) {
		GregorianCalendar gdate = new GregorianCalendar();
		gdate.setTimeInMillis(date.getTime());
		gdate.add(GregorianCalendar.MINUTE, minute);
		return gdate.getTime();
	}

	/**
	 * @Title: formatDate
	 * @Description: 获取指定格式日期字符串
	 * @param @param date
	 * @param @param patter
	 * @param @return
	 * @return String
	 */
	public static String formatDate(Date date, String patter) {
		if(ObjectUtils.isEmpty(date)){
			return "";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(patter);
			return sdf.format(date);
		} catch (RuntimeException e) {
			LOGGER.error("formatDate---error:"+e.getMessage(), e);
			return "";
		}
	}

	/**
	 * @Title: formatDate
	 * @Description: 按默认格式格式化时间
	 * @param @param date
	 * @param @return
	 * @return String
	 */
	public static String formatDate(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		} catch (RuntimeException e) {
			LOGGER.error("formatDate---error:"+e.getMessage(), e);
			return "";
		}
	}

	/**
	 * 按照指定的格式来解析字符串成为时间
	 * 
	 * @param date
	 * @param patter
	 * @return
	 */
	public static Date parseDate(String strDate, String patter) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(patter);
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			LOGGER.error("parseDate---error:"+e.getMessage(), e);
			return null;
		}
		return date;
	}

	/**
	 * @Title: parseDate
	 * @Description: 按默认格式解析时间
	 * @param @param strDate
	 * @param @return
	 * @return Date
	 */
	public static Date parseDate(String strDate) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			LOGGER.error("parseDate---error:"+e.getMessage(), e);
			return null;
		}
		return date;
	}

	/**
	 * 获得现在的时间(格式:yyyy-MM-dd)
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(dateFormat.format(now));
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date getTimeDate() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return dateFormat.parse(dateFormat.format(now));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 计算两个时间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(betweenDays));
	}

	/**
	 * @Title: getTimestamp
	 * @Description: 获取1-2的时间差，单位为ms
	 * @param @param time1
	 * @param @param time2
	 * @param @return
	 * @return int
	 */
	public static int getTimestamp(Date time1, Date time2) {
		if (time1 == null || time2 == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(time1);
		long m1 = cal.getTimeInMillis();
		cal.setTime(time2);
		long m2 = cal.getTimeInMillis();
		long betweenSec = m2 - m1;
		return Integer.parseInt(String.valueOf(betweenSec));
	}

	/**
	 * @Title: getWeekTimesBE
	 * @Description: 获取一周的起止时间
	 * @param @param offset 星期偏移量，0为本周，-1为上周，1为下周，如此类推
	 * @param @return date[0]：开始时间，格式2012-03-04
	 *        00:00:00；date[1]：结束时间，格式2012-03-10 23:59:59；异常为null
	 * @return Date[]
	 */
	public static Date[] getWeekTimesBE(int offset) {
		try {
			Date[] dates = new Date[2];
			SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 得到当前日期
			Calendar cal = Calendar.getInstance();

			// 得到本周第一天日期
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			cal.add(Calendar.DATE, -dayOfWeek + 1 + (7 * offset));
			Date begin = cal.getTime();
			String weekFirstStr = f1.format(begin);
			begin = f2.parse(weekFirstStr + " 00:00:00");

			// 得到本周最后一天
			cal.add(Calendar.DATE, 6);
			Date end = cal.getTime();
			String weekLastStr = f1.format(end);
			end = f2.parse(weekLastStr + " 23:59:59");
			dates[0] = begin;
			dates[1] = end;
			return dates;
		} catch (Exception e) {
			LOGGER.error("getWeekTimesBE---error:"+e.getMessage(), e);
			return null;
		}
	}

	/**
	 * @Title: getMonthTimesBE
	 * @Description: 获取一月的起止时间
	 * @param @param offset 月份偏移量，0为本月，-1为上月，1为下月，如此类推
	 * @param @return date[0]：开始时间，格式2012-03-01
	 *        00:00:00；date[1]：结束时间，格式2012-03-31 23:59:59；异常为null
	 * @return Date[]
	 */
	public static Date[] getMonthTimesBE(int offset) {
		try {
			Date[] dates = new Date[2];
			// 得到当前日期
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, offset);

			int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			// 按你的要求设置时间
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), maxDay,
					23, 59, 59);
			Date end = cal.getTime();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0,0);
			Date begin = cal.getTime();
			dates[0] = begin;
			dates[1] = end;
			return dates;
		} catch (Exception e) {
			LOGGER.error("getMonthTimesBE---error:"+e.getMessage(), e);
			return null;
		}
	}

	/**
	 * @Title: getDayTimesBE
	 * @Description: 获取一天的开始结束时间
	 * @param @param date
	 * @param @return
	 * @return Date[]
	 */
	public static Date[] getDayTimesBE(Date date) {
		Date[] dates = new Date[2];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// 按你的要求设置时间
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal
				.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		Date end = cal.getTime();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal
				.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date begin = cal.getTime();
		dates[0] = begin;
		dates[1] = end;
		return dates;
	}

	/**
	 * @Title: isNotEmpty
	 * @Description: 判断列表是否为空
	 * @param @param list
	 * @param @return
	 * @return boolean
	 */
	public static boolean isNotEmpty(List list) {
		boolean returnBoolean = false;
		if (list != null && !list.isEmpty()) {
			returnBoolean = true;
		}

		return returnBoolean;
	}

	/**
	 * @Title: isNotEmpty
	 * @Description: 判断数组是否为空
	 * @param @param ObjectArray
	 * @param @return
	 * @return boolean
	 */
	public static boolean isNotEmpty(Object[] objectArray) {
		boolean returnBoolean = false;
		if (objectArray != null && objectArray.length > 0) {
			returnBoolean = true;
		}

		return returnBoolean;
	}

	/**
	 * @Title: isNotEmpty
	 * @Description: 判断字符串是否为空
	 * @param @param strings
	 * @param @return
	 * @return boolean
	 */
	public static boolean isNotEmpty(String... strings) {
		boolean returnBoolean = true;

		if (strings != null && strings.length > 0) {
			for (String string : strings) {
				if (string == null || "".equals(string)) {
					returnBoolean = false;
					break;
				}
			}
		} else {
			returnBoolean = false;
		}

		return returnBoolean;
	}

	/*
	 * @purpose:calculate the page count using the count and pagesize @params:
	 * int ,int @return: int
	 */
	public static int count2PageCount(int count, int pageSize) {
		int pageCount = count / pageSize;
		if (count % pageSize != 0) {
			pageCount++;
		}

		return pageCount;
	}

	/*
	 * @purpose:calculate the page count using the count and pagesize @params:
	 * int ,int @return: int
	 */
	public static int count2PageCount(long countLong, int pageSize) {
		int count = Long.valueOf(countLong).intValue();
		int pageCount = count / pageSize;
		if (count % pageSize != 0) {
			pageCount++;
		}

		return pageCount;
	}

	/*
	 * @purpose:put a List to String use the specified pattern ";" @params: List
	 * 
	 * @return: String
	 */
	public static String listToString(List list) {
		StringBuilder stringBuilder = new StringBuilder();

		if (Util.isNotEmpty(list)) {
			for (Object object : list) {
				if (object != null) {
					stringBuilder.append(object.toString());
					stringBuilder.append(";");
				}
			}
		}

		if (stringBuilder.length() == 0) {
			return null;
		} else {
			return stringBuilder.toString();
		}
	}

	/*
	 * @purpose:put a List to String use the specified pattern @params: List
	 * 
	 * @return: String
	 */
	public static String listToString(List list, String pattern) {
		StringBuilder stringBuilder = new StringBuilder();

		if (Util.isNotEmpty(list)) {
			for (Object object : list) {
				if (object != null) {
					stringBuilder.append(object.toString());
					stringBuilder.append(pattern);
				}
			}
		}

		if (stringBuilder.length() == 0) {
			return null;
		} else {
			return stringBuilder.toString();
		}
	}

	/*
	 * @purpose:put a array to a String using the specified pattern ";" @params:
	 * Object[] @return: String
	 */
	public static String array2String(Object[] objects) {
		String string = null;

		StringBuilder stringBuilder = new StringBuilder();
		if (Util.isNotEmpty(objects)) {
			for (Object object : objects) {
				if (object != null) {
					stringBuilder.append(object.toString());
					stringBuilder.append(";");
				}
			}
		}

		if (stringBuilder.length() > 0) {
			string = stringBuilder.toString();
		}

		return string;
	}

	/*
	 * @purpose:put a Object Array to List @params: Object[] @return: List
	 */
	public static List arrayToList(Object[] objects) {
		List list = null;

		if (Util.isNotEmpty(objects)) {
			list = new ArrayList();
			for (Object object : objects) {
				if (object != null) {
					list.add(object);
				}
			}
		}

		return list;
	}

	/*
	 * @purpose:put a String array to List<Integer> @params: String[] @return:
	 * List<Integer>
	 */
	public static List<Integer> stringArrayToListInteger(String[] strings) {
		List<Integer> list = null;

		try {
			if (Util.isNotEmpty(strings)) {
				list = new ArrayList<Integer>();
				for (String string : strings) {
					if (string != null) {
						list.add(Integer.parseInt(string));
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("stringArrayToListInteger---error:"+e.getMessage(), e);
			list = null;
		}

		return list;
	}

	/*
	 * @purpose:put a String to List<String> based on the specified pattern ";"
	 * 
	 * @params: String @return: List<String>
	 */
	public static List<String> stringToList(String string) {
		List<String> list = null;

		if (Util.isNotEmpty(string)) {
			String[] stringArray = string.split(";");
			list = arrayToList(stringArray);
		}

		return list;
	}
	
	/*
	 * @purpose:put a String to List<Integer> based on the specified pattern ";"
	 * 
	 * @params: String @return: List<Integer>
	 */
	public static List<Integer> stringToIntegerList(String string) {
		List<Integer> list = null;

		if (Util.isNotEmpty(string)) {
			String[] stringArray = string.split(",");
			list = arrayToList(stringArray);
		}

		return list;
	}

	// 解析输入流成byte数组
	public static byte[] recvMsg(InputStream inputstream, int length) {
		try {
			byte content[] = new byte[length];
			int readCount = 0; // 已经成功读取的字节的个数
			while (readCount < length) {
				int size = (length - readCount) > 1024 ? 1024
						: (length - readCount);
				readCount += inputstream.read(content, readCount, size);
			}
			return content;
		} catch (Exception e) {
			LOGGER.error("recvMsg---error:"+e.getMessage(), e);
			return null;
		}
	}

	/**
	 * @Title: isNumeric
	 * @Description: 判断字符串是否为数字
	 * @Param @param str
	 * @Param @return
	 * @return boolean
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断手机号是否合法
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		if (null == phone || "".equals(phone)) {
			return false;
		}
		String regExp = "^1[3,5,8]{1}[0-9]{1}[0-9]{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(phone);
		return m.find();
	}
	
	/**
	 * 判断邮箱号是否合法
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email)) {
			return false;
		}
		String regExp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(email);
		return m.find();
	}

	/**
	 * @Title: parseListToString
	 * @Description: 列表转换为字符串
	 * @param @param list 要转换的对象
	 * @param @param split 分隔符
	 * @param @return
	 * @return String
	 */
	public static String parseListToString(List list, String split) {
		if (list != null && !list.isEmpty()) {
			StringBuilder buffer = new StringBuilder("");
			int len = list.size();
			for (int i = 0; i < len; i++) {
				if (i != (len - 1)) {
					buffer.append(list.get(i));
					buffer.append(split);
				} else {
					buffer.append(list.get(i));
				}
			}
			return buffer.toString();
		}
		return null;
	}

	/**
	 * @Title: pasreArrayToString
	 * @Description: 数组转字符串
	 * @param @param arr
	 * @param @param split
	 * @param @return
	 * @return String
	 */
	public static String pasreArrayToString(Object[] arr, String split) {
		if (arr != null && arr.length > 0) {
			StringBuilder buffer = new StringBuilder("");
			for (int i = 0; i < arr.length; i++) {
				if (i != (arr.length - 1)) {
					buffer.append(arr[i]);
					buffer.append(split);
				} else {
					buffer.append(arr[i]);
				}
			}
			return buffer.toString();
		}
		return null;
	}

	/**
	 * @Title: getFileSuffix
	 * @Description: 获取文件后缀，返回如：.jpg
	 * @param @param name
	 * @param @return
	 * @return String
	 */
	public static String getFileSuffix(String name) {
		int loc = name.lastIndexOf('.');
		if (loc != -1) {
			return name.substring(loc);
		}
		return null;
	}

	/**
	 * @Title: getTimeFileName
	 * @Description: 获取默认的以是时间命名的文件名
	 * @param @return
	 * @return String
	 */
	public static String getTimeFileName(String suffix) {
		return formatDate(new Date(), "yyyyMMddHHmmssSSS") + suffix;
	}

	/**
	 * @Title: getAgeByBirthday
	 * @Description: 计算年龄
	 * @param @param birthday
	 * @param @return
	 * @return String
	 */
	public static String getAgeByBirthday(Date birthday) {
		int days = daysBetween(birthday, new Date());
		int year = days / 365;
		int month = (days % 365) / 30;
		String age = "";
		if (year > 0) {
			age = year + "岁";
			if (month > 0) {
				age = age + month + "个月";
			}
		} else if (month > 0) {
			age = month + "个月";
		}
		return age;
	}

	/**
	 * @Title: deleteFile
	 * @Description: 删除文件或文件夹
	 * @param @param realPath
	 * @return void
	 */
	public static void deleteFile(String realPath) {
		File file = new File(realPath);
		if (file.isFile() && file.exists()) {
			if(!file.delete()){
				LOGGER.error("删除文件失败");
			}
		} else {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					
					if(!files[i].delete()){
						LOGGER.error("删除文件失败");
						break;
					}
				}
			}
			if(!file.delete()){
				LOGGER.error("删除文件失败");
			}
		}
	}

	/**
	 * @Title: getFileSize
	 * @Description: 根据路径获取文件大小
	 * @param @param path
	 * @param @return
	 * @return long
	 */
	public static long getFileSize(String path) {
		File file = new File(path);
		if (file.isFile() && file.exists()) {
			return file.length();
		}
		return 0;
	}

	/**
	 * @Title: makeDir
	 * @Description: 创建目录，如果存在则不创建
	 * @param @param path
	 * @return void
	 */
	public static boolean makeDir(String path) {
		return new File(path).mkdirs();
	}
	
	
	public static byte[] getImageToBytes(String imgPath) {  
	    byte[] bytes = null;  
	    ByteArrayOutputStream out = new ByteArrayOutputStream();  
	  
	    try {  
	        //创建URL  
	        URL url = new URL(imgPath);  
	        //得到连接  
	        HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();  
	        //得到连接地址的输入流  
	        InputStream in = urlConn.getInputStream();  
	  
	        int size;  
	        //缓冲值  
	        bytes = new byte[1024];  
	        if(in != null){  
	            //循环读输入流至read返回-1为止，并写到缓存中  
	            while((size=in.read(bytes)) != -1){  
	                out.write(bytes, 0, size);  
	            }  
	        }  
	        out.close();//关闭输出流  
	        in.close();//关闭输入流  
	        urlConn.disconnect();//断开连接  
	  
	        } catch (Exception e) {  
	            LOGGER.error("getImageToBytes---error:"+e.getMessage(), e);  
	        }  
	  
	        return out.toByteArray();  
	}
	
	public static void bytesToImgSave(byte[] bytes, String imgFile) {  
	    //UUID序列号作为保存图片的名称  
	    
	    File f = new File(imgFile);  
	  
	    try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
			for (int i = 0; i < bytes.length; i++){
				out.write(bytes[i]);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("bytesToImgSave---error:"+e.getMessage(), e);
		}
	}
	
	public static String getServerPath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/");
    }

	/**
	 * @Title: getDateTreeDir
	 * @Description: 组装根据年/月/日格式的文件目录 如：2012/04/20/
	 * @param @param date
	 * @param @return
	 * @return String
	 */
	public static String getDateTreeDir(Date date) {
		return Util.formatDate(date, "yyyy") + "/"
				+ Util.formatDate(date, "MM") + "/"
				+ Util.formatDate(date, "dd") + "/";
	}
	
	/***
	 * 
	* @Title: saveUploadExcelFile 
	* @Description: 将excel文件写入到本地 
	* @param @param fileName
	* @param @param xlsFile
	* @param @return  
	* @return boolean
	 * @throws IOException 
	 */
	public static boolean saveFile(String fileName,byte[] fileBytes) throws IOException{
		boolean result = false;
		FileOutputStream fops = null;
		try {			
			File file = new File(fileName);
			if (!file.getParentFile().exists() 
					&& !file.getParentFile().mkdirs()) {
					LOGGER.error("saveFile--create file error.");
			}
			fops = new FileOutputStream(file);
			fops.write(fileBytes);
			fops.flush();
			fops.close();
			result = true;
			
		} catch (Exception e) {
			LOGGER.error("saveFile---error:"+e.getMessage(), e);
		}finally{
			if(fops!=null){
				fops.close();
			}
		}
		return result;
	}
}
