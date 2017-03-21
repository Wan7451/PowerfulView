/**
 * @author xieyue 
 * @date 创建时间：2015年1月29日 下午3:20:49
 * @version 1.0
 */
package com.dampscrolldemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.dampscrolldemo.R;
import com.dampscrolldemo.adapter.GridViewAdapter;
import com.dampscrolldemo.adapter.ListViewAdapter;
import com.dampscrolldemo.widget.DampScrollView;
import com.dampscrolldemo.widget.DampScrollView.OnHeaderRefreshListener;
import com.dampscrolldemo.widget.DampScrollView.OnScrollListener;
import com.dampscrolldemo.widget.NoScrollGridView;
import com.dampscrolldemo.widget.NoScrollListView;

public class ListViewActivity extends Activity  implements OnScrollListener, OnHeaderRefreshListener {

	private DampScrollView dampScrollView;
	private NoScrollListView listView;
	private ListViewAdapter adapter;
	private ImageView bg_imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		init();
	}
	
	private void init() {
		dampScrollView = (DampScrollView)findViewById(R.id.dampScrollView);
		listView = (NoScrollListView)findViewById(R.id.noscroll_listview);
		bg_imageView = (ImageView)findViewById(R.id.bg_Image);
		dampScrollView.setbackGroundView(bg_imageView);
		dampScrollView.setchildView(listView);
		dampScrollView.setOnScrollListener(this);
		dampScrollView.setOnHeaderRefreshListener(this);
		listView.setFocusable(false);
		adapter = new ListViewAdapter(this, getDatas());
		listView.setAdapter(adapter);
	}

	private List<String> getDatas() {
		List<String> datas = new ArrayList<String>();
		for(int i = 0; i < 20; i++) {
			String data = "测试数据，第" + i + "行";
			datas.add(data);
		}
		return datas;
	}
	
	@Override
	public void onHeaderRefresh() {
		Toast.makeText(ListViewActivity.this, "下拉到顶部恢复", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onScroll(int scrollY) {
		// TODO Auto-generated method stub
		
	}

}

