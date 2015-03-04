package com.finddreams.baselib.utils;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.finddreams.baselib.R;
/**
 * @Description: Activity的工具类
 * @author http://blog.csdn.net/finddreams
 */ 
public class ActivityUtil {

	private static final String TAG = "ActivityUtil";
	
	/**
	 * 延迟去往新的Activity
	 * @param context
	 * @param cls
	 * @param delay
	 */
	public static void delayToActivity(final Context context,final Class<?> cls,long delay) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				context.startActivity(new Intent(context, cls));
			}
		}, delay);
	}
	/**
	 * 跳转到另一个Activity，不携带数据，不设置flag
	 * @param context
	 * @param cls
	 */
	public static void goToActivity(Context context,Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(context, cls);
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}
	/**
	 * go to activity,use animation
	 * @param context
	 * @param cls
	 * @param enterAnim
	 * @param exitAnim
	 */
	public static void goToActivity(Context context,Class<?> cls,int enterAnim,int exitAnim,Bundle bundle) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(enterAnim,exitAnim);
	}
	/**
	 * to new activity,use animation from right to left
	 * @param context
	 * @param cls
	 */
	public static void goToActivityFromLeft2Right(Context context,Class<?> cls) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_right,R.anim.out_to_right);
	}
	/**
	 * to new activity,use animation from right to left carry data
	 * @param context
	 * @param cls
	 */
	public static void goToActivityFromLeft2Right(Context context,Class<?> cls,Bundle bundle) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_right,R.anim.out_to_right);
	}
	/**
	 * to new activity,use animation from left to right
	 * @param context
	 * @param cls
	 */
	public static void goToActivityFromRight2Left(Context context,Class<?> cls) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_left,R.anim.out_to_left);
	}
	/**
	 * to new activity,use animation from left to right carry data
	 * @param context
	 * @param cls
	 */
	public static void goToActivityFromRight2Left(Context context,Class<?> cls,Bundle bundle) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_left,R.anim.out_to_left);
	}
	/**
	 * to new activity,use animation from bottom to top carry data
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public static void goToActivityFromBottom2Top(Context context,Class<?> cls,Bundle bundle) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_bottom,R.anim.out_to_top);
	}
	/**
	 * to new activity,use animation from bottom to top
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public static void goToActivityFromBottom2Top(Context context,Class<?> cls) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_bottom,R.anim.out_to_top);
	}
	/**
	 * to new activity,use animation form top to bottom
	 * @param context
	 * @param cls
	 */
	public static void goToActivityFromTop2Bottom(Context context,Class<?> cls) {
		Activity activity = (Activity)context;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.in_from_top,R.anim.out_to_bottom);
	}
	
	
	/**
	 * 跳转到另一个Activity，携带数据
	 * @param context
	 * @param cls
	 */
	public static void goToActivity(Context context,Class<?> cls,Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(context, cls);
		intent.putExtras(bundle);
		context.startActivity(intent);
	}
	
	
}
