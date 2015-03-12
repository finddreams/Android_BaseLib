package com.finddreams.baselib.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;

/**
 * 缓存工具
 */
public class CacheUtil {
	public final static int MAX_FAILCOUNT = 3; // 最大失败次数，超过即不再重新抓取
	public final static String TAG = "CacheTools";

	/**
	 * 保存HTTP缓存
	 * 
	 * @param cacheDir
	 * @param cacheKey
	 * @param cacheValue
	 * @return
	 */
	public static boolean saveHttpCache(String cacheDir, String cacheKey,
			Object cacheValue) {
		boolean flag = false;
		int cnt = 0;
		do {
			try {
				File destDir = new File(cacheDir);
				if (!destDir.exists()) {
					destDir.mkdirs();
				}
				ObjectOutputStream os = new ObjectOutputStream(
						new FileOutputStream(cacheDir + cacheKey));
				os.writeObject(cacheValue);
				os.close();
				flag = true;
				break;
			} catch (Exception e) {
				cnt++;
				LogManager.i(TAG, "写入缓存失败,正在尝试第" + cnt + "次重新写入");
			}
		} while (cnt < MAX_FAILCOUNT);
		return flag;
	}

	/**
	 * 读取 HTTP 缓存
	 * 
	 * @param cacheDir
	 * @param cacheKey
	 * @return
	 */

	public static Object readHttpCache(String cacheDir, String cacheKey) {
		File file = new File(cacheDir + cacheKey);
		if (!file.exists()) {
			return null;
		}
		Object cacheObj = null;
		int cnt = 0;
		do {
			try {
				ObjectInputStream is = new ObjectInputStream(
						new FileInputStream(file));
				cacheObj = is.readObject();
				is.close();
				break;
			} catch (Exception e) {
				e.printStackTrace();
				cnt++;
				LogManager.i(TAG, "读取缓存失败,正在尝试第" + cnt + "次重新读取");
			}
		} while (cnt < MAX_FAILCOUNT);
		return cacheObj;
	}

	public static void clearAppCache(Context context) {
		context.deleteDatabase("webview.db");
		context.deleteDatabase("webview.db-shm");
		context.deleteDatabase("webview.db-wal");
		context.deleteDatabase("webviewCache.db");
		context.deleteDatabase("webviewCache.db-shm");
		context.deleteDatabase("webviewCache.db-wal");
		// 清除数据缓存
		clearFolder(context.getFilesDir(), System.currentTimeMillis());
		clearFolder(context.getCacheDir(), System.currentTimeMillis());
	}

	/**
	 * 清除文件存目录
	 * 
	 * @param dir
	 *            目录
	 * @param numDays
	 *            当前系统时间
	 * @return
	 */
	public static int clearFolder(File dir, long curTime) {
		int cnt = 0;
		int deletedFiles = 0;
		do {
			if (dir != null && dir.isDirectory()) {
				try {
					for (File child : dir.listFiles()) {
						if (child.isDirectory()) {
							deletedFiles += clearFolder(child, curTime);
						}
						if (child.lastModified() < curTime) {
							if (child.delete()) {
								deletedFiles++;
							}
						}
					}
				} catch (Exception e) {
					cnt++;
					LogManager.i(TAG, "清除缓存失败,正在尝试第" + cnt + "清除缓存");
				}
			}
			return deletedFiles;
		} while (cnt < MAX_FAILCOUNT);

	}

	/**
	 * 计算缓存大小
	 * 
	 * @param context
	 * @return
	 */
	public static String getHttpCacheSize(Context context) {
		// 计算缓存大小
		long fileSize = 0;
		String cacheSize = "0KB";
		File filesDir = context.getFilesDir();
		File cacheDir = context.getCacheDir();
		fileSize += getDirSize(filesDir);
		fileSize += getDirSize(cacheDir);
//		fileSize += getDirSize(ImageLoader.getInstance().getDiscCache()
//				.getDirectory());

		if (fileSize > 0)
			cacheSize = formatFileSize(fileSize);
		return cacheSize;
	}

	/**
	 * 获取目录文件大小
	 * 
	 * @param dir
	 * @return
	 */
	public static long getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		long dirSize = 0;
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				dirSize += file.length();
			} else if (file.isDirectory()) {
				dirSize += file.length();
				dirSize += getDirSize(file); // 递归调用继续统计
			}
		}
		return dirSize;
	}

	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return B/KB/MB/GB
	 */
	public static String formatFileSize(long fileS) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}
}