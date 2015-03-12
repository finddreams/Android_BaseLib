package com.finddreams.baselib.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
/**
 * @Description: 关于文本字符串的处理类
 * @author http://blog.csdn.net/finddreams
 */ 
public class StringUtil {
	
	private static String key2 = "finddreams";
	private static String key1 = "13245";
	/** 年月日时分秒 */
	public final static String FORMAT_YMDHMS = "yyyy-MM-dd kk:mm:ss";
	/** 获得当前时间 */
	public static CharSequence currentTime(CharSequence inFormat) {
		return DateFormat.format(inFormat, System.currentTimeMillis());
	}

	public static String getWebCon(String domain) {
		// System.out.println("开始读取内容...("+domain+")");
		StringBuffer sb = new StringBuffer();
		try {
			java.net.URL url = new java.net.URL(domain);
			BufferedReader in = new BufferedReader(new InputStreamReader(url
					.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
		} catch (Exception e) { // Report any errors that arise
			sb.append(e.toString());
			System.err.println(e);
			System.err
					.println("Usage:   java   HttpClient   <URL>   [<filename>]");
		}
		return sb.toString();
	}
	/**
     * 字符串转stream
     * @param str
     * @return
     */
    public static InputStream StringToInputStream(String str) {
        if (TextUtils.isEmpty(str))
            return null;
        ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        return stream;
    }
    /**
	 * 输入流转字符串
	 * @param is
	 * @return
	 */
	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}
	/**
     * 字符串替换
     * @param line
     * @param oldString
     * @param newString
     * @return
     */
    public static final String replace(String line, String oldString,
            String newString) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

	/**
     * 检查是否符合手机号码格式
     * 用正则表达式匹配输入的手机号码是否正确
     * @param phoneNum
     * @return
     */
    public static boolean isMobileNO(String phoneNum) {
        final String regx = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";

        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(phoneNum);

        return m.matches();
    }

	/**
	 * 加密
	 * @param password
	 * @return
	 */
	public static String encryptionKey(String password) {
		byte[] keyByte1 = key1.getBytes();
		byte[] keyByte2 = key2.getBytes();
		byte[] pwdByte = password.getBytes();
		for (int i = 0; i < pwdByte.length; i++) {
			pwdByte[i] = (byte) (pwdByte[i] ^ keyByte1[i % keyByte1.length]);
		}
		byte[] countByte = new byte[pwdByte.length + keyByte1.length];
		for (int i = 0; i < countByte.length; i++) {
			if (i < pwdByte.length)
				countByte[i] = pwdByte[i];
			else
				countByte[i] = keyByte1[i - pwdByte.length];
		}
		for (int i = 0; i < countByte.length; i++) {
			countByte[i] = (byte) (countByte[i] ^ keyByte2[i % keyByte2.length]);
		}
		return bytesToHexString(countByte);
	}
	
	/**
	 * 解密
	 * @param password
	 * @return
	 */
	public static String decryptionKey(String password){
		byte[] keyByte1 = key1.getBytes();
		byte[] keyByte2 = key2.getBytes();
		//password = hexStr2Str(password);
		byte[] pwdByte = hexStr2Bytes(password);
		
		for (int i = 0; i < pwdByte.length; i++) {
			pwdByte[i] = (byte) (pwdByte[i] ^ keyByte2[i % keyByte2.length]);
		}
		
		byte[] lastByte = new byte[pwdByte.length - keyByte1.length];
		for (int i = 0; i < lastByte.length; i++) {
			lastByte[i] = pwdByte[i];
		}
		for (int i = 0; i < lastByte.length; i++) {
			lastByte[i] = (byte) (lastByte[i] ^ keyByte1[i % keyByte1.length]);
		}
		
		return new String(lastByte);
	}
	
	

	/**
	 * 把字节数组转换成16进制字符串
	 * @param bArray
	 * @return
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}
	/**   
	 * 十六进制转换字符串  
	 * @param String str Byte字符串(Byte之间无分隔符 如:[616C6B])  
	 * @return String 对应的字符串  
	 */      
	public static String hexStr2Str(String hexStr)    
	{      
	    String str = "0123456789ABCDEF";      
	    char[] hexs = hexStr.toCharArray();      
	    byte[] bytes = new byte[hexStr.length() / 2];      
	    int n;      
	  
	    for (int i = 0; i < bytes.length; i++)    
	    {      
	        n = str.indexOf(hexs[2 * i]) * 16;      
	        n += str.indexOf(hexs[2 * i + 1]);      
	        bytes[i] = (byte) (n & 0xff);      
	    }      
	    return new String(bytes);      
	}  
	/**  
	 * bytes字符串转换为Byte值  
	 * @param String src Byte字符串，每个Byte之间没有分隔符  
	 * @return byte[]  
	 */    
	public static byte[] hexStr2Bytes(String src)    
	{    
	    int m=0,n=0;    
	    int l=src.length()/2;    
	    byte[] ret = new byte[l];    
	    for (int i = 0; i < l; i++)    
	    {    
	        m=i*2+1;    
	        n=m+1;    
	        ret[i] = Byte.decode("0x" + src.substring(i*2, m) + src.substring(m,n));    
	    }    
	    return ret;    
	}   
	
	/**
	 * hightlight text
	 * @param start
	 * @param end
	 * @param text
	 */
	public static void highlight(int start, int end,TextView text) {
		SpannableStringBuilder spannable = new SpannableStringBuilder(text.getText().toString());
		ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
		spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		text.setText(spannable);
	}
	
	public static String getHightLightText(String text) {
		Pattern p = Pattern.compile("<em>([^</em>]*)");//匹配<title>开头，</title>结尾的文档
        Matcher m = p.matcher(text );//开始编译
        String keyword = null;
        while (m.find()) {
            LogManager.d("hight light:", m.group(1));
            keyword = m.group(1);
        }
        return keyword;
	}
	
	//判断email格式是否正确
  	public static boolean isEmail(String email) {
  		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
  		Pattern p = Pattern.compile(str);
  		Matcher m = p.matcher(email);
  		
  		return m.matches();
  	}
	
}
