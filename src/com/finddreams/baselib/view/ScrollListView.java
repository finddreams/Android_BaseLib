package com.finddreams.baselib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @Description:ScrollView中嵌套ListView的解决方案，
 * 解决在scrollview中只显示Listview中一行的问题
 * @author http://blog.csdn.net/finddreams
 */ 

public class ScrollListView extends ListView {
	private boolean haveScrollbar = false;  
	
	public ScrollListView(Context context) {
		super(context);
	}
	
	public ScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public ScrollListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	

	public void setHaveScrollbar(boolean haveScrollbar) {
		this.haveScrollbar = haveScrollbar;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(haveScrollbar){
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}else{
			int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  
            super.onMeasure(widthMeasureSpec, expandSpec);  
		}
	}


	
}
