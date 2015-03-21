package com.finddreams.baselib.base;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.finddreams.baselib.R;
import com.finddreams.baselib.utils.ConstantsValue;
import com.finddreams.baselib.view.ProgressWebView;
/**
 * WebView
 * @author liuxiang
 * @date 2015-1-30 下午6:07:05
 * @version V1.0
 */ 
public class BaseWebActivity extends BaseActivity {

//	private View mLoadingView;
	protected ProgressWebView mWebView;
	private ProgressBar web_progressbar;
	@Override
	protected void initView() {
		setContentView(R.layout.activity_baseweb);
	//	mLoadingView = findViewById(R.id.baseweb_loading_indicator);
		mWebView = (ProgressWebView) findViewById(R.id.baseweb_webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
	}

	@Override
	protected void initData() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String url = bundle.getString(ConstantsValue.INTENT_URL);
		String title = bundle.getString(ConstantsValue.INTENT_TITLE);
	//	if(!TextUtils.isEmpty(url)&&TextUtils.isEmpty(title)){
			mWebView.loadUrl(url);
			
	//	}
		
	}
	@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			mWebView=null;
			
		}

	
}
