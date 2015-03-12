package com.finddreams.baselib.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finddreams.baselib.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.util.LogUtils;
/**
 * @Description:自动播放的广告条，viewpager，带标题和滚动的小圆点，有点击事件
 * @author http://blog.csdn.net/finddreams
 */ 
public class RollViewPager extends ViewPager {
	private String TAG = "RollViewPager";
	private Context context;
	private int currentItem;
	private ArrayList<String> uriList;
	private ArrayList<View> dots;
	private TextView title;
	private ArrayList<String> titles;
	private int[] resImageIds;
	private int dot_focus_resId;
	private int dot_normal_resId;
	private OnPagerClickCallback onPagerClickCallback;
	private boolean isShowResImage = false;
	MyOnTouchListener myOnTouchListener;
	ViewPagerTask viewPagerTask;
	private BitmapUtils bitmapUtils;
	private PagerAdapter adapter;

	/** 触摸时按下的点 **/
	PointF downP = new PointF();
	/** 触摸时当前的点 **/
	PointF curP = new PointF();
	private int abc = 1;
	private float mLastMotionX;
	private float mLastMotionY;

	private float firstDownX;
	private float firstDownY;
	private boolean flag = false;

	private long start = 0;

	public class MyOnTouchListener implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			curP.x = event.getX();
			curP.y = event.getY();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:

				start = System.currentTimeMillis();
				handler.removeCallbacksAndMessages(null);
				// 记录按下时候的坐标
				// 切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
				downP.x = event.getX();
				downP.y = event.getY();
				// 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
				// getParent().requestDisallowInterceptTouchEvent(true);
				break;
			case MotionEvent.ACTION_MOVE:
				handler.removeCallbacks(viewPagerTask);
				Log.i("d", (curP.x - downP.x) + "----" + (curP.y - downP.y));
				// if (Math.abs(curP.x - downP.x) > Math.abs(curP.y - downP.y)
				// && (getCurrentItem() == 0 || getCurrentItem() == getAdapter()
				// .getCount() - 1)) {
				// getParent().requestDisallowInterceptTouchEvent(false);
				// } else {
				// getParent().requestDisallowInterceptTouchEvent(false);
				// }
				// 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
				break;
			case MotionEvent.ACTION_CANCEL:
				// getParent().requestDisallowInterceptTouchEvent(false);
				startRoll();
				break;
			case MotionEvent.ACTION_UP:
				downP.x = event.getX();
				downP.y = event.getY();
				long duration = System.currentTimeMillis() - start;
				if (duration <= 500 && downP.x == curP.x) {
					onPagerClickCallback.onPagerClick(currentItem);
				} else {
				}
				startRoll();
				break;
			}
			return true;
		}
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		final float x = ev.getX();
		final float y = ev.getY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			getParent().requestDisallowInterceptTouchEvent(true);
			abc = 1;
			mLastMotionX = x;
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			if (abc == 1) {
				if (Math.abs(x - mLastMotionX) < Math.abs(y - mLastMotionY)) {
					abc = 0;
					getParent().requestDisallowInterceptTouchEvent(false);
					LogUtils.d("in listview");
				} else {
					getParent().requestDisallowInterceptTouchEvent(true);
				}

			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			getParent().requestDisallowInterceptTouchEvent(false);
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	public class ViewPagerTask implements Runnable {
		@Override
		public void run() {
			currentItem = (currentItem + 1)
					% (isShowResImage ? resImageIds.length : uriList.size());
			handler.obtainMessage().sendToTarget();
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			RollViewPager.this.setCurrentItem(currentItem);
			startRoll();
		}
	};

	public RollViewPager(Context context, ArrayList<View> dots,
			int dot_focus_resId, int dot_normal_resId,
			OnPagerClickCallback onPagerClickCallback) {
		super(context);
		this.context = context;
		this.dots = dots;
		this.dot_focus_resId = dot_focus_resId;
		this.dot_normal_resId = dot_normal_resId;
		this.onPagerClickCallback = onPagerClickCallback;
		viewPagerTask = new ViewPagerTask();
		bitmapUtils = new BitmapUtils(context);
		bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
		myOnTouchListener = new MyOnTouchListener();
	}

	public void setUriList(ArrayList<String> uriList) {
		isShowResImage = false;
		this.uriList = uriList;
	}

	public void notifyDataChange() {
		adapter.notifyDataSetChanged();
	}

	public ArrayList<View> getDots() {
		return dots;
	}

	public void setDots(ArrayList<View> dots) {
		this.dots = dots;
	}

	public void setResImageIds(int[] resImageIds) {
		isShowResImage = true;
		this.resImageIds = resImageIds;
	}

	public void setTitle(TextView title, ArrayList<String> titles) {
		this.title = title;
		this.titles = titles;
		if (title != null && titles != null && titles.size() > 0)
			title.setText(titles.get(0));//
	}

	private boolean hasSetAdapter = false;

	/**
	 * 
	 */

	public void startRoll() {
		if (!hasSetAdapter) {
			hasSetAdapter = true;
			this.setOnPageChangeListener(new MyOnPageChangeListener());
			adapter = new ViewPagerAdapter();
			this.setAdapter(adapter);
		}
		handler.postDelayed(viewPagerTask, 4000);
	}

	class MyOnPageChangeListener implements OnPageChangeListener {
		int oldPosition = 0;

		@Override
		public void onPageSelected(int position) {
			currentItem = position;
			if (title != null)
				title.setText(titles.get(position));
			if (dots != null && dots.size() > 0) {
				dots.get(position).setBackgroundResource(dot_focus_resId);
				dots.get(oldPosition).setBackgroundResource(dot_normal_resId);
			}
			oldPosition = position;
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
	}

	class ViewPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return isShowResImage ? resImageIds.length : uriList.size();
		}

		@Override
		public Object instantiateItem(View container, final int position) {
			View view = View.inflate(context, R.layout.viewpager_item, null);
			((ViewPager) container).addView(view);
			view.setOnTouchListener(myOnTouchListener);
			ImageView imageView = (ImageView) view.findViewById(R.id.image);
			if (isShowResImage) {
				imageView.setImageResource(resImageIds[position]);
			} else {
				bitmapUtils.display(imageView, uriList.get(position));
			}
			return view;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		handler.removeCallbacksAndMessages(null);
		super.onDetachedFromWindow();
	}

	public interface OnPagerClickCallback {
		public abstract void onPagerClick(int position);
	}

	// @Override
	// public boolean onTouchEvent(MotionEvent arg0) {
	// // 每次进行onTouch事件都记录当前的按下的坐标
	// curP.x = arg0.getX();
	// curP.y = arg0.getY();
	//
	// if (arg0.getAction() == MotionEvent.ACTION_DOWN) {
	// // 记录按下时候的坐标
	// // 切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
	// downP.x = arg0.getX();
	// downP.y = arg0.getY();
	// // 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
	// getParent().requestDisallowInterceptTouchEvent(true);
	// }
	//
	// if (arg0.getAction() == MotionEvent.ACTION_MOVE) {
	// // 此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
	// getParent().requestDisallowInterceptTouchEvent(true);
	// }
	//
	// if (arg0.getAction() == MotionEvent.ACTION_UP) {
	// // 在up时判断是否按下和松手的坐标为一个点
	// // 如果是一个点，将执行点击事件，这是我自己写的点击事件，而不是onclick
	// if (downP.x == curP.x && downP.y == curP.y) {
	// // onSingleTouch();
	// return true;
	// }
	// }
	//
	// return super.onTouchEvent(arg0);
	// }

}
