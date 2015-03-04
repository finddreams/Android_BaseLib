package com.finddreams.baselib.http;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.util.LogUtils;

/**
 * @Description:url的帮助类
 * @author http://blog.csdn.net/finddreams
 */ 
public class URLUtils {
	
	 public final static String HOST = "blog.csdn.net"; 
	public static final String PATH = "finddreams";
	public static final String SCHEME = "http";
	public static final String defaultEncoding = "UTF-8";

	public static String getUrl(String baseurl) {
		if (baseurl != null) {
			String url = SCHEME + "://" + HOST + "/" + PATH + baseurl;
			return url;
		}
		return null;
	}
	/**
	 * 创建Url
	 * @param resource
	 * @return
	 */
	public static String createURL(String resource) {
		try {
			String url = URIUtils.createURI(SCHEME, HOST, -1, PATH + resource,
					"", null).toString();
			LogUtils.d(url);
			return url;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String createURL(String resource, Map<String, String> params) {
		return createURL(resource, params, defaultEncoding);
	}

	public static String createURL(String resource, Map<String, String> params,
			String encoding) {
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		for (Iterator<Map.Entry<String, String>> it = params.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it
					.next();
			parameters.add(new BasicNameValuePair(entry.getKey(), entry
					.getValue()));
		}

		try {
			String url = URIUtils.createURI(SCHEME, HOST, -1, PATH + resource,
					URLEncodedUtils.format(parameters, encoding), null)
					.toString();

			LogUtils.d(url);
			return url;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;

	}
}
