package com.dampscrolldemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 不可以滑动的ListView
 * @Description TODO
 * @Author xieyue 
 * @Date Create at 2015年1月29日 下午3:22:31
 */
public class NoScrollListView extends ListView {
	
	public NoScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, mExpandSpec);
	}
}
