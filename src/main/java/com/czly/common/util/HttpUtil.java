package com.czly.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.NoRouteToHostException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
	private static Logger logger = LogManager.getLogger(HttpUtil.class);
	public static String get(String url) throws Exception{
		HttpGet get = new HttpGet(url);
		//设置5秒的超时
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();//设置请求和传输超时时间
		get.setConfig(requestConfig);
		CloseableHttpClient client = null;
		try{
			client = HttpClients.createDefault();
		}catch(IllegalStateException e){
			return null;
		}
		 
		HttpEntity httpEntity = null;
		try {
			CloseableHttpResponse response = client.execute(get);
			httpEntity = response.getEntity();
			if (httpEntity != null){
				return EntityUtils.toString(httpEntity);
			}
		} catch( HttpHostConnectException hostException){
			logger.error("Connection error: connect to " + url);
		} catch (NoRouteToHostException noroutE) {
			logger.error("java.net.NoRouteToHostException : No route to host: connect" + url);
		} catch (IllegalStateException e) {
			throw new Exception("http的get请求异常");
		} catch (Exception e) {
			throw new Exception("http的get请求异常");
		}
		return null;
	}
	
	public static String post(String url, Map<String, String> params){
		HttpPost post = new HttpPost(url);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		for (String key : keySet){
			formparams.add(new BasicNameValuePair(key, params.get(key)));
		}
		UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(formparams, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		post.setEntity(entity);
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpEntity httpEntity = null;
		try {
			CloseableHttpResponse response = client.execute(post);
			httpEntity = response.getEntity();
			if (httpEntity != null){
				return EntityUtils.toString(httpEntity);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String delete(String url){
		HttpDelete get = new HttpDelete(url);
		CloseableHttpClient client = HttpClients.createDefault();
		HttpEntity httpEntity = null;
		try {
			CloseableHttpResponse response = client.execute(get);
			httpEntity = response.getEntity();
			if (httpEntity != null){
				return EntityUtils.toString(httpEntity);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String put(String url, Map<String, String> params){
		HttpPut put = new HttpPut(url);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if (params != null){
			Set<String> keySet = params.keySet();
			for (String key : keySet){
				formparams.add(new BasicNameValuePair(key, params.get(key)));
			}
			UrlEncodedFormEntity entity = null;
			try {
				entity = new UrlEncodedFormEntity(formparams, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			put.setEntity(entity);
		}
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpEntity httpEntity = null;
		try {
			CloseableHttpResponse response = client.execute(put);
			httpEntity = response.getEntity();
			if (httpEntity != null){
				return EntityUtils.toString(httpEntity);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String doubleEncode(String value){
		try {
			return URLEncoder.encode( URLEncoder.encode(value,"utf-8"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("对字符进行urlencode串编码出现错误,字符串值："+value);
			e.printStackTrace();
		}
		return value;
	}
	public static String decode(String value){
		try {
			return URLDecoder.decode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("对字符串进行urldecode解码出现错误,字符串值："+value);
			e.printStackTrace();
		}
		return value;
	}
	
	public static String postJSON(String url, String json){
		HttpPost post = new HttpPost(url);
		StringEntity entity;
		try {
			entity = new StringEntity(json);
			entity.setContentEncoding("UTF-8");    
			entity.setContentType("application/json");    
			post.setEntity(entity);   
			
			CloseableHttpClient client = HttpClients.createDefault();
			HttpEntity httpEntity = null;
			CloseableHttpResponse response = client.execute(post);
			httpEntity = response.getEntity();
			if (httpEntity != null){
				return EntityUtils.toString(httpEntity);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject getAccessFromCloud(String cloudIp, String tenantName, String username, String password){
		String tokenurl = "http://"+cloudIp+":5000/v2.0/tokens";
		JSONObject authObj = new JSONObject();
		authObj.put("tenantName", tenantName);
		
		JSONObject passwordCredentialsObj = new JSONObject();
		passwordCredentialsObj.put("username", username);
		passwordCredentialsObj.put("password", password);
		
		authObj.put("passwordCredentials", passwordCredentialsObj);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("auth", authObj);
		
		String resultStr  = HttpUtil.postJSON(tokenurl, jsonObject.toJSONString());
		JSONObject result = null;
		if (resultStr != null){
			//处理转换错误
			try{
				result = JSONObject.parseObject(resultStr);
			}catch(Throwable e){
				e.printStackTrace();
			}
			return result;
		}
		return null;
	}
	
	public static String postCloudWithToken(String url, String json, String XAuthToken){
		HttpPost post = new HttpPost(url);
		post.addHeader("X-Auth-Token", XAuthToken);
		StringEntity entity;
		try {
			entity = new StringEntity(json);
			entity.setContentEncoding("UTF-8");    
			entity.setContentType("application/json");    
			post.setEntity(entity);   
			
			CloseableHttpClient client = HttpClients.createDefault();
			HttpEntity httpEntity = null;
			CloseableHttpResponse response = client.execute(post);
			httpEntity = response.getEntity();
			if (httpEntity != null){
				return EntityUtils.toString(httpEntity);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String putCloudWithToken(String url, Map<String, String> params, String XAuthToken){
		HttpPut put = new HttpPut(url);
		put.addHeader("X-Auth-Token", XAuthToken);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if (params != null){
			Set<String> keySet = params.keySet();
			for (String key : keySet){
				formparams.add(new BasicNameValuePair(key, params.get(key)));
			}
			UrlEncodedFormEntity entity = null;
			try {
				entity = new UrlEncodedFormEntity(formparams, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			put.setEntity(entity);
		}
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpEntity httpEntity = null;
		try {
			CloseableHttpResponse response = client.execute(put);
			httpEntity = response.getEntity();
			if (httpEntity != null){
				return EntityUtils.toString(httpEntity);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static InputStream getCloudWithToken(String url, String XAuthToken){
		HttpGet get = new HttpGet(url);
		get.addHeader("X-Auth-Token", XAuthToken);
		CloseableHttpClient client = HttpClients.createDefault();
		HttpEntity httpEntity = null;
		try {
			CloseableHttpResponse response = client.execute(get);
			httpEntity = response.getEntity();
			if (httpEntity != null){
				return httpEntity.getContent();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String deleteCloudWithToken(String url, String XAuthToken){
		HttpDelete delete = new HttpDelete(url);
		delete.addHeader("X-Auth-Token", XAuthToken);
		CloseableHttpClient client = HttpClients.createDefault();
		HttpEntity httpEntity = null;
		try {
			CloseableHttpResponse response = client.execute(delete);
			httpEntity = response.getEntity();
			if (httpEntity != null){
				return EntityUtils.toString(httpEntity);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static InputStream getCloudFileByRange(String url, String XAuthToken,
				long offset, long length){
		HttpGet get = new HttpGet(url);
		get.addHeader("X-Auth-Token", XAuthToken);
		get.addHeader("Range", "bytes="+offset+"-"+length);
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpEntity httpEntity = null;
		try {
			CloseableHttpResponse response = client.execute(get);
			httpEntity = response.getEntity();
			if (httpEntity != null){
				return httpEntity.getContent();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}