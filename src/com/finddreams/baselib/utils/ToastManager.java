package com.finddreams.baselib.utils;

import android.content.Context;
import android.widget.Toast;

import com.finddreams.baselib.view.CustomToast;

/**
 * @Description: 管理toast的类
 * @author http://blog.csdn.net/finddreams
 */ 
public class ToastManager {

		protected static final String TAG = "AppToast";
		public static CustomToast toast;
		/**
		 * 信息提示
		 * @param context
		 * @param content
		 */
		public static void makeToast(Context context, String content) {
			if(context==null)return;
			if(toast != null)
				toast.cancel();
			toast = new CustomToast(context, content, Toast.LENGTH_LONG);
			toast.show();
		}

		public static void showShortText(Context context, int resId) {
			try {
				if(context==null)return;
				if(toast != null)
					toast.cancel();
				toast = new CustomToast(context, context.getString(resId),Toast.LENGTH_SHORT);
				toast.show();
			} catch (Exception e) {
				LogManager.e(TAG,e.getMessage());
			}
		}
		public static void showShortText(Context context, CharSequence text) {
			if(context==null)return;
			if(toast != null)
				toast.cancel();
			toast = new CustomToast(context, text.toString(),Toast.LENGTH_SHORT);
			toast.show();
		}
		
		public static void showLongText(Context context, int resId) {
			try {
				if(context==null)return;
				if(toast != null)
					toast.cancel();
				toast = new CustomToast(context, context.getString(resId),Toast.LENGTH_LONG);
				toast.show();
				
			} catch (Exception e) {
				LogManager.e(TAG,e.getMessage());
			}
		}

		public static void showLongText(Context context, CharSequence text) {
			if(context==null)return;
			if(toast != null)
				toast.cancel();
			toast = new CustomToast(context, text.toString(),Toast.LENGTH_LONG);
			toast.show();
		}

		
	}


