/**
 * @author xieyue 
 * @date 创建时间：2015年1月29日 上午11:13:52
 * @version 1.0
 */
package com.dampscrolldemo.adapter;

import java.util.List;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dampscrolldemo.R;

public class ListViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<String> datas;
	private LayoutInflater mInflater;
	
	public ListViewAdapter(Context mContext, List<String> datas) {
		this.mContext = mContext;
		this.datas = datas;
		mInflater = LayoutInflater.from(mContext);
	}
	
	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView = mInflater.inflate(R.layout.item_listview, null);
			holder = new ViewHolder();
			holder.itemView = (TextView) convertView.findViewById(R.id.item_list);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		holder.itemView.setText(datas.get(position));
		return convertView;
	}
	
	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class ViewHolder {
		 TextView itemView;
	}
	
}
