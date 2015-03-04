package com.finddreams.baselib.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
/**
 * @Description: app的帮助类
 * @author http://blog.csdn.net/finddreams
 */ 
public class AppHelper {
	
	private static final String TAG = "AppHelper";
	/**
	 * @param context
	 * @return
	 */
	public static String getAppVersion(Context context) {
		 try {
			// 获取packagemanager的实例
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
			String version = packInfo.versionName;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		 return null;
	}
	
	/**
	 * get android os version no
	 * @return
	 */
	public static float getAndroidVersion() {
		return Float.valueOf(android.os.Build.VERSION.RELEASE);
	}
	
	/**
	 * get device model
	 * @return
	 */
	public String getDeviceModel () {
		return android.os.Build.MODEL;
	}
	
	/**
	 * get android os sdk version  2.2 = 8,2.3 = 9,4.2.1 = 17
	 * @return sdk version
	 */
	public static int getSDKVersion(){
		return android.os.Build.VERSION.SDK_INT;
	}
	/**
	 * 根据Uri安装apk
	 * @param context
	 * @param uri
	 */
	public void installApk(Context context,Uri uri) {
        if (uri.toString().endsWith(".apk")) {
             Intent intent = new Intent();
             intent.setAction(Intent.ACTION_VIEW);
//                 intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
             intent.setDataAndType(uri,"application/vnd.android.package-archive");
             context.startActivity(intent);
        }
	}
	
}
