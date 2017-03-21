/**
 * @author xieyue 
 * @date 创建时间：2015年1月29日 上午11:08:37
 * @version 1.0
 */
package com.dampscrolldemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
/**
 * 不可以滑动的GridView
 * @Description TODO
 * @Author xieyue 
 * @Date Create at 2015年1月29日 上午11:19:50
 */
public class NoScrollGridView extends GridView {
	public NoScrollGridView(Context context) {
		super(context);

	}

	public NoScrollGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
