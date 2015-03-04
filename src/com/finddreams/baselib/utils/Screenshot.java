package com.finddreams.baselib.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.view.View;

public class Screenshot {

	private static final String TAG = "Screenshot";
	/**
	 * Screenshot and save image to the sdcard 
	 * 
	 * @param context
	 * @return image file
	 * @throws FileNotFoundException
	 */
	public static File getScreenshot(Context context,View view) throws FileNotFoundException {
		File file = null;
		try {
			if (view == null) {
				return null;
			}
//		View view = ((Activity)context).getWindow().getDecorView();
//		View mainScreen = ((Activity)context).findViewById(layoutResId);
			int height = DisplayUtil.getMobileHeight(context);
			int width = DisplayUtil.getMobileWidth(context);
			Bitmap bitmap = Bitmap.createBitmap( width, height, Config.ARGB_8888 );
			Canvas canvas = new Canvas(bitmap);
			
			view.draw(canvas);
			long time = System.currentTimeMillis();
			
			file = new File(ConstantsValue.IMAGE_APP_PATH+ "/" + time + ".png");
			
			FileOutputStream f = null;
			f = new FileOutputStream(file);
			boolean b = bitmap.compress(Bitmap.CompressFormat.PNG, 100, f);
			if(b){
				LogManager.d(TAG, "screentshot success");
			}
			if (file.exists()) {
				LogManager.d(TAG, "image file exists");
			} else {
				LogManager.d(TAG, "image file not exists");
			}
		} catch (Exception e) {
			LogManager.e(TAG, "getScreenshot", e);
		}
		return file;
	}
	
}
