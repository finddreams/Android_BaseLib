package com.finddreams.baselib.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.finddreams.baselib.http.MyHttpClient;
import com.lidroid.xutils.ViewUtils;
import com.loopj.android.http.AsyncHttpClient;

/**
 * @Description:所有页面view的基类。
 * @author http://blog.csdn.net/finddreams
 */ 
public abstract class BasePage implements OnClickListener {

	protected Context context;
	protected View contentView;
	protected AsyncHttpClient httpClient;
	
	public boolean isLoadSuccess=false;
	
	public BasePage(Context context) {
		this.context = context;
		contentView = initView((LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		ViewUtils.inject(contentView);
		httpClient = MyHttpClient.getHttpClient();
		
	}
	protected abstract View initView(LayoutInflater inflater);

	public abstract void initData();

	public View getContentView() {
		return contentView;
	}
	@Override
	public void onClick(View v) {
	}
	
	protected void startActivity(Activity activity) {
		Intent intent = new Intent(context, activity.getClass());
		context.startActivity(intent);
		
		// AppManager.getAppManager().finishActivity();

	}
	
	
}
