package com.finddreams.baselib.activity;

import java.io.IOException;
import java.util.Vector;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.finddreams.baselib.R;
import com.finddreams.baselib.utils.AppManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.zxing.barcode.CameraManager;
import com.zxing.barcode.CaptureActivityHandler;
import com.zxing.barcode.InactivityTimer;
import com.zxing.barcode.ViewfinderView;


/**
 * @Description: 二维码扫描界面，已经封装好；以startActivityForResult打开,在返回结果中获得二维码结果
 * @author http://blog.csdn.net/finddreams
 */ 
public class CaptureActivity extends Activity implements Callback ,OnClickListener{

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.90f;
	private boolean vibrate;
	
	private TextView capture_back;
	private TextView top_title;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.camera);
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		capture_back=(TextView)findViewById(R.id.top_back_tv);
		capture_back.setOnClickListener(this);
		top_title=(TextView)findViewById(R.id.top_title);
		top_title.setOnClickListener(this);
		top_title.setText(R.string.scan);
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		//ToastUtil.toastshow(CaptureActivity.this, "ִ������");
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
		
		
	}

	
	
	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}
	
	/**
	 * Handler scan result
	 * @param result
	 * @param barcode
	 */
	public void handleDecode(Result result, Bitmap barcode) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
	
		final String resultString = result.getText();
		
		
		if (resultString.equals("")) {
			Toast.makeText(CaptureActivity.this, "扫描失败或者二维码无内容!", Toast.LENGTH_SHORT).show();
		}
		
//		else if (resultString.contains("http://q.cha4.net/index.php?app=member&act=getUserInfoApi")) {
//			
//			    Intent intent3 = new Intent(CaptureActivity.this,OtherUserDetailActivity.class);
//				Bundle bundle3 = new Bundle();			
//				bundle3.putString("url", resultString);
//				intent3.putExtras(bundle3);
//				startActivity(intent3);	
//			
//		}
		}
	
	
	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.qrcode_found);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

    
	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.top_back_tv:
		
			AppManager.getAppManager().finishActivity();
			break;

			
		default:
			break;
		}
	}



	 

	
}