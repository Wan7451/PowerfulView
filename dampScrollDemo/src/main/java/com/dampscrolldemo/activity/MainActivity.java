/**
 * @author xieyue 
 * @date 创建时间：2015年1月29日 下午3:11:13
 * @version 1.0
 */
package com.dampscrolldemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.dampscrolldemo.R;

public class MainActivity extends Activity implements OnClickListener {

	private Button listView_btn, gridView_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
	}
	
	private void init() {
		listView_btn = (Button)findViewById(R.id.listview_btn);
		gridView_btn = (Button)findViewById(R.id.gridview_btn);
		listView_btn.setOnClickListener(this);
		gridView_btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.listview_btn:
			startActivity(new Intent(MainActivity.this, ListViewActivity.class));
			break;
		case R.id.gridview_btn:
			startActivity(new Intent(MainActivity.this, GridViewActivity.class));
			break;
		default:
			break;
		}
	}


}

