package com.finddreams.baselib.base;


import com.finddreams.baselib.http.MyHttpClient;
import com.finddreams.baselib.utils.AppManager;
import com.loopj.android.http.AsyncHttpClient;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
/**
 * @Description:所有Activity的基类，是个抽象类，把整个项目中都需要用到的东西封装起来
 * @author http://blog.csdn.net/finddreams
 */ 
public abstract class BaseActivity extends FragmentActivity {
	
	protected Context context;
	protected AsyncHttpClient httpClient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		AppManager.getAppManager().addActivity(this);
		context = this;
		httpClient = MyHttpClient.getHttpClient();
		initView();
		initData();
	}
	/**
	 * 抽象方法子类必须实现
	 */
	protected abstract void initView();

	protected abstract void initData();
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		AppManager.getAppManager().finishActivity(this);
	}
}
