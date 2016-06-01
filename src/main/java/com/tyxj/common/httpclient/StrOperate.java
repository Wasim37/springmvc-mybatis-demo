package com.tyxj.common.httpclient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: StrOperate 
 * @Description: 字符串处理相关的工具类
 * @author 
 * @date 2014年8月20日 下午6:15:19 
 *  
 */

public class StrOperate {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StrOperate.class);

	 // 禁止调用构造方法
    private StrOperate() {
    }
    
    /**
     * 检查字符串是否存在值
     * 
     * @param str 待检验的字符串
     * @return 当 str 不为 null 或 "" 就返回 true
     */
    public static boolean hasValue(String str) {
        return str != null && !"".equals(str);
    }

    /**
     * 对参数进行UTF-8编码，并替换特殊字符
     * 
     * @param paramDecString 待编码的参数字符串
     * @return 完成编码转换的字符串
     */
    public static String paramEncode(String paramDecString) {
        if (!hasValue(paramDecString)) {
            return "";
        }
        try {
            return URLEncoder.encode(paramDecString, "UTF-8").replace("+", "%20")
                    .replace("*", "%2A").replace("%7E", "~")
                    .replace("#", "%23");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 将 %XX 换为原符号，并进行UTF-8反编码
     * 
     * @param paramEncString 待反编码的参数字符串
     * @return 未进行UTF-8编码和字符替换的字符串
     */
    public static String paramDecode(String paramEncString) {
        int nCount = 0;
        for (int i = 0; i < paramEncString.length(); i++) {
            if (paramEncString.charAt(i) == '%') {
                i += 2;
            }
            nCount++;
        }

        byte[] sb = new byte[nCount];

        for (int i = 0, index = 0; i < paramEncString.length(); i++) {
            if (paramEncString.charAt(i) != '%') {
                sb[index++] = (byte) paramEncString.charAt(i);
            } else {
                StringBuilder sChar = new StringBuilder();
                sChar.append(paramEncString.charAt(i + 1));
                sChar.append(paramEncString.charAt(i + 2));
                sb[index++] = Integer.valueOf(sChar.toString(), 16).byteValue();
                i += 2;
            }
        }
        String decode = "";
        try {
            decode = new String(sb, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        	LOGGER.info(e.getMessage(), e);
        }
        return decode;
    }

    /**
     * 分割queryString，取得List&#60NameValuePair&#62格式存储的参数队列.
     * 
     * @param queryString 查询字符串
     * @return 以List&#60NameValuePair&#62格式存储的参数队列.
     */
    public static List<NameValuePair> getQueryParamsList(String queryString) {
    	String queryStr = queryString;
        if (queryStr.startsWith("?")) {
            queryStr = queryStr.substring(1);
        }

        List<NameValuePair> result = new ArrayList<NameValuePair>();

        if (queryStr != null && !"".equals(queryStr)) {
            String[] p = queryStr.split("&");
            for (String s : p) {
                if (s != null && !"".equals(s)
                		&& s.indexOf('=') > -1) {
                    String[] temp = s.split("=");
                    if (temp.length > 1) {
                        result.add(new BasicNameValuePair(temp[0], paramDecode(temp[1])));
                    }
                }
            }
        }
        return result;
    }  
    
    /**
     * 根据List&#60NameValuePair&#62格式存储的参数队列，生成queryString
     * @param queryParamsList
     * @return 不包括？的queryString
     */
    public static String getQueryString(List<NameValuePair> queryParamsList){
        StringBuilder queryString=new StringBuilder();
        for(NameValuePair param:queryParamsList){
            queryString.append('&');
            queryString.append(param.getName());
            queryString.append('=');
            queryString.append(paramEncode(param.getValue()));
        }
        //去掉第一个&号
        return queryString.toString().substring(1);
    }
    
    /**
     * 根据 timestamp 生成各类时间状态串
     * 
     * @param timestamp 距1970 00:00:00 GMT的秒数
     * @return 时间状态串(如：刚刚5分钟前)
     */
    public static String getTimeState(String timestamp) {
        if (timestamp == null || "".equals(timestamp)) {
            return "";
        }

        try {
            long timestampNew = Long.parseLong(timestamp) * 1000;
            if (System.currentTimeMillis() - timestampNew < 1 * 60 * 1000) {
                return "刚刚";
            } else if (System.currentTimeMillis() - timestampNew < 30 * 60 * 1000) {
                return ((System.currentTimeMillis() - timestampNew) / 1000 / 60)
                        + "分钟前";
            } else {
                Calendar now = Calendar.getInstance();
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(timestampNew);
                if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                        && c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                        && c.get(Calendar.DATE) == now.get(Calendar.DATE)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("今天 HH:mm");
                    return sdf.format(c.getTime());
                }
                if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                        && c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                        && c.get(Calendar.DATE) == now.get(Calendar.DATE) - 1) {
                    SimpleDateFormat sdf = new SimpleDateFormat("昨天 HH:mm");
                    return sdf.format(c.getTime());
                } else if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("M月d日 HH:mm:ss");
                    return sdf.format(c.getTime());
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat(
                            "yyyy年M月d日 HH:mm:ss");
                    return sdf.format(c.getTime());
                }
            }
        } catch (Exception e) {
        	LOGGER.info(e.getMessage(), e);
            return "";
        }
    }
}
