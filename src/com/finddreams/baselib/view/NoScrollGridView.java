package com.finddreams.baselib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
/**
 * @Description:没有滚动条的gridview
 * @author http://blog.csdn.net/finddreams
 */ 
public class NoScrollGridView extends GridView {
	public NoScrollGridView(Context context) {
		super(context);
	}

	public NoScrollGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
