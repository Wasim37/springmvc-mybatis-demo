package com.tyxj.common.httpclient;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;

/** 
 * @ClassName: HttpUtil 
 * @Description:  完成Http通讯前各种预处理操作的工具类
 * @author 
 * @date 2014年8月20日 下午6:17:32 
 *  
 */

public class HttpUtil {

	 /**
     * <p>从带文件名的路径中获取文件对应的MIME type.</p>
     * @param fileName
     *            带文件名的文件路径.
     * @return MIME type.
     */
    public static String getContentType(String fileName) {
        return new MimetypesFileTypeMap().getContentType(fileName);
    }

    /**
     * 根据File对象得到其对应MIME typee.
     * @param file
     *            File对象
     * @return MIME type.
     */
    public static String getContentType(File file) {
        return new MimetypesFileTypeMap().getContentType(file);
    }
}
