package com.finddreams.baselib.utils;

import android.os.Environment;

public class ConstantsValue {

	public static final String COMMON_ACTION = "finddreams";
	private static final String IMAGE_PATH_DIR = "/.finddreams/img/";
    private static final String IMAGE_APP_PATH_DIR = "/.finddreams/app_img/";
    private static final String APP_LOG_PATH_DIR = "/.finddreams/log/";
    public static final String IMAGE_APP_PATH = Environment.getExternalStorageDirectory() + IMAGE_APP_PATH_DIR;
	
    public static final boolean DEBUG = true;
	public static final String INTENT_URL = "url";
	public static final String INTENT_TITLE = "title";

}
