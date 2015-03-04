package com.finddreams.baselib.utils;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
/**
 * @Description:屏幕显示的工具类
 * @author http://blog.csdn.net/finddreams
 */ 
public class DisplayUtil {
	
	public static final String TAG = "DisplayUtil";

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static int getScreenPicHeight(int screenWidth, Bitmap bitmap) {
		int picWidth = bitmap.getWidth();
		int picHeight = bitmap.getHeight();
		int picScreenHeight = 0;
		picScreenHeight = (screenWidth * picHeight) / picWidth;
		return picScreenHeight;
	}
	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(float pxValue, float fontScale) {
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(float spValue, float fontScale) {
		return (int) (spValue * fontScale + 0.5f);
	}
	
	public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
	
    public static int densityDPI(Context context){
    	return context.getResources().getDisplayMetrics().densityDpi;
    }
    /**
	 * 得到设备屏幕的宽度
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 得到设备屏幕的高度
	 */
	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}
	/**
	 * return system bar height
	 * @param context
	 * @return
	 */
	public static int getStatuBarHeight(Context context) {
		try {
			Class<?> c = Class.forName("com.android.internal.R$dimen");  
			Object obj;
			obj = c.newInstance();
			Field field = c.getField("status_bar_height");  
	        int width = Integer.parseInt(field.get(obj).toString());  
	        int height = context.getResources().getDimensionPixelSize(width);
	        return height;
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.e(TAG, "getStatuBarHeight",e);
		}
		return 0;
	}
	
	public static int getStatuBarHeight2(Context context) {
		Rect frame = new Rect();  
		((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);  
        int statusBarHeight = frame.top;  
        return statusBarHeight;
	}
	/**
	 * get screen height of this cellphone
	 * @param context
	 * @return
	 */
	public static int getMobileHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
	    int height = dm.heightPixels;  //得到高度
	    return height;
	}
	/**
	 * get screen width of this cellphone
	 * @param context
	 * @return
	 */
	public static int getMobileWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;    //得到宽度
	    return width;
		
	}
	
}
