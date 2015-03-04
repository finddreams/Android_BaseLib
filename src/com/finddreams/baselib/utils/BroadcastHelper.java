package com.finddreams.baselib.utils;

import android.content.Context;
import android.content.Intent;

public class BroadcastHelper {

	public static void sendCommonBroadCast(Context context,String content) {
		Intent intent = new Intent();
        intent.setAction(ConstantsValue.COMMON_ACTION);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(ConstantsValue.COMMON_ACTION, content);
        context.sendBroadcast(intent);
	}
	
	public static void sendBroadCast(Context context,String action,String key,String value) {
		Intent intent = new Intent();
        intent.setAction(action);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(key, value);
        context.sendBroadcast(intent);
	}
	
	public static void sendBroadCast(Context context,String action,String key,int value) {
		Intent intent = new Intent();
		intent.setAction(action);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.putExtra(key, value);
		context.sendBroadcast(intent);
	}
}
