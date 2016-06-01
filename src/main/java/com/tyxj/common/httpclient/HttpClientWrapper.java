package com.tyxj.common.httpclient;

import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** 
 * @ClassName: HttpClientWrapper 
 * @Description: 
 * @author 
 * @date 2014年8月20日 下午6:20:37 
 *  
 */

public class HttpClientWrapper {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientWrapper.class);
	
	 public static DefaultHttpClient wrapClient(DefaultHttpClient base) {
	        try {
	            SSLContext ctx = SSLContext.getInstance("TLS");
	            X509TrustManager tm = new X509TrustManager() {
	                public void checkClientTrusted(X509Certificate[] xcs, String string) {
	                	return;
	                }

	                public void checkServerTrusted(X509Certificate[] xcs, String string) {
	                	return;
	                }

	                public X509Certificate[] getAcceptedIssuers() {
	                    return null;
	                }

	            };
	            ctx.init(null, new TrustManager[] { tm }, null);
	            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
	            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	            ClientConnectionManager ccm = base.getConnectionManager();
	            SchemeRegistry sr = ccm.getSchemeRegistry();
	            sr.register(new Scheme("https", ssf, 443));
	            return new DefaultHttpClient(ccm, base.getParams());
	        } catch (Exception e) {
	        	LOGGER.info(e.getMessage(), e);
	            return null;
	        }

	    }
}
