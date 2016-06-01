package com.tyxj.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: noah
 * Date: 9/16/13
 * Time: 10:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	
    private static final String ENCRYPTED_PREFIX = "Encrypted:{";
    private static final String ENCRYPTED_SUFFIX = "}";
    
    //加密属性特征正则
    private static Pattern encryptedPattern = Pattern.compile("Encrypted:\\{((\\w|\\-)*)\\}");  

    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptPropertyPlaceholderConfigurer.class);

    private static Set<String> encryptedProps = Collections.emptySet();

    public void setEncryptedProps(Set<String> encryptedProps) {
        this.encryptedProps = encryptedProps;
    }

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
    	String vPropertyValue = propertyValue;
    	//如果在加密属性名单中发现该属性
        if (encryptedProps.contains(propertyName)) {
        	//判断该属性是否已经加密
            final Matcher matcher = encryptedPattern.matcher(vPropertyValue); 
            //已经加密，进行解密
            if (matcher.matches()) {  
            	//获得加密值
                String encryptedString = matcher.group(1);
                //调用AES进行解密，SEC_KEY与属性名联合做密钥更安全
                String decryptedPropValue = AesUtils.decrypt(encryptedString);  

                //!=null说明正常
                if (ObjectUtils.isNotEmpty(decryptedPropValue)) {  
                	//设置解决后的值
                	vPropertyValue = decryptedPropValue; 
                } else {
                	//说明解密失败
                    LOGGER.error("Decrypt " + propertyName + "=" + vPropertyValue + " error!");
                }
            }
        }
        //将处理过的值传给父类继续处理
        return super.convertProperty(propertyName, vPropertyValue);  
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    	
    	//正常执行属性文件加载
        super.postProcessBeanFactory(beanFactory);    

        //加载完后，遍历location，对properties进行加密
        for (Resource location : locations) {   
            try {
                final File file = location.getFile();
                //如果是一个普通文件
                if (file.isFile()) {  

                	//如果有写权限调用文件加密方法
                    if (file.canWrite()) { 
                        encrypt(file);
                    } else {
                        if (LOGGER.isWarnEnabled()) {
                            LOGGER.warn("File '" + location + "' can not be write!");
                        }
                    }

                } else {
                    if (LOGGER.isWarnEnabled()) {
                        LOGGER.warn("File '" + location + "' is not a normal file!");
                    }
                }

            } catch (IOException e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("File '" + location + "' is not a normal file!",e);
                }
            }
        }

    }

    private static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNotBlank(String str) {
        return !isBlank(str);
    }


    /**
     * 属性文件加密方法
     *
     * @param file
     */
    private static void encrypt(File file) {
    	//定义输出行缓存
        List<String> outputLine = new ArrayList<String>(); 
        //是否加密属性文件标识
        boolean doEncrypt = false;     


        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String line = null;
            do {
            	//按行读取属性文件
                line = bufferedReader.readLine(); 
                
                //判断是否文件结束
                if (line != null) {
                    if (isNotBlank(line)) {
                        line = line.trim();
                        //如果是非注释行
                        if (!line.startsWith("#")) {
                        	//将属性名与值分离（修正1）
                            int eIndex = line.indexOf("=");  
                            String key = line.substring(0,eIndex);       
                            String value = line.substring(eIndex+1);      
                            if (key != null && value != null && encryptedProps.contains(key)) {
                                //发现是加密属性
                                    final Matcher matcher = encryptedPattern.matcher(value);
                                    //如果是非加密格式，则`进行加密
                                    if (!matcher.matches()) { 
                                    	//进行加密，SEC_KEY与属性名联合做密钥更安全
                                        value = ENCRYPTED_PREFIX + AesUtils.encrypt(value) + ENCRYPTED_SUFFIX;   
                                        //生成新一行的加密串
                                        line = key + "=" + value;  
                                        //设置加密属性文件标识
                                        doEncrypt = true;    

                                        if (LOGGER.isDebugEnabled()) {
                                            LOGGER.debug("encrypt property:" + key);
                                        }
                                    }
                            }
                        }
                    }
                    outputLine.add(line);
                }

            } while (line != null);


        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }

        //判断属性文件加密标识
        if (doEncrypt) {     
            BufferedWriter bufferedWriter = null;
            File tmpFile = null;
            try {
            	//创建临时文件
                tmpFile = File.createTempFile(file.getName(), null, file.getParentFile());   

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Create tmp file '" + tmpFile.getAbsolutePath() + "'.");
                }

                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tmpFile),StandardCharsets.UTF_8));

                final Iterator<String> iterator = outputLine.iterator();
                
                //将加密后内容写入临时文件
                while (iterator.hasNext()) {                           
                    bufferedWriter.write(iterator.next());
                    if (iterator.hasNext()) {
                        bufferedWriter.newLine();
                    }
                }

                bufferedWriter.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }

            File backupFile = new File(file.getAbsoluteFile() + "_" + System.currentTimeMillis());  //准备备份文件名

            //以下为备份，异常恢复机制
            //重命名原properties文件，（备份）
            if (!file.renameTo(backupFile)) {   
                LOGGER.error("Could not encrypt the file '" + file.getAbsoluteFile() + "'! Backup the file failed!");
                if(tmpFile != null){
                	//删除临时文件
                	 boolean isSuccess = tmpFile.delete(); 
                	 if(!isSuccess){
            			 LOGGER.info("EncryptPropertyPlaceholderConfigurer---encrypt---deleteTmpFile---fail!!!");
            		 }
                }
               
            } else {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Backup the file '" + backupFile.getAbsolutePath() + "'.");
                }
                //临时文件重命名失败 （加密文件替换原失败）
                if (!tmpFile.renameTo(file)) {   
                    LOGGER.error("Could not encrypt the file '" + file.getAbsoluteFile() + "'! Rename the tmp file failed!");
                    //恢复备份
                    if (backupFile.renameTo(file)) {   
                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info("Restore the backup, success.");
                        }
                    } else {
                        LOGGER.error("Restore the backup, failed!");
                    }
                } else {  
                	//（加密文件替换原成功）
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Rename the file '" + tmpFile.getAbsolutePath() + "' -> '" + file.getAbsoluteFile() + "'.");
                    }
                    //删除备份文件
                    boolean dBackup = backupFile.delete();

                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Delete the backup '" + backupFile.getAbsolutePath() + "'.(" + dBackup + ")");
                    }
                }
            }


        }

    }

    protected Resource[] locations;

    @Override
    public void setLocations(Resource[] locations) {   
    	//由于location是父类私有，所以需要记录到本类的locations中
        super.setLocations(locations);
        this.locations = locations;
    }

    @Override
    public void setLocation(Resource location) {   
    	//由于location是父类私有，所以需要记录到本类的locations中
        super.setLocation(location);
        this.locations = new Resource[]{location};
    }
    public static void main(String[] args) {
    	encrypt(new File("E:\\init.properties"));
	}
    
    
}