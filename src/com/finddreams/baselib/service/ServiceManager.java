package com.finddreams.baselib.service;

import android.app.Service;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;

/**
 * @Description:获得系统服务管理器
 * @author http://blog.csdn.net/finddreams
 */ 
public class ServiceManager {

    private static ConnectivityManager cm;
    private static LocationManager locationManager;
    private static TelephonyManager telephonyManager;
    private static InputMethodManager inputMethodManager;
    private static Vibrator vibrator;
    private static SensorManager sensorManager;
    private static Sensor accelerometerSensor;
    private static Sensor lightSensor;//光线传感器引用
    private static LayoutInflater inflater;

    private ServiceManager(){};

    /**
     * 获得ConnectivityManager
     * @param context
     * @return
     */
    public static ConnectivityManager getConnectivityManager(Context context) {

        if (cm == null) {
        	synchronized (ServiceManager.class) {
        		if (cm == null) {
        			cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        		}
			}
        }
        return cm;
    }

    /**
     * 获得LocationManager
     * @param context
     * @return
     */
    public static LocationManager getLocationManager(Context context) {
        if (locationManager == null) {
        	synchronized (ServiceManager.class) {
        		if (locationManager == null) {
        			locationManager = ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));
        		}
			}
        }
        return locationManager;
    }
    /**
     * 获得TelephonyManager
     * @param context
     * @return
     */
    public static TelephonyManager getTelephonyManager(Context context) {
        if (telephonyManager == null) {
        	synchronized(ServiceManager.class) {
        		if (telephonyManager == null) {
        			telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        		}
        	}
        }
        return telephonyManager;
    }
    /**
     * 获得InputMethodManager
     * @param context
     * @return
     */
    public static InputMethodManager getInputMethodManager(Context context) {
    	if (inputMethodManager == null) {
    		synchronized(ServiceManager.class) {
    			if (inputMethodManager == null) {
    				inputMethodManager = ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
    			}
    		}
    	}
    	return inputMethodManager;
    }

    /**
     * 获得震动的控制器
     * @param context
     * @return
     */
    public static Vibrator getVibrator(Context context) {
    	if (vibrator == null) {
    		synchronized(ServiceManager.class) {
    			if (vibrator == null) {
    				vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE); 
    			}
    		}
    	}
    	return vibrator;
    }
    /**
     * 获得传感器管理器
     * @param context
     * @return
     */
    public static SensorManager getSensorManager(Context context) {
    	if (sensorManager == null) {
    		synchronized(ServiceManager.class) {
    			if (sensorManager == null) {
    				sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    			}
    		} 
    	}
    	return sensorManager;
    }
    /**
     * 获得加速管理器
     * @param context
     * @return
     */
    public static Sensor getAccelerometerSensor(Context context) {
    	if ( accelerometerSensor == null ){
    		synchronized(ServiceManager.class) {
    			if (accelerometerSensor == null) {
    				accelerometerSensor = getSensorManager(context).getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    			}
    		}
    	}
    	return accelerometerSensor;
    }
    public static Sensor getLightSensor(Context context) {
    	if ( lightSensor == null ){
    		synchronized(ServiceManager.class) {
    			if (lightSensor == null) {
    				lightSensor = getSensorManager(context).getDefaultSensor(Sensor.TYPE_LIGHT);
    			}
    		}
    	}
    	return lightSensor;
    }
    
    public static LayoutInflater getLayoutInflate(Context context) {
    	if ( inflater == null ){
    		synchronized(ServiceManager.class) {
    			if (inflater == null) {
    				inflater = LayoutInflater.from(context);
    			}
    		}
    	}
    	return inflater;
    }

}
