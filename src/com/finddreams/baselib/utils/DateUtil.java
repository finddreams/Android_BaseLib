package com.finddreams.baselib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

public class DateUtil {

	private static final String TAG = "DateUtil";
	
	private static final String FORMAT1= "yyyy-MM-dd HH:mm:ss";
	private static final String FORMAT2 = "yyyyMMddHHmmss";
	private static SimpleDateFormat sdfNormal;
	private static SimpleDateFormat sdfStr;
	
	static {
		sdfNormal = new SimpleDateFormat(FORMAT1);
		sdfStr = new SimpleDateFormat(FORMAT2);
	}
	
	public static String formatDate(String date){
		String format = null;
		try {
			if (date == null || "".equals(date)) {
				return "";
			}
			format = sdfNormal.format(sdfNormal.parse(date));
		} catch (ParseException e) {
			LogManager.e(TAG, "formatDate", e);
			return date;
		}
		return format;
	}
	/**
	 * get current date,date pattern  : yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentDate(){
		String format = null;
		format = sdfNormal.format(new Date());
		return format;
	}
	
	public static String formatDateStr(String date) {
		String format = null;
		try {
			Date parseDate = sdfStr.parse(date);
			format = sdfNormal.format(parseDate);
		} catch (ParseException e) {
			LogManager.e(TAG, "formatDateStr", e);
			return date;
		}
		return format;
	}
	
	/**
	 * 获取当前时间的字符串
	 */
	public static String getCurrentDateStr() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		int w = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		String mDate = c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1)
				+ "月" + c.get(Calendar.DATE) + "日  " + weekDays[w];
		return mDate;
	}
	
}
