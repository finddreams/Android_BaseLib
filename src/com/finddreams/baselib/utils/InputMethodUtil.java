package com.finddreams.baselib.utils;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.finddreams.baselib.service.ServiceManager;
/**
 * @Description:对于软键盘的管理
 * @author http://blog.csdn.net/finddreams
 */ 
public class InputMethodUtil {

	/**
	 * 延迟打开软键盘，默认延迟500ms
	 * @param context
	 */
	public static void showInputMethodDelay(final Context context) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	InputMethodManager imm = ServiceManager.getInputMethodManager(context);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 500);
	}
	/**
	 * 延迟打开软键盘
	 * @param context
	 * @param delay 延迟时间
	 */
	public static void showInputMethodDelay(final Context context,long delay) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				InputMethodManager imm = ServiceManager.getInputMethodManager(context);
				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}, delay);
	}
	/**
	 * 直接打开软键盘
	 * @param context
	 */
	public static void showInputMethod(final Context context) {
		InputMethodManager imm = ServiceManager.getInputMethodManager(context);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	/**
	 * 关闭软键盘
	 * @param context
	 * @param view
	 */
	public static void hiddenInputMethod(final Context context,View view) {
		InputMethodManager imm = ServiceManager.getInputMethodManager(context);
    	imm.hideSoftInputFromWindow(view.getWindowToken(), 0); 
	}
	
	public static void hideBottomSoftInputMethod(Context context) {
		((Activity)context).getWindow().setSoftInputMode(
        		WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
	}
	
}
