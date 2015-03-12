package com.finddreams.baselib.view;

import java.util.Timer;

import java.util.TimerTask;

import android.content.Context;

import android.graphics.Color;

import android.os.Handler;

import android.os.Looper;

import android.os.Message;

import android.util.AttributeSet;

import android.view.Gravity;

import android.view.View;

import android.widget.Button;

/**
 * @Description:发送验证码的button，带有倒计时，以及在发送的过程中不可点击； 
 * 调用方式 view.startTickWork()方法即可；
 * @author http://blog.csdn.net/finddreams
 */
public class SendValidateButton extends Button {
	private static final int DISABLE_TIME = 60;
	private static final int MSG_TICK = 0;
	private Timer mTimer = null;
	private TimerTask mTask = null;
	private int mDisableTime = DISABLE_TIME; // 倒计时时间，默认60秒
	private int mEnableColor = Color.WHITE;
	private int mDisableColor = Color.GRAY;
	private String mEnableString = "获取验证码";
	private String mDisableString = "剩余";
	private String Second = "秒";
	private boolean mClickBle = true;
	private SendValidateButtonListener mListener = null;
	
	public int getmDisableTime() {

		return mDisableTime;

	}

	public int getmEnableColor() {

		return mEnableColor;

	}

	public void setmEnableColor(int mEnableColor) {

		this.mEnableColor = mEnableColor;

		this.setTextColor(mEnableColor);

	}

	public int getmDisableColor() {
		return mDisableColor;
	}

	public void setmDisableColor(int mDisableColor) {

		this.mDisableColor = mDisableColor;

	}

	public String getmEnableString() {

		return mEnableString;

	}

	public boolean isDisable() {
		if (mDisableTime > 0) {
			return true;
		}
		return false;
	}

	public void setmEnableString(String mEnableString) {

		this.mEnableString = mEnableString;

		if (this.mEnableString != null) {

			this.setText(mEnableString);
		}
	}

	public String getmDisableString() {

		return mDisableString;

	}

	public void setmDisableString(String mDisableString) {

		this.mDisableString = mDisableString;

	}

	public void setmListener(SendValidateButtonListener mListener) {

		this.mListener = mListener;

	}

	private Handler mHandler = new Handler(Looper.getMainLooper()) {

		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {

			case MSG_TICK:

				tickWork();

				break;

			default:

				break;
			}
			super.handleMessage(msg);
		}
	};

	public SendValidateButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		this.setText(mEnableString);
		this.setGravity(Gravity.CENTER);
		this.setTextColor(mEnableColor);
		initTimer();
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mListener != null && mClickBle)
				{
					// startTickWork();
					mListener.onClickSendValidateButton();
				}
			}
		});
	}

	private void initTimer() {
		mTimer = new Timer();
	}

	private void initTimerTask() {
		mTask = new TimerTask()
		{
			@Override
			public void run()
			{
				mHandler.sendEmptyMessage(MSG_TICK);
			}
		};
	}

	public void startTickWork() {
		if (mClickBle) {
			mClickBle = false;

			SendValidateButton.this.setText(mDisableString + mDisableTime
					+ Second);
			this.setEnabled(false);
			SendValidateButton.this.setTextColor(mDisableColor);
			initTimerTask();
			mTimer.schedule(mTask, 0, 1000);

		}

	}

	/**
	 * 每秒钟调用一次
	 */
	private void tickWork() {

		mDisableTime--;

		this.setText(mDisableString + mDisableTime + Second);

		if (mListener != null)
		{
			mListener.onTick();
		}
		if (mDisableTime <= 0)
		{
			stopTickWork();
		}
	}

	public void stopTickWork() {
		mTask.cancel();
		mTask = null;
		mDisableTime = DISABLE_TIME;
		this.setText(mEnableString);

		this.setTextColor(mEnableColor);
		this.setEnabled(true);
		mClickBle = true;
	}

	public interface SendValidateButtonListener {
		public void onClickSendValidateButton();
		public void onTick();
	}
}
