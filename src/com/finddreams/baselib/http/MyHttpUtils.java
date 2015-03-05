package com.finddreams.baselib.http;

import java.io.IOException;

import org.apache.http.HttpEntity;

import android.content.Context;

import com.finddreams.baselib.R;
import com.finddreams.baselib.utils.StringUtil;
import com.finddreams.baselib.utils.ToastManager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;

/**
 * 自定义的HttpUtils
 * 
 * @author liuxiang
 * @date 2015-2-5 下午3:46:48
 * @version V1.0
 */
public class MyHttpUtils {
	
	private static final int CONNTIMEOUT = 1000 * 15;
	private static final int CACHE_TIME = 1000 * 20;
	private static HttpUtils http;
	private Context context;

	public MyHttpUtils(Context context) {
		this.context = context;
		http = new HttpUtils(CONNTIMEOUT);

	}

	public void setCache() {
		http.configCurrentHttpCacheExpiry(CACHE_TIME);
	}

	public void httpGet(String url, RequestCallBack<String> callback) {
		http(HttpMethod.GET, url, null, callback);
	}

	public void httpPost(String url, RequestParams params,
			RequestCallBack<String> callback) {
		http(HttpMethod.POST, url, params, callback);
	}

	protected void http(HttpRequest.HttpMethod method, String url,
			RequestParams params, RequestCallBack<String> callback) {
		http.configCurrentHttpCacheExpiry(CACHE_TIME);
		LogUtils.allowD = true;
		if (params != null) {
			if (params.getQueryStringParams() != null)
				LogUtils.d(url + params.getQueryStringParams().toString());
			HttpEntity entity = params.getEntity();
			if (entity != null) {

				try {
					LogUtils.d(url + StringUtil.convertStreamToString(entity.getContent()));
				} catch (IOException e) {
				
					e.printStackTrace();
				}
			}
		} else {
			params = new RequestParams();
		}

		if (0 == NetStateUtil.isNetworkAvailable(context)) {
			showCustomToast(context.getString(R.string.no_net));
			http.send(method, url, params, callback);
		} else {
			http.send(method, url, params, callback);
		}
	}

	protected void showCustomToast(String str) {
		ToastManager.showShortText(context, str);
	}
}
