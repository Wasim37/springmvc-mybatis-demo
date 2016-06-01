package com.tyxj.common.httpclient;

import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: BHttpClient 
 * @Description: 
 * @author 
 * @date 2014年8月20日 下午6:19:09 
 *  
 */

/**
 * 自定义参数的Httpclient。<br>
 * 提供httpGet，httpPost两种传送消息的方式<br>
 * 提供httpPost上传文件的方式
 */
public class BHttpClient {

	// SDK默认参数设置
    public static final int CONNECTION_TIMEOUT = 50000;
    public static final int CON_TIME_OUT_MS = 50000;
    public static final int SO_TIME_OUT_MS = 50000;
    public static final int MAX_CONNECTIONS_PER_HOST = 20;
    public static final int MAX_TOTAL_CONNECTIONS = 200;

    // 日志输出
    private static final Logger LOGGER = LoggerFactory.getLogger(BHttpClient.class);

    private HttpClient httpClient;

    public BHttpClient() {
        this(MAX_CONNECTIONS_PER_HOST, MAX_TOTAL_CONNECTIONS, CON_TIME_OUT_MS, SO_TIME_OUT_MS, null, null);
    }

    /**
     * 个性化配置连接管理器
     * 
     * @param maxConnectionsPerHost
     *            设置默认的连接到每个主机的最大连接数
     * @param maxTotalConnections
     *            设置整个管理连接器的最大连接数
     * @param conTimeOutMs
     *            连接超时
     * @param soTimeOutMs
     *            socket超时
     * @param routeCfgList
     *            特殊路由配置列表，若无请填null
     * @param proxy
     *            代理设置，若无请填null
     */
    @SuppressWarnings("deprecation")
	public BHttpClient(int maxConnectionsPerHost, int maxTotalConnections, int conTimeOutMs, int soTimeOutMs, List<RouteCfg> routeCfgList, HttpHost proxy) {

        // 使用默认的 socket factories 注册 "http" & "https" protocol scheme
        SchemeRegistry supportedSchemes = new SchemeRegistry();
        supportedSchemes.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        supportedSchemes.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
        ThreadSafeClientConnManager connectionManager = new ThreadSafeClientConnManager(supportedSchemes);

        // 参数设置
        HttpParams httpParams = new SyncBasicHttpParams();
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);

        httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, conTimeOutMs);
        httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeOutMs);
        // 与之前两行作用相同

        HttpProtocolParams.setUseExpectContinue(httpParams, false);

        connectionManager.setDefaultMaxPerRoute(maxConnectionsPerHost);
        connectionManager.setMaxTotal(maxTotalConnections);

        HttpClientParams.setCookiePolicy(httpParams, CookiePolicy.IGNORE_COOKIES);

        // 对特定路由修改最大连接数
        if (null != routeCfgList) {
            for (RouteCfg routeCfg : routeCfgList) {
                HttpHost localhost = new HttpHost(routeCfg.getHost(), routeCfg.getPort());
                connectionManager.setMaxForRoute(new HttpRoute(localhost), routeCfg.getMaxConnetions());
            }
        }

        httpClient = HttpClientWrapper.wrapClient(new DefaultHttpClient(connectionManager, httpParams));

        // 设置代理
        if (null != proxy) {
            httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }
    }

    /**
     * Get方法传送消息
     * 
     * @param url
     *            连接的URL
     * @param queryString
     *            请求参数串
     * @return 服务器返回的信息
     * @throws Exception
     */
    public String httpGet(String url, String queryString) throws Exception {

    	String urlStr = url;
        String responseData = null;
        if (queryString != null && !"".equals(queryString)) {
            urlStr += "?" + queryString;
        }
        LOGGER.info("BHttpClient httpGet [1] url = " + urlStr);

        HttpGet httpGet = new HttpGet(urlStr);
        httpGet.getParams().setParameter("http.socket.timeout", new Integer(CONNECTION_TIMEOUT));

        HttpResponse response;
        response = httpClient.execute(httpGet);

        try {
        	LOGGER.info("BHttpClient httpGet [2] StatusLine : " + response.getStatusLine());
            responseData = EntityUtils.toString(response.getEntity());
            LOGGER.info("BHttpClient httpGet [3] Response = " + responseData);
        } catch (Exception e) {
        	LOGGER.info(e.getMessage(), e);
        } finally {
            httpGet.abort();
        }

        return responseData;
    }

    /**
     * Post方法传送消息
     * 
     * @param url
     *            连接的URL
     * @param queryString
     *            请求参数串
     * @return 服务器返回的信息
     * @throws Exception
     */
    public String httpPost(String url, String queryString) throws Exception {
        String responseData = null;
        URI tmpUri = new URI(url);
        LOGGER.info("BHttpClient httpPost [1] url = " + tmpUri.toURL());

        HttpPost httpPost = new HttpPost(tmpUri);
        httpPost.getParams().setParameter("http.socket.timeout", new Integer(CONNECTION_TIMEOUT));
        if (queryString != null && !"".equals(queryString)) {
        	LOGGER.info("QueryString = " + queryString);
            StringEntity reqEntity = new StringEntity(queryString,HTTP.UTF_8);
            // 设置类型
            reqEntity.setContentType("application/x-www-form-urlencoded");
            // 设置请求的数据
            httpPost.setEntity(reqEntity);
        }

        try {
            HttpResponse response = httpClient.execute(httpPost);
            LOGGER.info("BHttpClient httpPost [2] StatusLine = " + response.getStatusLine());
            responseData = EntityUtils.toString(response.getEntity());
            LOGGER.info("BHttpClient httpPost [3] responseData = " + responseData);
        } catch (Exception e) {
        	LOGGER.info(e.getMessage(), e);
        	responseData += "------exception:"+e.getMessage();
        } finally {
            httpPost.abort();
        }

        return responseData;
    }
    
    /**
     * Post方法传送消息
     * 
     * @param url
     *            连接的URL
     * @param queryString
     *            请求参数串
     * @return 服务器返回的信息
     * @throws Exception
     */
    public String httpPost(String url, String queryString,String headerCookie) throws Exception {
        String responseData = null;
        URI tmpUri = new URI(url);
        LOGGER.info("BHttpClient httpPost [1] url = " + tmpUri.toURL());

        HttpPost httpPost = new HttpPost(tmpUri);
        httpPost.getParams().setParameter("http.socket.timeout", new Integer(CONNECTION_TIMEOUT));
        
        httpPost.setHeader("cookie", headerCookie);
        
        if (queryString != null && !"".equals(queryString)) {
        	LOGGER.info("QueryString = " + queryString);
            StringEntity reqEntity = new StringEntity(queryString,HTTP.UTF_8);
            // 设置类型
            reqEntity.setContentType("application/x-www-form-urlencoded");
            // 设置请求的数据
            httpPost.setEntity(reqEntity);
        }

        try {
            HttpResponse response = httpClient.execute(httpPost);
            LOGGER.info("BHttpClient httpPost [2] StatusLine = " + response.getStatusLine());
            responseData = EntityUtils.toString(response.getEntity());
            LOGGER.info("BHttpClient httpPost [3] responseData = " + responseData);
        } catch (Exception e) {
        	LOGGER.info(e.getMessage(), e);
        	responseData += "------exception:"+e.getMessage();
        } finally {
            httpPost.abort();
        }

        return responseData;
    }
    
    /**
     * Post方法传送消息
     * 
     * @param url
     *            连接的URL
     * @param queryString
     *            请求参数串
     * @return 服务器返回的信息
     * @throws Exception
     */
    public String httpPost(String url, String queryString,List<NameValuePair> nameValuePairList) throws Exception {
        String responseData = null;
        URI tmpUri = new URI(url);
        LOGGER.info("BHttpClient httpPost [1] url = " + tmpUri.toURL());

        HttpPost httpPost = new HttpPost(tmpUri);
        httpPost.getParams().setParameter("http.socket.timeout", new Integer(CONNECTION_TIMEOUT));
        
        if (queryString != null && !"".equals(queryString)) {
        	
        	UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList,HTTP.UTF_8);
        	entity.setChunked(false);
            // 设置请求的数据
            httpPost.setEntity(entity);
        }

        try {
            HttpResponse response = httpClient.execute(httpPost);
            LOGGER.info("BHttpClient httpPost [2] StatusLine = " + response.getStatusLine());
            responseData = EntityUtils.toString(response.getEntity());
            LOGGER.info("BHttpClient httpPost [3] responseData = " + responseData);
        } catch (Exception e) {
        	LOGGER.info(e.getMessage(), e);
        } finally {
            httpPost.abort();
        }

        return responseData;
    }
    
    

    /**
     * Post方法传送消息
     * 
     * @param url
     *            连接的URL
     * @param queryString
     *            请求参数串
     * @return 服务器返回的信息
     * @throws Exception
     */
    public String httpPostWithFile(String url, String queryString, List<NameValuePair> files) throws Exception {

        String responseData = null;

        URI uri = new URI(url);
        LOGGER.info("BHttpClient httpPostWithFile [1]  uri = " + uri.toURL());
        MultipartEntity mpEntity = new MultipartEntity();
        HttpPost httpPost = new HttpPost(uri);
        StringBody stringBody;
        FileBody fileBody;
        File targetFile;
        String filePath;
        FormBodyPart fbp;

        List<NameValuePair> queryParamList = StrOperate.getQueryParamsList(queryString);
        for (NameValuePair queryParam : queryParamList) {
            stringBody = new StringBody(queryParam.getValue(), Charset.forName("UTF-8"));
            fbp = new FormBodyPart(queryParam.getName(), stringBody);
            mpEntity.addPart(fbp);
        }

        for (NameValuePair param : files) {
            filePath = param.getValue();
            targetFile = new File(filePath);
            LOGGER.info("---------- File Path = " + filePath + "\n---------------- MIME Types = " + HttpUtil.getContentType(targetFile));
            fileBody = new FileBody(targetFile, HttpUtil.getContentType(targetFile), "UTF-8");
            fbp = new FormBodyPart(param.getName(), fileBody);
            mpEntity.addPart(fbp);

        }
        
        httpPost.setEntity(mpEntity);

        try {
            HttpResponse response = httpClient.execute(httpPost);
            LOGGER.info("BHttpClient httpPostWithFile [2] StatusLine = " + response.getStatusLine());
            responseData = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
        	LOGGER.info(e.getMessage(), e);
        } finally {
            httpPost.abort();
        }
        LOGGER.info("BHttpClient httpPostWithFile [3] responseData = " + responseData);
        return responseData;
    }

    /**
     * 断开QHttpClient的连接
     */
    public void shutdownConnection() {
        try {
            httpClient.getConnectionManager().shutdown();
        } catch (Exception e) {
        	LOGGER.info(e.getMessage(), e);
        }
    }
}
