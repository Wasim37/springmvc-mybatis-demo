package com.tyxj.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/** 
 * @ClassName: XssHttpServletRequestWrapper 
 * @Description: 
 * @author Issac
 * @date 2014-6-12 下午02:19:14 
 *  
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	
	public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }
	
    public String[] getParameterValues(String parameter) {
      String[] values = super.getParameterValues(parameter);
      if (values==null)  {
                  return null;
          }
      int count = values.length;
      String[] encodedValues = new String[count];
      for (int i = 0; i < count; i++) {
                 encodedValues[i] = cleanXSS(values[i]);
       }
      return encodedValues;
    }
    
    public String getParameter(String parameter) {
          String value = super.getParameter(parameter);
          if (value == null) {
                 return null;
                  }
          return cleanXSS(value);
    }
    
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
        	 return null;
        }
        return cleanXSS(value);
    }
    
    private String cleanXSS(String valueStr) {
        //You'll need to remove the spaces from the html entities below
    	String value = valueStr;
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }
    
    
}
