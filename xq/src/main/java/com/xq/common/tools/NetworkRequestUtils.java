package com.xq.common.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;


import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import com.xq.common.enums.HttpMethod;
import com.xq.common.enums.URLAddressEnum;
import com.xq.common.system.BreakerCommand;
import com.xq.common.system.HystrixInfo;
import com.xq.common.system.HystrixPoolEnum;

import net.sf.json.JSONObject;

/**
 * @Description: 网络请求工具类
 * 依赖jar包：httpclient-4.5.jar（低于4.3不行）；httpcore-4.4.1.jar
 */
public class NetworkRequestUtils {
	public static void main(String[] args) {
		/*System.out.println(doGet("https://api.woshipm.com/author/recommendList4PC.html"));
*/			
	}
	
	public static JSONObject doGet(String url,List <NameValuePair> params) {
		CloseableHttpClient httpClient = getCloseableHttpClient(url);
		CloseableHttpResponse response = null;
		try {
			HttpGet httpget = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom()  
			        .setConnectTimeout(20000).setConnectionRequestTimeout(20000)  
			        .setSocketTimeout(20000).build();  
			httpget.setConfig(requestConfig);  
			try {
				if(params!=null){
					((HttpResponse) httpget).setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
				}				
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return getJsonObject(EntityUtils.toString(entity, "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new JSONObject();
	}
	public static InputStream doGetReturnInputstream(String url) {
		CloseableHttpClient httpClient = getCloseableHttpClient(url);
		CloseableHttpResponse response = null;
		try {
			HttpGet httpget = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom()  
			        .setConnectTimeout(20000).setConnectionRequestTimeout(20000)  
			        .setSocketTimeout(20000).build();  
			httpget.setConfig(requestConfig);  
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return entity.getContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * @Description: 执行post请求
	 */
	public static JSONObject doPost(String url, List <NameValuePair> params) {
		CloseableHttpClient httpClient = getCloseableHttpClient(url);
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()  
			        .setConnectTimeout(20000).setConnectionRequestTimeout(20000)  
			        .setSocketTimeout(20000).build();  
			httpPost.setConfig(requestConfig);  
			try {
				if(params!=null){
					httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
				}				
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return getJsonObject(EntityUtils.toString(entity, "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new JSONObject();
	}
	
	private static JSONObject getJsonObject(String result) {
		if (StringUtils.isNotBlank(result)) {
			try {
				return JSONObject.fromObject(result);
			} catch (Exception e) {
				System.err.println(result);
				e.printStackTrace();
			}
		}
		return new JSONObject();
	}

	public static JSONObject httpConnect(URLAddressEnum urlEnum,List <NameValuePair> params,HttpMethod httpMethod){
		HystrixInfo hystrixInfo=new HystrixInfo(urlEnum, urlEnum.getType().getName(), HystrixPoolEnum.http.name());
		hystrixInfo.setParams(params);
		hystrixInfo.setHttpMethod(httpMethod);
		BreakerCommand command = new BreakerCommand(hystrixInfo);
		return command.execute();
	}
	private static CloseableHttpClient getCloseableHttpClient(String url) {
		if (url.contains("api.weixin.qq.com")) {
			return HttpClients.createDefault();
		}
		SSLContext sslContext = null;
		try {
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(final X509Certificate[] arg0, final String arg1) throws CertificateException {
					return true;
				}
			}).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslSocketFactory).build();
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setSslcontext(sslContext).setConnectionManager(connMgr);
		return httpClientBuilder.build();
	}
}
