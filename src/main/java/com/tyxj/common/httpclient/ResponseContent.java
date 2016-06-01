package com.tyxj.common.httpclient;

import java.io.UnsupportedEncodingException;

/**
 * 封装HttpClient返回数据
 * <p>
 * @author   yangjian1004
 * @Date     Aug 5, 2014     
 */
public class ResponseContent {
    private String encoding;
 
    private byte[] contentBytes;
 
    private int statusCode;
 
    private String contentType;
 
    private String contentTypeString;
 
    public String getEncoding() {
        return encoding;
    }
 
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
 
    public String getContentType() {
        return this.contentType;
    }
 
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
 
    public String getContentTypeString() {
        return this.contentTypeString;
    }
 
    public void setContentTypeString(String contenttypeString) {
        this.contentTypeString = contenttypeString;
    }
 
    public String getContent() throws UnsupportedEncodingException {
        return this.getContent(this.encoding);
    }
 
    @SuppressWarnings("hiding")
    public String getContent(String encoding) throws UnsupportedEncodingException {
        if (encoding == null) {
            return new String(contentBytes, "UTF-8");
        }
        return new String(contentBytes, encoding);
    }
 
    public String getUTFContent() throws UnsupportedEncodingException {
        return this.getContent("UTF-8");
    }
 
    public byte[] getContentBytes() {
        return contentBytes;
    }
 
    public void setContentBytes(byte[] contentBytes) {
        this.contentBytes = contentBytes;
    }
 
    public int getStatusCode() {
        return statusCode;
    }
 
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
 
}
