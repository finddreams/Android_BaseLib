package com.finddreams.baselib.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.finddreams.baselib.service.ServiceManager;
/**
 * @Description:检查网络状态的工具类
 * @author http://blog.csdn.net/finddreams
 */ 
public class NetStateUtil {

	/**
	 * Check current network whether available
	 * @param context
	 * @return
	 */
	public static boolean checkNet(Context context) {

		ConnectivityManager manager = ServiceManager.getConnectivityManager(context);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null && info.isAvailable() && info.isConnected()) {
			return true;
		}else {
			return false;
		}
	}

    /**
     * Judge network  whether Wifi connectivity
     * @param context
     * @return
     */
	public static boolean isWifi(Context context) {
        ConnectivityManager manager = ServiceManager.getConnectivityManager(context);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
        	int type = networkInfo.getType();
        	if (networkInfo.isAvailable() && networkInfo.isConnected() && type == ConnectivityManager.TYPE_WIFI ) {
        		return true;
        	}
        }
		return false;
	}
    /**
     * 判断当前网络是否是移动流量连接
     * @param context
     * @return
     */
    public static  boolean  isMobile(Context context) {
        ConnectivityManager manager = ServiceManager.getConnectivityManager(context);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
        	int type = networkInfo.getType();
        	if (networkInfo.isAvailable() && networkInfo.isConnected() && type == ConnectivityManager.TYPE_MOBILE) {
        		return true;
        	}
        }
        return false;
    }

    /**
     * Whether open wifi
     * @param context
     * @return
     */
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = ServiceManager.getConnectivityManager(context);
        TelephonyManager mgrTel = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

}
