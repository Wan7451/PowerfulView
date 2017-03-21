package com.dampscrolldemo.activity;

import java.util.ArrayList;
import java.util.List;

import com.dampscrolldemo.R;
import com.dampscrolldemo.adapter.GridViewAdapter;
import com.dampscrolldemo.widget.DampScrollView;
import com.dampscrolldemo.widget.DampScrollView.OnHeaderRefreshListener;
import com.dampscrolldemo.widget.DampScrollView.OnScrollListener;
import com.dampscrolldemo.widget.NoScrollGridView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GridViewActivity extends Activity implements OnScrollListener, OnHeaderRefreshListener {

	private DampScrollView dampScrollView;
	private NoScrollGridView gridView;
	private GridViewAdapter adapter;
	private ImageView bg_imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gridview);
		init();
	}
	
	private void init() {
		dampScrollView = (DampScrollView)findViewById(R.id.dampScrollView);
		gridView = (NoScrollGridView)findViewById(R.id.noscroll_gridView);
		bg_imageView = (ImageView)findViewById(R.id.bg_Image);
		dampScrollView.setbackGroundView(bg_imageView);
		dampScrollView.setchildView(gridView);
		dampScrollView.setOnScrollListener(this);
		dampScrollView.setOnHeaderRefreshListener(this);
		gridView.setFocusable(false);
		adapter = new GridViewAdapter(this, getDatas(), getWidth());
		gridView.setAdapter(adapter);
	}
	
	/**
	 * 获取屏幕宽度
	 * @return 屏幕宽度
	 */
	private int getWidth() {
		return getWindowManager().getDefaultDisplay().getWidth();
	}
	
	private List<Drawable> getDatas() {
		List<Drawable> drawables = new ArrayList<Drawable>();
		for(int i = 0; i < 10; i++) {
			Drawable drawable = getResources().getDrawable(R.drawable.item);
			drawables.add(drawable);
		}
		return drawables;
	}

	@Override
	public void onHeaderRefresh() {
		Toast.makeText(GridViewActivity.this, "下拉到顶部恢复", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onScroll(int scrollY) {
		Log.i("DampScrollDemo", "scrollY = " + scrollY);
	}
}
