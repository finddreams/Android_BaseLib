package com.finddreams.baselib.http;

import java.util.HashMap;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @Description:使用了android-async-http的网络帮助类
 * @author http://blog.csdn.net/finddreams
 */ 
public class MyHttpClient {
	
	private static int CONNECTTIMEOUT = 15 * 1000; // 连接超时
	private static AsyncHttpClient client;
	private MyHttpClient(){
	}
	public static AsyncHttpClient getHttpClient(){
		if(client==null){
			client=new AsyncHttpClient();
			client.setTimeout(CONNECTTIMEOUT);
		}
		return client;
	}
	/**
	 * 带参数的get请求
	 * @param urlString
	 * @param params
	 * @param res
	 */
    public void sendGet(String urlString, RequestParams params,
            AsyncHttpResponseHandler res){
 
        client.get(urlString, params, res);
 
    }
	public void sendGet(String url,AsyncHttpResponseHandler  responseHandler){
		client.get(url, responseHandler);
	}
	
	public void sendPost(String url,RequestParams params,HashMap<String, String> vaules,AsyncHttpResponseHandler responseHandler){
		
		client.post(url, params, responseHandler);
	}
}
